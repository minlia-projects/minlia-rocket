package com.minlia.rocket.stateful.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author will
 */
@ApiModel(value = "withNameRequestBody", description = "带ID的请求体")
@Data
public class WithNameRequestBody implements Body {

  @ApiModelProperty(value = "name", notes = "名称")
  @JsonProperty
  private String name;
}
