package com.minlia.rocket.security.authentication.service;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class DummyUserDetailsService implements UserDetailsService {

  public DummyUserDetailsService() {
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String login) {

    //模拟从数据库里取出的用户信息
    DummyUserDetails dummyUserDetails = new DummyUserDetails();
    dummyUserDetails.setUsername(login);
    dummyUserDetails.setPassword(passwordEncoder.encode("111111"));
    dummyUserDetails.setEnabled(true);
    dummyUserDetails.setExpired(false);
    dummyUserDetails.setLocked(false);
    dummyUserDetails.setAuthorities(getSimpleGrantedAuthorities("a", "b", "c"));
    return dummyUserDetails;
  }

  @Data
  class DummyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private boolean expired;

    Collection<? extends GrantedAuthority> authorities;


    @Override
    public boolean isAccountNonExpired() {
      return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
      return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return !expired;
    }

    @Override
    public boolean isEnabled() {
      return enabled;
    }
  }


  public List<GrantedAuthority> getSimpleGrantedAuthorities(String... rawPermissions) {
    final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    final List<String> permissions = Lists.newArrayList(rawPermissions);
    if (!CollectionUtils.isEmpty(permissions)) {
      for (String permission : permissions) {
        authorities.add(new SimpleGrantedAuthority(permission));
      }
    }
    return authorities;
  }


}
