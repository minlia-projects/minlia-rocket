package com.minlia.rocket.security.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;

/**
 * @author will
 */
public class AfterPrincipalLoadedEvent extends ApplicationEvent {

  public AfterPrincipalLoadedEvent(Authentication source) {
    super(source);
  }
}
