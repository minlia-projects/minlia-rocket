package com.minlia.rocket.data.interfaces;

import com.minlia.rocket.data.body.ToggleRequestBody;
import java.io.Serializable;

public interface IOperations<ENTITY extends Serializable, ID extends Serializable> {

  // find - one

  ENTITY findOne(final ID id);

  // save

  ENTITY save(final ENTITY resource);

  // save all

  Iterable<ENTITY> saveAll(Iterable<ENTITY> resources);

  // update

  ENTITY update(final ENTITY resource);

//  // update
//
//  WithResultBody<ENTITY> enable(final ID id);

  // delete

  Boolean deleteOne(final ID id);

  // delete all

  Boolean deleteAll(Iterable<ID> ids);

  // countByCondition

  Long count();

  Boolean exists(final ID id);


  ENTITY toggle(ToggleRequestBody<ID> toggleRequestBody);
}
