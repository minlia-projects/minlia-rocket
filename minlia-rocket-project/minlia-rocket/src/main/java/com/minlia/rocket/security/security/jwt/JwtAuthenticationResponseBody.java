package com.minlia.rocket.security.security.jwt;

import com.minlia.rocket.stateful.body.Body;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证后的返回
 *
 * @author will
 */
@ApiModel(value = "JwtAuthenticationResponseBody")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponseBody implements Body {

  private JwtAccessToken accessToken;

  private JwtRefreshToken refreshToken;


}
