package com.minlia.rocket.security.rebecca.config.swagger;

import com.minlia.rocket.swagger.abstraction.AbstractSwaggerConfig;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author will
 */
public class RebeccaSwaggerConfig extends AbstractSwaggerConfig {

  @Bean
  public Docket rebeccaManageSwaggerDocket() {
    return createNewDocket("-Rebecca-Api-", "/api/v1/security/rebecca/.*");
  }

  @Bean
  public Docket rebeccaOpenSwaggerDocket() {
    return createNewDocket("-Rebecca-Open-Api-", "/api/v1/open/auth/builtin/.*");
  }


}
