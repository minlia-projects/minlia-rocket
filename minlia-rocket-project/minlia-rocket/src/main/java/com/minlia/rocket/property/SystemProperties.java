package com.minlia.rocket.property;

import com.minlia.rocket.i18n.properties.LanguageProperties;
import com.minlia.rocket.ignored.SystemIgnoredListProperties;
import com.minlia.rocket.problem.ProblemProperties;
import com.minlia.rocket.security.JwtProperties;
import com.minlia.rocket.swagger.properties.SwaggerConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


/**
 * @author will
 */
@ConfigurationProperties(prefix = "system",ignoreInvalidFields = true,ignoreUnknownFields = true)
@Data
public class SystemProperties {

  private CorsConfiguration cors;

  private SystemIgnoredListProperties ignored;

  private ProblemProperties problem;

  private JwtProperties security;

  private SwaggerConfigurationProperties swagger;

  private LanguageProperties i18n;

}
