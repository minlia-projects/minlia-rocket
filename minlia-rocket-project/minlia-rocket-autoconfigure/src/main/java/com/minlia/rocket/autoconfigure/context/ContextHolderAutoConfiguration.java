package com.minlia.rocket.autoconfigure.context;

import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.context.EnvironmentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

/**
 * @author will
 */
@Configuration
@AutoConfigureOrder
@Slf4j
@ConditionalOnClass(value = {ContextHolder.class,EnvironmentHolder.class})
public class ContextHolderAutoConfiguration {

  public ContextHolderAutoConfiguration(){
  }

  @Bean
  @ConditionalOnMissingBean
  public ContextHolder applicationContextHolder() {
    log.debug("Starting Context#ContextHolder Configuration");
    StopWatch watch = new StopWatch();
    watch.start();
    ContextHolder contextHolder=new ContextHolder();
    watch.stop();
    log.debug("Finished Context#ContextHolder Configuration in {} ms", watch.getTotalTimeMillis());
    return contextHolder;
  }

  @Bean
  @ConditionalOnMissingBean
  public EnvironmentHolder environmentHolder() {

    log.debug("Starting Context#EnvironmentHolder Configuration");
    StopWatch watch = new StopWatch();
    watch.start();
    EnvironmentHolder environmentHolder=new EnvironmentHolder();
    watch.stop();
    log.debug("Finished Context#EnvironmentHolder Configuration in {} ms", watch.getTotalTimeMillis());
    return environmentHolder;
  }

}