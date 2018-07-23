package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.loggable.annotation.Loggable;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 如果可以在接口上进行RequestMapping相关的注解的话， 可以抽离成各种不同的接口
 *
 * @author will
 * @since 2.0.3
 */
@Loggable
public interface AbstractEndpoint<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody> extends
    CreationEndpoint<ENTITY, ID>
    , FindOneByIdEndpoint<ENTITY, ID>
    , FindListByConditionEndpoint<ENTITY, QUERY>
    , FindPaginatedByConditionEndpoint<ENTITY, QUERY>
    , UpdateableEndpoint<ENTITY, ID>
    , DeleteByIdsEndpoint<ENTITY, ID>
    , DeleteByConditionEndpoint<ENTITY, QUERY>
    , ExistsByConditionEndpoint<ENTITY, QUERY>
    , CountByConditionEndpoint<ENTITY, QUERY> {

  /**
   * 获取service
   */
  @Override
  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();


  /**
   * 获取条件化的服务
   */
  @Override
  @Autowired
  public abstract ConditionalService<ENTITY, QUERY> getConditionalService();

  //以下方法已抽离成多个接口

//  @Override
//  @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Create")
//  public ResponseEntity<StatefulBody<ENTITY>> create(@RequestBody ENTITY entity) {
//    ENTITY created = getRawService().save(entity);
//    return Responses.created(SuccessResponseBody.builder()
//        .payload(created).build());
//  }
//
//  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Find one by id")
//  public ResponseEntity<StatefulBody<ENTITY>> findOne(@PathVariable ID id) {
//    ENTITY entity = getRawService().findOne(id);
//    return Responses.ok(SuccessResponseBody.builder()
//        .payload(entity).build());
//  }
//
//  @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Update")
//  public ResponseEntity<StatefulBody<ENTITY>> update(@RequestBody ENTITY entity) {
//    ENTITY updated = getRawService().update(entity);
//    return Responses.ok(SuccessResponseBody.builder()
//        .payload(updated).build());
//  }
//
//  @DeleteMapping(value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Delete one")
//  public ResponseEntity<StatefulBody<ENTITY>> deleteOne(@PathVariable ID id) {
//    getRawService().deleteOne(id);
//    return Responses.noContent(SuccessResponseBody.builder().build());
//  }
//
//  @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Delete by conditions")
//  public ResponseEntity<StatefulBody<ENTITY>> delete(@RequestBody WithIdItemBody requestBody) {
//    if (null != requestBody && null != requestBody.getItems()) {
//      for (WithIdBody withIdBody : requestBody.getItems()) {
//        getRawService().deleteOne((ID) withIdBody.getId());
//      }
//    }
//    return Responses.noContent(SuccessResponseBody.builder().build());
//  }
//
//  @PostMapping(value = "/countByCondition", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Count by conditions")
//  public ResponseEntity<StatefulBody<WithResultBody<Long>>> countByCondition(@RequestBody QUERY requestBody) {
//    WithResultBody<Long> body = new WithResultBody();
//    body.setResult(getReadonlyService().countByCondition(requestBody));
//    return Responses.ok(SuccessResponseBody.builder()
//        .payload(body).build());
//  }
//
//  @PostMapping(value = "/existsByCondition", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Exists by conditions")
//  public ResponseEntity<StatefulBody<WithResultBody<Boolean>>> existsByCondition(
//      @RequestBody QUERY requestBody) {
//    WithResultBody<Boolean> body = new WithResultBody();
//    body.setResult(getReadonlyService().existsByCondition(requestBody));
//    return Responses.ok(SuccessResponseBody.builder()
//        .payload(body).build());
//  }
//
//
//  /**
//   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
//   */
//  @PostMapping(value = "/findAllByCondition", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Find all by conditions with paginated result")
//  public ResponseEntity<StatefulBody<PageResponseBody<ENTITY>>> findAllByCondition(
//      @RequestBody QUERY requestBody,
//      @PageableDefault Pageable pageable) {
//    return Responses.ok(SuccessResponseBody.builder()
//        .payload(getReadonlyService().findAllByCondition(requestBody, pageable)).build());
//  }
//
//  /**
//   * 使用  @Pretend(value = "**,-payload.items.code") 进行结果排除，不需要此字段在前端展示
//   */
//  @PostMapping(value = "/findAllList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//  @ApiOperation(value = "Find all by conditions with list result")
//  public ResponseEntity<StatefulBody<List<ENTITY>>> findAllList(@RequestBody QUERY requestBody) {
//    return Responses.ok(SuccessResponseBody.builder()
//        .payload(getReadonlyService().findAllByCondition(requestBody)).build());
//  }

}
