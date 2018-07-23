package com.minlia.rocket.security.authentication.provider;

import com.minlia.rocket.security.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SecurityAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private AuthenticationService authenticationService;

  /**
   * 具体实现请参见 @see RbacAuthenticationService
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return authenticationService.authentication(authentication);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }

}