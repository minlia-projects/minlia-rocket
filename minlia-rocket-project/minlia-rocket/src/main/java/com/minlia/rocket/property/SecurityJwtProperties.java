package com.minlia.rocket.property;

import java.time.Duration;
import lombok.Data;

@Data
public class SecurityJwtProperties {

  private Boolean enabled;
  private String issuer;
  private String secretKey;

//  TODO change to Duration
  private Long tokenValidityInSeconds;
  private Long tokenValidityInSecondsForRememberMe;
  private String authenticationHeaderName;

  public SecurityJwtProperties() {
    this.enabled = true;
    this.tokenValidityInSeconds = 1800L;//Duration.ofSeconds(1800L);
    this.tokenValidityInSecondsForRememberMe = 2592000L;//Duration.ofSeconds(2592000L);
    this.authenticationHeaderName = "X-Auth-Token";
  }

}