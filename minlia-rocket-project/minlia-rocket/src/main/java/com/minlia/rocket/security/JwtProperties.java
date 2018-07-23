package com.minlia.rocket.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "system.security", ignoreUnknownFields = true, ignoreInvalidFields = true)
@Data
public class JwtProperties {

  private Boolean enabled;

  private String issuer;
  private String secretKey;
  private long tokenValidityInSeconds;
  private long tokenValidityInSecondsForRememberMe;

  public JwtProperties() {
    this.enabled = true;
    this.tokenValidityInSeconds = 1800L;
    this.tokenValidityInSecondsForRememberMe = 2592000L;
  }

}