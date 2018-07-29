package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.WithIdBody;
import com.minlia.rocket.stateful.body.WithIdItemBody;
import com.minlia.rocket.stateful.body.impl.FailureResponseBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
    @ApiResponse(code = 400, message = "Bad Request", response = FailureResponseBody.class),
    @ApiResponse(code = 401, message = "Unauthorized", response = FailureResponseBody.class),
    @ApiResponse(code = 403, message = "Forbidden", response = FailureResponseBody.class),
    @ApiResponse(code = 404, message = "Not Found", response = FailureResponseBody.class),
    @ApiResponse(code = 417, message = "Expectation Failure", response = FailureResponseBody.class),
})
@FunctionalInterface
public interface DeleteByIdsEndpoint<ENTITY extends Serializable, ID extends Serializable> {

  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();

  //TODO 添加权限点控制
  @Loggable
  @DeleteMapping(value = "/deleteByIds", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Delete by ids")
  public default ResponseEntity<StatefulBody<ENTITY>> delete(
      @RequestBody WithIdItemBody<ID> requestBody) {
    if (null != requestBody && null != requestBody.getItems()) {
      for (WithIdBody withIdBody : requestBody.getItems()) {
        getRawService().deleteOne((ID) withIdBody.getId());
      }
    }
    return Responses.noContent(SuccessResponseBody.builder().build());
  }
}
