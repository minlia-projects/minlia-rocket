package com.minlia.rocket.samples.web.modules.config;

import com.minlia.rocket.swagger.abstraction.AbstractSwaggerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author will
 */
@Configuration
@ConditionalOnProperty(prefix = "system.swagger",name = "enabled",havingValue = "true")
public class MerchantSwaggerConfig extends AbstractSwaggerConfig {
  /**
   * 自定义当前前缀endpoint的组名
   * @return
   */
  @Bean
  public Docket merchantSwagger(){
    return createNewDocket("Chess", "/api/v1/endpoint/v1/.*");
  }

}
