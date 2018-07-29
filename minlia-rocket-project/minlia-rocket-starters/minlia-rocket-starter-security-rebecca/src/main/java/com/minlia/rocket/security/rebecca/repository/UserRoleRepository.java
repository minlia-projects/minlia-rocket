package com.minlia.rocket.security.rebecca.repository;

import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.security.rebecca.entity.UserRole;
import java.util.List;

/**
 * 用户角色数据处理层
 * @author rdteam
 */
public interface UserRoleRepository extends AbstractRepository<UserRole,Long> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(Long roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(Long userId);
}
