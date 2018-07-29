package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
    @ApiResponse(code = 400, message = "Bad Request", response = StatefulBody.class),
    @ApiResponse(code = 401, message = "Unauthorized", response = StatefulBody.class),
    @ApiResponse(code = 403, message = "Forbidden", response = StatefulBody.class),
    @ApiResponse(code = 404, message = "Not Found", response = StatefulBody.class),
    @ApiResponse(code = 417, message = "Expectation Failure", response = StatefulBody.class),
})
@FunctionalInterface
public interface FindOneByIdEndpoint<ENTITY extends Serializable, ID extends Serializable> {

  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();

  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  //TODO 添加权限点控制
  @Loggable
  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Find one by id")
  public default ResponseEntity<StatefulBody<ENTITY>> findOne(@PathVariable ID id) {
    ENTITY entity = getRawService().findOne(id);
    return Responses.ok(SuccessResponseBody.builder()
        .payload(entity).build());
  }
}
