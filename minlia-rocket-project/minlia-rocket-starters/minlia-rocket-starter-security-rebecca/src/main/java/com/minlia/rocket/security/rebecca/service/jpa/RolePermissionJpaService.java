package com.minlia.rocket.security.rebecca.service.jpa;

import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.security.rebecca.body.RolePermissionPageableQueryRequestBody;
import com.minlia.rocket.security.rebecca.body.RolePermissionQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.RolePermission;
import java.util.List;

/**
 */
public interface RolePermissionJpaService extends
    AbstractJpaService<RolePermission, Long, RolePermissionQueryRequestBody,RolePermissionPageableQueryRequestBody> {

  /**
   * 通过permissionId获取
   */
  List<RolePermission> findByPermissionId(Long permissionId);

  /**
   * 通过roleId删除
   */
  void deleteByRoleId(Long roleId);
}