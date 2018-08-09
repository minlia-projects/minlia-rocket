package com.minlia.rocket.stateful.body;

import com.minlia.rocket.stateful.body.impl.FailureResponseBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiModel;

/**
 * @author will
 */
@ApiModel(value = "ApiResponseBody", description = "ApiResponseBody")
public class ApiResponseBody<T> extends StatefulBody {

  public ApiResponseBody() {
    super();
  }

  public ApiResponseBody(Integer code, Integer status, String message, T payload) {
    super(code, status, message, payload);
  }

}
