package com.minlia.rocket.security.credential;

import com.minlia.rocket.stateful.body.Body;
import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 8/15/17
 */
public class BuiltinSigninRequestBody implements Body {

  @NotNull
  private String principal;

  @NotNull
  private String credential;

  public BuiltinSigninRequestBody() {

  }

  public BuiltinSigninRequestBody(String principal, String credential) {
    this.principal = principal;
    this.credential = credential;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public String getCredential() {
    return credential;
  }

  public void setCredential(String credential) {
    this.credential = credential;
  }
}
