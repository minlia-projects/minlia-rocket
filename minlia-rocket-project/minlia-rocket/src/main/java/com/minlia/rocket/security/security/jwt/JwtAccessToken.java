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
@ApiModel(value = "Jwt令牌")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAccessToken implements Body {

  /**
   * 认证之后的 TOKEN
   */
  @ApiModelProperty(value = "认证之后的 TOKEN")
  private String value;

  /**
   * 将在此时刻过期
   */
  @ApiModelProperty(value = "将在此时刻过期")
  private Long expiration;
  /**
   * 颁发时间
   */
  @ApiModelProperty(value = "颁发时间")
  private Long issuedAt;

//  /**
//   * 将要过期时使用此 TOKEN 进行权限延期申请
//   */
//  @ApiModelProperty(value = "将要过期时使用此 TOKEN 进行权限延期申请")
//  private String refreshToken;


}