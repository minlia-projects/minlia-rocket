package com.minlia.rocket.security.credential;

/**
 *
 * @author will
 * @date 8/14/17
 */
public interface WithGuidCredential extends Credential {

  /**
   * Global User Identity
   */
  public String getGuid();


}
