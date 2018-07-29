package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.body.StatefulBody;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 如果可以在接口上进行RequestMapping相关的注解的话， 可以抽离成各种不同的接口
 *
 * @author will
 * @since 2.0.3
 */
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
    @ApiResponse(code = 400, message = "Bad Request", response = StatefulBody.class),
    @ApiResponse(code = 401, message = "Unauthorized", response = StatefulBody.class),
    @ApiResponse(code = 403, message = "Forbidden", response = StatefulBody.class),
    @ApiResponse(code = 404, message = "Not Found", response = StatefulBody.class),
    @ApiResponse(code = 417, message = "Expectation Failure", response = StatefulBody.class),
})
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
