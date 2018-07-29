package com.minlia.rocket.security.rebecca.repository;

import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.security.rebecca.entity.RolePermission;
import java.util.List;

/**
 * @author will
 */
public interface RolePermissionRepository extends AbstractRepository<RolePermission, Long> {

  /**
   * 通过permissionId获取
   */
  List<RolePermission> findByPermissionId(Long permissionId);

  /**
   * 通过roleId删除
   */
  void deleteByRoleId(Long roleId);
}