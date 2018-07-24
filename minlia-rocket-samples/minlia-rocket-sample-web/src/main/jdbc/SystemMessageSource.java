package com.minlia.rocket.samples.web.openapi.jdbc;

import com.minlia.rocket.samples.web.openapi.jdbc.entity.Translation;
import com.minlia.rocket.samples.web.openapi.jdbc.repository.TranslationRepository;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractMessageSource;

/**
 * @author will
 */
public class SystemMessageSource extends AbstractMessageSource {

    private TranslationRepository translationRepository;
    private static final Logger logger = LogManager.getLogger(
        SystemMessageSource.class);
    private Map<Translation.AvailableLanguage, Map<String, String>> translations =
            new HashMap<Translation.AvailableLanguage, Map<String, String>>();

    public SystemMessageSource() {
        reload();
    }
    public SystemMessageSource(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
        reload();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MessageFormat result = createMessageFormat(getTranslation(code, locale), locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getTranslation(code, locale);
    }

    public void reload() {
        logger.info("Load translations from database");
        translations.clear();
        translations.putAll(loadTranslations());
    }

    private String getTranslation(String code, Locale locale) {
        Translation.AvailableLanguage language = Translation.AvailableLanguage.fromCode(locale.getLanguage());
        if (language == null) {
            logger.error("Locale '" + locale.getLanguage() + "' was not found in available locales");
            return code;
        }

        Map<String, String> languageTranslations = translations.get(language);
        if (languageTranslations == null) {
            logger.warn("Locale '" + language.code() + "' was not found in translations");
            return code;
        }

        String translation = languageTranslations.get(code);
        if (translation == null) {
            try {
                translation = getParentMessageSource().getMessage(code, null, locale);
            } catch (NoSuchMessageException e) {
                logger.warn("Code '" + code + "' for Locale '" + language.code() + "' was not found in translations");
                return code;
            }
        }

        return translation;
    }

    private Map<Translation.AvailableLanguage, Map<String, String>> loadTranslations() {
        Map<Translation.AvailableLanguage, Map<String, String>> translations =
                new HashMap<Translation.AvailableLanguage, Map<String, String>>();
        List<Translation> allTranslations = translationRepository.findAll();

        for (Translation translation : allTranslations) {
            Translation.AvailableLanguage language = translation.getLanguage();
            if (! translations.containsKey(language)) {
                translations.put(language, new HashMap<String, String>());
            }
            translations.get(language).put(translation.getCode(), translation.getValue());
        }

        return translations;
    }

}
