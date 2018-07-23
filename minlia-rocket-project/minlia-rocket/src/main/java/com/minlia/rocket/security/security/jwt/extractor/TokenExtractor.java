package com.minlia.rocket.security.security.jwt.extractor;

public interface TokenExtractor {

  public String extract(String payload);
}
