package com.minlia.rocket.samples.web.modules.chess.service;

import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.samples.web.modules.chess.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: Jpa服务实现类
 * @copyright:  2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
@Service
public class MerchantJpaServiceImpl implements MerchantJpaService {
  @Autowired
  private MerchantRepository jpaRepository;

  @Override
  public AbstractRepository<Merchant, Long> getJpaRepository() {
    return jpaRepository;
  }
}
