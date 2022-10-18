package com.example.sportfieldschedulingsystem.mapper;

import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import com.example.sportfieldschedulingsystem.repository.RoleRepository;
import com.example.sportfieldschedulingsystem.security.UserDetailsImpl;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private ModelMapper modelMapper;

    private RoleRepository roleRepository;

    public UserMapper(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        try {
            modelMapper.addMappings(entityToDTOPropertyMap);
            modelMapper.addMappings(detailsToDTOPropertyMap);
            }catch (Exception e) {
                e.printStackTrace();
            }

    }

    PropertyMap<UserEntity, UserDTO> entityToDTOPropertyMap = new PropertyMap<UserEntity, UserDTO>() {
        @Override
        protected void configure() {
            map(source.getRolesName(), destination.getRoles());
            skip(destination.getPassword());
        }
    };


    PropertyMap<UserDetailsImpl, UserDTO> detailsToDTOPropertyMap = new PropertyMap<UserDetailsImpl, UserDTO>() {
        @Override
        protected void configure() {
            map(source.getRolesName(), destination.getRoles());
            skip(destination.getPassword());
        }
    };

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setRoleEntities(dto.getRoles().stream().map(role -> roleRepository.findOneByRoleName(role)).collect(Collectors.toList()));
        return entity;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);

        return entity;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        return dto;
    }

    public UserDTO toDTO(UserDetails userDetails) {
        UserDTO dto = modelMapper.map(userDetails, UserDTO.class);
        return dto;
    }

    public List<UserEntity> toEntityList(List<UserDTO> fieldDTOS) {
        List<UserEntity> entities = new ArrayList<>();

        fieldDTOS.forEach((dto -> {
            entities.add(toEntity(dto));
        }));

        return entities;
    }

    public List<UserDTO> toDTOList(List<UserEntity> fieldEntities) {
        List<UserDTO> dtos = new ArrayList<>();

        fieldEntities.forEach(entity -> {
            dtos.add(toDTO(entity));
        });

        return dtos;
    }
}
