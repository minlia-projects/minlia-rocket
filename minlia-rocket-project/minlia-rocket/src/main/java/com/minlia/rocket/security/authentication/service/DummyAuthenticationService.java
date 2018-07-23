package com.minlia.rocket.security.authentication.service;

import java.util.ArrayList;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class DummyAuthenticationService implements AuthenticationService {

  @Override
  public Authentication authentication(Authentication authentication) {
    return new UsernamePasswordAuthenticationToken(null, null, new ArrayList<>());
  }
}
