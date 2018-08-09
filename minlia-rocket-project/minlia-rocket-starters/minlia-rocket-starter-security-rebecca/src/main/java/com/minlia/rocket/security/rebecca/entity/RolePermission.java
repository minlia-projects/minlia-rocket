package com.minlia.rocket.security.rebecca.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.minlia.rocket.data.entity.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author rdteam
 */
@Data
@Entity
@Table(name = "map_role_permission")
@TableName("map_role_permission")
public class RolePermission extends AbstractEntity<Long> {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(value = "角色id")
  private Long roleId;

  @ApiModelProperty(value = "权限id")
  private Long permissionId;
}