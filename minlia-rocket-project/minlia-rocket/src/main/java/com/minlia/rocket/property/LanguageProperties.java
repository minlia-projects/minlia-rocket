package com.minlia.rocket.property;

import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import org.springframework.boot.convert.DurationUnit;

@Data
public class LanguageProperties {

  private String languageRequestParameterName = "lang";
  private String languageRequestHeaderParameterName = "X-Request-Lang";

  private String basename = "i18n/messages";

  private Charset encoding = Charset.forName("UTF-8");

  @DurationUnit(ChronoUnit.SECONDS)
  private Duration cacheDuration = Duration.ofDays(1L);

  private boolean fallbackToSystemLocale = true;
  private boolean alwaysUseMessageFormat = false;
  private boolean useCodeAsDefaultMessage = true;


}
