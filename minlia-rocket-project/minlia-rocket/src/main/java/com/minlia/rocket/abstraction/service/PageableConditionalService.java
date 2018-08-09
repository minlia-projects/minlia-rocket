package com.minlia.rocket.abstraction.service;

import com.minlia.rocket.data.body.PageResponseBody;
import com.minlia.rocket.data.body.PageableQueryRequestBody;
import org.springframework.data.domain.Pageable;

/**
 * An abstract find service with paginated result
 *
 * @author will
 * @since 2.0.3
 */
public interface PageableConditionalService<ENTITY, PAGEABLE_QUERY extends PageableQueryRequestBody> {

  /**
   * paginated returning according query request body and pageable
   */
  public PageResponseBody<ENTITY> findAllByCondition(PAGEABLE_QUERY queryRequestBody, Pageable pageable);

}
