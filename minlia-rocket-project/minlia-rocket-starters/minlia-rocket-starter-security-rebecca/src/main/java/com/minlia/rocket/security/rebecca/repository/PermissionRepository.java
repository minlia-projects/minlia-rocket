package com.minlia.rocket.security.rebecca.repository;

import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.security.rebecca.entity.Permission;
import java.util.List;

/**
 * @author will
 */
public interface PermissionRepository extends AbstractRepository<Permission,Long> {

    /**
     * 通过层级查找
     * 默认升序
     * @param level
     * @return
     */
    List<Permission> findByLevelOrderBySortOrder(Integer level);

    /**
     * 通过parendId查找
     * @param parentId
     * @return
     */
    List<Permission> findByParentIdOrderBySortOrder(Integer parentId);

    /**
     * 通过类型和层级查找
     * @param level
     * @param type
     * @return
     */
    List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type);

    /**
     * 通过parendId和类型查找
     * @param type
     * @param parentId
     * @return
     */
    List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, Integer parentId);
}