package com.minlia.rocket.autoconfigure.rebecca;

import com.minlia.rocket.security.rebecca.config.swagger.RebeccaSwaggerConfig;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ConditionalOnProperty(
    prefix = "system.security",
    name = {"enabled"},
    havingValue = "true"
)
public class RebeccaAutoConfiguration {

  @Bean
  public UserJpaService userJpaService() {
    return new UserJpaServiceImpl();
  }

  @Bean
  public RoleJpaService roleJpaService() {
    return new RoleJpaServiceImpl();
  }

  @Bean
  public PermissionJpaService permissionJpaService() {
    return new PermissionJpaServiceImpl();
  }

  @Bean
  public RolePermissionJpaService rolePermissionJpaService() {
    return new RolePermissionJpaServiceImpl();
  }

  @Bean
  public UserRoleJpaService userRoleJpaService() {
    return new UserRoleJpaServiceImpl();
  }

  @Bean
  public UserRoleBatisService userRoleBatisServiceImpl() {
    return new UserRoleBatisServiceImpl();
  }

  @Bean
  public PermissionBatisService permissionBatisServiceImpl() {
    return new PermissionBatisServiceImpl();
  }


  @Bean
  public UserDetailsService userDetailsService() {
    return new RebeccaUserDetailsServiceImpl();
  }


  @Configuration
  @Import(value = {RebeccaSwaggerConfig.class})
  public static class InternalRebeccaSwaggerConfig {

  }


  @Configuration
  @ComponentScan(value = {"com.minlia.rocket.security.rebecca.endpoint"})
  public static class InternalRebeccaEndpointConfig {

  }

}
