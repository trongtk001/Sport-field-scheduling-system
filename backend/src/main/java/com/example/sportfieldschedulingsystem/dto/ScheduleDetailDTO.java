package com.example.sportfieldschedulingsystem.dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ScheduleDetailDTO extends BaseDTO {

    private Date checkinDate;

    private Time timeRemaining;

    private Long fieldId;

    private List<Long> serviceIDs;

    private Long scheduleID;

    public ScheduleDetailDTO() {
    }

    public ScheduleDetailDTO(Long id) {
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

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public List<Long> getServiceIDs() {
        return serviceIDs;
    }

    public void setServiceIDs(List<Long> serviceIDs) {
        this.serviceIDs = serviceIDs;
    }

    public Long getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Long scheduleID) {
        this.scheduleID = scheduleID;
    }
}
