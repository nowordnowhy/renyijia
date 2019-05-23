package com.renyijia.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-23
 * @email : zhou_wenya@163.com
 */
@Service
public class MyUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User '" + username + "' not found");
//        }
        String password="";
        List<Role> roles=new ArrayList<>();
        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(password)//
                .authorities(roles)//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
