package com.minlia.rocket.problem;

import com.minlia.rocket.context.ContextHolder;
import java.net.URI;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

public class ProblemType {

  private static ProblemProperties problemProperties;

  public static URI withCode(Object code) {

    ApplicationContext context = ContextHolder.getContext();
    if (null != context) {
      problemProperties = context.getBean(ProblemProperties.class);
    }
    String problemEndpoint = problemProperties.getCodePortalPrefix();
    if (StringUtils.isEmpty(problemEndpoint)) {
      problemEndpoint = "http://code.minlia.com/";
    }
    if (!problemEndpoint.endsWith("/")) {
      problemEndpoint += "/";
    }
    return URI.create(problemEndpoint + code);
  }
}
