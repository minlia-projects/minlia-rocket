package com.minlia.rocket.data.interfaces;

import java.io.Serializable;

/**
 * @author will
 */
public interface IService<ENTITY extends Serializable, ID extends Serializable> extends
    IRawService<ENTITY, ID> {

}
