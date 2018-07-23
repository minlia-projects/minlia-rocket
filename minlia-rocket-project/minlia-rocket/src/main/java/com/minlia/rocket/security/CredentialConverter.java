package com.minlia.rocket.security;

import com.minlia.rocket.problem.Intrinsics;
import com.minlia.rocket.security.code.SecurityApiCode;
import com.minlia.rocket.security.credential.LoginCredential;
import com.minlia.rocket.security.credential.LoginCredentials;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;

public class CredentialConverter {

  public static LoginCredential convertToLoginCredential(LoginCredentials credential) {
    Intrinsics.isNull(credential, SecurityApiCode.NOT_NULL, HttpStatus.UNAUTHORIZED);

    if (!StringUtils.isEmpty(credential.getCellphone())) {
      return new LoginCredential(credential.getCellphone(), credential.getPassword());
    }

    if (!StringUtils.isEmpty(credential.getEmail())) {
      return new LoginCredential(credential.getEmail(), credential.getPassword());
    }

    if (!StringUtils.isEmpty(credential.getUsername())) {
      return new LoginCredential(credential.getUsername(), credential.getPassword());
    }
    return new LoginCredential();
  }

  /**
   * 前置校验, 是否只传入了一组登录对象
   */
  private void preConditions(LoginCredential credential) {
    if (StringUtils.isBlank(credential.getLogin()) || StringUtils
        .isBlank(credential.getPassword())) {
      throw new AuthenticationServiceException("Username or Password not provided");
//            SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken());
    }
  }
}
