package com.example.sportfieldschedulingsystem.security;

import com.example.sportfieldschedulingsystem.entity.UserEntity;
import com.example.sportfieldschedulingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findOneByUserName(username).orElse(null);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return UserDetailsImpl.build(userEntity);
    }
}
