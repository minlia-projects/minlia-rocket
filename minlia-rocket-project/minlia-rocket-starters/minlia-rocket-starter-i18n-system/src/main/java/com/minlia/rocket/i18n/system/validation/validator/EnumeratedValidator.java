package com.minlia.rocket.i18n.system.validation.validator;

import com.minlia.rocket.i18n.system.validation.constraints.Enumerated;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.EnumUtils;

public class EnumeratedValidator implements ConstraintValidator<Enumerated, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(Enumerated constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClazz();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return EnumUtils.isValidEnum( (Class) this.enumClass, value);
    }

}
