package com.minlia.rocket.security.endpoint;

import com.minlia.rocket.pretend.annotation.Pretend;
import com.minlia.rocket.security.context.SecurityUserHolder;
import com.minlia.rocket.security.user.BuiltinUserBody;
import com.minlia.rocket.security.user.BuiltinUserMapper;
import com.minlia.rocket.security.user.DummyUserDetail;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 */
@Api(tags = "Builtin-Me", description = "Me")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/security/builtin")
@ConditionalOnProperty(
    prefix = "system.security",
    name = {"enabled"},
    havingValue = "true"
)
@ConditionalOnMissingBean(value = {BuiltinMeEndpoint.class})
public class BuiltinMeEndpoint {



  @Autowired
  BuiltinUserMapper builtinUserMapper;

  @Pretend(value = "payload.password")
  @ApiOperation(value = "Me", notes = "Me",httpMethod = "GET")
  @RequestMapping(value = "me", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<StatefulBody> me() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User userDetails =null;
    if (!(auth instanceof AnonymousAuthenticationToken)) {
      userDetails=  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    String username=SecurityUserHolder.getCurrentUsername().get();
    DummyUserDetail entity=new DummyUserDetail();
    entity.setUsername(username);


    entity.setAuthorities(userDetails.getAuthorities());
    entity.setEnabled(userDetails.isEnabled());
    entity.setExpired(!userDetails.isAccountNonExpired());
    entity.setLocked(!userDetails.isAccountNonLocked());
    entity.setActivated(true);


    BuiltinUserBody body=builtinUserMapper.entityToModel(entity);

    return Responses.ok(SuccessResponseBody.builder().payload(body).build());
  }
}
