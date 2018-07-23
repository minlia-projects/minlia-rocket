package com.minlia.rocket.security.credential;

/**
 * Created by will on 8/14/17.
 */
public interface WithEmailCredential extends WithGuidCredential {

  /**
   * 邮箱
   */
  public String getEmail();

}
