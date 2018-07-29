package com.minlia.rocket.data.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.minlia.rocket.data.enumeration.DataStatusEnumeration;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonAutoDetect(
//        fieldVisibility = JsonAutoDetect.Visibility.ANY,
//        getterVisibility = JsonAutoDetect.Visibility.NONE,
//        setterVisibility = JsonAutoDetect.Visibility.NONE,
//        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
//        creatorVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<ID extends Serializable> extends WithIdEntity<ID> {

  private static final long serialVersionUID = 1L;

  @CreatedBy
  @ApiModelProperty(value = "Created by")
  private String createdBy;

  //  @NotNull
  @CreatedDate
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "Created date")
  private Date createdDate;

  @LastModifiedBy
  @ApiModelProperty(value = "Last modified by")
  private String lastModifiedBy;

  @LastModifiedDate
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "Last modified date")
  private Date lastModifiedDate;


  @TableLogic
  @ApiModelProperty(value = "Data status")
  @Enumerated(value = EnumType.STRING)
  private DataStatusEnumeration dataStatus;


  /**
   * By default set data status to NORMAL
   */
  @PrePersist
  public void prePersist() {
    this.setDataStatus(DataStatusEnumeration.NORMAL);
    this.setCreatedDate(new Date());
    this.setLastModifiedDate(new Date());
    //TODO set as guid of current user
    this.setCreatedBy("//TODO as guid");
  }

  /**
   * TODO The situation of mybatis needs to be considered
   */
  @PreUpdate
  public void preUpdate() {
    this.setLastModifiedDate(new Date());
    //TODO set as guid of current user
    this.setLastModifiedBy("//TODO as guid");
  }


  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public DataStatusEnumeration getDataStatus() {
    return dataStatus;
  }

  public void setDataStatus(DataStatusEnumeration dataStatus) {
    this.dataStatus = dataStatus;
  }
}