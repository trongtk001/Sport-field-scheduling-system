package com.example.sportfieldschedulingsystem.security;

import com.example.sportfieldschedulingsystem.entity.RoleEntity;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String userName;
    private String password;
    private String fullName;
    private String gmail;
    private String phone;
    private byte status;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String userName, String password, String fullName, String gmail, String phone, byte status, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.gmail = gmail;
        this.phone = phone;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserEntity user) {
        return new UserDetailsImpl(user.getUserName(), user.getPassword(), user.getFullName(), user.getGmail(), user.getPhone(), user.getStatus(), user.getRoleEntities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status == 1;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<String> getRolesName() {
        return authorities.stream().map(authorities -> authorities.getAuthority()).collect(Collectors.toList());
    }
}
