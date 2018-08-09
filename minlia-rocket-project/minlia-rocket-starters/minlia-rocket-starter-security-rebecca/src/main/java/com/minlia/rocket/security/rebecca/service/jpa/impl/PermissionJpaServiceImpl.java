package com.minlia.rocket.security.rebecca.service.jpa.impl;

import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.security.rebecca.body.PermissionPageableQueryRequestBody;
import com.minlia.rocket.security.rebecca.body.PermissionQueryRequestBody;
import com.minlia.rocket.security.rebecca.entity.Permission;
import com.minlia.rocket.security.rebecca.repository.PermissionRepository;
import com.minlia.rocket.security.rebecca.service.jpa.PermissionJpaService;
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
 * @author will
 */
@Slf4j
@Transactional
public class PermissionJpaServiceImpl implements PermissionJpaService {

  @Override
  public AbstractRepository<Permission, Long> getJpaRepository() {
    return this.permissionRepository;
  }

  @Autowired
  private PermissionRepository permissionRepository;


  @Override
  public List<Permission> findByLevelOrderBySortOrder(Integer level) {

    return permissionRepository.findByLevelOrderBySortOrder(level);
  }

  @Override
  public List<Permission> findByParentIdOrderBySortOrder(Integer parentId) {

    return permissionRepository.findByParentIdOrderBySortOrder(parentId);
  }

  @Override
  public List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type) {

    return permissionRepository.findByLevelAndTypeOrderBySortOrder(level, type);
  }

  @Override
  public List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, Integer parentId) {

    return permissionRepository.findByTypeAndParentIdOrderBySortOrder(type, parentId);
  }


  @Override
  public Specification<Permission> getFindAllPageableSpecification(
      PermissionPageableQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<Permission> getFindAllSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<Permission> getCountSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<Permission> getExistsSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<Permission> getDeleteByConditionSpecification(
      PermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public Specification<Permission> getConditions(PermissionQueryRequestBody queryRequestBody) {
    return new Specification<Permission>() {
      @Nullable
      @Override
      public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Path<String> codeField = root.get("code");
        Path<String> labelField = root.get("label");
//        Path<String> appidField = root.get("appid");
//                Path<String> emailField = root.get("email");
//                Path<Integer> genderField=root.get("gender");
//                Path<Integer> typeField=root.get("type");
//        Path<Status> statusField=root.get("status");
//
//        Path<Merchant> merchantField=root.get("merchant");
        Path<Date> createdDateField = root.get("createdDate");

        List<Predicate> predicates = new ArrayList<Predicate>();

        //模糊搜素
        if (!StringUtils.isEmpty(queryRequestBody.getCode())) {
          predicates.add(cb.like(codeField, '%' + queryRequestBody.getCode() + '%'));
        }

        if (!StringUtils.isEmpty(queryRequestBody.getLabel())) {
          predicates.add(cb.like(labelField, '%' + queryRequestBody.getLabel() + '%'));
        }
//        if(!StringUtils.isEmpty(entity.getAppid())){
//          list.add(cb.like(appidField,'%'+entity.getAppid()+'%'));
//        }
//                if(StrUtil.isNotBlank(entity.getMobile())){
//                    list.add(cb.like(mobileField,'%'+entity.getMobile()+'%'));
//                }
//                if(StrUtil.isNotBlank(entity.getEmail())){
//                    list.add(cb.like(emailField,'%'+entity.getEmail()+'%'));
//                }
//
//                //性别
//                if(entity.getGender()!=null){
//                    list.add(cb.equal(genderField, entity.getGender()));
//                }
//        if(entity.getMerchant()!=null){
//          list.add(cb.equal(merchantField, entity.getMerchant()));
//        }
//                //类型
//                if(entity.getType()!=null){
//                    list.add(cb.equal(typeField, entity.getType()));
//                }
        //状态
//        if(entity.getStatus()!=null){
//          list.add(cb.equal(statusField, entity.getStatus()));
//        }
        //创建时间
//        if (!StringUtils.isEmpty(searchBody.getStartDate()) && StringUtils
//            .isNotBlank(searchBody.getEndDate())) {
//          Date start = DateUtil.parse(searchBody.getStartDate());
//          Date end = DateUtil.parse(searchBody.getEndDate());
//          predicates.add(cb.between(createdDateField, start, DateUtil.endOfDay(end)));
//        }

        Predicate[] arr = new Predicate[predicates.size()];
        cq.where(predicates.toArray(arr));
        return null;
      }
    };
  }

}
