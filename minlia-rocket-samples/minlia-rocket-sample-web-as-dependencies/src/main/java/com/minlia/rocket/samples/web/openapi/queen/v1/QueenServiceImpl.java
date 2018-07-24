package com.minlia.rocket.samples.web.openapi.queen.v1;

import com.minlia.rocket.data.batis.abstraction.AbstractMapper;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisServiceImpl;
import com.minlia.rocket.samples.web.openapi.queen.v1.body.QueenQueryRequestBody;
import com.minlia.rocket.samples.web.openapi.queen.v1.dao.QueenDao;
import com.minlia.rocket.samples.web.openapi.queen.v1.entity.Queen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueenServiceImpl extends AbstractBatisServiceImpl<Queen,Long,QueenQueryRequestBody> implements QueenService {

  @Autowired
  private QueenDao queenDao;

  @Override
  public AbstractMapper<Queen> getBatisDao() {
    return queenDao;
  }
}