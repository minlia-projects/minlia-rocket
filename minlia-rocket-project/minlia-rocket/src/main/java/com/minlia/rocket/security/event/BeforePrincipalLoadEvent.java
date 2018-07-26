package com.minlia.rocket.security.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;

/**
 * @author will
 */
public class BeforePrincipalLoadEvent extends ApplicationEvent  {

  public BeforePrincipalLoadEvent(Authentication source) {
    super(source);
  }
}
