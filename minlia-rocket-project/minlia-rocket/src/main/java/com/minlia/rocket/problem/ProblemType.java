package com.minlia.rocket.problem;

import com.minlia.rocket.context.ContextHolder;
import com.minlia.rocket.property.SystemProperties;
import java.net.URI;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

public class ProblemType {

  private static SystemProperties systemProperties;

  public static URI withCode(Object code) {

    ApplicationContext context = ContextHolder.getContext();
    if (null != context) {
       systemProperties = context.getBean(SystemProperties.class);
    }
    String problemEndpoint = systemProperties.getProblem().getCodePortalPrefix();
    if (StringUtils.isEmpty(problemEndpoint)) {
      problemEndpoint = "http://code.minlia.com/";
    }
    if (!problemEndpoint.endsWith("/")) {
      problemEndpoint += "/";
    }
    return URI.create(problemEndpoint + code);
  }
}
