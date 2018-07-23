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
    log.debug("Starting Minlia Rocket Context#ContextHolder");
    StopWatch watch = new StopWatch();
    watch.start();
    ContextHolder contextHolder=new ContextHolder();
    watch.stop();
    log.debug("Started Minlia Rocket Context#ContextHolder in {} ms", watch.getTotalTimeMillis());
    return contextHolder;
  }

  @Bean
  @ConditionalOnMissingBean
  public EnvironmentHolder environmentHolder() {

    log.debug("Starting Minlia Rocket Context#EnvironmentHolder");
    StopWatch watch = new StopWatch();
    watch.start();
    EnvironmentHolder environmentHolder=new EnvironmentHolder();
    watch.stop();
    log.debug("Started Minlia Rocket Context#EnvironmentHolder in {} ms", watch.getTotalTimeMillis());
    return environmentHolder;
  }

}