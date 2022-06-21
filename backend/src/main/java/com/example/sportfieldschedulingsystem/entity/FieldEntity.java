package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "field")
public class FieldEntity extends BaseEntity{
    @Column(columnDefinition = "nvarchar(255)")
    private String fieldName;
    @Column(length = 50)
    private String type;
    @Column(columnDefinition = "nvarchar(255)")
    private String address;
    @Column(columnDefinition = "nvarchar(500)")
    private String description;
    @Column
    private Double price;

    @ManyToMany
    @JoinTable(name = "field_service",
            joinColumns = @JoinColumn(name = "field_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceEntity> serviceEntities = new ArrayList<>();

    @OneToMany(mappedBy = "fieldEntityFB")
    private List<FeedbackEntity> feedbackEntities = new ArrayList<>();

    @OneToMany(mappedBy = "fieldEntityS")
    private List<ScheduleEntity> scheduleEntities = new ArrayList<>();


    public List<FeedbackEntity> getFeedbackEntities() {
        return feedbackEntities;
    }

    public void setFeedbackEntities(List<FeedbackEntity> feedbackEntities) {
        this.feedbackEntities = feedbackEntities;
    }

    public List<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public void setServiceEntities(List<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
