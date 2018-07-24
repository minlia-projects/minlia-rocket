package com.minlia.rocket.samples.web.openapi.king.v1;

import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.samples.web.openapi.king.v1.body.KingQueryRequestBody;
import com.minlia.rocket.samples.web.openapi.king.v1.entity.King;

public interface KingService extends AbstractJpaService<King, Long, KingQueryRequestBody> {

}