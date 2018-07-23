package com.minlia.rocket.data.interfaces;

import java.io.Serializable;

public interface IRawService<ENTITY extends Serializable, ID extends Serializable> extends
    IOperations<ENTITY, ID> {


}