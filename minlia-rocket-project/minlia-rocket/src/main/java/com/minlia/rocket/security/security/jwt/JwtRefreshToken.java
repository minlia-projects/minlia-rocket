package com.minlia.rocket.security.security.jwt;

import com.minlia.rocket.stateful.body.Body;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author will
 */
@ApiModel(value = "Jwt Refresh Token")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshToken implements Body {

  /**
   * 认证之后的 TOKEN
   */
  @ApiModelProperty(value = "认证之后的 TOKEN")
  private String value;

}