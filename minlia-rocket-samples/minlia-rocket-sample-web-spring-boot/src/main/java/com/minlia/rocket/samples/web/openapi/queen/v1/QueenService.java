package com.minlia.rocket.samples.web.openapi.queen.v1;

import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisService;
import com.minlia.rocket.samples.web.openapi.queen.v1.body.QueenQueryRequestBody;
import com.minlia.rocket.samples.web.openapi.queen.v1.entity.Queen;

public interface QueenService extends AbstractBatisService<Queen, Long, QueenQueryRequestBody> {

}