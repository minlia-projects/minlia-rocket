package com.minlia.rocket.property;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SystemIgnoredListProperties {

  private List<String> urls = new ArrayList<>();
}

