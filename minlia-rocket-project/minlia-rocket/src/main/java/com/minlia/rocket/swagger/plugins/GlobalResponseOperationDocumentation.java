//package com.minlia.rocket.swagger.plugins;
//
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.common.collect.Sets;
//
//import fr.nduheron.poc.springrestapi.tools.exception.model.ErrorParameter;
//import fr.nduheron.poc.springrestapi.tools.exception.model.FunctionalError;
//import springfox.documentation.builders.ResponseMessageBuilder;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ResolvedMethodParameter;
//import springfox.documentation.service.ResponseMessage;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.OperationBuilderPlugin;
//import springfox.documentation.spi.service.contexts.OperationContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.common.SwaggerPluginSupport;
//
///**
// * Gestion automatique de la documentation swagger pour les erreurs.
// */
//@Component
//@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
//@ConditionalOnBean(Docket.class)
//public class GlobalResponseOperationDocumentation implements OperationBuilderPlugin {
//
//	@Override
//	public boolean supports(DocumentationType delimiter) {
//		return true;
//	}
//
//	@Override
//	public void apply(OperationContext context) {
//		if (context.findControllerAnnotation(RestController.class).isPresent()) {
//			Set<ResponseMessage> responsesMessage = Sets.newHashSet();
//
//			// BAD REQUEST
//			Optional<ResolvedMethodParameter> optionnalBadRequest = context.getParameters().stream()
//					.filter(methodParameter -> methodParameter.findAnnotation(RequestBody.class).isPresent())
//					.findFirst();
//			if (optionnalBadRequest.isPresent()) {
//				responsesMessage.add(new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
//						.responseModel(new ModelRef("list", new ModelRef(ErrorParameter.class.getSimpleName())))
//						.message("Les paramètres de la requête sont invalides.").build());
//			}
//
//			// NOT FOUND
//			long cptNotFound = context.getParameters().stream()
//					.filter(methodParameter -> methodParameter.findAnnotation(PathVariable.class).isPresent()).count();
//			if (cptNotFound > 0 && (context.httpMethod() != HttpMethod.DELETE || cptNotFound > 1)) {
//				responsesMessage.add(new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
//						.message("La ressource n'existe pas.").build());
//			}
//
//			// Erreur fonctionnelle
//			com.google.common.base.Optional<ApiConflictResponse> optionalConflict = context
//					.findAnnotation(ApiConflictResponse.class);
//			if (optionalConflict.isPresent()) {
//				responsesMessage.add(new ResponseMessageBuilder().code(HttpStatus.CONFLICT.value())
//						.message(optionalConflict.get().message())
//						.responseModel(new ModelRef(FunctionalError.class.getSimpleName())).build());
//
//			}
//
//			// Erreur technique
//			responsesMessage.add(new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//					.message("Une erreur inattendue s'est produite.").build());
//
//			context.operationBuilder().responseMessages(responsesMessage);
//		}
//	}
//
//}