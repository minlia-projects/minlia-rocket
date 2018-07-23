package com.minlia.rocket.autoconfigure.problem;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minlia.rocket.problem.ApiExceptionHandler;
import com.minlia.rocket.problem.ErrorAttributes;
import com.minlia.rocket.problem.Intrinsics;
import com.minlia.rocket.problem.ProblemProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
@ConditionalOnWebApplication(type = SERVLET)
@EnableConfigurationProperties(value = {ProblemProperties.class})
public class ApiProblemAutoConfiguration {


  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter(
        new ObjectMapper().registerModule(new ProblemModule()));
  }

  @Bean
  @ConditionalOnMissingBean
  public ApiExceptionHandler apiExceptionHandler() {
    return new ApiExceptionHandler();
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
