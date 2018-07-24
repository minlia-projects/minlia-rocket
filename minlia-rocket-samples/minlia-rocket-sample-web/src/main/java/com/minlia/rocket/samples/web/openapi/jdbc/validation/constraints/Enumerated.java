package com.minlia.rocket.samples.web.openapi.jdbc.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.minlia.rocket.samples.web.openapi.jdbc.validation.validator.EnumeratedValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = EnumeratedValidator.class)
@Documented
public @interface Enumerated {

    String message() default "{constraints.enumerated}";

    Class<? extends Enum<?>> enumClazz();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
