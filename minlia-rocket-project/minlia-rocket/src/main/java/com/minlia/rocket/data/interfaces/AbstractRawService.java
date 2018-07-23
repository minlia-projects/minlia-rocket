package com.minlia.rocket.data.interfaces;

import java.io.Serializable;

public abstract class AbstractRawService<ENTITY extends Serializable, ID extends Serializable> implements
    IRawService<ENTITY, ID> {

}
