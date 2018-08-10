package com.minlia.rocket.security.rebecca.service.jpa.impl;

import cn.hutool.core.date.DateUtil;
import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.enumeration.Gender;
import com.minlia.rocket.enumeration.Status;
import com.minlia.rocket.security.rebecca.body.UserPageableQueryRequestBody;
import com.minlia.rocket.security.rebecca.body.UserQueryRequestBody;
import com.minlia.rocket.security.rebecca.dao.PermissionDao;
import com.minlia.rocket.security.rebecca.dao.UserRoleDao;
import com.minlia.rocket.security.rebecca.entity.Permission;
import com.minlia.rocket.security.rebecca.entity.Role;
import com.minlia.rocket.security.rebecca.entity.User;
import com.minlia.rocket.security.rebecca.repository.UserRepository;
import com.minlia.rocket.security.rebecca.service.jpa.UserJpaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 用户接口实现
 *
 * @author rdteam
 */
@Slf4j
@Transactional
public class UserJpaServiceImpl implements UserJpaService {

  @Autowired
  private UserRepository userRepository;

    @Autowired
    private UserRoleDao userRoleMapper;

  @Autowired
  private PermissionDao permissionDao;


  @Override
  public AbstractRepository<User, Long> getJpaRepository() {
    return this.userRepository;
  }

  @Override
  public User findByUsername(String username) {

//        List<User> list=userRepository.findByUsernameAndStatus(username, CommonConstant.USER_STATUS_NORMAL);
    List<User> list = userRepository.findByUsername(username);
    if (list != null && list.size() > 0) {
      User user = list.get(0);
            List<Role> roleList = userRoleMapper.findByUserId(user.getId());
            user.setRoles(roleList);
      List<Permission> permissionList = permissionDao.findByUserId(user.getId());
      user.setPermissions(permissionList);
      return user;
    }
    return null;
  }

  @Override
  public List<User> findByStatusAndType(Status status, Integer type) {

    return userRepository.findByStatusAndType(status, type);
  }


  @Override
  public Boolean isEnabledById(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return null;
    }
    switch (user.getStatus()) {
      case DISABLED:
        return Boolean.FALSE;
      case ENABLED:
        return Boolean.TRUE;
      default:
        return null;
    }
  }


  @Override
  public Specification<User> getFindAllPageableSpecification(UserPageableQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<User> getFindAllSpecification(UserQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<User> getCountSpecification(UserQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<User> getExistsSpecification(UserQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<User> getDeleteByConditionSpecification(
      UserQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public Specification<User> getConditions(UserQueryRequestBody queryRequestBody) {
    return new Specification<User>() {
      @Nullable
      @Override

      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Path<String> usernameField = root.get("username");
        Path<String> mobileField = root.get("mobile");
        Path<String> emailField = root.get("email");
        Path<Gender> genderField = root.get("gender");
        Path<Integer> typeField = root.get("type");
        Path<Status> statusField = root.get("status");
        Path<Date> createdDateField = root.get("createdDate");

        List<Predicate> list = new ArrayList<Predicate>();

        //模糊搜素
        if (!StringUtils.isEmpty(queryRequestBody.getUsername())) {
          list.add(cb.like(usernameField, '%' + queryRequestBody.getUsername() + '%'));
        }
        if (!StringUtils.isEmpty(queryRequestBody.getMobile())) {
          list.add(cb.like(mobileField, '%' + queryRequestBody.getMobile() + '%'));
        }
        if (!StringUtils.isEmpty(queryRequestBody.getEmail())) {
          list.add(cb.like(emailField, '%' + queryRequestBody.getEmail() + '%'));
        }

        //性别
        if (queryRequestBody.getGender() != null) {
          list.add(cb.equal(genderField, queryRequestBody.getGender()));
        }
        //类型
        if (queryRequestBody.getType() != null) {
          list.add(cb.equal(typeField, queryRequestBody.getType()));
        }
        //状态
        if (queryRequestBody.getStatus() != null) {
          list.add(cb.equal(statusField, queryRequestBody.getStatus()));
        }
        //创建时间
        if (!StringUtils.isEmpty(queryRequestBody.getStartDate()) && !StringUtils.isEmpty(queryRequestBody.getEndDate())) {
          Date start = DateUtil.parse(queryRequestBody.getStartDate());
          Date end = DateUtil.parse(queryRequestBody.getEndDate());
          list.add(cb.between(createdDateField, start, DateUtil.endOfDay(end)));
        }

        Predicate[] arr = new Predicate[list.size()];
        cq.where(list.toArray(arr));
        return null;
      }
    };
  }

}
