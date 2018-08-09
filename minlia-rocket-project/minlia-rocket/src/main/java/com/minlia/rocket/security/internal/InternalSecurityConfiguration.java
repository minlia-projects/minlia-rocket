package com.minlia.rocket.security.internal;

import com.google.common.collect.ImmutableList;
import com.minlia.rocket.property.SystemProperties;
import com.minlia.rocket.security.security.jwt.JwtConfigurer;
import com.minlia.rocket.security.security.jwt.TokenProvider;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StopWatch;
import org.springframework.web.cors.CorsConfigurationSource;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class InternalSecurityConfiguration extends WebSecurityConfigurerAdapter {

  public static final String[] IGNORED_SWAGGER_PREFIX = {"/swagger-resources/**", "/api-docs**",
      "/webjars/**", "/swagger-ui.html**"};
  @Autowired
  private AuthenticationManagerBuilder authenticationManagerBuilder;
  @Autowired
  private TokenProvider tokenProvider;
  @Autowired
  private SecurityProblemSupport problemSupport;

  @Autowired
  protected BCryptPasswordEncoder passwordEncoder;


  @Autowired
  private CorsConfigurationSource corsConfigurationSource;

  @Autowired
  private SystemProperties systemProperties;

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Value(value = "${system.swagger.enabled:false}")
  private Boolean isSwaggerEnabled;



  public static class CsrfRequireMatcher implements RequestMatcher {
    private static final Pattern ALLOWED_METHODS =
        Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private static final List<String> LOCALHOST_PATTERNS =
        ImmutableList.of("127.0.0.1", "0:0:0:0:0:0:0:1");

    @Override
    public boolean matches(HttpServletRequest request) {
//      // CSRF disabled on GET, HEAD, TRACE, OPTIONS (i.e. enabled for POST, PUT, DELETE)
//      if (ALLOWED_METHODS.matcher(request.getMethod()).matches()) {
//        return false;
//      }
//
//      // CSRF not required on localhost when swagger-ui is referer
//      final String remoteHost = request.getRemoteHost();
//      final String referer = request.getHeader("Referer");
//      if (remoteHost != null && referer != null
//          && LOCALHOST_PATTERNS.contains(remoteHost)
//          && "http://localhost:8080/swagger-ui.html".equals(referer)) {
//        return false;
//      }
//      // otherwise, CSRF is required
//      return true;

      return false;
    }
  }

  private JwtConfigurer securityConfigurerAdapter() {
    return new JwtConfigurer(tokenProvider);
  }

//  public InternalSecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
//      TokenProvider tokenProvider,
//      SecurityProblemSupport problemSupport) {
//    this.authenticationManagerBuilder = authenticationManagerBuilder;
//    this.tokenProvider = tokenProvider;
//    this.problemSupport = problemSupport;
//  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
  }

  @Override
  public void configure(WebSecurity web) {

    //当启用swagger时才添加，不启用时无需添加
    if (isSwaggerEnabled) {
      log.info("Swagger was enabled, adding resources to ignore list");
      if (IGNORED_SWAGGER_PREFIX.length > 0) {
        for (String ignoredSwaggerPrefix : IGNORED_SWAGGER_PREFIX) {
          web.ignoring().antMatchers(ignoredSwaggerPrefix);
        }
      }
    }

    //添加用户自定义的清单
    List<String> ignoredUrls = systemProperties.getIgnored().getUrls();
    //TODO
    //自动添加系统的登录API到忽略列表
    //自动添加系统的登出API到忽略列表

    //添加忽略列表概念
    if (null != ignoredUrls && ignoredUrls.size() > 0) {
      for (String url : ignoredUrls) {
        web.ignoring().antMatchers(url);
      }
    }

  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.debug("Starting security configuration");
    StopWatch watch = new StopWatch();
    watch.start();
    http.headers()
        .cacheControl().disable();
    http
        .cors().configurationSource(corsConfigurationSource)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(problemSupport)
        .accessDeniedHandler(problemSupport)
        .and()
        .csrf()//.ignoringAntMatchers("/**")
        .disable()
//        .headers()
//        .frameOptions()
//        .disable()
//        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers("/**").authenticated()

        .and()
        .apply(securityConfigurerAdapter());

    watch.stop();
    log.debug("Finishing security configuration in {} ms", watch.getTotalTimeMillis());

  }

  /**
   * For security 2.0.1.RELEASE fix only
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
