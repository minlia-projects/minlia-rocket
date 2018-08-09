package com.minlia.rocket.security.credential;

/**
 *
 * @author will
 * @date 8/14/17
 */
public interface WithUsernameCredential extends WithGuidCredential {

  /**
   * 用户名
   */
  public String getUsername();

}
