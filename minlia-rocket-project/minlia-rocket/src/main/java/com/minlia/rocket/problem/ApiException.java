package com.minlia.rocket.problem;

import static org.zalando.problem.Status.EXPECTATION_FAILED;

import com.minlia.rocket.i18n.Lang;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.context.i18n.LocaleContextHolder;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

/**
 * @author will
 */
public class ApiException extends AbstractThrowableProblem {

  private static final long serialVersionUID = 1L;

  private static final String CODE = "code";


  public ApiException(String code) {
    super(ProblemType.withCode(code), EXPECTATION_FAILED.getReasonPhrase(), EXPECTATION_FAILED,
        null, null, null, toProblemApiCode(code, new Object[]{},
            LocaleContextHolder.getLocale()));
  }

  public ApiException(String code, Object[] arguments) {
    super(ProblemType.withCode(code), EXPECTATION_FAILED.getReasonPhrase(), EXPECTATION_FAILED,
        null, null, null, toProblemApiCode(code, arguments, LocaleContextHolder.getLocale()));
  }

  public ApiException(String code, Object[] arguments, Locale locale) {
    super(ProblemType.withCode(code), EXPECTATION_FAILED.getReasonPhrase(), EXPECTATION_FAILED,
        null, null, null, toProblemApiCode(code, arguments, locale));
  }


  public ApiException(Integer code) {
    super(ProblemType.withCode(code), EXPECTATION_FAILED.getReasonPhrase(), EXPECTATION_FAILED,
        null, null, null, toProblemApiCode(code, new Object[]{}, LocaleContextHolder.getLocale()));
  }


  public ApiException(Integer code, Object[] arguments) {
    super(ProblemType.withCode(code), EXPECTATION_FAILED.getReasonPhrase(), EXPECTATION_FAILED,
        null, null, null, toProblemApiCode(code, arguments, LocaleContextHolder.getLocale()));
  }


  public ApiException(Integer code, Object[] arguments, Locale locale) {
    super(ProblemType.withCode(code), EXPECTATION_FAILED.getReasonPhrase(), EXPECTATION_FAILED,
        null, null, null, toProblemApiCode(code, arguments, locale));
  }


  public ApiException(Integer code, StatusType httpStatus) {
    super(ProblemType.withCode(code), httpStatus.getReasonPhrase(), httpStatus, null, null, null,
        toProblemApiCode(code, new Object[]{}, LocaleContextHolder.getLocale()));
  }

  public ApiException(Integer code, StatusType httpStatus, Object[] arguments) {
    super(ProblemType.withCode(code), httpStatus.getReasonPhrase(), httpStatus, null, null, null,
        toProblemApiCode(code, arguments, LocaleContextHolder.getLocale()));
  }

  public ApiException(Integer code, StatusType httpStatus, Object[] arguments, Locale locale) {
    super(ProblemType.withCode(code), httpStatus.getReasonPhrase(), httpStatus, null, null, null,
        toProblemApiCode(code, arguments, locale));
  }


  public static Map<String, Object> toProblemApiCode(Object code, Object[] arguments,
      Locale locale) {
//   Version version= VersionUtil.mavenVersionFor(Intrinsics.class.getClassLoader(), "com.minlia.rocket", "minlia-rocket-starter-problem");
//   if(null==version){
//
//   }
    Map<String, Object> parameters = new HashMap<>();
//    StringBuffer sb=new StringBuffer();
//    for(Object s:arguments){
//      sb.append(s.toString()+" ");
//      System.out.println(s.toString());
//    }
//    parameters.put("message", "{{"+ExceptionConverter.convert(code)+" , "+sb.toString()+" , "+locale+"}}");
    parameters.put("message", Lang.get(ExceptionConverter.convert(code), arguments, locale));
    parameters.put(CODE, code);
    return parameters;
  }

}
