package com.example.sportfieldschedulingsystem.api.output;

import java.util.Date;

public class ResponseMessage {
    Date timestamp;
    int status;
    String message;
    String path;

    public ResponseMessage(int status, String message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
