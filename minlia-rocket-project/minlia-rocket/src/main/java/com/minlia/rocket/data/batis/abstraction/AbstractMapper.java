package com.minlia.rocket.data.batis.abstraction;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Notes: 由于 mybatis 的扫描机制，不可以将此抽象类命名为 mapper包名下
 */
public interface AbstractMapper<T> extends BaseMapper<T> {

//  List<?> pageList(Page page, @Param("ew") Wrapper<T> wrapper);
}
