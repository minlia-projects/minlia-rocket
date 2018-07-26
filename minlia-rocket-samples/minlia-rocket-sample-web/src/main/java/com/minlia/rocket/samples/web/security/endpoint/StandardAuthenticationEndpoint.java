package com.minlia.rocket.samples.web.security.endpoint;

import com.minlia.rocket.security.JwtProperties;
import com.minlia.rocket.security.credential.LoginCredential;
import com.minlia.rocket.security.security.jwt.JwtAccessToken;
import com.minlia.rocket.security.security.jwt.JwtAuthenticationResponseBody;
import com.minlia.rocket.security.security.jwt.JwtRefreshToken;
import com.minlia.rocket.security.security.jwt.TokenProvider;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 */
@Api(tags = "Uaa Authentication", description = "Authentication")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/open")
@ConditionalOnProperty(
    prefix = "system.security",
    name = {"enabled"},
    havingValue = "true"
)
public class StandardAuthenticationEndpoint {

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  @Lazy
  private AuthenticationManager authenticationManager;

  @ApiOperation(value = "Authentication", notes = "Authentication")
  @RequestMapping(value = "authentication", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<StatefulBody> authentication(@Valid @RequestBody LoginCredential credential) {

    //构造一个用于认证的Authentication对象
    UsernamePasswordAuthenticationToken toBeAuthenticated =
        new UsernamePasswordAuthenticationToken(credential.getLogin(), credential.getPassword());

    //使用spring security authenticationManager 进行认证
    //如需定制验证流程，请自定义实现 AuthenticationService
    Authentication authentication = this.authenticationManager.authenticate(toBeAuthenticated);

    //认证完成后，进行返回封装

    JwtAccessToken accessToken = tokenProvider
        .createJwtAccessToken(credential.getLogin(), authentication.getAuthorities(), true);
    JwtRefreshToken refreshToken = tokenProvider.createJwtRefreshToken(credential.getLogin());

    JwtAuthenticationResponseBody body = JwtAuthenticationResponseBody.builder()
        .accessToken(accessToken).refreshToken(refreshToken).build();

    return Responses.ok(SuccessResponseBody.builder().payload(body).build());
  }
}
