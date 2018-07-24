package com.minlia.rocket.samples.web.openapi.jdbc.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.minlia.rocket.samples.web.openapi.jdbc.validation.validator.UniqueValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validate that a field is unique in database The dao should implement UniqueValidable interface
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {

  String message() default "{constraints.unique}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * The dao should implement UniqueValidable interface
   */
  String daoName() default "";

  String fieldName() default "";

  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  public @interface List {

    Unique[] value();
  }

}
