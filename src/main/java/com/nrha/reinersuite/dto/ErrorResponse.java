package com.nrha.reinersuite.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@JsonPropertyOrder({"status", "code", "error", "message", "timestamp"})
public class ErrorResponse {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Throwable throwable;
    private final ZonedDateTime timestamp;
    private int status;
    private HttpStatus code;
    private String error;
    private String message;

    public ErrorResponse(HttpStatus code, String error, String message) {
        this.code = code;
        this.status = code.value();
        this.error = error;
        this.message = message;
        this.timestamp = ZonedDateTime.now();
    }

    public ErrorResponse(Exception e) {
        this.error = getErrorFromException(e);
        this.message = getMessageFromException(e);
        this.throwable = e;
        this.timestamp = ZonedDateTime.now();
    }

    public static ErrorResponse of(HttpStatus httpStatus, String error, String message) {
        return new ErrorResponse(httpStatus, error, message);
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
        this.status = code.value();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    @JsonIgnore
    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    protected String getErrorFromException(Throwable t) {

        StringBuilder sb = new StringBuilder();

        if (t != null)
            sb.append(t.getClass().getName());

        return sb.toString();
    }

    protected String getMessageFromException(Throwable t) {

        StringBuilder sb = new StringBuilder();

        if (t != null)
            sb.append(t.getMessage().trim());

        return sb.toString();
    }
}
