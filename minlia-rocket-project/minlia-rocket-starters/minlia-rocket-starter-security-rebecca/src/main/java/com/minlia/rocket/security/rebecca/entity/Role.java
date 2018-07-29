package com.minlia.rocket.security.rebecca.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.minlia.rocket.data.entity.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
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
@Table(name = "role")
@TableName("role")
public class Role extends AbstractEntity {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(value = "角色编码 以ROLE_开头")
  private String code;

  @ApiModelProperty(value = "角色显示名称")
  private String name;


  @ApiModelProperty(value = "是否为注册默认角色")
  private Boolean defaultRole;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "拥有权限")
  private List<Permission> permissions;
}
