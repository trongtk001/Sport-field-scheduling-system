package com.example.sportfieldschedulingsystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleDTO extends BaseDTO{

    private String name;

    private Date bookingDate;

    private String note;

    private byte status;

    private String userName;

    private List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();

    public ScheduleDTO() {
        this.bookingDate = new Date();
    }

    public ScheduleDTO(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ScheduleDetailDTO> getScheduleDetailDTOS() {
        return scheduleDetailDTOS;
    }

    public void setScheduleDetailDTOS(List<ScheduleDetailDTO> scheduleDetailDTOS) {
        this.scheduleDetailDTOS = scheduleDetailDTOS;
    }
}
