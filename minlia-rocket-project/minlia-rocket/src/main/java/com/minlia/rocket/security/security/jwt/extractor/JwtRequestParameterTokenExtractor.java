package com.minlia.rocket.security.security.jwt.extractor;

import com.minlia.rocket.problem.Intrinsics;
import com.minlia.rocket.security.code.SecurityApiCode;

//@Component
public class JwtRequestParameterTokenExtractor implements TokenExtractor {

  public static String HEADER_PREFIX = "Bearer ";

  @Override
  public String extract(String header) {
    Intrinsics.isNotNull(header, SecurityApiCode.ACCESS_TOKEN_INVALID);
//        if (StringUtils.isEmpty(header)) {
//            throw new AuthenticationServiceException("Authorization header cannot be blank!");
//        }

    Intrinsics.is(header.length() < HEADER_PREFIX.length(), SecurityApiCode.ACCESS_TOKEN_INVALID);
//        if (header.length() < HEADER_PREFIX.length()) {
//            throw new AuthenticationServiceException("Invalid authorization header size.");
//        }

    return header.substring(HEADER_PREFIX.length(), header.length());
  }
}
