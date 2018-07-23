package com.minlia.rocket.security.security.jwt;//package com.minlia.cloud.security.security.jwt;
//
//import static com.mc.security.utils.WebUtils.JWT_ACCESS_EXPIRATION_MIN;
//import static com.mc.security.utils.WebUtils.JWT_CLAIMS_SCOPE;
//import static com.mc.security.utils.WebUtils.JWT_ISSUER;
//import static com.mc.security.utils.WebUtils.JWT_REFRESH_EXPIRATION_MIN;
//import static com.mc.security.utils.WebUtils.JWT_SECRET_KEY;
//
//import com.mc.account.models.UserRole;
//import com.mc.security.user.DbUserAuthority;
//import com.mc.security.utils.WebUtils;
//
//import org.joda.time.DateTime;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
///**
// * @author Wenyu
// * @since 2/19/17
// */
//@Component
//public class JwtTokenFactory {
//
//    public JwtAccessToken createAccessToken(String username, Collection<DbUserAuthority> authorities) {
//        if (StringUtils.isEmpty(username)) {
//            throw new IllegalArgumentException("Username is missing when creating access jwt");
//        }
//
//        if (authorities == null || authorities.isEmpty()) {
//            throw new IllegalArgumentException("Authorities is missing when creating access jwt");
//        }
//
//        Claims claims = Jwts.claims()
//                .setSubject(username)
//                .setId(UUID.randomUUID().toString());
//        claims.put(JWT_CLAIMS_SCOPE, authorities.stream()
//                .map(DbUserAuthority::getAuthority)
//                .collect(Collectors.toList()));
//
//        DateTime current = new DateTime();
//        DateTime expiration = current.plusMinutes(JWT_ACCESS_EXPIRATION_MIN);
//
//        // Jwts.builder().setClaims() will create a default subject which means if setClaims() is called after
//        // Jwts.builder().setSubject(), the previous subject will be lost
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(JWT_ISSUER)
//                .setIssuedAt(current.toDate())
//                .setExpiration(expiration.toDate())
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
//                .compact();
//
//        return new JwtAccessToken(token);
//    }
//
//    /**
//     * Refresh jwt which has a longer expiration for re-building access jwt.
//     *
//     * The blacklist jwt doesn't contain the user authorities.
//     *
//     * @param username
//     * @return
//     */
//    public JwtRefreshToken createRefreshToken(String username) {
//        if (StringUtils.isEmpty(username)) {
//            throw new IllegalArgumentException("Username is missing when creating access jwt");
//        }
//
//        Claims claims = Jwts.claims()
//                .setSubject(username)
//                .setId(UUID.randomUUID().toString()); // JTI
//        claims.put(JWT_CLAIMS_SCOPE, Collections.singletonList(UserRole.REFRESH));
//
//        DateTime current = new DateTime();
//        DateTime expiration = current.plusMinutes(JWT_REFRESH_EXPIRATION_MIN);
//
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(JWT_ISSUER)
//                .setIssuedAt(current.toDate())
//                .setExpiration(expiration.toDate())
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
//                .compact();
//
//        return new JwtRefreshToken(token);
//    }
//
//    public static class TokenMapBuilder {
//
//        private final Map<String, String> tokenMap = new HashMap<>();
//
//        public TokenMapBuilder with(JwtAccessToken accessToken) {
//            tokenMap.put(WebUtils.JWT_ACCESS_TOKEN, accessToken.value());
//            return this;
//        }
//
//        public TokenMapBuilder with(JwtRefreshToken refreshToken) {
//            tokenMap.put(WebUtils.JWT_REFRESH_TOKEN, refreshToken.value());
//            return this;
//        }
//
//        public Map<String, String> build() {
//            return this.tokenMap;
//        }
//    }
//}
