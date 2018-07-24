package com.minlia.rocket.i18n.system;

import com.minlia.rocket.i18n.system.SystemMessageSourceAutoConfiguration.ResourceBundleCondition;
import com.minlia.rocket.i18n.system.repository.TranslationRepository;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link MessageSource}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Eddú Meléndez
 */
@Configuration
//@ConditionalOnMissingBean(value = MessageSource.class, search = SearchStrategy.CURRENT)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Conditional(ResourceBundleCondition.class)
@EnableConfigurationProperties
@Slf4j
@ComponentScan
public class SystemMessageSourceAutoConfiguration {

  private static final Resource[] NO_RESOURCES = {};

  @Bean
  @ConfigurationProperties(prefix = "system.messages")
  public MessageSourceProperties messageSourceProperties() {
    return new MessageSourceProperties();
  }

  @Autowired
//  @Lazy
  private TranslationRepository translationRepository;


  @Primary
  @Bean
  public MessageSource databaseDrivenMessageSource() {

    log.debug("Starting system message source configuration");
    StopWatch watch = new StopWatch();
    watch.start();

    SystemMessageSource systemMessageSource = new SystemMessageSource(
        translationRepository);
    if (null != parentMessageSource()) {
      systemMessageSource.setParentMessageSource(parentMessageSource());
    }

    watch.stop();
    log.debug("Finishing system message source configuration in {} ms",
        watch.getTotalTimeMillis());

    return systemMessageSource;
  }

  //	@Bean
  private MessageSource parentMessageSource() {
    MessageSourceProperties properties = messageSourceProperties();
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    if (StringUtils.hasText(properties.getBasename())) {
      messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
          StringUtils.trimAllWhitespace(properties.getBasename())));
    }
    if (properties.getEncoding() != null) {
      messageSource.setDefaultEncoding(properties.getEncoding().name());
    }
    messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
    Duration cacheDuration = properties.getCacheDuration();
    if (cacheDuration != null) {
      messageSource.setCacheMillis(cacheDuration.toMillis());
    }
    messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
    messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());

    return messageSource;
  }

  protected static class ResourceBundleCondition extends SpringBootCondition {

    private static ConcurrentReferenceHashMap<String, ConditionOutcome> cache = new ConcurrentReferenceHashMap<>();

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
        AnnotatedTypeMetadata metadata) {
      String basename = context.getEnvironment()
          .getProperty("system.messages.basename", "messages");
      ConditionOutcome outcome = cache.get(basename);
      if (outcome == null) {
        outcome = getMatchOutcomeForBasename(context, basename);
        cache.put(basename, outcome);
      }
      return outcome;
    }

    private ConditionOutcome getMatchOutcomeForBasename(ConditionContext context,
        String basename) {
      ConditionMessage.Builder message = ConditionMessage
          .forCondition("ResourceBundle");
      for (String name : StringUtils.commaDelimitedListToStringArray(
          StringUtils.trimAllWhitespace(basename))) {
        for (Resource resource : getResources(context.getClassLoader(), name)) {
          if (resource.exists()) {
            return ConditionOutcome
                .match(message.found("bundle").items(resource));
          }
        }
      }
      return ConditionOutcome.noMatch(
          message.didNotFind("bundle with basename " + basename).atAll());
    }

    private Resource[] getResources(ClassLoader classLoader, String name) {
      String target = name.replace('.', '/');
      try {
        return new PathMatchingResourcePatternResolver(classLoader)
            .getResources("classpath*:" + target + ".properties");
      } catch (Exception ex) {
        return NO_RESOURCES;
      }
    }

  }

}
