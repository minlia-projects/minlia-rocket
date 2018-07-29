package com.minlia.rocket.swagger.plugins;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * @author will
 */
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class LanguageRequestParameterOperationBuilderPlugin implements OperationBuilderPlugin {

  private final ParameterBuilder parameterBuilder = new ParameterBuilder();


  @Value(value = "${system.i18n.language-request-header-parameter-name:X-Request-Lang}")
  private String languageRequestHeaderParameterName;


  @Override
  public void apply(final OperationContext operationContext) {
      final String description = "Request Language";
      final List<Parameter> parameters = Lists.newArrayList(
          this.parameterBuilder
              .parameterType("header")
              .name(languageRequestHeaderParameterName)
              .modelRef(new ModelRef("string"))
              .defaultValue("zh_CN")
              .description(description)
              .allowMultiple(false)
              .required(false)
              .build()
      );

      operationContext.operationBuilder().parameters(parameters);
  }

  @Override
  public boolean supports(final DocumentationType delimiter) {
    return DocumentationType.SWAGGER_2.equals(delimiter);
  }
}