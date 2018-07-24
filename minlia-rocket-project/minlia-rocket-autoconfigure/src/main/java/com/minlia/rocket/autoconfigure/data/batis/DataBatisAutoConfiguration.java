package com.minlia.rocket.autoconfigure.data.batis;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.data.batis.event.publisher.AfterCreatedEventPublisher;
import com.minlia.rocket.data.batis.event.publisher.BeforeCreatedEventPublisher;
import com.minlia.rocket.data.batis.interceptor.CreatedMethodInterceptor;
import com.minlia.rocket.data.scope.DataScopeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;

/**
 * Minlia Data Batis Auto Configuration
 */
@Configuration
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class,MybatisSqlSessionFactoryBean.class})
@Slf4j
public class DataBatisAutoConfiguration {


  @Configuration
  @MapperScan(basePackages = {".**.dao" })
  public static class ImportDataBatisConfiguration{



    @Bean
    @ConditionalOnMissingBean(value = {CreatedMethodInterceptor.class})
    public CreatedMethodInterceptor createdMethodInterceptor(){
      return new CreatedMethodInterceptor();
    }



    @Bean
    @ConditionalOnMissingBean(value = {AfterCreatedEventPublisher.class})
    public AfterCreatedEventPublisher afterCreatedEventPublisher(){
      return new AfterCreatedEventPublisher();
    }


    @Bean
    @ConditionalOnMissingBean(value = {BeforeCreatedEventPublisher.class})
    public BeforeCreatedEventPublisher beforeCreatedEventPublisher(){
      return new BeforeCreatedEventPublisher();
    }


    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    @ConditionalOnMissingBean(value = {PerformanceInterceptor.class})
    @ConditionalOnClass(value = {PerformanceInterceptor.class})
    @Profile("!production")
    public PerformanceInterceptor performanceInterceptor() {
      return new PerformanceInterceptor();
    }

//    @Bean
//    public DimensionInterceptor dimensionInterceptor() {
//        return new DimensionInterceptor();
//    }

    @Bean
    @ConditionalOnMissingBean(value = {SqlExplainInterceptor.class})
    @ConditionalOnClass(value = {SqlExplainInterceptor.class})
    @Profile("!production")
    public SqlExplainInterceptor sqlExplainInterceptor() {
      return new SqlExplainInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(value = {OptimisticLockerInterceptor.class})
    @ConditionalOnClass(value = {OptimisticLockerInterceptor.class})
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
      return new OptimisticLockerInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(value = {DataScopeInterceptor.class})
    @ConditionalOnClass(value = {DataScopeInterceptor.class})
    public DataScopeInterceptor dataScopeInterceptor() {
      return new DataScopeInterceptor();
    }

    /**
     * mybatis-plus分页插件<br> 文档：http://mp.baomidou.com<br>
     */
    @Bean
    @ConditionalOnMissingBean(value = {PaginationInterceptor.class})
    @ConditionalOnClass(value = {PaginationInterceptor.class})
    public PaginationInterceptor paginationInterceptor() {
      log.debug("Starting Batis Configuration");
      StopWatch watch = new StopWatch();
      watch.start();
      PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
      paginationInterceptor.setLocalPage(true);
      watch.stop();
      log.debug("Finished Batis Configuration in {} ms", watch.getTotalTimeMillis());
      return paginationInterceptor;
    }

  }



}