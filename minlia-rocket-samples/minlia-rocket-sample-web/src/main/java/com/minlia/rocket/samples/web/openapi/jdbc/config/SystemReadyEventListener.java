package com.minlia.rocket.samples.web.openapi.jdbc.config;

import com.minlia.rocket.samples.web.openapi.jdbc.DatabaseDrivenMessageSource;
import com.minlia.rocket.samples.web.openapi.jdbc.repository.TranslationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author will
 */
@Slf4j
@Component
//@Order(value = Ordered.LOWEST_PRECEDENCE)
public class SystemReadyEventListener implements
    ApplicationListener<ApplicationReadyEvent> {



  @Autowired
  private MessageSource messageSource;

  @Autowired
  @Lazy
  private TranslationRepository translationRepository;

  @Lazy
  @Bean
  @Primary
//  @ConditionalOnMissingBean
  public DatabaseDrivenMessageSource databaseDrivenMessageSource() {
    DatabaseDrivenMessageSource databaseDrivenMessageSource = new DatabaseDrivenMessageSource(
        translationRepository);
    if(null!=messageSource) {
      databaseDrivenMessageSource.setParentMessageSource(messageSource);
    }
    return databaseDrivenMessageSource;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    log.debug("Starting i18n jdbc message source configuration");
    StopWatch watch = new StopWatch();
    watch.start();


//    DefaultListableBeanFactory context =
//        new DefaultListableBeanFactory();
//
//    GenericBeanDefinition gbd = new GenericBeanDefinition();
//
//    gbd.setBeanClass(DatabaseDrivenMessageSource.class);
//
//    MutablePropertyValues mpv = new MutablePropertyValues();
//    mpv.add("translationRepository", translationRepository);
//    mpv.add("parentMessageSource", messageSource);
//
//    //alternatively we can use:
////     gbd.getPropertyValues().addPropertyValue("date", new Date());
//    gbd.setPropertyValues(mpv);
//
//    context.registerBeanDefinition("databaseDrivenMessageSource", gbd);
//


    watch.stop();
    log.debug("Finishing i18n jdbc message source configuration in {} ms",
        watch.getTotalTimeMillis());
  }
}
