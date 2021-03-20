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

    public StatusMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getOk() {
        return ok;
    }

    public StatusMessage setOk(Boolean ok) {
        this.ok = ok;
        return this;
    }
}
