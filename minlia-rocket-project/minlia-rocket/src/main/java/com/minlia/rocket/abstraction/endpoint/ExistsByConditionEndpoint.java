package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.WithResultBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public interface ExistsByConditionEndpoint<ENTITY extends Serializable, QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/exists", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Exists by conditions")
  public default ResponseEntity<StatefulBody<WithResultBody<Boolean>>> exists(
      @RequestBody QUERY requestBody) {
    WithResultBody<Boolean> body = new WithResultBody();
    body.setResult(getConditionalService().existsByCondition(requestBody));
    return Responses.ok(SuccessResponseBody.builder()
        .payload(body).build());
  }


}
