package com.minlia.rocket.samples.web.modules.chess.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.minlia.rocket.data.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @description:
 * @copyright: 2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
@TableName(value = "merchant", resultMap = "BaseResultMap")
@Table(name = "merchant")
@Entity
public class Merchant extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.ID_WORKER)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String content;

  private String name;

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
