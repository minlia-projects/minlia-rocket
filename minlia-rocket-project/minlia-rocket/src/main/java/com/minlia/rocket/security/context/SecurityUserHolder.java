package com.minlia.rocket.security.context;

import java.util.Optional;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author will
 */
public class SecurityUserHolder {


  public static Optional<String> getCurrentUsername() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return Optional.ofNullable(securityContext.getAuthentication())
        .map(authentication -> {
          if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
          } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
          }
          return null;
        });
  }


  public static Optional<String> getCurrentUserJwt() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return Optional.ofNullable(securityContext.getAuthentication())
        .filter(authentication -> authentication.getCredentials() instanceof String)
        .map(authentication -> (String) authentication.getCredentials());
  }


  public static boolean isAuthenticated() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
//
//    return Optional.ofNullable(securityContext.getAuthentication())
//        .map(authentication -> authentication.getAuthorities().stream()
//            .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("")))
//        .orElse(false);

    return securityContext.getAuthentication().isAuthenticated();
  }

  public static boolean isCurrentUserInRole(String authority) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return Optional.ofNullable(securityContext.getAuthentication())
        .map(authentication -> authentication.getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
        .orElse(false);
  }

}
