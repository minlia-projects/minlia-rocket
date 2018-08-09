package com.minlia.rocket.security.rebecca.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.minlia.rocket.data.entity.AbstractEntity;
import com.minlia.rocket.enumeration.Status;
import io.swagger.annotations.ApiModelProperty;
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
 * @author rdteam
 */
@Data
@Entity
@Table(name = "user")
@TableName("user")
public class User extends AbstractEntity<Long> {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(value = "用户名")
  @Column(unique = true, nullable = false)
  private String username;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "appid")
  private String appid;

  @ApiModelProperty(value = "guid")
  private String guid;

  @ApiModelProperty(value = "openid")
  private String openid;

  @ApiModelProperty(value = "昵称")
  private String nickName;

  @ApiModelProperty(value = "手机")
  private String mobile;

  @ApiModelProperty(value = "邮件")
  private String email;

  @ApiModelProperty(value = "地址")
  private String address;

  @ApiModelProperty(value = "0女 1男 2保密")
  @Column(length = 1)
  private Integer gender;

  @ApiModelProperty(value = "用户头像")
  @Column(length = 1000)
  private String avatar = "https://s1.minlia.com/2018/06/19/avatar.png";

  @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
  private Integer type = 0;

  @Enumerated(value = EnumType.STRING)
  @ApiModelProperty(value = "Enabled or Disabled")
  private Status status = Status.DISABLED;

  @ApiModelProperty(value = "描述/详情/备注")
  private String description;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "用户拥有角色")
  private List<Role> roles;

  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "用户拥有的权限")
  private List<Permission> permissions;
}