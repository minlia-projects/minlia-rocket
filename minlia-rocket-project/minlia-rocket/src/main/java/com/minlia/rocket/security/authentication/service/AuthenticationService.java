package com.minlia.rocket.security.authentication.service;


import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.problem.ApiPreconditions;
import com.minlia.rocket.security.code.SecurityApiCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * 认证服务 <br />
 * 用于验证当前登录的用户，如有任何问题直接抛出异常<br />
 * @author will
 * @date 8/14/17
 */
@FunctionalInterface
public interface AuthenticationService {

  /**
   * 验证用户 参考 AuthenticationProvider 实现
   * @param authentication 用于验证的用户详情信息，如：用户名、密码
   */
  public Authentication authentication(Authentication authentication);

  /**
   * 定义一个默认的验证流程
   */
  default void validation(Authentication authentication, UserDetails userDetails) {

    PasswordEncoder passwordEncoder;
    passwordEncoder = ContextHolder.getContext().getBean(PasswordEncoder.class);

    if (!StringUtils.isEmpty(authentication.getCredentials().toString())) {
      Boolean passwordMatches = passwordEncoder
          .matches(authentication.getCredentials().toString(), userDetails.getPassword());
      if (!passwordMatches) {
        ApiPreconditions.throwException(SecurityApiCode.PASSWORD_INVALID);
      }
    }

    if (!userDetails.isAccountNonExpired()) {
      ApiPreconditions.throwException(SecurityApiCode.ACCOUNT_EXPIRED);
    }

    if (!userDetails.isAccountNonLocked()) {
      ApiPreconditions.throwException(SecurityApiCode.ACCOUNT_LOCKED);
    }

    if (!userDetails.isEnabled()) {
      ApiPreconditions.throwException(SecurityApiCode.ACCOUNT_DISABLED);
    }

    if (!userDetails.isCredentialsNonExpired()) {
      ApiPreconditions.throwException(SecurityApiCode.CREDENTIALS_EXPIRED);
    }
  }
}