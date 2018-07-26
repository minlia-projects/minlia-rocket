package com.minlia.rocket.samples.web.showcases.contextholder.body;

import com.minlia.rocket.stateful.body.Body;
import lombok.Data;

/**
 * @author will
 */
@Data
public class ShowcaseContextHolderRequestBody implements Body {


  private String requestedShowcaseContextHolderParameter;

}
