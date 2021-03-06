package com.minlia.rocket.samples.web;

import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.problem.ApiPreconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/open/test")
@Slf4j
public class TestEndpoint {

  @GetMapping
  @Loggable
  public String ok() {
    ApplicationContext ac = ContextHolder.getContext();
    log.debug("ContextHolder with context: {}", ac);
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
    ApiPreconditions.throwException(4104);
    return "OK";
  }

}