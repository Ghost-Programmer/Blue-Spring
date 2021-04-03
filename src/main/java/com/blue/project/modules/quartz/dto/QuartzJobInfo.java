package com.blue.project.modules.quartz.dto;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
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
    private String triggerDescription;
    private String jobDescription;
    private String calendar;
    private String cronExpression;
    private Integer priority;

    public QuartzJobInfo(JobDetail jobDetail, Trigger trigger, String status) {
        this.jobName = jobDetail.getKey().getName();
        this.group = jobDetail.getKey().getGroup();
        this.jobDescription = jobDetail.getDescription();
        this.triggerDescription = trigger.getDescription();
        if(trigger instanceof CronTrigger) {
            this.cronExpression = ((CronTrigger)trigger).getCronExpression();
        }

        this.priority = trigger.getPriority();
        this.calendar = trigger.getCalendarName();
        this.status = status;
        this.startTime = ZonedDateTime.ofInstant(trigger.getStartTime().toInstant(), ZoneId.systemDefault());
        this.nextFireTime = ZonedDateTime.ofInstant(trigger.getNextFireTime().toInstant(), ZoneId.systemDefault());
        this.previousFireTime = ZonedDateTime.ofInstant(trigger.getPreviousFireTime().toInstant(), ZoneId.systemDefault());
    }

    public QuartzJobInfo(JobDetail jobDetail, String status) {
        this.jobName = jobDetail.getKey().getName();
        this.group = jobDetail.getKey().getGroup();
        this.jobDescription = jobDetail.getDescription();
        this.status = status;

        this.calendar = null;
        this.triggerDescription = null;
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

    public String getTriggerDescription() {
        return triggerDescription;
    }

    public void setTriggerDescription(String triggerDescription) {
        this.triggerDescription = triggerDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
