package com.minlia.rocket.security.authentication.service;


import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.problem.Intrinsics;
import com.minlia.rocket.security.code.SecurityApiCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * 认证服务 <br /> 用于验证当前登录的用户，如有任何问题直接抛出异常<br />
 *
 * @author will
 * @date 8/14/17
 */
@FunctionalInterface
public interface AuthenticationService {

  /**
   * 验证用户 参考 AuthenticationProvider 实现
   *
   * @param authentication 用于验证的用户详情信息，如：用户名、密码
   */
  public Authentication authentication(Authentication authentication);

  /**
   * 定义一个默认的验证流程
   */
  default void validation(Authentication authentication, UserDetails userDetails) {

    PasswordEncoder passwordEncoder;
    passwordEncoder = ContextHolder.getContext().getBean(PasswordEncoder.class);

    Boolean isPasswordEmpty = StringUtils.isEmpty(authentication.getCredentials().toString());

    //密码是否为空
    Intrinsics.is(isPasswordEmpty, SecurityApiCode.PASSWORD_SHOWLD_NO_BE_EMPTY);

    Boolean passwordMatches = passwordEncoder
        .matches(authentication.getCredentials().toString(), userDetails.getPassword());

    //密码是否匹配
    Intrinsics.is(!passwordMatches, SecurityApiCode.PASSWORD_INVALID);

    //账户是否已过期
    Intrinsics.is(!userDetails.isAccountNonExpired(), SecurityApiCode.ACCOUNT_EXPIRED);

    //账户是否已锁定
    Intrinsics.is(!userDetails.isAccountNonLocked(), SecurityApiCode.ACCOUNT_LOCKED);

    //账户是否已禁用
    Intrinsics.is(!userDetails.isEnabled(), SecurityApiCode.ACCOUNT_DISABLED);

    //凭证是否已过期
    Intrinsics.is(!userDetails.isCredentialsNonExpired(), SecurityApiCode.CREDENTIALS_EXPIRED);
  }
}