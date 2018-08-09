package com.minlia.rocket.security.authentication.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.minlia.rocket.security.user.BuiltinUserBody;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author will 定义一个默认的用于开发的用户详情<br />
 *
 * 目前还没有标准的用户，所以需要在项目里实现一个用户详情服务类<br />
 */
@Slf4j
public class DummyUserDetailsService implements UserDetailsService {

  public DummyUserDetailsService() {
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String login) {
    log.info("Loading user by system fallback DummyUserDetailsService with username: {}",login);

    //模拟从数据库里取出的用户信息
    User dummyUserDetails = new User(login,passwordEncoder.encode("111111"),true,true,true,true,getSimpleGrantedAuthorities("ROLE_USER", "ROLE_ADMIN"));
//    dummyUserDetails.setUsername(login);
//    dummyUserDetails.setPassword(passwordEncoder.encode("111111"));
//    dummyUserDetails.setEnabled(true);
//    dummyUserDetails.setExpired(false);
//    dummyUserDetails.setLocked(false);
//    dummyUserDetails.setAuthorities(getSimpleGrantedAuthorities("ROLE_USER", "ROLE_ADMIN"));
    return dummyUserDetails;
  }


  public Set<GrantedAuthority> getSimpleGrantedAuthorities(String... rawPermissions) {
    final Set<GrantedAuthority> authorities = Sets.newHashSet();
    final List<String> permissions = Lists.newArrayList(rawPermissions);
    if (!CollectionUtils.isEmpty(permissions)) {
      for (String permission : permissions) {
        authorities.add(new SimpleGrantedAuthority(permission));
      }
    }
    return authorities;
  }


}
