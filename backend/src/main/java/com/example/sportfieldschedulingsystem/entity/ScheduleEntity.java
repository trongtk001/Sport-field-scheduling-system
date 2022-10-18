package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "schedule")
public class ScheduleEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String note;

    @Column
    private Date bookingDate;

    @Column
    private byte status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "scheduleEntity")
    private List<ScheduleDetailEntity> scheduleDetailEntities = new ArrayList<>();

    public ScheduleEntity() {
    }

    public ScheduleEntity(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<ScheduleDetailEntity> getScheduleDetailEntities() {
        return scheduleDetailEntities;
    }

    public void setScheduleDetailEntities(List<ScheduleDetailEntity> scheduleDetailEntities) {
        this.scheduleDetailEntities = scheduleDetailEntities;
    }
}