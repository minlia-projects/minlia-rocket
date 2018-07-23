package com.minlia.rocket.stateful.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(value = "withIdBody", description = "带Id的主体")
public class WithIdBody<ID extends Serializable> implements Body {

  /**
   * ID
   */
  @ApiModelProperty(value = "id", notes = "ID")
  private ID id;

}
