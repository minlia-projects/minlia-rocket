package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.WithResultBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FunctionalInterface
public interface DeleteByConditionEndpoint<ENTITY extends Serializable, QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  //TODO 添加权限点控制
  @Loggable
  @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(value = "Delete by conditions")
  public default ResponseEntity<StatefulBody<ENTITY>> delete(
      @RequestBody QUERY requestBody) {
    WithResultBody<Integer> withResultBody = new WithResultBody<>();
    withResultBody.setResult(getConditionalService().deleteByCondition(requestBody));
    return Responses.ok(SuccessResponseBody.builder().payload(withResultBody).build());
  }
}
