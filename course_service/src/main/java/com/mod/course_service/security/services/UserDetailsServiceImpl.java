package com.mod.course_service.security.services;


import com.mod.course_service.document.auth.Authorities;
import com.mod.course_service.document.auth.Login;
import com.mod.course_service.repository.auth.AuthoritiesRepository;
import com.mod.course_service.repository.auth.LoginRepository;
import com.mod.course_service.repository.auth.RoleRepository;
import com.mod.course_service.security.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    RoleRepository roleRepository;

    private final PasswordConfig passwordConfig;

    @Autowired
    public UserDetailsServiceImpl(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login user = loginRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());


        user.getRoles().forEach(role -> {
            for (Authorities authorities1 : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authorities1.getAuthority()));
            }
        });

        return UserDetailsImpl.build(user);
    }

}