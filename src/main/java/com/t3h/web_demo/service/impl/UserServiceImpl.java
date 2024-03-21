package com.t3h.web_demo.service.impl;

import com.t3h.web_demo.service.UserService;
import com.t3h.web_demo.storage.entity.UserEntity;
import com.t3h.web_demo.storage.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserDetailsService : co san spring
 * custom UserDetailsService
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        List<String> roleMap = userRepository.findRoleByUserId(user.getUserId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roleMap) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        if (user == null) {
            throw new LoginException("Not found user");
        }

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };


        return userDetails;
    }

    @Override
    public UserEntity findUser(String username, String pw) {
        UserEntity user = userRepository.findByUserName(username);
        if (user == null) {
            return null;
        }
        boolean isMatchPW = passwordEncoder.matches(pw, user.getPassword());
        if (!isMatchPW) {
            return null;
        }

        List<String> roleMap = userRepository.findRoleByUserId(user.getUserId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roleMap) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new TestingAuthenticationToken(user, user.getPassword(), "ROLE_" + roleMap.get(0));
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        return user;
    }
}
