package com.example.sportfieldschedulingsystem.mapper;

import com.example.sportfieldschedulingsystem.dto.UserDTO;
import com.example.sportfieldschedulingsystem.entity.RoleEntity;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import com.example.sportfieldschedulingsystem.repository.RoleRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    public UserMapper(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        modelMapper.addMappings(toDTOEntityPropertyMap);
    }

    PropertyMap<UserEntity, UserDTO> toDTOEntityPropertyMap = new PropertyMap<UserEntity, UserDTO>() {
        @Override
        protected void configure() {
            skip(destination.getPassword());
        }
    };

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setRoleEntities(toRoleEntity(dto.getRoles()));
        return entity;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);

       if (dto.getRoles() != null) {
           entity.setRoleEntities(toRoleEntity(dto.getRoles()));
       }

        return entity;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        List<String> roles = new ArrayList<>();

        entity.getRoleEntities().forEach(roleEntity -> {
            roles.add(roleEntity.getRoleName());
        });

        dto.setRoles(roles);
        return dto;
    }

    public List<UserEntity> toEntityList(List<UserDTO> fieldDTOS) {
        List<UserEntity> userEntities = new ArrayList<>();

        fieldDTOS.forEach((dto -> {
            userEntities.add(toEntity(dto));
        }));

        return userEntities;
    }

    public List<UserDTO> toDTOList(List<UserEntity> fieldEntities) {
        List<UserDTO> userDTOS = new ArrayList<>();

        fieldEntities.forEach(entity -> {
            userDTOS.add(toDTO(entity));
        });

        return userDTOS;
    }

    private List<RoleEntity> toRoleEntity(List<String> roles) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        roles.forEach(role -> {
            RoleEntity roleEntity = roleRepository.findOneByRoleName(role);
            if(roleEntity == null) {
                roleEntity = new RoleEntity();
                roleEntity.setRoleName(role);
            }
            roleEntities.add(roleEntity);
        });
        return roleEntities;
    }

}
