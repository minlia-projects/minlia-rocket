package com.minlia.rocket.enumeration;

import com.baomidou.mybatisplus.enums.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value = "Gender")
public enum Gender implements IEnum {

  @ApiModelProperty(value = "UNKNOW")
  UNKNOW,
  @ApiModelProperty(value = "MALE")
  MALE,
  @ApiModelProperty(value = "FEMALE")
  FEMALE;

  @Override
  public Serializable getValue() {
    return this.name();
  }


}
