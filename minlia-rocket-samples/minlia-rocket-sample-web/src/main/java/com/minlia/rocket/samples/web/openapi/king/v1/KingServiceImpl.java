package com.minlia.rocket.samples.web.openapi.king.v1;

import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.samples.web.openapi.king.v1.entity.King;
import com.minlia.rocket.samples.web.openapi.king.v1.repository.KingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingServiceImpl implements KingService {

  @Autowired
  private KingRepository kingRepository;

  @Override
  public AbstractRepository<King, Long> getJpaRepository() {
    return kingRepository;
  }
}