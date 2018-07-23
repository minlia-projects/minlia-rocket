package com.minlia.rocket.data.batis.event.publisher;

import com.minlia.rocket.data.batis.event.AfterCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author will
 */
public class AfterCreatedEventPublisher implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  public void publish(Object object) {
    AfterCreatedEvent event = new AfterCreatedEvent(object);
    this.publisher.publishEvent(event);
  }
}
