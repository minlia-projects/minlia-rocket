package com.minlia.rocket.samples.web.endpoint;

import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.i18n.system.SystemMessageSource;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.problem.ApiPreconditions;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;

@RestController
@RequestMapping(value = "api/v1/open/test")
@ApiOperation(value = "Test Endpoint",tags = "Test",notes = "Test Endpoint")
@Slf4j
public class ApiProblemTestEndpoint {

  @GetMapping
  @Loggable
  public String ok() {
    ApplicationContext ac = ContextHolder.getContext();
    log.debug("ContextHolder with context: {}", ac);
    return "OK";
  }

  @GetMapping(value = "translationRefresh")
  @Loggable
  public String translationRefresh() {
    ApplicationContext ac = ContextHolder.getContext();
    SystemMessageSource messageSource= (SystemMessageSource) ac.getBean(MessageSource.class);
    log.debug("MessageSource reload: {}", messageSource);
    messageSource.reload();
    return "OK";
  }

  /**
   * excepted status 417 with code 4104
   *
   * @return
   */
  @GetMapping(value = "417")
  @Loggable
  public String exception() {
    ApiPreconditions.throwException(4104,Status.OK,new Object[]{"参数1","参数2"});
//    ApiPreconditions.is(true,41044,(HttpStatus)Status.OK,new Object[]{});
    return "OK";
  }

  /**
   * excepted status 417 with code 4104
   *
   * @return
   */
  @GetMapping(value = "4172")
  @Loggable
  public String exception2() {
    ApiPreconditions.throwException(41042,Status.EXPECTATION_FAILED,new Object[]{"我就是参数1的内容","我就是参数2的内容"});
//    ApiPreconditions.throwException(41042);
    return "OK";
  }

}