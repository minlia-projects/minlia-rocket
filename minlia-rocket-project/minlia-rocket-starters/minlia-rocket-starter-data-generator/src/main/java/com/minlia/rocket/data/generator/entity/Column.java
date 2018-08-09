package com.minlia.rocket.data.generator.entity;

import lombok.Data;

@Data
public class Column {

  private String columnName;
  private String dataType;
  private String columnComment;
  private String isNullable;
  private Integer ordinalPosition;
  private String extra;
  private Integer characterMaximumLength;
  private String columnDefault;

}
