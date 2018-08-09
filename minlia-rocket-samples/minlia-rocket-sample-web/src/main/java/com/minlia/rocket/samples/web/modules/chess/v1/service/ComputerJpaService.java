package com.minlia.rocket.samples.web.modules.chess.v1.service;

import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerPageableQueryRequestBody;

/**
 * @description:  Jpa 服务
 * @copyright:  2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
public interface ComputerJpaService extends AbstractJpaService<Computer, Long, ComputerQueryRequestBody, ComputerPageableQueryRequestBody> {

}