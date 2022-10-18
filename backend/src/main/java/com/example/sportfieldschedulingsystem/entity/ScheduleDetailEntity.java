package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "schedule_detail")
public class ScheduleDetailEntity extends BaseEntity{

    @Column
    private Date checkinDate;

    @Column
    private Time timeRemaining;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity scheduleEntity;

    @OneToOne
    @JoinColumn(name = "field_id")
    private FieldEntity fieldEntity;

    @ManyToMany
    @JoinTable(name = "schedule_detail_service",
            joinColumns = @JoinColumn(name = "schedule_detail_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceEntity> serviceEntities = new ArrayList<>();

    public ScheduleDetailEntity() {
    }

    public ScheduleDetailEntity(Long id) {
        super(id);
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Time getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Time timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }

    public void setScheduleEntity(ScheduleEntity scheduleEntity) {
        this.scheduleEntity = scheduleEntity;
    }

    public FieldEntity getFieldEntity() {
        return fieldEntity;
    }

    public void setFieldEntity(FieldEntity fieldEntity) {
        this.fieldEntity = fieldEntity;
    }

    public List<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public void setServiceEntities(List<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
    }
}
