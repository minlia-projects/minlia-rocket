package com.minlia.rocket.security.rebecca.service.jpa;


import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.security.rebecca.body.RoleQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.Role;
import java.util.List;

/**
 * 角色接口
 * @author rdteam
 */
public interface RoleJpaService extends AbstractJpaService<Role,Long,RoleQueryRequestBody> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);

    /**
     * 判断是否存在除角色Id外角色名一致的角色
     * @param id 角色Id
     * @param name 角色名称
     * @return 存在/不存在
     */
    Boolean existsByIdIsNotAndName(Long id, String name);

    /**
     * 判断是否存在除角色Id外角色名一致的角色
     * @param id 角色Id
     * @param code 角色编码
     * @return 存在/不存在
     */
    Boolean existsByIdIsNotAndCode(Long id, String code);
}
