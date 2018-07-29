package com.minlia.rocket.security.rebecca.service.jpa;


import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.security.rebecca.body.UserRoleQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.UserRole;
import java.util.List;

/**
 * 用户角色接口
 *
 * @author rdteam
 */
public interface UserRoleJpaService extends
    AbstractJpaService<UserRole, Long, UserRoleQueryRequestBody> {

  /**
   * 通过roleId查找
   */
  List<UserRole> findByRoleId(Long roleId);

  /**
   * 删除用户角色
   */
  void deleteByUserId(Long userId);
}
