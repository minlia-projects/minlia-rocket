package com.minlia.rocket.security.rebecca.dao;

import com.minlia.rocket.data.batis.abstraction.AbstractMapper;
import com.minlia.rocket.security.rebecca.entity.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author rdteam
 */
public interface PermissionDao extends AbstractMapper<Permission> {

  /**
   * 通过用户id获取
   */
  List<Permission> findByUserId(@Param("userId") Long userId);

  /**
   * 通过roleId获取
   */
  List<Permission> findByRoleId(@Param("roleId") Long roleId);
}
