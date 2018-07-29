package com.minlia.rocket.autoconfigure.listener;

import com.minlia.rocket.listener.SystemReadyEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author will
 */
@Configuration
@ConditionalOnClass(SystemReadyEventListener.class)
@ConditionalOnMissingBean({SystemReadyEventListener.class})
@Slf4j
public class SystemListenerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public SystemReadyEventListener languageProperties() {
    return new SystemReadyEventListener();
  }


}
