package com.minlia.rocket.abstraction.service;

import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.data.body.PageResponseBody;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * An abstract find service with paginated result
 *
 * @author will
 * @since 2.0.3
 */
public interface ConditionalService<ENTITY, QUERY extends QueryRequestBody> {

  /**
   * paginated returning according query request body and pageable
   */
//  public PageResponseBody<ENTITY> findAllByCondition(QUERY queryRequestBody, Pageable pageable);

  /**
   * list returning according query request body and pageable
   */
  public List<ENTITY> findAllByCondition(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Long countByCondition(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Boolean existsByCondition(QUERY queryRequestBody);

  /**
   *
   * @param queryRequestBody
   * @return
   */
  public Integer deleteByCondition(QUERY queryRequestBody);

}
