package com.minlia.rocket.security.rebecca.service.batis.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.minlia.rocket.data.batis.abstraction.AbstractMapper;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisServiceImpl;
import com.minlia.rocket.security.rebecca.body.UserRolePageableQueryRequestBody;
import com.minlia.rocket.security.rebecca.body.UserRoleQueryRequestBody;
import com.minlia.rocket.security.rebecca.dao.UserRoleDao;
import com.minlia.rocket.security.rebecca.entity.Role;
import com.minlia.rocket.security.rebecca.entity.UserRole;
import com.minlia.rocket.security.rebecca.service.batis.UserRoleBatisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author rdteam
 */
@Transactional
public class UserRoleBatisServiceImpl extends
    AbstractBatisServiceImpl<UserRole, Long, UserRoleQueryRequestBody,UserRolePageableQueryRequestBody> implements
    UserRoleBatisService {

  @Autowired
  private UserRoleDao userRoleDao;

  @Override
  public List<Role> findByUserId(Long userId) {
    return userRoleDao.findByUserId(userId);
  }


  @Override
  public AbstractMapper<UserRole> getBatisDao() {
    return userRoleDao;
  }

  @Override
  public EntityWrapper<UserRole> getFindAllPageableSpecification(
      UserRolePageableQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<UserRole> getFindAllSpecification(
      UserRoleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<UserRole> getCountSpecification(UserRoleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<UserRole> getExistsSpecification(UserRoleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<UserRole> getDeleteByConditionSpecification(
      UserRoleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public EntityWrapper<UserRole> getConditions(UserRoleQueryRequestBody queryRequestBody) {
    EntityWrapper<UserRole> entityWrapper = new EntityWrapper<UserRole>();

    if (!StringUtils.isEmpty(queryRequestBody.getCode())) {
      entityWrapper.like("code", queryRequestBody.getCode());
    }

    if (!StringUtils.isEmpty(queryRequestBody.getLabel())) {
      entityWrapper.like("label", queryRequestBody.getLabel());
    }
    return entityWrapper;
  }
}
