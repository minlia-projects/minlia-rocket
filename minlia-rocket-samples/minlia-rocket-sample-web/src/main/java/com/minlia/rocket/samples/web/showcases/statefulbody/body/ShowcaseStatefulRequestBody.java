package com.minlia.rocket.samples.web.showcases.statefulbody.body;

import com.minlia.rocket.stateful.body.Body;
import lombok.Data;

/**
 * @author will
 */
@Data
public class ShowcaseStatefulRequestBody implements Body {


  private String requestedShowcaseStatefulParameter;

}
