package com.minlia.rocket.data.entity;

import java.io.Serializable;

/**
 * @author will
 */
public interface WithNestedSetEntity<ID extends Serializable> {

  long STEP_OF_INCREASE = 2L;
  long LFT_OF_ROOT = 1L;
  long RT_OF_ROOT = 2L;
  int LEVEL_OF_ROOT = 1;

  ID getParentId();

  void setParentId(ID parentID);

  Long getLft();

  void setLft(Long lft);

  Long getRt();

  void setRt(Long rt);

  Integer getLevel();

  void setLevel(Integer level);

  
}
