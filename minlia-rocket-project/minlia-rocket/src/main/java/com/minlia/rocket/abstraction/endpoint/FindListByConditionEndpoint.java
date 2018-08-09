package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author will
 */ //@ApiResponses(value = {
//    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
//    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
//    @ApiResponse(code = 400, message = "Bad Request", response = FailureResponseBody.class),
//    @ApiResponse(code = 401, message = "Unauthorized", response = FailureResponseBody.class),
//    @ApiResponse(code = 403, message = "Forbidden", response = FailureResponseBody.class),
//    @ApiResponse(code = 404, message = "Not Found", response = FailureResponseBody.class),
//    @ApiResponse(code = 417, message = "Expectation Failure", response = FailureResponseBody.class),
//})
@FunctionalInterface
public interface FindListByConditionEndpoint<ENTITY extends Serializable, QUERY extends QueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/findList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "")
  @ApiOperation(nickname = "findList",value = "Find all by conditions with list result",httpMethod = "POST")//,produces =MediaType.APPLICATION_JSON_UTF8_VALUE
  @ResponseStatus(value = HttpStatus.OK)
  public default ResponseEntity<StatefulBody<List<ENTITY>>> findList(
      @RequestBody QUERY requestBody) {
    return Responses.ok(SuccessResponseBody.builder()
        .payload(getConditionalService().findAllByCondition(requestBody)).build());
  }

}
