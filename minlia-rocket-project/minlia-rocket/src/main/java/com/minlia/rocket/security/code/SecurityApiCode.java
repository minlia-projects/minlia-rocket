package com.minlia.rocket.security.code;


import com.minlia.rocket.i18n.ApiCode;

public class SecurityApiCode extends ApiCode {


  /**
   * 定义本模块代码基数
   */
  public static final int BASE_CODE = BASED_ON + 3000;
  public static final int ACCESS_TOKEN_INVALID = BASE_CODE + 1;


  public static final int PASSWORD_INVALID = BASE_CODE + 11;
  public static final int ACCOUNT_EXPIRED = BASE_CODE + 12;
  public static final int ACCOUNT_LOCKED = BASE_CODE + 13;
  public static final int ACCOUNT_DISABLED = BASE_CODE + 14;
  public static final int CREDENTIALS_EXPIRED = BASE_CODE + 15;


  public SecurityApiCode() {
    throw new AssertionError();
  }


}
