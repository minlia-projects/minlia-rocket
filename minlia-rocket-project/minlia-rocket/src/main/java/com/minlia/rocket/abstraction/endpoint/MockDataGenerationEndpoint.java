package com.minlia.rocket.abstraction.endpoint;

import com.minlia.rocket.constants.EnvironmentProperties;
import com.minlia.rocket.data.entity.WithIdEntity;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.loggable.annotation.Loggable;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author will
 */
@FunctionalInterface
public interface MockDataGenerationEndpoint<ENTITY extends Serializable, ID extends Serializable> {

  @Autowired
  public abstract IRawService<ENTITY, ID> getRawService();

  //TODO 添加权限点控制


  /**
   * 非生产环境时可用
   * @param entity
   * @return
   */
  @Profile(value = EnvironmentProperties.NONE_PRODUCTION)

  @Loggable
  @PostMapping(value = "/mockDataGeneration", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ApiOperation(nickname = "mockDataGeneration",value = "mockDataGeneration",httpMethod = "POST")//,produces =MediaType.APPLICATION_JSON_UTF8_VALUE
  @ResponseStatus(value = HttpStatus.CREATED)
  public default ResponseEntity<StatefulBody> generation(@RequestBody ENTITY entity) {

//    WithIdItemBody withIdItemBody=new WithIdItemBody();
//    List<WithIdBody<ENTITY>> items= Lists.newArrayList();

    for(Integer i =0;i< 10;i++) {

      EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom();

      ENTITY entityGenerated = (ENTITY)enhancedRandom.nextObject(entity.getClass());

      beforeMocked(entityGenerated);
      WithIdEntity<ID> idEntity=(WithIdEntity<ID>)entityGenerated;

      idEntity.setId(null);

      ENTITY entityGeneratedWithIdChanged=(ENTITY)idEntity;

      ENTITY created = getRawService().save(entityGeneratedWithIdChanged);

//      WithIdBody withIdBody=new WithIdBody();
//
//      WithIdEntity<ID> idEntity=(WithIdEntity<ID>)created;
//
//      withIdBody.setId(idEntity.getId());
//
//      items.add(withIdBody);
    }

//    withIdItemBody.setItems(items);

//    WithResultBody<String> withResultBody=new WithResultBody<>();
//    withResultBody.set

    return Responses.created(SuccessResponseBody.builder().build());
  }


  default void beforeMocked(ENTITY entityGenerated){

  }
}

