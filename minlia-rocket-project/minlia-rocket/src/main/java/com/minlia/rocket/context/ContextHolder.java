package com.minlia.rocket.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author will
 */
public class ContextHolder implements ApplicationContextAware {

  private static ApplicationContext applicatioContext;

  public static ApplicationContext getContext() {
    return ContextHolder.applicatioContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    ContextHolder.applicatioContext = ctx;
  }

}
