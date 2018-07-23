package com.minlia.rocket.data.util;

import com.google.common.base.CaseFormat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;

public class ReflectUtil {


  public static Object setValue(Object instance, String property, Object value) {
    try {

//      Field field=instance.getClass().getDeclaredField(property);
//      field.setAccessible(true);
//      field.set(instance,value);

      Method setNameMethod = instance.getClass().getMethod("set" + StringUtils
              .capitalize(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, property))
          , new Class<?>[]{String.class});
      instance = setNameMethod.invoke(instance, value);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
//    } catch (NoSuchFieldException e) {
//      e.printStackTrace();
//    } catch (IllegalAccessException e) {
//      e.printStackTrace();
    }
    return instance;
  }
}
