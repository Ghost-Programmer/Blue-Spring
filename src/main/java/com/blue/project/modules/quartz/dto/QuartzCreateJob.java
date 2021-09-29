package com.blue.project.modules.quartz.dto;

import java.time.ZonedDateTime;

public class QuartzCreateJob {
    private ZonedDateTime calendar;
    private String cronExpression;
    private String code;

    public QuartzCreateJob() {
    }

    public ZonedDateTime getCalendar() {
        return calendar;
    }

    public void setCalendar(ZonedDateTime calendar) {
        this.calendar = calendar;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
