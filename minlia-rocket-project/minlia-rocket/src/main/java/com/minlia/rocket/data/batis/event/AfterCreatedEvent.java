package com.minlia.rocket.data.batis.event;

import org.springframework.context.ApplicationEvent;

public class AfterCreatedEvent extends ApplicationEvent {


  private static final long serialVersionUID = 1L;

  public AfterCreatedEvent(Object source) {
    super(source);
  }

}
