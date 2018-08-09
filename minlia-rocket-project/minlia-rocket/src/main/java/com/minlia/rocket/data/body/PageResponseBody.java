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
@ApiModel(value = "PageResponseBody" )
public class PageResponseBody<T> implements Body {

  @JsonProperty
  @ApiModelProperty(value = "page")
  private Long page;

  @JsonProperty
  @ApiModelProperty(value = "size")
  private Long size;

  @JsonProperty
  @ApiModelProperty(value = "totalPages")
  private Long totalPages;

  @JsonProperty
  @ApiModelProperty(value = "totalElements")
  private Long totalElements;
  @JsonProperty
  @ApiModelProperty(value = "items")
  private List<T> items;

}
