//package com.minlia.rocket.data.generator.endpoint;
//
//import com.minlia.rocket.loggable.annotation.Loggable;
//import com.minlia.rocket.property.SystemProperties;
//import com.minlia.rocket.stateful.Responses;
//import com.minlia.rocket.stateful.body.StatefulBody;
//import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
//import com.minlia.rocket.swagger.annotation.ApiOperationSince;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/api/v1/data/code/generation")
//@ApiOperation(value = "Code Generation Endpoint", tags = "Code Generation", notes = "Code Generation Endpoint")
//@Slf4j
//public class CodeGenerationV1Endpoint {
//
//
//  @Autowired
//  private SystemProperties systemProperties;
//
//  @PostMapping
//  @Loggable
//  @ApiOperationSince(value = "1.0.0")
//  @ApiOperation(value = "Code Generation", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//  @Deprecated
//  public @ResponseBody
//  ResponseEntity<StatefulBody> generation() {
//    return Responses.ok(SuccessResponseBody.builder().build());
//  }
//}
