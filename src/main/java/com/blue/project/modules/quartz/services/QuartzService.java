package com.blue.project.modules.quartz.services;

import com.blue.project.modules.quartz.jobs.HelloWorldJob;
import name.mymiller.utils.ListUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuartzService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void scheduleJobs() {
        logger.info("************************* Scheduling Jobs ***************************************");
        JobDetail helloWorldJobDetail = JobBuilder.newJob(HelloWorldJob.class)
                .storeDurably()
                .withIdentity("HelloWorld", "System")
                .withDescription("Test Job")
                .build();


        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(helloWorldJobDetail)
                .withIdentity("HelloWorldTrigger", "System")
                .withDescription("Test Trigger")
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever()).build();

        try {
            this.scheduleJob(helloWorldJobDetail,trigger);
        } catch (SchedulerException e) {
           logger.error("Failed to schedule HelloWorld Job",e);
        }
    }

    public void addCalendar(String calendarName) throws SchedulerException {
        Calendar calendar = this.getCalendar(calendarName);
        if(calendar != null) {
            this.schedulerFactoryBean.getScheduler().addCalendar(calendarName, calendar, true, true);
        }
    }

    public boolean deleteCalendar(String calendarName) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().deleteCalendar(calendarName);
    }

    public List<String> getCalendarNames() throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getCalendarNames();
    }

    public void addJob(JobDetail jobDetail, boolean replace) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().addJob(jobDetail,replace);
    }

    public boolean checkIfJobKeyExists(JobKey jobKey) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().checkExists(jobKey);
    }

    public boolean checkIfTriggerExists(TriggerKey triggerKey) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().checkExists(triggerKey);
    }

    public boolean deleteJob(String jobName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().deleteJob(new JobKey(jobName,group));
    }

    public JobDetail getJobDetail(String jobName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getJobDetail(new JobKey(jobName,group));
    }

    public List<String> getJobGroupNames() throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getJobGroupNames();
    }

    public Trigger getTrigger(String triggerName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getTrigger(new TriggerKey(triggerName,group));
    }

    public List<String> getTriggerGroupNames() throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getTriggerGroupNames();
    }

    public List<? extends Trigger> getTriggersOfJob(String jobName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getTriggersOfJob(new JobKey(jobName,group));
    }

   public void pauseAll() throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().pauseAll();
    }

    public void pauseJob(String jobName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().pauseJob(new JobKey(jobName,group));
    }

    public void pauseTrigger(String triggerName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().pauseTrigger(new TriggerKey(triggerName,group));
    }

    public void rescheduleJob(String triggerName, String group, Trigger newTrigger) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().rescheduleJob(new TriggerKey(triggerName,group),newTrigger);
    }

    public void resumeAll() throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().resumeAll();
    }

    public void resumeJob(String jobName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().resumeJob(new JobKey(jobName,group));
    }

    public void resumeTrigger(String triggerName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().resumeTrigger(new TriggerKey(triggerName,group));
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().scheduleJob(jobDetail,trigger);
    }

    public void triggerJob(String jobName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().triggerJob(new JobKey(jobName,group));
    }

    public void triggerJob(String jobName, String group, JobDataMap data) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().triggerJob(new JobKey(jobName,group),data);
    }

    public boolean unscheduleJob(String triggerName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().unscheduleJob(new TriggerKey(triggerName,group));
    }

    public boolean isJobRunning(String jobName, String group) throws SchedulerException {
        return ListUtils.safe(this.schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs())
                .stream()
                .filter(context -> context.getJobDetail().getKey().getGroup().equals(group))
                .filter(context -> context.getJobDetail().getKey().getName().equals(jobName))
                .findFirst().isPresent();
    }


    public String getJobState(String jobName, String group) {
        return null;
    }


    private Calendar getCalendar(String calendarName) {
        return null;
    }
}
