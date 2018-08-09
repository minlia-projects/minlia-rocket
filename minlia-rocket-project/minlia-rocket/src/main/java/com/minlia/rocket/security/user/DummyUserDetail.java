package com.minlia.rocket.security.user;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class DummyUserDetail extends BuiltinUserBody implements UserDetails {

  private String password;
  private boolean enabled;
  private boolean locked;
  private boolean expired;

  @Override
  public boolean isAccountNonExpired() {
    return !expired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !expired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
