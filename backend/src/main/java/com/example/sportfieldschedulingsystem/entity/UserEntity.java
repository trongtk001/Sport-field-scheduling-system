package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(length = 50, nullable = false)
    private String userName;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(columnDefinition = "nvarchar(100)", nullable = false)
    private String fullName;
    @Column(length = 20)
    private String phone;
    @Column(length = 50)
    private String gmail;
    @Column(columnDefinition = "nvarchar(300)")
    private String address;
    @Column(length = 200)
    private String avatar;
    @Column(nullable = false)
    private byte status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", targetEntity = FeedbackEntity.class)
    private List<FeedbackEntity> feedbackEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", targetEntity = ScheduleEntity.class)
    private List<ScheduleEntity> scheduleEntities = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id) {
        super(id);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public List<FeedbackEntity> getFeedbackEntities() {
        return feedbackEntities;
    }

    public void setFeedbackEntities(List<FeedbackEntity> feedbackEntities) {
        this.feedbackEntities = feedbackEntities;
    }

    public List<ScheduleEntity> getScheduleEntities() {
        return scheduleEntities;
    }

    public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
        this.scheduleEntities = scheduleEntities;
    }

    public List<String> getRolesName() {
        List<String> rolesName = new ArrayList<>();
        roleEntities.forEach(roleEntity -> rolesName.add(roleEntity.getRoleName()));
        return rolesName;
    }
}
