package com.minlia.rocket.swagger.plugins;

import com.google.common.collect.Lists;
import com.minlia.rocket.property.SecurityJwtProperties;
import com.minlia.rocket.property.SystemProperties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by zhanghaolun on 16/11/1.
 */
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class AuthenticationTokenHeaderOperationBuilderPlugin implements OperationBuilderPlugin {

  private final ParameterBuilder parameterBuilder = new ParameterBuilder();

  @Autowired
  private SystemProperties systemProperties;


  @Value(value = "${system.security.authentication-header-name:X-Auth-Token}")
  private String authenticationHeaderName;

  @Override
  public void apply(final OperationContext operationContext) {
    final SecurityJwtProperties securityProperties = this.systemProperties.getSecurity();

    // Get endpoint request mapping
    final String mapping = operationContext.requestMappingPattern();

    // Check if private api endpoint
    if (securityProperties.getEnabled()) {
      // Create auth header parameter
      final String description = "Authentication X-Auth-Token";
      final List<Parameter> parameters = Lists.newArrayList( //
          this.parameterBuilder
              .parameterType("header")
              .name(authenticationHeaderName)
              .defaultValue("Bearer ")
              .modelRef(new ModelRef("string"))
              .description(description)
              .allowMultiple(false)
              .required(false)
              .build() //
      );

      // Add parameter to endpoint documentation
      operationContext.operationBuilder().parameters(parameters);
    }
  }

  @Override
  public boolean supports(final DocumentationType delimiter) {
    return DocumentationType.SWAGGER_2.equals(delimiter);
  }
}