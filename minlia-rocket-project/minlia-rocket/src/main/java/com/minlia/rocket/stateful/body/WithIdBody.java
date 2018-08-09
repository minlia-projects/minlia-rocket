package com.minlia.rocket.stateful.body;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minlia.rocket.data.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Transient;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(value = "WithIdBody", description = "WithIdBody")
public class WithIdBody<ID extends Serializable> implements Body {

  /**
   * ID
   */
  @ApiModelProperty(value = "id", notes = "ID")
  private ID id;




  @Override
  @Transient
  @org.springframework.data.annotation.Transient
  @JSONField(serialize = false)
  public int hashCode() {
    return 17 + (isEmpty() ? 0 : getId().hashCode() * 31);
  }


  /**
   * 判断是否为空
   *
   * @return 是否为空
   */
  @Transient
  @org.springframework.data.annotation.Transient
  @JsonIgnore
  @JSONField(serialize = false)
  public boolean isEmpty() {
    return (null == getId());
  }


  /**
   * 判断是否相等
   *
   * @param obj 对象
   * @return 是否相等
   */
  @Override
  @Transient
  @org.springframework.data.annotation.Transient
  @JSONField(serialize = false)
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (isEmpty() || obj == null || !getClass().isAssignableFrom(obj.getClass())) {
      return false;
    }
    AbstractEntity entity = (AbstractEntity) obj;
    if (entity.isEmpty()) {
      return false;
    }
    return getId().equals(entity.getId());
  }


  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "(id=" + this.getId() + ")";
  }
}
