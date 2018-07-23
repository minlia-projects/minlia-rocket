package com.minlia.rocket.data.enumeration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Status of data")
public enum DataStatusEnumeration implements WithStatusEnumeration<Integer> {

  @ApiModelProperty(value = "Normal")
  NORMAL(1),
  @ApiModelProperty(value = "Locked")
  LOCKED(2),
  @ApiModelProperty(value = "Deleted")
  DELETED(3);


  private Integer status;


  private DataStatusEnumeration(Integer status) {
    this.status = status;
  }

  @Override
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
