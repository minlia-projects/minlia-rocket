package com.minlia.rocket.autoconfigure.swagger;

import static com.google.common.collect.Lists.newArrayList;

import com.fasterxml.classmate.TypeResolver;
import com.minlia.rocket.property.SystemProperties;
import com.minlia.rocket.swagger.plugins.LanguageRequestParameterOperationBuilderPlugin;
//import com.minlia.rocket.swagger.plugins.PageableParameterOperationBuilderPlugin;
import com.minlia.rocket.swagger.plugins.UuidAndDateParameterOperationBuilderPlugin;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StopWatch;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.TypeNameExtractor;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@Configuration
@ComponentScan(value = {"com.minlia.rocket.swagger"})
@ConditionalOnClass({Swagger2DocumentationConfiguration.class,
    ApiOperation.class})
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)

@Slf4j
public class SwaggerAutoConfiguration {

  @Autowired
  private SystemProperties systemProperties;

  @Bean
  public Docket defaultSpecification() {
    log.debug("Starting swagger configuration");
    StopWatch watch = new StopWatch();
    watch.start();

    String groupName = "-The-Default-API-Group-";
    String path = systemProperties.getSwagger().getPath();
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .groupName(groupName)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex(path))
        .build()
        .securitySchemes(newArrayList(apiKey()))
        .securityContexts(newArrayList(securityContext()))
        .apiInfo(apiInfo())

        .useDefaultResponseMessages(false)
        .ignoredParameterTypes(ApiIgnore.class);
    watch.stop();
    log.debug("Finishing swagger configuration in {} ms", watch.getTotalTimeMillis());
    return docket;
  }

  @Bean
  @ConditionalOnMissingBean
  SecurityScheme apiKey() {
    return new ApiKey("apiKey", systemProperties.getSwagger().getKeyName(),
        systemProperties.getSwagger()
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
        .forPaths(PathSelectors.any())

        .build();
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
    return new ApiInfoBuilder().title(systemProperties.getSwagger().getTitle())
        .description(systemProperties.getSwagger().getDescription()).version(
            systemProperties.getSwagger().getVersion())
        .contact(systemProperties.getSwagger().getContact()).build();
  }


  @Autowired
  private TypeNameExtractor nameExtractor;

  @Autowired
  private TypeResolver resolver;

//  @Bean
//  @ConditionalOnMissingBean
//  public PageableParameterOperationBuilderPlugin pageableParameterBuilder() {
//    return new PageableParameterOperationBuilderPlugin(nameExtractor, resolver);
//  }

  @Bean
  @ConditionalOnMissingBean
  //开发环境不需要，否则会导致生成SWAGGER有多余参数
  public LanguageRequestParameterOperationBuilderPlugin languageParameterBuilder() {
    return new LanguageRequestParameterOperationBuilderPlugin();
  }

//  @Bean
//  @ConditionalOnMissingBean
//  public OperationNicknameIntoUniqueIdReader operationNicknameIntoUniqueIdReader() {
//    return new OperationNicknameIntoUniqueIdReader();
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public AuthenticationTokenHeaderOperationBuilderPlugin authenticationTokenHeaderBuilderPlugin() {
//    return new AuthenticationTokenHeaderOperationBuilderPlugin();
//  }

//  @Bean
//  @ConditionalOnMissingBean
//  public UniqueIdOperationBuilderPlugin uniqueIdSetterPlugin() {
//    return new UniqueIdOperationBuilderPlugin("");
//  }

//  @Bean
//  @ConditionalOnMissingBean
//  public SinceOperationBuilderPlugin sinceOperationBuilderPlugin() {
//    return new SinceOperationBuilderPlugin();
//  }

  @Bean
  @ConditionalOnMissingBean
  public UuidAndDateParameterOperationBuilderPlugin uuidAndDateParameterBuilder() {
    return new UuidAndDateParameterOperationBuilderPlugin();
  }


//  @Configuration
////  @EnableSwagger
//  @EnableSwagger2//enable official Swagger2
//  @Import({Swagger2Config.class})
//  public static class Swagger2AutoConfig {
//
//  }


}
