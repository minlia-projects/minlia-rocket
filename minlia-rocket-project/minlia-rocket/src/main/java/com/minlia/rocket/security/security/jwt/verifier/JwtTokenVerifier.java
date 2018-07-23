package com.minlia.rocket.security.security.jwt.verifier;//package com.minlia.cloud.security.security.jwt.verifier;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.BadCredentialsException;
//
//public class JwtTokenVerifier implements TokenVerifier {
//
//  @Override
//  public void validate(Claims claims) {
//
//  }
//
//  public void validate(Jws<Claims> claims) {
//
//    String jti = claims.getBody().getId();
//
//    if (StringUtils.isEmpty(jti) || jwtRevokedTokenDAO.findByJti(jti) != null) {
//      throw new BadCredentialsException("Token has been revoked");
//    }
//  }
//
//}
