package com.minlia.rocket.i18n.system.validation.validator;

/**
 * Implement isUnique() to use in validator
 */
public interface UniqueValidable {

    boolean isUnique(String fieldName, String value);

}
