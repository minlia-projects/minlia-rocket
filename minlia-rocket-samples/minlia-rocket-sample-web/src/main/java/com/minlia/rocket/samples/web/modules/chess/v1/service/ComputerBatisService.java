package com.minlia.rocket.samples.web.modules.chess.v1.service;

import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisService;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerPageableQueryRequestBody;
/**
 * @description: 服务类
 * @copyright:  2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
public interface ComputerBatisService extends AbstractBatisService<Computer,Long,ComputerQueryRequestBody,ComputerPageableQueryRequestBody> {
	
}
