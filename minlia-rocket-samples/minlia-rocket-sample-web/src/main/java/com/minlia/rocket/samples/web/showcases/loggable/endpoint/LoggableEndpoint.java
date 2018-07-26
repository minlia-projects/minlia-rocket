package com.minlia.rocket.samples.web.showcases.loggable.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.samples.web.showcases.loggable.body.ShowcaseLoggableRequestBody;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 */
@RestController
@Api(value = "Features Loggable Api", tags = "Minlia-Rocket-Features")
@RequestMapping(value = "/xapi/v1/showcases")
public class LoggableEndpoint {

  @Loggable
  @ApiOperation(value = "loggable",consumes =APPLICATION_JSON_VALUE)
  @PostMapping(value = "loggable",consumes = {APPLICATION_JSON_VALUE})
  public ResponseEntity<StatefulBody> loggable(@Valid @RequestBody ShowcaseLoggableRequestBody body) {
    return Responses.ok(SuccessResponseBody.builder().payload(body).build());
  }
}
