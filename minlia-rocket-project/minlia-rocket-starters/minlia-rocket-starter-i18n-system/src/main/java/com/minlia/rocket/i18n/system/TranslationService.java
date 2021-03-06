package com.minlia.rocket.i18n.system;

import com.minlia.rocket.i18n.system.dto.TranslationCreateDto;
import com.minlia.rocket.i18n.system.dto.TranslationDto;
import com.minlia.rocket.i18n.system.entity.Translation;
import com.minlia.rocket.i18n.system.repository.TranslationRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author will
 */
@Service("translationService")
public class TranslationService {

  @Autowired
  private TranslationRepository translationDao;

  @Autowired
  private MessageSource messageSource;

  @Transactional
  public void save(Translation translation) {
    this.translationDao.save(translation);
    reloadMessageSource();
  }

  public Translation find(long id) {
    return this.translationDao.findById(id).get();
  }

  public List<Translation> findAll() {
    return this.translationDao.findAll();
  }

//  public Translation findByCodeAndLanguage(String code, Translation.AvailableLanguage language) {
  public Translation findByCodeAndLanguage(String code, String language) {
    return this.translationDao.findByCodeAndLanguage(code, language);
  }

  @Transactional
  public void delete(long id) {
    Translation translation = this.find(id);

    if (translation == null) {
      throw new ObjectNotFoundException(id, "translation");
    }

    translationDao.delete(translation);
    reloadMessageSource();
  }

  @Transactional
  public Translation create(TranslationCreateDto translationDto) {
    Translation translation = new Translation();
    translation.setCode(translationDto.getCode());
//    translation.setLanguage(Translation.AvailableLanguage.valueOf(translationDto.getLanguage()));
    translation.setLanguage(translationDto.getLanguage());
    translation.setValue(translationDto.getValue());
//    translation.setCreatedAt(LocalDateTime.now());
//    translation.setUpdatedAt(LocalDateTime.now());
    this.save(translation);
    return translation;
  }

  public boolean isUnique(TranslationDto dto) {
//    Translation.AvailableLanguage language = Translation.AvailableLanguage
//        .valueOf(dto.getLanguage());
    String language = dto.getLanguage();
    if (translationDao.findByCodeAndLanguage(dto.getCode(), language) == null) {
      return true;
    }
    return false;
  }

  /**
   * Reload messageSource after change on database
   */
  private void reloadMessageSource() {
    if (messageSource instanceof SystemMessageSource) {
      ((SystemMessageSource) messageSource).reload();
    } else if (messageSource instanceof DelegatingMessageSource) {
      DelegatingMessageSource delegatingMessageSource = (DelegatingMessageSource) messageSource;
      if (delegatingMessageSource.getParentMessageSource() != null
          && delegatingMessageSource.getParentMessageSource() instanceof SystemMessageSource) {
        ((SystemMessageSource) delegatingMessageSource.getParentMessageSource()).reload();
      }
    }
  }

}
