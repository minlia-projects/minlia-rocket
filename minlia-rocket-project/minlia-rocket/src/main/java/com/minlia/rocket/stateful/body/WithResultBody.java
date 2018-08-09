package com.minlia.rocket.stateful.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(value = "WithResultBody", description = "WithResultBody")
public class WithResultBody<T> implements Body {

  /**
   * result
   */
  @ApiModelProperty(value = "result", notes = "Result")
  private T result;

}
