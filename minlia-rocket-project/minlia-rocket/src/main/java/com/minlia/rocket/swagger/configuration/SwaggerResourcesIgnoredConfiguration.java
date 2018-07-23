package com.minlia.rocket.swagger.configuration;//package com.minlia.cloud.swagger.starter.configuration;
//
//import cn.chenhuanming.utils.jwt.JwtSecurityAutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//
///**
// * 添加swagger相关静态资源到spring security ignore清单里面
// */
//@Order(value = 101)
//@Configuration
//@ConditionalOnClass(value = {WebSecurity.class, JwtSecurityAutoConfiguration.class})
//public class SwaggerResourcesIgnoredConfiguration extends
//    JwtSecurityAutoConfiguration {
//
//  @Override
//  public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/swagger-ui.html");
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/swagger-resources/**");
//  }
//}
