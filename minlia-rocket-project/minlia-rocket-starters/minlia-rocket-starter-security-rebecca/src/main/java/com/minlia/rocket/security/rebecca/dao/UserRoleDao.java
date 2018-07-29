package com.minlia.rocket.security.rebecca.dao;

import com.minlia.rocket.data.batis.abstraction.AbstractMapper;
import com.minlia.rocket.security.rebecca.entity.Role;
import com.minlia.rocket.security.rebecca.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author rdteam
 */
public interface UserRoleDao extends AbstractMapper<UserRole> {

  /**
   * 通过用户id获取
   */
  List<Role> findByUserId(@Param("userId") Long userId);
}
