package com.minlia.rocket.samples.web.openapi.king.v1;

import com.minlia.rocket.abstraction.endpoint.AbstractEndpoint;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.samples.web.openapi.king.v1.body.KingQueryRequestBody;
import com.minlia.rocket.samples.web.openapi.king.v1.entity.King;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/v1/open/king")
public class KingEndpoint implements AbstractEndpoint<King, Long, KingQueryRequestBody> {

  @Autowired
  private KingService kingService;

  @Override
  public IRawService<King, Long> getRawService() {
    return kingService;
  }

  @Override
  public ConditionalService<King, KingQueryRequestBody> getConditionalService() {
    return kingService;
  }
}

