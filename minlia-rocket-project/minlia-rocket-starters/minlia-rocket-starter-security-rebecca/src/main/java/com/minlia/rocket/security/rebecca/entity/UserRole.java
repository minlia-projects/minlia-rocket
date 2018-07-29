package com.minlia.rocket.security.rebecca.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.minlia.rocket.data.entity.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 * @author rdteam
 */
@Data
@Entity
@Table(name = "map_user_role")
@TableName("map_user_role")
public class UserRole extends AbstractEntity {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ApiModelProperty(value = "用户唯一id")
  private String userId;

  @ApiModelProperty(value = "角色唯一id")
  private String roleId;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "角色名")
  private String roleName;
}
