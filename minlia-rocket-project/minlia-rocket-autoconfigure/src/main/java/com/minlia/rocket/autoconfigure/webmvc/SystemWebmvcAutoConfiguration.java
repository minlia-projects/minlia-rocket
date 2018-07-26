package com.minlia.rocket.autoconfigure.webmvc;

import com.minlia.rocket.property.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author will
 */
@Configuration
@EnableConfigurationProperties(value = {SystemProperties.class})
@Slf4j
public class SystemWebmvcAutoConfiguration {

  @Autowired
  private SystemProperties systemProperties;

  @Bean
  public CorsFilter corsFilter() {
    return new CorsFilter(corsConfigurationSource());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = systemProperties.getCors();
    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
      log.debug("Registering cors filter");
      source.registerCorsConfiguration("/**", config);
    }
    return source;
  }


  @Bean
  public FilterRegistrationBean corsWebMvcFilter() {
    final FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter());
    bean.setOrder(0);
    return bean;
  }

  @Bean
  public WebMvcConfigurer webMvcCorsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        CorsConfiguration config = systemProperties.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
          registry.addMapping("/**").allowedMethods(
              config.getAllowedMethods().toArray(new String[config.getAllowedMethods().size()]));
        }

      }
    };
  }


}
