package com.minlia.rocket.security.user;

import com.minlia.rocket.stateful.body.Body;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author will
 * 当没有用户系统时，使用此主体承载用户对象
 */
@Data
public class BuiltinUserBody implements Body {

  private Long id;

  @NotBlank
//  @Size(min = 1, max = 50)
  private String username;

  @Size(max = 50)
  private String nickname;

  @Size(max = 50)
  private String name;

  @Email
  @Size(max = 255)
  private String email;

  @Size(max = 255)
  private String phone;

  @Size(max = 255)
  private String avatar;

  private boolean activated = false;

  @Size(max = 20)
  private String langKey;

  private boolean enabled = true;

  private Collection<GrantedAuthority> authorities;

  private String createdBy;

  private Instant createdDate;

  private String lastModifiedBy;

  private Instant lastModifiedDate;

}
