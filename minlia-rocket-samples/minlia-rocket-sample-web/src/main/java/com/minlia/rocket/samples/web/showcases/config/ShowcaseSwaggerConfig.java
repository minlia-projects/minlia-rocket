package com.minlia.rocket.samples.web.showcases.config;

import com.minlia.rocket.swagger.abstraction.AbstractSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author will
 */
@Configuration
public class ShowcaseSwaggerConfig extends AbstractSwaggerConfig {
  /**
   * 自定义当前前缀endpoint的组名
   * @return
   */
  @Bean
  public Docket showcaseSwagger(){
    return createNewDocket("Showcases", "/xapi/v1/showcases/.*");
  }

}
