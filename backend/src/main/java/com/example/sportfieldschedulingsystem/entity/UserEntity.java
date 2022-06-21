package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = false)
    private byte status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleEntities = new ArrayList<>();


    @OneToMany(mappedBy = "userEntity")
    private List<FeedbackEntity> feedbackEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<OrderEntity> orderEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<ScheduleEntity> schedulingEntities = new ArrayList<>();

    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public List<FeedbackEntity> getFeedbackEntities() {
        return feedbackEntities;
    }

    public void setFeedbackEntities(List<FeedbackEntity> feedbackEntities) {
        this.feedbackEntities = feedbackEntities;
    }

    public List<ScheduleEntity> getSchedulingEntities() {
        return schedulingEntities;
    }

    public void setSchedulingEntities(List<ScheduleEntity> schedulingEntities) {
        this.schedulingEntities = schedulingEntities;
    }

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
