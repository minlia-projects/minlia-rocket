package com.minlia.rocket.data.generator.dao;

import com.minlia.rocket.data.generator.entity.Column;
import com.minlia.rocket.data.generator.entity.Table;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TableDao {

  @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
  List<Table> listTable();

  @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
  List<Column> listTableColumn(String tableName);
}

