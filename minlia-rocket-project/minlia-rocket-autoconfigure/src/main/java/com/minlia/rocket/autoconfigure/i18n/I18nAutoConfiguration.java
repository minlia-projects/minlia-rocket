package com.minlia.rocket.autoconfigure.i18n;

import com.minlia.rocket.i18n.LanguageRequestInterceptor;
import com.minlia.rocket.i18n.LanguageRequestLocaleResolver;
import com.minlia.rocket.i18n.properties.LanguageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author will
 */
@Configuration
@ComponentScan
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass(WebMvcConfigurer.class)
@ConditionalOnMissingBean({LanguageRequestInterceptor.class})
@EnableConfigurationProperties(value = {LanguageProperties.class})
@Slf4j
public class I18nAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public LanguageProperties languageProperties(){
    return new LanguageProperties();
  }

  /**
   * WARNING: Never config interceptors out of WebMvcConfigurer, it will cause stack overflow
   * exception at runtime.
   */
  @Configuration
  public static class SystemI18nConfiguration implements WebMvcConfigurer {

    @Primary
    @Bean(name = "localeResolver")
    public LanguageRequestLocaleResolver localeResolver() {
      return new LanguageRequestLocaleResolver();
    }

    @Value(value = "${system.i18n.language-request-parameter-name:lang}")
    private String parameterName;

    @Value(value = "${system.i18n.language-request-header-parameter-name:X-Request-Lang}")
    private String headerParameterName;

    @Bean
    @ConditionalOnMissingBean
    public LanguageRequestInterceptor languageRequestInterceptor() {

      log.debug("Starting i18n configuration");
      StopWatch watch = new StopWatch();
      watch.start();
      LanguageRequestInterceptor languageRequestInterceptor = new LanguageRequestInterceptor();
      if (!StringUtils.isEmpty(parameterName)) {
        languageRequestInterceptor.setParamName(parameterName);
      }
      if (!StringUtils.isEmpty(headerParameterName)) {
        languageRequestInterceptor.setHeaderParameterName(headerParameterName);
      }
      watch.stop();
      log.debug("Finishing i18n configuration in {} ms", watch.getTotalTimeMillis());
      return languageRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(languageRequestInterceptor());
    }

  }


}
