package com.minlia.rocket.samples.web.openapi.jdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author will
 */
@Slf4j
@Component
//@Order(value = Ordered.LOWEST_PRECEDENCE)
public class SystemStartedEventListener implements
    ApplicationListener<ApplicationStartedEvent> {


  @Override
  public void onApplicationEvent(ApplicationStartedEvent event) {
    log.debug("ApplicationStartedEvent");
//    StopWatch watch = new StopWatch();
//    watch.start();
//
//    watch.stop();
//    log.debug("Finishing i18n jdbc message source configuration in {} ms",
//        watch.getTotalTimeMillis());
  }
}
