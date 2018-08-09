package com.minlia.rocket.samples.web.modules.chess.v1.service;

import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.samples.web.modules.chess.v1.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: Jpa服务实现类
 * @copyright:  2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
@Service
public class ComputerJpaServiceImpl implements ComputerJpaService {
  @Autowired
  private ComputerRepository jpaRepository;

  @Override
  public AbstractRepository<Computer, Long> getJpaRepository() {
    return jpaRepository;
  }
}
