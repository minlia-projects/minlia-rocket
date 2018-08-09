package com.minlia.rocket.abstraction.endpoint;

import com.google.common.collect.Lists;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.abstraction.service.PageableConditionalService;
import com.minlia.rocket.data.body.PageRequestBody;
import com.minlia.rocket.data.body.PageResponseBody;
import com.minlia.rocket.data.body.PageableQueryRequestBody;
import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.data.body.Sorting;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ApiResponses(value = {
//    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
//    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
//    @ApiResponse(code = 400, message = "Bad Request", response = FailureResponseBody.class),
//    @ApiResponse(code = 401, message = "Unauthorized", response = FailureResponseBody.class),
//    @ApiResponse(code = 403, message = "Forbidden", response = FailureResponseBody.class),
//    @ApiResponse(code = 404, message = "Not Found", response = FailureResponseBody.class),
//    @ApiResponse(code = 417, message = "Expectation Failure", response = FailureResponseBody.class),
//})
@FunctionalInterface
public interface FindPaginatedByConditionEndpoint<ENTITY extends Serializable, PAGEABLE_QUERY extends PageableQueryRequestBody> {

  @Autowired
  public abstract PageableConditionalService<ENTITY, PAGEABLE_QUERY> getPageableConditionalService();

  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/findPaginated", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(nickname = "findPaginated",value = "Find all by conditions with paginated result",httpMethod = "POST" )//,produces =MediaType.APPLICATION_JSON_UTF8_VALUE
  @ResponseStatus(value = HttpStatus.OK)
  public default ResponseEntity<StatefulBody<PageResponseBody<ENTITY>>> findPaginated(
      @RequestBody PAGEABLE_QUERY requestBody
  ) {
    PageRequestBody pageRequestBody= requestBody.getPageable();
    //将自定义可以序列化的Sort转换为DataJpa所需的Sort
    List<Order> orders= Lists.newArrayList();
    if((null!=pageRequestBody.getSort()) && pageRequestBody.getSort().size()>0) {
      for(Sorting sort:pageRequestBody.getSort()) {
        String sortBy=sort.getProperty();
        if (sortBy != null) {
          orders.add(new Sort.Order(sort.getDirection(), sortBy));
        }
      }
    }

    Sort sort= Sort.by(orders);
    PageRequest pageRequest=new PageRequest(pageRequestBody.getPage(),pageRequestBody.getSize(),sort);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(getPageableConditionalService().findAllByCondition(requestBody, pageRequest)).build());
  }


}
