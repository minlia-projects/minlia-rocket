package com.minlia.rocket.context;

import org.springframework.beans.BeansException;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class EnvironmentHolder implements EnvironmentAware {

  private static Environment environment;

  public static Environment getEnvironment() {
    return environment;
  }

  @Override
  public void setEnvironment(Environment env) throws BeansException {
    environment = env;
  }

}
