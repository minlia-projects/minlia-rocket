package com.minlia.rocket.samples.web.modules.chess.service;

import com.minlia.rocket.samples.web.modules.chess.body.MerchantPageableQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisService;
import com.minlia.rocket.samples.web.modules.chess.body.MerchantQueryRequestBody;
/**
 * @description: 服务类
 * @copyright:  2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
public interface MerchantBatisService extends AbstractBatisService<Merchant,Long,MerchantQueryRequestBody,MerchantPageableQueryRequestBody> {
	
}
