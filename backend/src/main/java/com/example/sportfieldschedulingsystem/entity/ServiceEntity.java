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
    private List<FieldEntity> fieldEntityList = new ArrayList<>();

    public List<FieldEntity> getFieldEntityList() {
        return fieldEntityList;
    }

    public void setFieldEntityList(List<FieldEntity> fieldEntityList) {
        this.fieldEntityList = fieldEntityList;
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
}
