//package com.minlia.rocket.autoconfigure.ignored;
//
//import com.minlia.rocket.ignored.SystemIgnoredListProperties;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author will
// */
//@Configuration
//@EnableConfigurationProperties(value = {SystemIgnoredListProperties.class})
//public class SystemIgnoredListAutoConfiguration {
//
//
//  @Bean
//  @ConditionalOnMissingBean
//  public SystemIgnoredListProperties systemIgnoredListProperties(){
//    return new SystemIgnoredListProperties();
//  }
//}
