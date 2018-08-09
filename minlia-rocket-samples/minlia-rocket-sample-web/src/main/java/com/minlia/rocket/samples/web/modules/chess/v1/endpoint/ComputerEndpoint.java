package com.minlia.rocket.samples.web.modules.chess.v1.endpoint;

import com.minlia.rocket.abstraction.endpoint.AbstractEndpoint;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.abstraction.service.PageableConditionalService;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerPageableQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import com.minlia.rocket.samples.web.modules.chess.v1.service.ComputerBatisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 管理接口
 * @since: 2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1/open/computer")
@Api(tags = "Computer")
public class ComputerEndpoint implements
    AbstractEndpoint<Computer, Long, ComputerQueryRequestBody, ComputerPageableQueryRequestBody> {



  @Autowired
  private ComputerBatisService batisService;


  @Override
  public IRawService<Computer, Long> getRawService() {
    return batisService;
  }

  @Override
  public ConditionalService<Computer, ComputerQueryRequestBody> getConditionalService() {
    return batisService;
  }

  @Override
  public PageableConditionalService<Computer, ComputerPageableQueryRequestBody> getPageableConditionalService() {
    return batisService;
  }

}
