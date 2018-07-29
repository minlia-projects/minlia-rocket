package com.minlia.rocket.security.rebecca.repository;
import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.enumeration.Status;
import com.minlia.rocket.security.rebecca.entity.User;
import java.util.List;

/**
 * 用户数据处理层
 * @author rdteam
 */
public interface UserRepository extends AbstractRepository<User,Long> {

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Status status);

    /**
     * 通过状态和类型获取用户
     * @param status
     * @param type
     * @return
     */
    List<User> findByStatusAndType(Status status, Integer type);

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户列表
     */
    List<User> findByUsername(String username);

    User findOneByAppidAndOpenid(String appid, String openid);
}
