package com.minlia.rocket.data.batis.event;

import com.minlia.rocket.stateful.body.StatefulBody;
import org.springframework.context.ApplicationEvent;

/**
 * @author will
 */
public class BeforQueryEvent extends ApplicationEvent {

  private StatefulBody body;

  public BeforQueryEvent(Object source, StatefulBody body) {
    super(source);
    this.body = body;
  }

  public BeforQueryEvent(Object source) {
    super(source);
  }

  public StatefulBody getBody() {
    return body;
  }
}