package com.minlia.rocket.swagger.abstraction;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author will
 * 提供自定义SwaggerConfig<br />
 * 一般用于自定义组名
 */
public class AbstractSwaggerConfig {

  @Autowired
  private SecurityScheme apiKey;

  @Autowired
  private SecurityContext securityContext;

  @Autowired
  private ApiInfo apiInfo;

//  @ConditionalOnProperty(prefix = "system.swagger",name = "enabled",havingValue = "true")
  protected Docket createNewDocket(String groupName, String path) {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .groupName(groupName)
        .select()
        .apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex(path))
        .build()

        .securitySchemes(newArrayList(apiKey))
        .securityContexts(newArrayList(securityContext))
        .apiInfo(apiInfo)
        .ignoredParameterTypes(ApiIgnore.class);

    return docket;

  }


}
