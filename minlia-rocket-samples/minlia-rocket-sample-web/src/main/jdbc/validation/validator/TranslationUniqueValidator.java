package com.minlia.rocket.samples.web.openapi.jdbc.validation.validator;

import com.minlia.rocket.samples.web.openapi.jdbc.TranslationService;
import com.minlia.rocket.samples.web.openapi.jdbc.dto.TranslationDto;
import com.minlia.rocket.samples.web.openapi.jdbc.validation.constraints.TranslationUnique;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TranslationUniqueValidator implements ConstraintValidator<TranslationUnique, TranslationDto> {

    @Autowired
    private TranslationService translationService;

    @Override
    public void initialize(TranslationUnique constraintAnnotation) { }

    @Override
    public boolean isValid(TranslationDto dto, ConstraintValidatorContext context) {
        boolean isValid = translationService.isUnique(dto);
        if (! isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("error.translation.unique")
                    .addPropertyNode("code")
                    .addConstraintViolation();
        }

        return isValid;
    }

}
