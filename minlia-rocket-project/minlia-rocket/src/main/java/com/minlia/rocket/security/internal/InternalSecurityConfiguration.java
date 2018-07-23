package com.minlia.rocket.security.internal;

import com.minlia.rocket.ignored.SystemIgnoredListProperties;
import com.minlia.rocket.security.security.jwt.JwtConfigurer;
import com.minlia.rocket.security.security.jwt.TokenProvider;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Import(SecurityProblemSupport.class)
//@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class InternalSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;

//  private final UserDetailsService userDetailsService;

  private final TokenProvider tokenProvider;
  private final SecurityProblemSupport problemSupport;
  @Autowired
  protected BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private CorsFilter corsFilter;
  @Autowired
  private SystemIgnoredListProperties systemIgnoredListProperties;

  @Autowired
  private AuthenticationProvider authenticationProvider;

//    @Autowired
//    private JHipsterProperties jHipsterProperties;
////

  public InternalSecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
//      UserDetailsService userDetailsService,
      TokenProvider tokenProvider,
//      CorsFilter corsFilter,
      SecurityProblemSupport problemSupport) {
    this.authenticationManagerBuilder = authenticationManagerBuilder;
//    this.userDetailsService = userDetailsService;
    this.tokenProvider = tokenProvider;
//        this.corsFilter = corsFilter;
    this.problemSupport = problemSupport;
  }

//  @PostConstruct
//  public void init() {
//    try {
//          //废除使用userDetailsService的方式
//      authenticationManagerBuilder
//          .userDetailsService(userDetailsService)
//          .passwordEncoder(passwordEncoder);
//    } catch (Exception e) {
//      throw new BeanInitializationException("Security configuration failed", e);
//    }
//  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    //使用authenticationProvider进行身份认证
    auth.authenticationProvider(authenticationProvider);
  }

//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = jHipsterProperties.getCors();
//        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
//            log.debug("Registering CORS filter");
//            source.registerCorsConfiguration("/api/**", config);
//            source.registerCorsConfiguration("/management/**", config);
//            source.registerCorsConfiguration("/v2/api-docs", config);
//        }
//        return new CorsFilter(source);
//    }

//  @Override
//  @ConditionalOnMissingBean
//  @Bean
//  public DummyUserDetailsService userDetailsService() {
//    return new DummyUserDetailsService();
//  }


  @Override
  public void configure(WebSecurity web) throws Exception {

    List<String> ignoredUrls = systemIgnoredListProperties.getUrls();
    //添加列表概念
    if (null != ignoredUrls && ignoredUrls.size() > 0) {
      for (String url : ignoredUrls) {
        web.ignoring().antMatchers(url);
      }
    }

    web.ignoring()
        .antMatchers(HttpMethod.OPTIONS, "/**")
//        .antMatchers("/app/**/*.{js,html}")
//        .antMatchers("/i18n/**")
//        .antMatchers("/content/**")
//        .antMatchers("/swagger-ui/index.html")
//        .antMatchers("/test/**")
//        .antMatchers("/h2-console/**")
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
        .authorizeRequests();

    //除配置文件忽略路径其它所有请求都需经过认证和授权
    for (String url : systemIgnoredListProperties.getUrls()) {
      registry.antMatchers(url).permitAll();
    }

    http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(problemSupport)
        .accessDeniedHandler(problemSupport)
        .and()
        .csrf()
        .disable()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
//        .antMatchers("/api/register").permitAll()
//        .antMatchers("/api/activate").permitAll()
//        .antMatchers("/api/authenticate").permitAll()
//        .antMatchers("/api/test").permitAll()
//        .antMatchers("/api/status").permitAll()
//        .antMatchers("/api/notActivated").permitAll()
//        .antMatchers("/api/account/reset-password/init").permitAll()
//        .antMatchers("/api/account/reset-password/finish").permitAll()
//        .antMatchers("/api/profile-info").permitAll()
//        .antMatchers("/api/**").authenticated()
//        .antMatchers("/management/health").permitAll()
//        .antMatchers("/v2/api-docs/**").permitAll()
//        .antMatchers("/swagger-resources/configuration/ui").permitAll()

        .antMatchers("/api/open/authenticate").permitAll()
        .antMatchers("/api/v**/open/**").permitAll()
        .antMatchers("/api/**").authenticated()

        .and()
        .apply(securityConfigurerAdapter());

  }

  private JwtConfigurer securityConfigurerAdapter() {
    return new JwtConfigurer(tokenProvider);
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
