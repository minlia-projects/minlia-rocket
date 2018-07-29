package com.minlia.rocket.security.rebecca.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.minlia.rocket.data.entity.AbstractEntity;
import com.minlia.rocket.enumeration.Status;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 * 菜单/权限
 *
 * @author rdteam
 */
@Data
@Entity
@Table(name = "permission")
@TableName("permission")
public class Permission extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(value = "菜单/权限名称")
  private String name;

  @ApiModelProperty(value = "层级")
  private Integer level;

  @ApiModelProperty(value = "类型 0页面 1具体操作")
  private Integer type;

  @ApiModelProperty(value = "菜单标题")
  private String title;

  @ApiModelProperty(value = "页面路径/资源链接url")
  @Column(nullable = false)
  private String path;

  @ApiModelProperty(value = "前端组件")
  private String component;

  @ApiModelProperty(value = "图标")
  private String icon;

  @ApiModelProperty(value = "按钮权限类型")
  private String buttonType;

  @ApiModelProperty(value = "父id")
  private Integer parentId;

  @ApiModelProperty(value = "说明备注")
  private String description;

  @ApiModelProperty(value = "排序值")
  @Column(precision = 10, scale = 2)
  private BigDecimal sortOrder;

//  @ApiModelProperty(value = "是否启用 0启用 1禁用")
//  private DataStatus status = CommonConstant.STATUS_NORMAL;


  @Enumerated(value = EnumType.STRING)
  @ApiModelProperty(value = "Enabled or Disabled")
  private Status status = Status.DISABLED;


  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "子菜单/权限")
  private List<Permission> children;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "页面拥有的按钮类型")
  private List<String> buttonTypes;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "节点展开 前端所需")
  private Boolean expand = true;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "是否勾选 前端所需")
  private Boolean checked = false;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "是否选中 前端所需")
  private Boolean selected = false;
}