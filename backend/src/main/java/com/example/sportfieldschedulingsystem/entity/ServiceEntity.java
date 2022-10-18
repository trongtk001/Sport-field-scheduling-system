package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceEntity extends BaseEntity{
    @Column(columnDefinition = "nvarchar(50)")
    private String serviceName;
    @Column
    private byte status;
    @Column
    private double price;

    @ManyToMany(mappedBy = "serviceEntities")
    private List<FieldEntity> fieldEntities = new ArrayList<>();

    @ManyToMany(mappedBy = "serviceEntities")
    private List<ScheduleDetailEntity> scheduleDetailEntities = new ArrayList<>();

    public ServiceEntity() {
    }

    public ServiceEntity(Long id) {
        super(id);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<FieldEntity> getFieldEntities() {
        return fieldEntities;
    }

    public void setFieldEntities(List<FieldEntity> fieldEntities) {
        this.fieldEntities = fieldEntities;
    }

    public List<ScheduleDetailEntity> getScheduleDetailEntities() {
        return scheduleDetailEntities;
    }

    public void setScheduleDetailEntities(List<ScheduleDetailEntity> scheduleDetailEntities) {
        this.scheduleDetailEntities = scheduleDetailEntities;
    }
}
