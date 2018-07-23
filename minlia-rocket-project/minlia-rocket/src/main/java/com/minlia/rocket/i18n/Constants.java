package com.minlia.rocket.i18n;

/**
 * @author will
 */
public class Constants {

  public static final String EXCEPTION_PREFIX = "Exceptions";
  public static final String X_LANG = "X-LANG";

  public Constants() {
    throw new AssertionError();
  }

  public enum LanguageTypes {
    /**
     * 异常 API 码
     */
    ExceptionsApiCode,
    /**
     * 消息
     */
    Message,
    /**
     * 验证消息
     */
    ValidationMessages
  }

}
