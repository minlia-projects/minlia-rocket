package com.minlia.rocket.data.event;

import java.io.Serializable;
import org.springframework.context.ApplicationEvent;

public class EntityCreatedEvent<ENTITY extends Serializable> extends ApplicationEvent {

  public EntityCreatedEvent(Object source) {
    super(source);
  }
}
