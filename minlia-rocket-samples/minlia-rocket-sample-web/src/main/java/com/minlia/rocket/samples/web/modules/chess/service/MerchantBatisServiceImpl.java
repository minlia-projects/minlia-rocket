package com.minlia.rocket.samples.web.modules.chess.service;

import com.minlia.rocket.samples.web.modules.chess.body.MerchantPageableQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import com.minlia.rocket.samples.web.modules.chess.dao.MerchantDao;
import com.minlia.rocket.samples.web.modules.chess.service.MerchantBatisService;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisServiceImpl;
import com.minlia.rocket.samples.web.modules.chess.body.MerchantQueryRequestBody;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.minlia.rocket.data.batis.abstraction.AbstractMapper;

/**
 * @description: 服务实现类
 * @copyright:  2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
@Service
public class MerchantBatisServiceImpl extends AbstractBatisServiceImpl<Merchant,Long,MerchantQueryRequestBody,MerchantPageableQueryRequestBody> implements MerchantBatisService {

@Autowired
private MerchantDao dao;

@Override
public AbstractMapper<Merchant> getBatisDao() {
    return dao;
}
}
