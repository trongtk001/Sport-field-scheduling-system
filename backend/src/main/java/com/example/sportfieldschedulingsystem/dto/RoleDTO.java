package com.example.sportfieldschedulingsystem.dto;


public class RoleDTO extends BaseDTO{

    private String roleName;

    public RoleDTO() {
    }

    public RoleDTO(Long id) {
        super(id);
    }

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
