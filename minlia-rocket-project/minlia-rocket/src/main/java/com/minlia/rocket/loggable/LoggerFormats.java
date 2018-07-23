package com.minlia.rocket.loggable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p> Logger message formats that can be replaced with the defaults. </p> There are list of
 * placeholder's that can be used: <ul> <li>${method.name} - method name</li> <li>${method.args} -
 * method arguments</li> <li>${method.result} - method results</li> <li>${method.duration} - method
 * runtime duration</li> <li>${method.warn.duration} - method runtime warning duration to be
 * displayed</li> <li>${error.class.name} - exception class name</li> <li>${error.message} -
 * exception message</li> <li>${error.source.class.name} - exception source class name</li>
 * <li>${error.source.line} - exception source line number that cause the exception</li> </ul>
 *
 * @author will
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoggerFormats {

  private String enter;
  private String warnBefore;
  private String warnAfter;
  private String after;
  private String error;

}
