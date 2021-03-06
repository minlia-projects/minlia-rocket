package com.minlia.rocket.autoconfigure.problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minlia.rocket.problem.ApiExceptionHandler;
import com.minlia.rocket.problem.ErrorAttributes;
import com.minlia.rocket.problem.Intrinsics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StopWatch;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

/**
 * @author will
 */
@Configuration
@ConditionalOnClass(value = {ConstraintViolationProblemModule.class, ProblemHandling.class,
    ProblemModule.class,Intrinsics.class,AbstractThrowableProblem.class})
@Slf4j
public class ApiProblemAutoConfiguration {


  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter(
        new ObjectMapper().registerModule(new ProblemModule()));
  }

  @Bean
  @ConditionalOnMissingBean
  public ApiExceptionHandler apiExceptionHandler() {
    log.debug("Starting api problem configuration");
    StopWatch watch = new StopWatch();
    watch.start();
    ApiExceptionHandler apiExceptionHandler= new ApiExceptionHandler();
    watch.stop();
    log.debug("Finishing api problem configuration in {} ms", watch.getTotalTimeMillis());
    return apiExceptionHandler;
  }


  @Bean
  @ConditionalOnMissingBean
  public ErrorAttributes errorAttributes() {
    return new ErrorAttributes();
  }


  /**
   * Module for serialization/deserialization of ConstraintViolationProblem.
   */
  @Bean
  @ConditionalOnMissingBean
  ConstraintViolationProblemModule constraintViolationProblemModule() {
    return new ConstraintViolationProblemModule();
  }

}
