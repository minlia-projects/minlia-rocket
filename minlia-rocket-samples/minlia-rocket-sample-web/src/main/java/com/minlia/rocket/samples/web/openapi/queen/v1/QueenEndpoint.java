package com.minlia.rocket.samples.web.openapi.queen.v1;

import com.minlia.rocket.abstraction.endpoint.AbstractEndpoint;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.samples.web.openapi.queen.v1.body.QueenQueryRequestBody;
import com.minlia.rocket.samples.web.openapi.queen.v1.entity.Queen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/v1/queen")
public class QueenEndpoint implements AbstractEndpoint<Queen, Long, QueenQueryRequestBody> {

  @Autowired
  private QueenService queenService;

  @Override
  public IRawService<Queen, Long> getRawService() {
    return queenService;
  }

  @Override
  public ConditionalService<Queen, QueenQueryRequestBody> getConditionalService() {
    return queenService;
  }
}

