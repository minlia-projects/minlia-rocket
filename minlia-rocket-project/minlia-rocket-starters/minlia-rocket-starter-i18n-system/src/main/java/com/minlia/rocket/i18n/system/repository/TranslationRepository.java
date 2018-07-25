package com.minlia.rocket.i18n.system.repository;


import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.i18n.system.entity.Translation;

/**
 * @author will
 */
public interface TranslationRepository extends AbstractRepository<Translation,Long> {

    /**
     * @param code
     * @param language
     * @return
     */
//    Translation findByCodeAndLanguage(String code, Translation.AvailableLanguage language);
    Translation findByCodeAndLanguage(String code, String language);

}
