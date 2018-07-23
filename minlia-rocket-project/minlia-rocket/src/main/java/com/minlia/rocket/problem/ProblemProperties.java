package com.minlia.rocket.problem;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "system.problem",ignoreInvalidFields = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemProperties {

  private String codePortalPrefix = "http://code.minlia.com/";


}
