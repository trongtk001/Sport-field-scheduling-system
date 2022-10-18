package com.example.sportfieldschedulingsystem.dto;

import javax.persistence.Column;

public class ServiceDTO extends BaseDTO{

    private String serviceName;
    private byte status;
    private double price;

    public ServiceDTO() {
    }

    public ServiceDTO(Long id) {
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
}
