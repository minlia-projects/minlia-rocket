package com.minlia.rocket.security.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author will
 */
@Mapper(componentModel = "spring", uses = {})
public interface BuiltinUserMapper {

  BuiltinUserBody entityToModel(DummyUserDetail entity);

  DummyUserDetail modelToEntity(BuiltinUserBody model);

  void updateEntityFromModel(BuiltinUserBody model, @MappingTarget DummyUserDetail entity);

}
