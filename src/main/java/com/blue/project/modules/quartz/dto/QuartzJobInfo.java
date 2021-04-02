package com.blue.project.modules.quartz.dto;

import org.quartz.Trigger;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class QuartzJobInfo {
    private ZonedDateTime startTime;
    private ZonedDateTime nextFireTime;
    private ZonedDateTime previousFireTime;
    private String jobName;
    private String group;
    private String status;

    public QuartzJobInfo(String jobName, String group, String status, Trigger trigger) {
        this.jobName = jobName;
        this.group = group;
        this.status = status;

        this.startTime = ZonedDateTime.ofInstant(trigger.getStartTime().toInstant(), ZoneId.systemDefault());
        this.nextFireTime = ZonedDateTime.ofInstant(trigger.getNextFireTime().toInstant(), ZoneId.systemDefault());
        this.previousFireTime = ZonedDateTime.ofInstant(trigger.getPreviousFireTime().toInstant(), ZoneId.systemDefault());
    }

    public QuartzJobInfo(String jobName, String group, String status) {
        this.jobName = jobName;
        this.group = group;
        this.status = status;

        this.startTime = null;
        this.nextFireTime = null;
        this.previousFireTime = null;
    }


    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(ZonedDateTime nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public ZonedDateTime getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(ZonedDateTime previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
