package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.abstraction.service.PageableConditionalService;
import com.minlia.rocket.data.body.PageableQueryRequestBody;
import com.minlia.rocket.data.body.QueryRequestBody;
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
//@ApiResponses(value = {
//    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
//    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
//    @ApiResponse(code = 400, message = "Bad Request", response = FailureResponseBody.class),
//    @ApiResponse(code = 401, message = "Unauthorized", response = FailureResponseBody.class),
//    @ApiResponse(code = 403, message = "Forbidden", response = FailureResponseBody.class),
//    @ApiResponse(code = 404, message = "Not Found", response = FailureResponseBody.class),
//    @ApiResponse(code = 417, message = "Expectation Failure", response = FailureResponseBody.class),
//})

@Loggable
public interface AbstractEndpoint<ENTITY extends Serializable, ID extends Serializable, QUERY extends QueryRequestBody, PAGEABLE_QUERY extends PageableQueryRequestBody> extends
    MockDataGenerationEndpoint<ENTITY, ID>,
    CreationEndpoint<ENTITY, ID>
    , FindOneByIdEndpoint<ENTITY, ID>
    , FindListByConditionEndpoint<ENTITY, QUERY>
    , FindPaginatedByConditionEndpoint<ENTITY, PAGEABLE_QUERY>
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


  /**
   * 获取带分页的条件化服务
   */
  @Override
  @Autowired
  public abstract PageableConditionalService<ENTITY, PAGEABLE_QUERY> getPageableConditionalService();

}
