package com.minlia.rocket.security.authentication.service;

import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.security.body.ValidationArgumentBody;
import com.minlia.rocket.security.event.AfterPrincipalLoadedEvent;
import com.minlia.rocket.security.event.AfterValidationEvent;
import com.minlia.rocket.security.event.BeforePrincipalLoadEvent;
import com.minlia.rocket.security.event.BeforeValidationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 内置标准的用户认证服务 <br />
 * 可以进行自定义整个认证流程，如添加验证码登录，使用手机号码加验证码方式登录等。<br />
 * 当认证失败时自动抛出异常，无需进行异常捕获
 * @author will
 */
@Slf4j
public class BuiltinStandardAuthenticationService implements AuthenticationService {

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * 标准认证流程
   *
   * @param authentication
   * @return
   */
  @Override
  public Authentication authentication(Authentication authentication) {

    //插入BeforePrincipalLoadEvent
    ContextHolder.getContext().publishEvent(new BeforePrincipalLoadEvent(authentication));

    //标准登录流程，根据传递进来的Principal,Credential创建认证令牌
    UserDetails userDetails = userDetailsService
        .loadUserByUsername(authentication.getPrincipal().toString());

    //TODO 可能会抛出UsernameNotFoundException, 需要进行处理

    //插入AfterPrincipalLoadedEvent
    ContextHolder.getContext().publishEvent(new AfterPrincipalLoadedEvent(authentication));

    //插入BeforeValidationEvent
    ContextHolder.getContext().publishEvent(
        new BeforeValidationEvent(new ValidationArgumentBody(authentication, userDetails)));

    //执行标准验证流程
    validation(authentication, userDetails);

    //插入AfterValidationEvent
    ContextHolder.getContext().publishEvent(
        new AfterValidationEvent(new ValidationArgumentBody(authentication, userDetails)));

    //构造已认证的用户名密码令牌
    UsernamePasswordAuthenticationToken authenticatedToken =
        new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
            userDetails.getPassword(), userDetails.getAuthorities());

    log.debug("Putting authenticated token to security context with result: {}",
        authenticatedToken);
    SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
    return authenticatedToken;
  }


}
