package com.minlia.rocket.autoconfigure.ignored;

import com.minlia.rocket.ignored.SystemIgnoredListProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author will
 */
@Configuration
@ConditionalOnProperty(prefix = "system.ignored.", name = "urls", havingValue = "true")
@EnableConfigurationProperties(value = {SystemIgnoredListProperties.class})
public class SystemIgnoredListAutoConfiguration {

}
