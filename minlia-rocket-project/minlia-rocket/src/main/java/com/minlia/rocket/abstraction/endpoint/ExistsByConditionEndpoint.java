package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.WithResultBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
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
public interface ExistsByConditionEndpoint<ENTITY extends Serializable, QUERY extends QueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/existsByConditions", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(nickname = "existsByConditions",value = "Exists by conditions",httpMethod = "POST")//,produces =MediaType.APPLICATION_JSON_UTF8_VALUE
  @ResponseStatus(value = HttpStatus.OK)
  public default ResponseEntity<StatefulBody<WithResultBody<Boolean>>> existsByConditions(
      @RequestBody QUERY requestBody) {
    WithResultBody<Boolean> body = new WithResultBody();
    body.setResult(getConditionalService().existsByCondition(requestBody));
    return Responses.ok(SuccessResponseBody.builder()
        .payload(body).build());
  }


}
