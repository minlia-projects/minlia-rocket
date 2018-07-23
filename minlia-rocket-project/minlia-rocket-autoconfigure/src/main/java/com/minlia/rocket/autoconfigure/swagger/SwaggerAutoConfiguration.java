package com.minlia.rocket.autoconfigure.swagger;

import com.minlia.rocket.swagger.EnableDevSwagger;
import com.minlia.rocket.swagger.configuration.Swagger2Config;
import com.minlia.rocket.swagger.properties.SwaggerConfigurationProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@Configuration
@ConditionalOnClass({Swagger2Config.class,Swagger2DocumentationConfiguration.class,ApiOperation.class,EnableDevSwagger.class})
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
@Profile(value = {"!production","!online"})
public class SwaggerAutoConfiguration {

  @Configuration
  @EnableDevSwagger
  @ConditionalOnMissingBean(EnableDevSwagger.class)
  public static class Swagger2AutoConfig {

  }


//  /**
//   * TODO problem has one WebMvcConfigurer
//   * merged
//   */
//  @Configuration
////  @EnableWebMvc
//  public static class EnableMinliaStaticResourceConfiguration implements WebMvcConfigurer {
//    private final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//        "classpath:/META-INF/resources/", "classpath:/resources/",
//        "classpath:/static/", "classpath:/public/"};
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//    }
//  }


}
