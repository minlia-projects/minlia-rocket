package com.minlia.rocket.security.security.jwt;


import com.minlia.rocket.problem.Intrinsics;
import com.minlia.rocket.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zalando.problem.Status;

@Slf4j
public class TokenProvider {

//  private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

//  private static final String AUTHORITIES_KEY = "auth";

//  public static final String JWT_ACCESS_TOKEN = "access";
//
//  public static final String JWT_REFRESH_TOKEN = "refresh";

  public static final String JWT_CLAIMS_SCOPE = "scopes";

  public static final String AUTHORIZATION_HEADER_NAME = "X-Auth-Token";
  public static final String AUTHORIZATION_PARAMETER_NAME = "x_auth_token";


  private final JwtProperties jwtProperties;

  public TokenProvider(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }


  /**
   * Create Jwt Access Token
   */
  public JwtAccessToken createJwtAccessToken(String username,
      Collection<? extends GrantedAuthority> authorities, boolean rememberMe) {
    if (StringUtils.isEmpty(username)) {
      throw new IllegalArgumentException("Username is missing when creating access jwt");
    }

    if (authorities == null || authorities.isEmpty()) {
      throw new IllegalArgumentException("Authorities is missing when creating access jwt");
    }

    String authoritiesString = authorities.stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    Claims claims = Jwts.claims()
        .setSubject(username)
        .setId(UUID.randomUUID().toString());
    claims.put(JWT_CLAIMS_SCOPE, authoritiesString);
    Date nowDate = new Date();
    long now = (nowDate).getTime();
    Date validity;
    if (rememberMe) {
      validity = new Date(now + this.jwtProperties.getTokenValidityInSecondsForRememberMe() * 1000);
    } else {
      validity = new Date(now + this.jwtProperties.getTokenValidityInSeconds() * 1000);
    }
    // Jwts.builder().setClaims() will create a default subject which means if setClaims() is called after
    // Jwts.builder().setSubject(), the previous subject will be lost
    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuer(jwtProperties.getIssuer())
        .setIssuedAt(nowDate)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
        .compact();

    return JwtAccessToken.builder().value(token).expiration(validity.getTime()).issuedAt(now)
        .build();
  }


  /**
   * Create Jwt Refresh Token
   */
  public JwtRefreshToken createJwtRefreshToken(String username) {
    if (StringUtils.isEmpty(username)) {
      throw new IllegalArgumentException("Username is missing when creating access jwt");
    }

    Claims claims = Jwts.claims()
        .setSubject(username)
        .setId(UUID.randomUUID().toString()); // JTI
    claims.put(JWT_CLAIMS_SCOPE, Collections.singletonList("REFRESH_TOKEN"));

//    DateTime current = new DateTime();
//    DateTime expiration = current.plusMinutes(JWT_REFRESH_EXPIRATION_MIN);

    Date nowDate = new Date();
    long now = (nowDate).getTime();
    Date validity = new Date(now + this.jwtProperties.getTokenValidityInSeconds() * 1000);

    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuer(jwtProperties.getIssuer())
        .setIssuedAt(nowDate)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
        .compact();
    return JwtRefreshToken.builder().value(token).build();
  }


  public Jws<Claims> parseClaims(String rawJwtAccessToken, String secretKey) {
    try {
      return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(rawJwtAccessToken);
    } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
      log.error("Invalid JWT Token", ex);
      Intrinsics.throwException(60001, Status.UNAUTHORIZED);
//      throw new BadCredentialsException("Invalid JWT token: ", ex);
    } catch (ExpiredJwtException expiredEx) {
      log.info("JWT Token is expired", expiredEx);
      Intrinsics.throwException(60002, Status.UNAUTHORIZED);
//      throw new JwtException("JWT Token expired", expiredEx);
    }

    return null;
  }

  public Authentication getAuthentication(String jwtAccessToken) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtProperties.getSecretKey())
        .parseClaimsJws(jwtAccessToken)
        .getBody();

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(JWT_CLAIMS_SCOPE).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, jwtAccessToken, authorities);
  }


  public boolean validateToken(String jwtAccessToken) {
    try {
      Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(jwtAccessToken);
      return true;
    } catch (SignatureException e) {
      log.info("Invalid JWT signature.");
      log.trace("Invalid JWT signature trace: {}", e);
    } catch (MalformedJwtException e) {
      log.info("Invalid JWT token.");
      log.trace("Invalid JWT token trace: {}", e);
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT token.");
      log.trace("Expired JWT token trace: {}", e);
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT token.");
      log.trace("Unsupported JWT token trace: {}", e);
    } catch (IllegalArgumentException e) {
      log.info("JWT token compact of handler are invalid.");
      log.trace("JWT token compact of handler are invalid trace: {}", e);
    }
    return false;
  }


  public String getUsernameFromToken(String jwtAccessToken) {
    return getClaimFromToken(jwtAccessToken, Claims::getSubject);
  }


  private Claims getAllClaimsFromToken(String jwtAccessToken) {
    return Jwts.parser()
        .setSigningKey(jwtProperties.getSecretKey())
        .parseClaimsJws(jwtAccessToken)
        .getBody();
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }


}
