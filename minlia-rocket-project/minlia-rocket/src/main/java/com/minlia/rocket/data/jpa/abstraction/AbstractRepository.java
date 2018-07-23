package com.minlia.rocket.data.jpa.abstraction;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 为了适应与Mybatis的Mapper保持统一命名
 *
 * @author will
 * @since 2.0.3
 */
@NoRepositoryBean
public interface AbstractRepository<ENTITY, ID extends Serializable> extends
    JpaRepository<ENTITY, ID>, JpaSpecificationExecutor<ENTITY> {

}
