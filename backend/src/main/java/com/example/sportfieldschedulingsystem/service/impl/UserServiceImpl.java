package com.example.sportfieldschedulingsystem.service.impl;

import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import com.example.sportfieldschedulingsystem.mapper.RoleMapper;
import com.example.sportfieldschedulingsystem.mapper.UserMapper;
import com.example.sportfieldschedulingsystem.repository.RoleRepository;
import com.example.sportfieldschedulingsystem.repository.UserRepository;
import com.example.sportfieldschedulingsystem.security.UserDetailsImpl;
import com.example.sportfieldschedulingsystem.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO save(UserDTO dto) {
        UserEntity entity;

        if (dto.getId() != null) {
            entity = userRepository.findById(dto.getId()).orElse(null);
            if(entity != null) {
                entity = userMapper.toEntity(dto, entity);
                entity = userRepository.save(entity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this user");
            }
        } else {
            entity = userRepository.findOneByUserName(dto.getUserName()).orElse(null);
            if(entity == null) {

                entity = userMapper.toEntity(dto);
                entity.setPassword(passwordEncoder.encode(dto.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(entity.getUserName(), null, null));

                entity = userRepository.save(entity);

            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT,"User with the same identity already exists");
            }
        }
        return userMapper.toDTO(entity);
    }

    @Override
    public UserDTO delete(long id) {
        return null;
    }

    @Override
    public UserDTO search(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userMapper.toDTO(userEntity);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userMapper.toDTO(userDetails);
    }

    @Override
    public UserDTO getInfo(long id) {

        UserEntity entity = userRepository.findById(id).orElse(null);

        return userMapper.toDTO(entity);
    }

    @Override
    public UserDTO getInfoByUsername(String username) {
        UserEntity entity = userRepository.findOneByUserName(username).orElse(null);

        return userMapper.toDTO(entity);
    }
}
