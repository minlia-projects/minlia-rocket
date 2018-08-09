package com.minlia.rocket.security;

import com.minlia.rocket.problem.Intrinsics;
import com.minlia.rocket.security.code.SecurityApiCode;
import com.minlia.rocket.security.credential.BuiltinSigninRequestBody;
import com.minlia.rocket.security.credential.SigninCredentials;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * @author will
 */
public class CredentialConverter {

  public static BuiltinSigninRequestBody convertToSigninCredential(SigninCredentials credential) {
    Intrinsics.isNull(credential, SecurityApiCode.NOT_NULL, HttpStatus.UNAUTHORIZED);

    if (!StringUtils.isEmpty(credential.getCellphone())) {
      return new BuiltinSigninRequestBody(credential.getCellphone(), credential.getCredential());
    }

    if (!StringUtils.isEmpty(credential.getEmail())) {
      return new BuiltinSigninRequestBody(credential.getEmail(), credential.getCredential());
    }

    if (!StringUtils.isEmpty(credential.getUsername())) {
      return new BuiltinSigninRequestBody(credential.getUsername(), credential.getCredential());
    }
    return new BuiltinSigninRequestBody();
  }

  /**
   * 前置校验, 是否只传入了一组登录对象
   */
  private void preConditions(BuiltinSigninRequestBody credential) {
    if (StringUtils.isBlank(credential.getPrincipal()) || StringUtils
        .isBlank(credential.getCredential())) {
      throw new AuthenticationServiceException("Username or Password not provided");
//            SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken());
    }
  }
}
