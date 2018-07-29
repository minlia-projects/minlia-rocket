package com.minlia.rocket.security.rebecca.service.batis.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.minlia.rocket.data.batis.abstraction.AbstractMapper;
import com.minlia.rocket.data.batis.abstraction.service.AbstractBatisServiceImpl;
import com.minlia.rocket.security.rebecca.body.PermissionQueryRequestBody;
import com.minlia.rocket.security.rebecca.dao.PermissionDao;
import com.minlia.rocket.security.rebecca.entity.Permission;
import com.minlia.rocket.security.rebecca.service.batis.PermissionBatisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author rdteam
 */
@Transactional
public class PermissionBatisServiceImpl extends
    AbstractBatisServiceImpl<Permission, Long, PermissionQueryRequestBody> implements
    PermissionBatisService {

  @Autowired
  private PermissionDao permissionDao;
  ;

  @Override
  public List<Permission> findByUserId(Long userId) {

    return permissionDao.findByUserId(userId);
  }

  @Override
  public List<Permission> findByRoleId(Long roleId) {

    return permissionDao.findByRoleId(roleId);
  }


  @Override
  public AbstractMapper<Permission> getBatisDao() {
    return permissionDao;
  }

  @Override
  public EntityWrapper<Permission> getFindAllSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<Permission> getCountSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<Permission> getExistsSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<Permission> getDeleteByConditionSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public EntityWrapper<Permission> getConditions(PermissionQueryRequestBody queryRequestBody) {
    EntityWrapper<Permission> entityWrapper = new EntityWrapper<Permission>();

    if (!StringUtils.isEmpty(queryRequestBody.getCode())) {
      entityWrapper.like("code", queryRequestBody.getCode());
    }

    if (!StringUtils.isEmpty(queryRequestBody.getLabel())) {
      entityWrapper.like("label", queryRequestBody.getLabel());
    }
    return entityWrapper;
  }
}
