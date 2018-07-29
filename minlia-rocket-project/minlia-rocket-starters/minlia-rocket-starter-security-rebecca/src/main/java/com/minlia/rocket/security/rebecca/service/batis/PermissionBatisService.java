package com.minlia.rocket.security.rebecca.service.batis;

import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisService;
import com.minlia.rocket.security.rebecca.body.PermissionQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionBatisService extends
    AbstractBatisService<Permission,Long, PermissionQueryRequestBody> {

  /**
   * 通过用户id获取
   * @param userId
   * @return
   */
//    @Cacheable(key = "#userId")
  List<Permission> findByUserId(Long userId);

  /**
   * 通过roleId获取
   * @param roleId
   * @return
   */
  List<Permission> findByRoleId(@Param("roleId") Long roleId);
}
