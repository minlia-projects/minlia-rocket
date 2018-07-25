package com.minlia.rocket.i18n.system;

import com.minlia.rocket.i18n.system.entity.Translation;
import com.minlia.rocket.i18n.system.repository.TranslationRepository;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author will
 */
@Slf4j
public class SystemMessageSource extends AbstractMessageSource {

  private TranslationRepository translationRepository;
  private Map<String, Map<String, String>> translations =
      new HashMap<String, Map<String, String>>();

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
    log.info("Clear translations from caches");
    translations.clear();
    log.info("Loading translations from database...");
    translations.putAll(loadTranslations());
    log.info("Items count of translations is: {}", translations.size());
  }


  /**
   * 查找并返回系统中已存在的译文
   * TODO to process arguments
   */
  private String getLocaleTranslation(String code, String language, String country) {
    String result = null;
    String localeStyle = null;
    if (!StringUtils.isEmpty(country) && !StringUtils.isEmpty(language)) {
      localeStyle = language + "_" + country;
    }

    //查找全形
    Map<String, String> translationsByLanguage = translations.get(localeStyle);
    if(!CollectionUtils.isEmpty(translationsByLanguage)) {
      result = translationsByLanguage.get(code);
    }
    //当全形找不到时进行language形式查找
    if (StringUtils.isEmpty(result)) {
      //查找language形式的
      localeStyle = language;
      translationsByLanguage = translations.get(localeStyle);
      if(!CollectionUtils.isEmpty(translationsByLanguage)) {
        result = translationsByLanguage.get(code);
      }
    }

    //当language形式也找不到的情况，则从parentMessageSource查找
    if (StringUtils.isEmpty(result)) {
      try {
        Locale locale = new Locale.Builder().setLanguage(language).setRegion(country).build();
        if(null!=getParentMessageSource()) {
          result = getParentMessageSource().getMessage(code, null, locale);
        }
      } catch (Exception e) {
        log.warn("Exception when find translation for code: {} with exception: {}",code,e.getMessage());
        return code;
      }
    }

    //再找不到时返回code的值加上
    if (StringUtils.isEmpty(result)) {
      result = String.format("%s%s%s", "%%", code, "%%");
    }

    return result;
  }

  private String getTranslation(String code, Locale locale) {

    //不能没有code查找
    if (StringUtils.isEmpty(code)) {
      throw new NoSuchMessageException(null);
    }

    //首先当没有传入Locale时获取系统默认的Locale
    if (locale == null) {
      locale = LocaleContextHolder.getLocale();
    }

    //先查找全形 zh_CN
    log.debug("Code: {} Locale: {}", code, locale);

    String language = locale.getLanguage();

    //再查找language形式 zh
    String country = locale.getCountry();

    String result = getLocaleTranslation(code, language, country);

    return result;
  }

  private Map<String, Map<String, String>> loadTranslations() {
    Map<String, Map<String, String>> translations =
        new HashMap<String, Map<String, String>>();
    List<Translation> allTranslations = translationRepository.findAll();

    for (Translation translation : allTranslations) {
      String language = translation.getLanguage();
      if (!translations.containsKey(language)) {
        translations.put(language, new HashMap<String, String>());
      }
      translations.get(language).put(translation.getCode(), translation.getValue());
    }

    return translations;
  }

}
