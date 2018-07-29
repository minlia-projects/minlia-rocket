package com.minlia.rocket.security.rebecca.service.jpa;


import com.minlia.rocket.data.jpa.abstraction.service.AbstractJpaService;
import com.minlia.rocket.enumeration.Status;
import com.minlia.rocket.security.rebecca.body.UserQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.User;
import java.util.List;

/**
 * 用户接口
 *
 * @author rdteam
 */
public interface UserJpaService extends AbstractJpaService<User, Long, UserQueryRequestBody> {

  /**
   * 通过用户名获取用户
   */
//    @Cacheable(key = "#username")
  User findByUsername(String username);

  /**
   * 通过状态和类型获取用户
   */
  List<User> findByStatusAndType(Status status, Integer type);

  /**
   * 多条件分页获取用户
   * @param user
   * @param searchVo
   * @param pageable
   * @return
   */
//    Page<User> findByCondition(User user, SearchVo searchVo, Pageable pageable);

  /**
   * 对应Id的用户是否启用中
   *
   * @param id 用户Id
   * @return 启用/禁用
   */
  Boolean isEnabledById(Long id);
}
