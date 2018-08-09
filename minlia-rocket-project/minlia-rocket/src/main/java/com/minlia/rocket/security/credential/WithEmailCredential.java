package com.minlia.rocket.security.credential;

/**
 *
 * @author will
 * @date 8/14/17
 */
public interface WithEmailCredential extends WithGuidCredential {

  /**
   * 邮箱
   */
  public String getEmail();

}
