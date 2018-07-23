package com.minlia.rocket.data.batis.abstraction.service;

import com.google.common.collect.Lists;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.data.adapter.PageResponseBodyAdapter;
import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import com.minlia.rocket.data.body.PageResponseBody;
import com.minlia.rocket.data.body.ToggleRequestBody;
import com.minlia.rocket.data.interfaces.AbstractRawService;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.CollectionUtils;


/**
 * @author will
 */
public abstract class AbstractBatisServiceImpl<ENTITY extends Serializable, ID extends Serializable, QUERY extends AbstractQueryRequestBody>
    extends AbstractRawService<ENTITY, ID>
    implements
    AbstractBatisService<ENTITY, ID, QUERY>,
    ConditionalService<ENTITY, QUERY> {


  /**
   * 根据ID获取
   */
  @Override
  public ENTITY findOne(ID id) {
    return getBatisDao().selectById(id);
  }

  /**
   * 保存
   */
  @Override
  public ENTITY save(ENTITY entity) {
    //TODO 添加事件
    getBatisDao().insert(entity);
    return entity;
  }

  /**
   * 批量保存与修改
   */
  @Override
  public Iterable<ENTITY> saveAll(Iterable<ENTITY> entities) {
    List<ENTITY> result = Lists.newArrayList();
    for (ENTITY entity : entities) {
      getBatisDao().insert(entity);
      result.add(entity);
    }
    return result;
  }

  /**
   * 修改
   */
  @Override
  public ENTITY update(ENTITY entity) {
    getBatisDao().update(entity, null);
    return entity;
  }

//  /**
//   * 修改
//   */
//  @Override
//  public WithResultBody<ENTITY> update(ENTITY entity) {
//    getBatisDao().update(entity, new Wrapper<ENTITY>() {
//      @Override
//      public String getSqlSegment() {
//        return null;
//      }
//    });
//    return new WithResultBody().setResult();
//  }


  /**
   * 根据Id删除
   */
  @Override
  public Boolean deleteOne(ID id) {
    getBatisDao().deleteById(id);
    return Boolean.TRUE;
  }


  /**
   * 批量删除
   */
  @Override
  public Boolean deleteAll(Iterable<ID> entities) {
    getBatisDao().deleteBatchIds(CollectionUtils.arrayToList(entities));
    return Boolean.TRUE;
  }


  /**
   * 统计总数
   */
  @Override
  public Long count() {
    return Long.parseLong(getBatisDao().selectCount(null).toString());
  }

  /**
   * ID是否存在
   */
  @Override
  public Boolean exists(ID id) {
    return getBatisDao().selectById(id) == null ? Boolean.FALSE : Boolean.TRUE;
  }


  /**
   * toggle 一般用于更新状态
   */
  @Override
  public ENTITY toggle(ToggleRequestBody toggleRequestBody) {
    //应该自已实现, 统一实现会有安全问题，导致可以设置任意属性
//    ENTITY entity=getBatisDao().selectById(toggleRequestBody.getId());
//    entity=(ENTITY)ReflectUtil.setValue(entity,toggleRequestBody.getProperty(),toggleRequestBody.getValue());
//    getBatisDao().update(entity,null);
    return null;
  }

//以下方法为 data 模块提供的功能


  /*
   * 获取查询条件的结果数
   */
  @Override
  public Long countByCondition(QUERY queryRequestBody) {
    return Long
        .parseLong(getBatisDao().selectCount(getCountSpecification(queryRequestBody)).toString());
  }

  /**
   * 根据条件查询是否存在此实体，存在返回TRUE, 不存在返回FALSE
   */
  @Override
  public Boolean existsByCondition(QUERY queryRequestBody) {
    return getBatisDao().selectCount(getExistsSpecification(queryRequestBody)) > 0 ? Boolean.TRUE
        : Boolean.FALSE;
  }

  @Override
  public Integer deleteByCondition(QUERY queryRequestBody) {
    return getBatisDao().delete(getDeleteByConditionSpecification(queryRequestBody));
  }


  @Override
  public PageResponseBody<ENTITY> findAllByCondition(QUERY queryRequestBody,
      Pageable pageable) {
    com.baomidou.mybatisplus.plugins.Page<ENTITY> page = new com.baomidou.mybatisplus.plugins.Page<ENTITY>();
    //当jpa oneBasedIndexParameter时需要+1
    page.setCurrent(pageable.getPageNumber() + 1);
    page.setSize(pageable.getPageSize());

    page.setAscs(getSortPropertiesList(pageable, Direction.ASC));
    page.setDescs(getSortPropertiesList(pageable, Direction.DESC));
    com.baomidou.mybatisplus.plugins.Page<ENTITY> pagedResult = page
        .setRecords(getBatisDao().selectPage(page, getFindAllSpecification(queryRequestBody)));
    return PageResponseBodyAdapter.adapt(pagedResult);
  }


  @Override
  public List<ENTITY> findAllByCondition(QUERY queryRequestBody) {
    return getBatisDao().selectList(getFindAllSpecification(queryRequestBody));
  }

  private List<String> getSortPropertiesList(Pageable pageable, Direction direction) {
    List<String> result = Lists.newArrayList();
    if (null != pageable.getSort()) {
      List<Sort.Order> list = IteratorUtils.toList(pageable.getSort().iterator());
      for (Sort.Order order : list) {
        Direction directionFound = order.getDirection();
        String property = order.getProperty();
        if (directionFound.equals(direction)) {
          result.add(property);
        }
      }
    }
    return result;
  }

}
