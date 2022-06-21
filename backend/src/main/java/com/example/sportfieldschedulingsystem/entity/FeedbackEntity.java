package com.example.sportfieldschedulingsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedbackEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity fieldEntityFB;

    @Column(columnDefinition = "nvarchar(500)")
    private String feedbackDetail;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public FieldEntity getFieldEntityFB() {
        return fieldEntityFB;
    }

    public void setFieldEntityFB(FieldEntity fieldEntityFB) {
        this.fieldEntityFB = fieldEntityFB;
    }

    public String getFeedbackDetail() {
        return feedbackDetail;
    }

    public void setFeedbackDetail(String feedbackDetail) {
        this.feedbackDetail = feedbackDetail;
    }
}
