package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.loggable.annotation.Loggable;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 如果可以在接口上进行RequestMapping相关的注解的话， 可以抽离成各种不同的接口
 *
 * @author will
 * @since 2.0.3
 */
@Loggable
public interface AbstractEndpoint<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody> extends
    CreationEndpoint<ENTITY, ID>
    , FindOneByIdEndpoint<ENTITY, ID>
    , FindListByConditionEndpoint<ENTITY, QUERY>
    , FindPaginatedByConditionEndpoint<ENTITY, QUERY>
    , UpdateableEndpoint<ENTITY, ID>
    , DeleteByIdsEndpoint<ENTITY, ID>
    , DeleteByConditionEndpoint<ENTITY, QUERY>
    , ExistsByConditionEndpoint<ENTITY, QUERY>
    , CountByConditionEndpoint<ENTITY, QUERY> {

  /**
   * 获取service
   */
  @Override
  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();


  /**
   * 获取条件化的服务
   */
  @Override
  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

}
