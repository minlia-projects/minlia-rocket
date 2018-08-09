package com.minlia.rocket.samples.web.modules.chess.service;

import com.minlia.rocket.samples.web.modules.chess.body.MerchantPageableQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.samples.web.modules.chess.body.MerchantQueryRequestBody;

/**
 * @description:  Jpa 服务
 * @copyright:  2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
public interface MerchantJpaService extends AbstractJpaService<Merchant, Long, MerchantQueryRequestBody,MerchantPageableQueryRequestBody> {

}