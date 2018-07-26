package com.minlia.rocket.autoconfigure.swagger;

import com.minlia.rocket.swagger.EnableSwagger;
import com.minlia.rocket.swagger.abstraction.Swagger2Config;
import com.minlia.rocket.swagger.properties.SwaggerConfigurationProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@Configuration
@ConditionalOnClass({Swagger2Config.class, Swagger2DocumentationConfiguration.class,
    ApiOperation.class, EnableSwagger.class})
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
@ConditionalOnProperty(prefix = "system.swagger", havingValue = "true", name = "enabled")
@Slf4j
public class SwaggerAutoConfiguration {

  @Configuration
  @EnableSwagger
  @ConditionalOnMissingBean(EnableSwagger.class)
  public static class Swagger2AutoConfig {

  }


}
