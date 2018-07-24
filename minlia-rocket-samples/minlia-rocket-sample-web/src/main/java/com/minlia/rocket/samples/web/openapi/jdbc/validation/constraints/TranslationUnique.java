package com.minlia.rocket.samples.web.openapi.jdbc.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.minlia.rocket.samples.web.openapi.jdbc.validation.validator.TranslationUniqueValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = TranslationUniqueValidator.class)
@Documented
public @interface TranslationUnique {

    String message() default "{error.translation.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
