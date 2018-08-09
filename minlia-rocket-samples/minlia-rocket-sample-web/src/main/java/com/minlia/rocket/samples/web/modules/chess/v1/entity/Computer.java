package com.minlia.rocket.samples.web.modules.chess.v1.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.minlia.rocket.data.entity.AbstractEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @description: 
 * @copyright:  2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
@TableName(value="computer",resultMap="BaseResultMap")
@Table(name = "computer")
@Entity
public class Computer extends AbstractEntity {

    private static final long serialVersionUID = 1L;

@TableId(value="id", type= IdType.AUTO)
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private String name;

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