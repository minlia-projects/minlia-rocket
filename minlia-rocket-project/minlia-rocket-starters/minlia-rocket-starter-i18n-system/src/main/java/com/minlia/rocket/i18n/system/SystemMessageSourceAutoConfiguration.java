package com.minlia.rocket.i18n.system;

import com.minlia.rocket.i18n.system.repository.TranslationRepository;
import com.minlia.rocket.property.LanguageProperties;
import com.minlia.rocket.property.SystemProperties;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link MessageSource}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Eddú Meléndez
 */
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
//@Conditional(ResourceBundleCondition.class)
@EnableConfigurationProperties
@Slf4j
@ComponentScan
public class SystemMessageSourceAutoConfiguration {

  private static final Resource[] NO_RESOURCES = {};

//  @Bean
//  @ConfigurationProperties(prefix = "system.i18n")
//  public MessageSourceProperties messageSourceProperties() {
//    return new MessageSourceProperties();
//  }


  @Autowired
  private SystemProperties systemProperties;

  @Autowired
  private TranslationRepository translationRepository;


  /**
   * WARNING: Never config interceptors out of WebMvcConfigurer, it will cause stack overflow
   * exception at runtime.
   */
  @Configuration
  public static class SystemI18nValidatorConfiguration implements WebMvcConfigurer {

    @Autowired
    private MessageSource messageSource;

    @Lazy
    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
      LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
      bean.setValidationMessageSource(messageSource);
      return bean;
    }


    @Override
    public Validator getValidator() {
      return validator();
    }
  }


  /**
   * As primary candidate of MessageSource to force enable system message source.
   *
   * @return MessageSource
   */
  @Primary
  @Bean
  public MessageSource systemMessageSource() {

    log.debug("Starting i18n system message source configuration");
    StopWatch watch = new StopWatch();
    watch.start();

    SystemMessageSource systemMessageSource = new SystemMessageSource(
        translationRepository);

    LanguageProperties properties = systemProperties.getI18n();
    systemMessageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
    systemMessageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());

    if (null != parentMessageSource()) {
      systemMessageSource.setParentMessageSource(parentMessageSource());
    }

    watch.stop();
    log.debug("Finishing i18n system message source configuration in {} ms",
        watch.getTotalTimeMillis());

    return systemMessageSource;
  }

  private MessageSource parentMessageSource() {
    LanguageProperties properties = systemProperties.getI18n();
    MergedMessageSource messageSource = new MergedMessageSource();
    if (StringUtils.hasText(properties.getBasename())) {
      messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
          StringUtils.trimAllWhitespace(properties.getBasename())));
    }
    if (properties.getEncoding() != null) {
      messageSource.setDefaultEncoding(properties.getEncoding().name());
    }
    messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
    Duration cacheDuration = properties.getCacheDuration();
    if (cacheDuration != null) {
      messageSource.setCacheMillis(cacheDuration.toMillis());
    }
    messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
    messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());

    return messageSource;
  }


}
