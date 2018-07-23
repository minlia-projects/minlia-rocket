package com.minlia.rocket.data.batis.abstraction.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.batis.abstraction.AbstractMapper;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.data.interfaces.IRawService;
import java.io.Serializable;

/**
 * @author will
 * @since 2.0.3
 */
public interface AbstractBatisService<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody> extends
    //with find service support
    ConditionalService<ENTITY, QUERY>, IRawService<ENTITY, ID> {

  public AbstractMapper<ENTITY> getBatisDao();

  /**
   * 搜索条件应该由后台服务控制，所以都在实现类里面进行条件组装
   */
  public default EntityWrapper<ENTITY> getFindAllSpecification(QUERY queryRequestBody) {
    return null;
  }

  public default EntityWrapper<ENTITY> getExistsSpecification(
      QUERY queryRequestBody) {
    return null;
  }

  public default EntityWrapper<ENTITY> getCountSpecification(
      QUERY queryRequestBody) {
    return null;
  }

  public default EntityWrapper<ENTITY> getDeleteByConditionSpecification(
      QUERY queryRequestBody) {
    return null;
  }


}
