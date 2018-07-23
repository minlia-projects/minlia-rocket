package com.minlia.rocket.problem;

import org.springframework.http.HttpStatus;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * @author will
 */
public class Intrinsics {

  /**
   * 检查参数是否为空，为空则抛出异常
   */
  public static void isNull(Object parameter, Integer code, Object... arguments) {
    if (null == parameter) {
      throwExceptionWithCodeAndArguments(code, arguments);
    }
  }

  public static void isNull(Object parameter, Integer code, HttpStatus status,
      Object... arguments) {
    if (null == parameter) {
      throwExceptionWithCodeAndArguments(code, status, arguments);
    }
  }

  public static void isNotNull(Object parameter, Integer code, Object... arguments) {
    if (null != parameter) {
      throwExceptionWithCodeAndArguments(code, arguments);
    }
  }

  public static void isNotNull(Object parameter, Integer code, HttpStatus status,
      Object... arguments) {
    if (null != parameter) {
      throwExceptionWithCodeAndArguments(code, status, arguments);
    }
  }

  /**
   * 表达式是否为真，当为真时抛出异常
   */
  public static void is(Boolean expression, Integer code, Object... arguments) {
    if (expression) {
      throwExceptionWithCodeAndArguments(code, arguments);
    }
  }

  public static void is(Boolean expression, Integer code, HttpStatus status, Object... arguments) {
    if (expression) {
      throwExceptionWithCodeAndArguments(code, status, arguments);
    }
  }

  /**
   * 表达式是否为假，当为假时抛出异常
   */
  public static void not(Boolean expression, Integer code, Object... arguments) {
    is(!expression, code, arguments);
  }

  public static void not(Boolean expression, Integer code, HttpStatus status, Object... arguments) {
    is(!expression, code, status, arguments);
  }


  private static void throwExceptionWithCodeAndArguments(Object code, Object... arguments) {
    throwExceptionWithCodeAndArguments(code, Status.EXPECTATION_FAILED, arguments);
  }

  private static void throwExceptionWithCodeAndArguments(Object code, StatusType status,
      Object... arguments) {
    //当code为Integer时才可能有参数
    if (null == arguments) {
      //为防止没有传入时出现异常
      arguments = new Object[]{};
    }
    //处理code为String的情况
    if (code instanceof String) {
      throw new ApiException(code.toString());
    } else if (code instanceof Integer) {
      throwException(Integer.valueOf(code.toString()), status, arguments);
    }
  }


  /**
   * 抛出异常
   */
  public static void throwException(Integer code, Object... arguments) {
    throw new ApiException(code, arguments);
  }

  /**
   * 抛出带错误状态的异常
   */
  public static void throwException(Integer code, StatusType httpStatus, Object... arguments) {
    throw new ApiException(code, httpStatus, arguments);
  }

  /**
   * 抛出异常
   */
  public static void throwException(String code) {
    throw new ApiException(code);
  }


}
