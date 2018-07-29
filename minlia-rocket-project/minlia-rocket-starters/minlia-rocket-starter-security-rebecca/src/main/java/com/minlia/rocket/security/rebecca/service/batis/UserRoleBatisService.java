package com.minlia.rocket.security.rebecca.service.batis;

import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisService;
import com.minlia.rocket.security.rebecca.body.UserRoleQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.Role;
import com.minlia.rocket.security.rebecca.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleBatisService extends
    AbstractBatisService<UserRole, Long, UserRoleQueryRequestBody> {

  /**
   * 通过用户id获取
   */
//    @Cacheable(key = "#userId")
  List<Role> findByUserId(@Param("userId") Long userId);
}
