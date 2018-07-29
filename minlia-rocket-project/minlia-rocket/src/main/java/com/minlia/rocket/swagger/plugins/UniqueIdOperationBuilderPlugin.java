package com.minlia.rocket.swagger.plugins;

import com.google.common.base.Optional;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.annotation.Order;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class UniqueIdOperationBuilderPlugin implements OperationBuilderPlugin {

    private final String operationIdPrefix;

    public UniqueIdOperationBuilderPlugin(final String operationIdPrefix) {
        this.operationIdPrefix = operationIdPrefix;
    }

    @Override
    public void apply(final OperationContext context) {
        final Optional<ApiOperation> methodAnnotation = context.findAnnotation(ApiOperation.class);
        if (methodAnnotation.isPresent()) {
            context.operationBuilder().uniqueId(operationIdPrefix + "_" + context.getName());
        }
    }

    @Override
    public boolean supports(final DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}