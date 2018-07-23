package com.minlia.rocket.security.credential;

/**
 * Created by will on 8/14/17.
 */
public interface WithUsernameCredential extends WithGuidCredential {

  /**
   * 用户名
   */
  public String getUsername();

}
