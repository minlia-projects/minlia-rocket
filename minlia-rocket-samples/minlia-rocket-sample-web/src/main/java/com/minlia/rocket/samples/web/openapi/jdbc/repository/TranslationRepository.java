package com.minlia.rocket.samples.web.openapi.jdbc.repository;


import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.samples.web.openapi.jdbc.entity.Translation;

/**
 * @author will
 */
public interface TranslationRepository extends AbstractRepository<Translation,Long> {

    /**
     *
     * @param code
     * @param language
     * @return
     */
    Translation findByCodeAndLanguage(String code, Translation.AvailableLanguage language);

}
