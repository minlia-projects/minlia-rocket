package com.minlia.rocket.security.event;

import com.minlia.rocket.security.body.ValidationArgumentBody;
import org.springframework.context.ApplicationEvent;

/**
 * @author will
 */
public class AfterValidationEvent extends ApplicationEvent {

  public AfterValidationEvent(ValidationArgumentBody source) {
    super(source);
  }
}
