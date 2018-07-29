package com.minlia.rocket.autoconfigure.security;

import com.minlia.rocket.security.authentication.provider.SecurityAuthenticationProvider;
import com.minlia.rocket.security.authentication.service.AuthenticationService;
import com.minlia.rocket.security.authentication.service.BuiltinStandardAuthenticationService;
import com.minlia.rocket.security.authentication.service.DummyUserDetailsService;
import com.minlia.rocket.security.internal.InternalSecurityConfiguration;
import com.minlia.rocket.security.security.jwt.TokenProvider;
import com.minlia.rocket.security.security.jwt.extractor.JwtHeaderTokenExtractor;
import com.minlia.rocket.security.security.jwt.extractor.TokenExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author will
 */
@Configuration
@ConditionalOnProperty(
    prefix = "system.security",
    name = {"enabled"},
    havingValue = "true"
)
@Slf4j
public class SecurityAutoConfiguration {

//  @Autowired
//  private SystemProperties systemProperties;


  public SecurityAutoConfiguration() {
  }

  @ConditionalOnMissingBean
  @Bean
  public AuthenticationProvider authenticationProvider() {
    return new SecurityAuthenticationProvider();
  }

  @ConditionalOnMissingBean
  @Bean
  public AuthenticationService authenticationService() {
    return new BuiltinStandardAuthenticationService();
  }

  @ConditionalOnMissingBean
  @Bean
  public UserDetailsService userDetailsService() {
    return new DummyUserDetailsService();
  }



  @Bean
  @ConditionalOnMissingBean
  protected BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean
  public TokenExtractor tokenExtractor() {
    return new JwtHeaderTokenExtractor();
  }

  @Bean
  @ConditionalOnMissingBean
  public TokenProvider tokenProvider() {
    return new TokenProvider();
  }

  @Bean
  public MethodInvokingFactoryBean methodInvokingFactoryBean() {
    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
    methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
    methodInvokingFactoryBean.setTargetMethod("setStrategyName");
    methodInvokingFactoryBean.setArguments(new String[]{"MODE_INHERITABLETHREADLOCAL"});
    return methodInvokingFactoryBean;
  }

  @Configuration
  @Import({InternalSecurityConfiguration.class})
  public static class TheInternalSecurityConfiguration {

    public TheInternalSecurityConfiguration() {
    }
  }
}
