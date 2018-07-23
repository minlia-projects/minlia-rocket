package com.minlia.rocket.i18n;

import com.minlia.rocket.context.ContextHolder;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 添加检查是否有启用配置 system.context-holder.enabled=true 如果没启用则抛出开发过程异常
 *
 * @author will
 */
@Slf4j
public class Lang {

  public static String get(String key) {
    return get(key, new Object[]{}, LocaleContextHolder.getLocale());
  }

  public static String get(String key, Object[] arguments) {
    return get(key, arguments, LocaleContextHolder.getLocale());
  }

  public static String get(String key, Object[] arguments, Locale locale) {
    String result = "{{" + key + "}}";
    try {
      ApplicationContext context = ContextHolder.getContext();
      if(null!=context) {
        result = context.getBean(MessageSource.class).getMessage(key, arguments, result, locale);
      }
      log.warn("ContextHolder is null at this time, please import minlia-rocket-starter-context first");
    } catch (org.springframework.context.NoSuchMessageException e) {
      log.warn("No translated message found for key: {}", key);
//      log.warn("No translated message found for key: {}" , key);
    }
    return result;
  }


}
