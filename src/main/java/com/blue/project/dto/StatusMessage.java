package com.blue.project.dto;

public class StatusMessage {
    String message;
    Boolean ok;

    public StatusMessage() {
    }

    public StatusMessage(String message, Boolean ok) {
        this.message = message;
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
