package com.minlia.rocket.security.credential;

/**
 *
 * @author will
 * @date 8/14/17
 */
public interface WithCellphoneCredential extends WithGuidCredential {

  /**
   * 手机号码
   */
  public String getCellphone();

}
