package com.minlia.rocket.pretend.advice;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.bohnman.squiggly.context.provider.SimpleSquigglyContextProvider;
import com.github.bohnman.squiggly.filter.SquigglyPropertyFilter;
import com.github.bohnman.squiggly.parser.SquigglyParser;
import com.minlia.rocket.pretend.annotation.Pretend;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@ControllerAdvice
public class PretendAdvice extends AbstractMappingJacksonResponseBodyAdvice {

  @Override
  protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
      MethodParameter returnType, ServerHttpRequest req, ServerHttpResponse res) {
    Pretend pretend = returnType.getMethodAnnotation(Pretend.class);
    if (pretend != null) {
      SquigglyPropertyFilter propertyFilter = new SquigglyPropertyFilter(
          new SimpleSquigglyContextProvider(new SquigglyParser(), pretend.value()));
      bodyContainer.setFilters(
          new SimpleFilterProvider().addFilter(SquigglyPropertyFilter.FILTER_ID, propertyFilter));
    }
  }

}