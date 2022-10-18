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
    private String thumbnailUrl;
    @Column
    private Double price;
    @Column
    private boolean status;

    @ManyToMany
    @JoinTable(name = "field_service",
            joinColumns = @JoinColumn(name = "field_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceEntity> serviceEntities = new ArrayList<>();

    @OneToMany(mappedBy = "fieldEntity")
    private List<FeedbackEntity> feedbackEntities = new ArrayList<>();

    @OneToOne(mappedBy = "fieldEntity")
    private ScheduleDetailEntity scheduleDetailEntity;

    public FieldEntity() {
    }

    public FieldEntity(Long id) {
        super(id);
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public void setServiceEntities(List<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
    }

    public List<FeedbackEntity> getFeedbackEntities() {
        return feedbackEntities;
    }

    public void setFeedbackEntities(List<FeedbackEntity> feedbackEntities) {
        this.feedbackEntities = feedbackEntities;
    }

    public ScheduleDetailEntity getScheduleDetailEntity() {
        return scheduleDetailEntity;
    }

    public void setScheduleDetailEntity(ScheduleDetailEntity scheduleDetailEntity) {
        this.scheduleDetailEntity = scheduleDetailEntity;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "fieldName='" + fieldName + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", serviceEntities=" + serviceEntities +
                ", feedbackEntities=" + feedbackEntities +
                ", scheduleDetailEntity=" + scheduleDetailEntity +
                '}';
    }
}
