package com.minlia.rocket.security.rebecca.service.dto;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.minlia.rocket.enumeration.Status;
import com.minlia.rocket.security.rebecca.entity.Permission;
import com.minlia.rocket.security.rebecca.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author rdteam
 */
@Slf4j
public class SecurityUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(User user) {

        if(user!=null) {
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setStatus(user.getStatus());
            this.setRoles(user.getRoles());
            this.setPermissions(user.getPermissions());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Permission> permissions = this.getPermissions();
        if(CollectionUtils.isNotEmpty(permissions)&&permissions.get(0)!=null){
            for (Permission permission : permissions) {
                authorityList.add(new SimpleGrantedAuthority(permission.getTitle()));
            }
        }
        return authorityList;
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return !Status.DISABLED.equals(this.getStatus());
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {

        return Status.ENABLED.equals(this.getStatus());
    }
}