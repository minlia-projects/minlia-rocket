package com.minlia.rocket.samples.web.openapi.jdbc.validation.validator;

/**
 * Implement isUnique() to use in validator
 */
public interface UniqueValidable {

    boolean isUnique(String fieldName, String value);

}
