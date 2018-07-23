//package com.minlia.rocket.data.jpa;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.dao.support.PersistenceExceptionTranslator;
//import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//public class DataJpaConfiguration {
//
//  @Autowired
//  LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
//
//  @Primary
//  @Bean
//  public JpaTransactionManager jpaTransactionManager() {
//    JpaTransactionManager transactionManager = new JpaTransactionManager();
//    transactionManager
//        .setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
//    return transactionManager;
//  }
//
//
//  @Bean
//  public PersistenceExceptionTranslator persistenceExceptionTranslator() {
//    return new HibernateExceptionTranslator();
//  }
//
//}
