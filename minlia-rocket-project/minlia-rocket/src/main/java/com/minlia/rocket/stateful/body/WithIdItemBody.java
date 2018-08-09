package com.minlia.rocket.stateful.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(value = "WithIdItemBody", description = "WithIdItemBody")
public class WithIdItemBody<ID extends Serializable> implements Body {

  @ApiModelProperty(value = "items", notes = "Items")
  private List<WithIdBody<ID>> items;
}
