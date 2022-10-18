package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedbackEntity extends BaseEntity{

    @Column(columnDefinition = "nvarchar(500)")
    private String feedbackDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity fieldEntity;

    public FeedbackEntity() {
    }

    public FeedbackEntity(Long id) {
        super(id);
    }

    public String getFeedbackDetail() {
        return feedbackDetail;
    }

    public void setFeedbackDetail(String feedbackDetail) {
        this.feedbackDetail = feedbackDetail;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public FieldEntity getFieldEntity() {
        return fieldEntity;
    }

    public void setFieldEntity(FieldEntity fieldEntity) {
        this.fieldEntity = fieldEntity;
    }
}
