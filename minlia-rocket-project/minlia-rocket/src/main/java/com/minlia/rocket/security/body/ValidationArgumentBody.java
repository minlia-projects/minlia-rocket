package com.minlia.rocket.security.body;

import com.minlia.rocket.stateful.body.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author will
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationArgumentBody implements Body {

  private Authentication authentication;
  private UserDetails userDetails;

}
