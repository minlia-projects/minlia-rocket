package com.minlia.rocket.samples.web.showcases.contextholder.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.samples.web.showcases.contextholder.body.ShowcaseContextHolderRequestBody;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 */
@Slf4j
@RestController
@Api(value = "Features Context Holder Body Api", tags = "Minlia-Rocket-Features")
@RequestMapping(value = "/xapi/v1/showcases")
public class ContextHolderEndpoint {

  @Loggable
  @ApiOperation(value = "contextHolder", consumes = APPLICATION_JSON_VALUE)
  @PostMapping(value = "contextHolder", consumes = {APPLICATION_JSON_VALUE})
  public ResponseEntity<StatefulBody> contextHolder(
      @Valid @RequestBody ShowcaseContextHolderRequestBody body) {


    log.debug("ContextHolder: {}",ContextHolder.getContext());

    return Responses.ok(SuccessResponseBody.builder().payload(body).build());
  }

}
