package com.example.sportfieldschedulingsystem.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[role]")
public class RoleEntity extends BaseEntity implements GrantedAuthority {


    @Column
    private String roleName;

    @ManyToMany(mappedBy = "roleEntities")
    private List<UserEntity> userEntities = new ArrayList<>();

    public RoleEntity() {
    }

    public RoleEntity(Long id) {
        super(id);
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}