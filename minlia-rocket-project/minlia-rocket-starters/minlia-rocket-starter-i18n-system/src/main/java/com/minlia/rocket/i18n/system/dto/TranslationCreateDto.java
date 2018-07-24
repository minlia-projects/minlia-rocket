package com.minlia.rocket.i18n.system.dto;

import com.minlia.rocket.i18n.system.entity.Translation;
import com.minlia.rocket.i18n.system.validation.constraints.Enumerated;
import com.minlia.rocket.i18n.system.validation.constraints.TranslationUnique;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

interface ValidLanguageGroup {}

@TranslationUnique()
@GroupSequence({
    ValidLanguageGroup.class, TranslationCreateDto.class})
public class TranslationCreateDto implements TranslationDto {

    @Length(max = 100)
    @NotBlank
    private String code;

    @NotNull
    @Enumerated(enumClazz=Translation.AvailableLanguage.class,
            message="{error.translation.invalid_language}", groups = ValidLanguageGroup.class)
    private String language;

    @NotBlank
    private String value;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
