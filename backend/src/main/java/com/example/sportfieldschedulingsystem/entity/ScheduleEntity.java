package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "schedule_entity")
public class ScheduleEntity extends BaseEntity {

    @Column
    private byte status;

    @Column
    private Timestamp bookingDate;

    @Column
    private Timestamp checkinDate;

    @Column
    private Time timeRemaining;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity fieldEntityS;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDetailEntity orderDetailEntity;

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Timestamp getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Timestamp checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Time getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Time timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public FieldEntity getFieldEntityS() {
        return fieldEntityS;
    }

    public void setFieldEntityS(FieldEntity fieldEntityS) {
        this.fieldEntityS = fieldEntityS;
    }

    public OrderDetailEntity getOrderDetailEntity() {
        return orderDetailEntity;
    }

    public void setOrderDetailEntity(OrderDetailEntity orderDetailEntity) {
        this.orderDetailEntity = orderDetailEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}