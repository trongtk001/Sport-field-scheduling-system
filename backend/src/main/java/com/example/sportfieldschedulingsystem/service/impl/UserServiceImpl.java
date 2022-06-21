package com.example.sportfieldschedulingsystem.service.impl;

import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import com.example.sportfieldschedulingsystem.mapper.UserMapper;
import com.example.sportfieldschedulingsystem.repository.UserRepository;
import com.example.sportfieldschedulingsystem.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createNew(UserDTO dto){

        UserEntity entity = userRepository.findOneByUserName(dto.getUserName()).orElse(null);
        if(null == entity) {
            entity = userMapper.toEntity(dto);
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User with the same identity already exists");
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(entity.getUserName(), null, null));
        entity = userRepository.save(entity);
        return userMapper.toDTO(entity);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        UserEntity entity = new UserEntity();
        UserEntity oldEntity = userRepository.findById(dto.getId()).orElse(null);
        if(oldEntity != null) {
            entity = userMapper.toEntity(dto, oldEntity);
            entity = userRepository.save(entity);
        }

        return userMapper.toDTO(entity);
    }

    @Override
    public UserDTO delete(long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO getInfo(long id) {

        UserEntity entity = userRepository.findById(id).orElse(null);

        return userMapper.toDTO(entity);
    }
}
