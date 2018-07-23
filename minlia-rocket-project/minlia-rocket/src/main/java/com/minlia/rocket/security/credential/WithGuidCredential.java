package com.minlia.rocket.security.credential;

/**
 * Created by will on 8/14/17.
 */
public interface WithGuidCredential extends Credential {

  /**
   * Global User Identity
   */
  public String getGuid();


}
