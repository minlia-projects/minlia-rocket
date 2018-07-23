package com.minlia.rocket.security.security.jwt;

import com.minlia.rocket.stateful.body.Body;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 刷新后的返回
 *
 * @author will
 */
@ApiModel(value = "JwtRefreshTokenResponseBody")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshedTokenResponseBody implements Body {

  private JwtAccessToken accessToken;
//  private JwtRefreshToken refreshToken;

}
