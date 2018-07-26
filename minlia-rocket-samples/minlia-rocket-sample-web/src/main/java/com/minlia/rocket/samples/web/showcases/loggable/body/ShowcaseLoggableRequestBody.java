package com.minlia.rocket.samples.web.showcases.loggable.body;

import com.minlia.rocket.stateful.body.Body;
import lombok.Data;

/**
 * @author will
 */
@Data
public class ShowcaseLoggableRequestBody implements Body {


  private String requestedShowcaseLoggableParameter;

}
