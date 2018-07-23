package com.minlia.rocket.security.authentication.service;


import org.springframework.security.core.Authentication;

/**
 * Created by will on 8/14/17.
 */
public interface AuthenticationService {

  /**
   * 验证用户 参考 AuthenticationProvider 实现
   */
  public Authentication authentication(Authentication authentication);

}