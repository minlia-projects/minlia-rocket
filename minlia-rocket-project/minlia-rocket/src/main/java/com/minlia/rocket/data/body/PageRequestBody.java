package com.minlia.rocket.data.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.stateful.body.Body;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(value = "pageRequestBody")
public class PageRequestBody implements Body {

  @ApiModelProperty(value = "page",example = "1")
  @JsonProperty
  private Integer page;

  @ApiModelProperty(value = "size",example = "20")
  @JsonProperty
  private Integer size;

  @ApiModelProperty(value = "sort")//,example = "{property:'id',direction:'DESC'}")
  @JsonProperty
  private List<Sorting> sort;
}
