package com.minlia.rocket.data.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author will
 */
@Data
@ApiModel(value = "sort")
public class Sorting {

  @ApiModelProperty(value = "property",example = "id")
  private String property;

  @ApiModelProperty(value = "direction",example = "DESC")
  private Direction direction;

}