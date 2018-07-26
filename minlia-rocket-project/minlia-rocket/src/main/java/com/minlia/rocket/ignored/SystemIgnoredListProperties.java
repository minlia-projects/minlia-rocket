package com.minlia.rocket.ignored;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "system.ignored")
public class SystemIgnoredListProperties {

  private List<String> urls = new ArrayList<>();
}
