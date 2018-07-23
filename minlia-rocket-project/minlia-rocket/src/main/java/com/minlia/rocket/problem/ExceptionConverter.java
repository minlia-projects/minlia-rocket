package com.minlia.rocket.problem;

import org.apache.commons.lang3.StringUtils;

public class ExceptionConverter {


  /**
   * @param code
   * @return
   */
  public static String convert(Object code) {
    String result = "";
    if (null != code) {
      result = String
          .format("%s%s%s", "ExceptionsApiCode", getClassForStatic().getSimpleName(), code);
      result = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(result), ".")
          .toLowerCase();
    }
    return result;
  }

  private static final Class<?> getClassForStatic() {
    return new Object() {
      public Class<?> getClassForStatic() {
        return this.getClass();
      }
    }.getClassForStatic();
  }


}