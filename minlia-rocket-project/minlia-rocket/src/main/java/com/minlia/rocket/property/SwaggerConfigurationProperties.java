package com.minlia.rocket.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SwaggerConfigurationProperties {

  private Boolean enabled = Boolean.TRUE;

  private String title = "Api Docs";

  private String version ="1.0.0";

  private String path = "/api/.*";
  private String description = "Minlia Cloud Api Documentations";
  private String contact = "cloud@minlia.com";

  private String keyName = "X-Auth-Token";
  private String passAs = "header";

  private Boolean redirect = false;

}
