package com.minlia.rocket.autoconfigure.data.jpa;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.util.StopWatch;

/**
 * Minlia Data Jpa Auto Configuration
 */
@Configuration
@AutoConfigureAfter(JpaRepositoriesAutoConfiguration.class)
@ConditionalOnClass({JpaRepository.class, LocalContainerEntityManagerFactoryBean.class,
    JpaTransactionManager.class, HibernateExceptionTranslator.class})
@ConditionalOnMissingBean({JpaTransactionManager.class,
    PersistenceExceptionTranslator.class})
@Slf4j
public class DataJpaAutoConfiguration {


  /**
   * 2.0.0 之后需要启用此注解来进行分页支持
   */
  @Configuration
  @EntityScan(basePackages = {".**.entity", "org.springframework.data.jpa.convert.threeten"})
  @EnableJpaRepositories(value = {
      ".**.repository"}, considerNestedRepositories = true, transactionManagerRef = "jpaTransactionManager")
  @EnableJpaAuditing
  public static class ImportDataJpaConfiguration {


    @Autowired
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Primary
    @Bean
    public JpaTransactionManager jpaTransactionManager() {
      log.debug("Starting Jpa Configuration");
      StopWatch watch = new StopWatch();
      watch.start();

      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager
          .setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
      watch.stop();

      log.debug("Finished Jpa Configuration in {} ms", watch.getTotalTimeMillis());
      return transactionManager;
    }


    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
      return new HibernateExceptionTranslator();
    }

  }
}