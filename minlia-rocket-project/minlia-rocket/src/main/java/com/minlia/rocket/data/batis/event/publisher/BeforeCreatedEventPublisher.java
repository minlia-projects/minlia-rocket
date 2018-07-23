package com.minlia.rocket.data.batis.event.publisher;

import com.minlia.rocket.data.batis.event.BeforeCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class BeforeCreatedEventPublisher implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  public void publish(Object object) {
    BeforeCreatedEvent event = new BeforeCreatedEvent(object);
    this.publisher.publishEvent(event);
  }

}
