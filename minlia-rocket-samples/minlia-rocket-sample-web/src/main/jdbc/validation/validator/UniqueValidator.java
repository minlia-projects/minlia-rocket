package com.minlia.rocket.samples.web.openapi.jdbc.validation.validator;

import com.minlia.rocket.samples.web.openapi.jdbc.validation.constraints.Unique;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private ApplicationContext applicationContext;

    private UniqueValidable dao;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.dao = (UniqueValidable) applicationContext.getBean(constraintAnnotation.daoName());
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return dao.isUnique(fieldName, value);
    }

}
