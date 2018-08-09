package com.minlia.rocket.samples.web.modules.chess.v1.service;

import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import com.minlia.rocket.samples.web.modules.chess.v1.dao.ComputerDao;
import com.minlia.rocket.samples.web.modules.chess.v1.service.ComputerBatisService;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisServiceImpl;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.v1.body.ComputerPageableQueryRequestBody;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.minlia.rocket.data.batis.abstraction.AbstractMapper;

/**
 * @description: 服务实现类
 * @copyright:  2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
@Service
public class ComputerBatisServiceImpl extends AbstractBatisServiceImpl<Computer,Long,ComputerQueryRequestBody,ComputerPageableQueryRequestBody> implements ComputerBatisService {

@Autowired
private ComputerDao dao;

@Override
public AbstractMapper<Computer> getBatisDao() {
    return dao;
}
}
