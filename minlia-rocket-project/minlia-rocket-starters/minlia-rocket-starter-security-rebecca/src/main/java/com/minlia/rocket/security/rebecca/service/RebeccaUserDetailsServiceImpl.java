package com.minlia.rocket.security.rebecca.service;

import com.minlia.rocket.problem.ApiPreconditions;
import com.minlia.rocket.security.rebecca.code.RebeccaApiCode;
import com.minlia.rocket.security.rebecca.entity.User;
import com.minlia.rocket.security.rebecca.service.dto.SecurityUserDetails;
import com.minlia.rocket.security.rebecca.service.jpa.UserJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @author rdteam
 */
@Slf4j
public class RebeccaUserDetailsServiceImpl implements UserDetailsService {

//    @Value("${system.loginAfterTime}")
//    private Integer loginAfterTime;

//    @Autowired
//    private StringRedisTemplate redisTemplate;

  @Autowired
  private UserJpaService userJpaService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Loading user by RebeccaUserDetailsService {} with username: ",username);

//        String flagKey = "loginFailFlag:"+username;
//        String value = redisTemplate.opsForValue().get(flagKey);
//        if(StrUtil.isNotBlank(value)){
//            //超过限制次数
//            throw new LoginFailLimitException("登录错误次数超过限制，请"+loginAfterTime+"分钟后再试");
//        }
    User user = userJpaService.findByUsername(username);
    ApiPreconditions.isNull(user,RebeccaApiCode.USERNAME_NOT_FOUND);

    return new SecurityUserDetails(user);
  }
}
