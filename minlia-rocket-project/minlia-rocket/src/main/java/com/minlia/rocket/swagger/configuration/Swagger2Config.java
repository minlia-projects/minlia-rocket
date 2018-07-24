package com.minlia.rocket.swagger.configuration;

import static com.google.common.collect.Lists.newArrayList;

import com.minlia.rocket.swagger.properties.SwaggerConfigurationProperties;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * Updated by will on 06/11/18. for springfox 2.9.2 upgrading.
 */
@Slf4j
public class Swagger2Config {


  @Autowired
  private SwaggerConfigurationProperties swaggerConfigurationProperties;


  @Bean
  public Docket defaultSpecification() {
    log.debug("Starting Swagger Configuration");
    StopWatch watch = new StopWatch();
    watch.start();

    String groupName = "Default Api";
    String path = swaggerConfigurationProperties.getPath();
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .groupName(groupName)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex(path))
        .build()
        .securitySchemes(newArrayList(apiKey()))
        .securityContexts(newArrayList(securityContext()))
        .apiInfo(apiInfo())
        .ignoredParameterTypes(ApiIgnore.class);
    watch.stop();
    log.debug("Finished Swagger Configuration in {} ms", watch.getTotalTimeMillis());
    return docket;
  }

  @Bean
  @ConditionalOnMissingBean
  SecurityScheme apiKey() {
    return new ApiKey("apiKey", swaggerConfigurationProperties.getKeyName(),
        swaggerConfigurationProperties
            .getPassAs());
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope(
        "global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("apiKey",
        authorizationScopes));
  }

  @Bean
  @ConditionalOnMissingBean
  SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any()).build();
  }


  @Bean
  @ConditionalOnMissingBean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder().displayOperationId(true).showExtensions(true)
        .displayRequestDuration(true).build();
  }


  @Bean
  @ConditionalOnMissingBean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder().title(swaggerConfigurationProperties.getTitle())
        .description(swaggerConfigurationProperties.getDescription()).version(
            swaggerConfigurationProperties.getVersion())
        .contact(swaggerConfigurationProperties.getContact()).build();
  }


}

