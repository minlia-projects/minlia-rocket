package com.minlia.rocket.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


/**
 * @author will
 */
@ConfigurationProperties(prefix = "system", ignoreInvalidFields = true, ignoreUnknownFields = true)
@Data
public class SystemProperties {

  private CorsConfiguration cors;

  private SystemIgnoredListProperties ignored;

  private ProblemProperties problem;

  private SecurityJwtProperties security;

  private SwaggerConfigurationProperties swagger;

  private LanguageProperties i18n;


}
