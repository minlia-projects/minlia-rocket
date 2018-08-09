package com.minlia.rocket.samples.web.modules.chess.v1.config;

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
public class ComputerSwaggerConfig extends AbstractSwaggerConfig {
  /**
   * 自定义当前前缀endpoint的组名
   * @return
   */
  @Bean
  public Docket computerSwagger(){
    return createNewDocket("Computer", "/api/v1/open/computer/.*");
  }

}
