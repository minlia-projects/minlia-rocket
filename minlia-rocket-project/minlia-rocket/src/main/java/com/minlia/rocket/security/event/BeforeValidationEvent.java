package com.minlia.rocket.security.event;

import com.minlia.rocket.security.body.ValidationArgumentBody;
import org.springframework.context.ApplicationEvent;

/**
 * @author will
 */
public class BeforeValidationEvent extends ApplicationEvent {

  public BeforeValidationEvent(ValidationArgumentBody source) {
    super(source);
  }
}

