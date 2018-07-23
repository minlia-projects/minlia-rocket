package com.minlia.rocket.security.security;//package com.minlia.cloud.security.security;
//
//import com.minlia.cloud.exception.Intrinsics;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.zalando.problem.Status;
//import org.zalando.problem.StatusType;
//
///**
// * Authenticate a user from the database.
// */
//@Slf4j
//public class DummyUserDetailsService implements UserDetailsService {
//
//
//    public DummyUserDetailsService( ) {
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(final String login)  {
//
////        log.debug("Authenticating {}", login);
////        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
////        Optional<User> userByEmailFromDatabase = userRepository.findOneWithAuthoritiesByEmail(lowercaseLogin);
////        return userByEmailFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user)).orElseGet(() -> {
////            Optional<User> userByLoginFromDatabase = userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin);
////            return userByLoginFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user))
////                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
////                    "database"));
////        });
//
////        throw new ApiException(ApiCode.REMOTE_BUSINESS_INVOKED_WITH_FAILURE_RESULT);
////        throw new UserNotActivatedException("User Not Found");
//
////        throw new UsernameNotFoundException("User " + login + " was not found in the " +
////            "database");
//
//        Intrinsics.throwException(ApiCode.AUTHENTICATION_FAILED, Status.BAD_REQUEST);
//        return null;
//    }
//
////    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
////        if (!user.getActivated()) {
////            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
////        }
////        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
////            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
////            .collect(Collectors.toList());
////        return new org.springframework.security.core.userdetails.User(user.getLogin(),
////            user.getPassword(),
////            grantedAuthorities);
////    }
//}
