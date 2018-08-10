package com.minlia.rocket.autoconfigure.rebecca;

import com.minlia.rocket.security.rebecca.config.swagger.RebeccaSwaggerConfig;
import com.minlia.rocket.security.endpoint.BuiltinAuthenticationEndpoint;
import com.minlia.rocket.security.rebecca.endpoint.UserManagementEndpoint;
import com.minlia.rocket.security.rebecca.service.RebeccaUserDetailsServiceImpl;
import com.minlia.rocket.security.rebecca.service.batis.PermissionBatisService;
import com.minlia.rocket.security.rebecca.service.batis.UserRoleBatisService;
import com.minlia.rocket.security.rebecca.service.batis.impl.PermissionBatisServiceImpl;
import com.minlia.rocket.security.rebecca.service.batis.impl.UserRoleBatisServiceImpl;
import com.minlia.rocket.security.rebecca.service.jpa.PermissionJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.RoleJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.RolePermissionJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.UserJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.UserRoleJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.impl.PermissionJpaServiceImpl;
import com.minlia.rocket.security.rebecca.service.jpa.impl.RoleJpaServiceImpl;
import com.minlia.rocket.security.rebecca.service.jpa.impl.RolePermissionJpaServiceImpl;
import com.minlia.rocket.security.rebecca.service.jpa.impl.UserJpaServiceImpl;
import com.minlia.rocket.security.rebecca.service.jpa.impl.UserRoleJpaServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ConditionalOnProperty(
    prefix = "system.security",
    name = {"enabled"},
    havingValue = "true"
)
public class RebeccaAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(value = UserJpaService.class)
  public UserJpaService userJpaService() {
    return new UserJpaServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = RoleJpaService.class)
  public RoleJpaService roleJpaService() {
    return new RoleJpaServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = PermissionJpaService.class)
  public PermissionJpaService permissionJpaService() {
    return new PermissionJpaServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = RolePermissionJpaService.class)
  public RolePermissionJpaService rolePermissionJpaService() {
    return new RolePermissionJpaServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = UserRoleJpaService.class)
  public UserRoleJpaService userRoleJpaService() {
    return new UserRoleJpaServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = UserRoleBatisService.class)
  public UserRoleBatisService userRoleBatisServiceImpl() {
    return new UserRoleBatisServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = PermissionBatisService.class)
  public PermissionBatisService permissionBatisServiceImpl() {
    return new PermissionBatisServiceImpl();
  }


  @Primary
  @Bean
  public UserDetailsService userDetailsService() {
    return new RebeccaUserDetailsServiceImpl();
  }



  @Bean
  @ConditionalOnMissingBean(value = BuiltinAuthenticationEndpoint.class)
  public BuiltinAuthenticationEndpoint rebeccaAuthenticationEndpoint() {
    return new BuiltinAuthenticationEndpoint();
  }


  @Bean
  @ConditionalOnMissingBean(value = UserManagementEndpoint.class)
  public UserManagementEndpoint userManagementEndpoint() {
    return new UserManagementEndpoint();
  }


  @Configuration
  @Import(value = {RebeccaSwaggerConfig.class})
  public static class InternalRebeccaSwaggerConfig {

  }


}
