package com.minlia.rocket.property;

import lombok.Data;

@Data
public class SecurityJwtProperties {

  private Boolean enabled;
  private String issuer;
  private String secretKey;
  private long tokenValidityInSeconds;
  private long tokenValidityInSecondsForRememberMe;
  private String authenticationHeaderName;

  public SecurityJwtProperties() {
    this.enabled = true;
    this.tokenValidityInSeconds = 1800L;
    this.tokenValidityInSecondsForRememberMe = 2592000L;
    this.authenticationHeaderName = "X-Auth-Token";
  }

}