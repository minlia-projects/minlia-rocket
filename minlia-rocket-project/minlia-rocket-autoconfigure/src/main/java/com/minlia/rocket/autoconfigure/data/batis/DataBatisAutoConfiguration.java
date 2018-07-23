package com.minlia.rocket.autoconfigure.data.batis;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import com.minlia.rocket.data.batis.event.publisher.AfterCreatedEventPublisher;
import com.minlia.rocket.data.batis.event.publisher.BeforeCreatedEventPublisher;
import com.minlia.rocket.data.batis.interceptor.CreatedMethodInterceptor;
import com.minlia.rocket.data.scope.DataScopeInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Minlia Data Batis Auto Configuration
 */
@Configuration
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class,MybatisSqlSessionFactoryBean.class})
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
      PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
      paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
//        /*
//         * 【测试多租户】 SQL 解析处理拦截器<br>
//         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
//         */
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId() {
//                return new LongValue(1L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                // 这里可以判断是否过滤表
//                /*
//                if ("user".equals(tableName)) {
//                    return true;
//                }*/
//                return false;
//            }
//        });
//
//
//        sqlParserList.add(tenantSqlParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
//        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//            @Override
//            public boolean doFilter(MetaObject metaObject) {
//                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
//                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
//                if ("com.baomidou.springboot.mapper.UserDao.selectListBySQL".equals(ms.getId())) {
//                    return true;
//                }
//                return false;
//            }
//        });
      return paginationInterceptor;
    }

  }



}