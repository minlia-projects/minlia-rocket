package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.data.body.PageResponseBody;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.FailureResponseBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK", response = StatefulBody.class),
    @ApiResponse(code = 201, message = "Created", response = StatefulBody.class),
    @ApiResponse(code = 400, message = "Bad Request", response = FailureResponseBody.class),
    @ApiResponse(code = 401, message = "Unauthorized", response = FailureResponseBody.class),
    @ApiResponse(code = 403, message = "Forbidden", response = FailureResponseBody.class),
    @ApiResponse(code = 404, message = "Not Found", response = FailureResponseBody.class),
    @ApiResponse(code = 417, message = "Expectation Failure", response = FailureResponseBody.class),
})
@FunctionalInterface
public interface FindPaginatedByConditionEndpoint<ENTITY extends Serializable, QUERY extends AbstractQueryRequestBody> {

  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  /**
   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
   */
  //TODO 添加权限点控制
  @Loggable
  @PostMapping(value = "/findPaginated", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Find all by conditions with paginated result")
//  @ApiImplicitParams({
//      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
//          value = "Results page you want to retrieve (0..N)"),
//      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
//          value = "Number of records per page."),
//      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//          value = "Sorting criteria in the format: property(,asc|desc). " +
//              "Default sort order is ascending. " +
//              "Multiple sort criteria are supported.")
//  })
  public default ResponseEntity<StatefulBody<PageResponseBody<ENTITY>>> findPaginated(
      @RequestBody QUERY requestBody,

      //由于自带的放出去接口不太清晰，需要定义一个，然后在service层进行Pageable转换
     @ApiIgnore @PageableDefault Pageable pageable) {
    return Responses.ok(SuccessResponseBody.builder()
        .payload(getConditionalService().findAllByCondition(requestBody, pageable)).build());
  }
}
