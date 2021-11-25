package com.blue.project.modules.quartz.services;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.organizations.dao.OrganizationsRepository;
import com.blue.project.modules.organizations.models.Organizations;
import com.blue.project.modules.quartz.dto.QuartzCreateJob;
import com.blue.project.modules.quartz.dto.QuartzJobInfo;
import com.blue.project.modules.quartz.jobs.EventCleanupJob;
import com.blue.project.modules.quartz.jobs.HelloWorldJob;
import com.blue.project.modules.quartz.jobs.SqlJob;
import name.mymiller.utils.ListUtils;
import org.quartz.Calendar;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuartzService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private OrganizationsRepository organizationsRepository;

    @PostConstruct
    public void scheduleJobs() {
        logger.info("************************* Scheduling Jobs ***************************************");
        JobDetail helloWorldJobDetail = JobBuilder.newJob(HelloWorldJob.class)
                .storeDurably()
                .withIdentity("HelloWorld", "System")
                .withDescription("Test Job")
                .build();


        SimpleTrigger helloWorldTrigger = TriggerBuilder.newTrigger()
                .forJob(helloWorldJobDetail)
                .withIdentity("HelloWorldTrigger", "System")
                .withDescription("Test Trigger")
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever()).build();

        JobDetail eventCleanupJobDetail = JobBuilder.newJob(EventCleanupJob.class)
                .storeDurably()
                .withIdentity("EventCleanup", "System")
                .withDescription("Cleanup Event Maintenance Database")
                .build();


        SimpleTrigger eventCleanupTrigger = TriggerBuilder.newTrigger()
                .forJob(eventCleanupJobDetail)
                .withIdentity("EventCleanupTrigger", "System")
                .withDescription("Cleanup Event Maintenance Database")
                .withSchedule(SimpleScheduleBuilder.repeatHourlyForever(12)).build();

        try {
            this.scheduleOrReplaceJob(helloWorldJobDetail, helloWorldTrigger);
            this.scheduleOrReplaceJob(eventCleanupJobDetail, eventCleanupTrigger);
        } catch (SchedulerException e) {
            logger.error("Failed to schedule HelloWorld Job", e);
        }
    }

    public void addCalendar(String calendarName) throws SchedulerException {
        Calendar calendar = this.getCalendar(calendarName);
        if (calendar != null) {
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
        this.schedulerFactoryBean.getScheduler().addJob(jobDetail, replace);
    }

    public boolean checkIfJobKeyExists(JobKey jobKey) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().checkExists(jobKey);
    }

    public boolean checkIfTriggerExists(TriggerKey triggerKey) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().checkExists(triggerKey);
    }

    public boolean deleteJob(String jobName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().deleteJob(new JobKey(jobName, group));
    }

    public JobDetail getJobDetail(String jobName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getJobDetail(new JobKey(jobName, group));
    }

    public List<String> getGroupNames() throws SchedulerException {
        List<String> groupNames = ListUtils.safe(this.organizationsRepository.findAll().stream().map(Organizations::getAbbreviation).collect(Collectors.toList()));
        groupNames.add("System");
        return groupNames.stream().sorted().collect(Collectors.toList());
    }

    public Trigger getTrigger(String triggerName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getTrigger(new TriggerKey(triggerName, group));
    }

    public List<String> getTriggerGroupNames() throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getTriggerGroupNames();
    }

    public List<? extends Trigger> getTriggersOfJob(String jobName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getTriggersOfJob(new JobKey(jobName, group));
    }

    public void pauseAll() throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().pauseAll();
    }

    public void pauseJob(String jobName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().pauseJob(new JobKey(jobName, group));
    }

    public void pauseTrigger(String triggerName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().pauseTrigger(new TriggerKey(triggerName, group));
    }

    public void rescheduleJob(String triggerName, String group, Trigger newTrigger) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().rescheduleJob(new TriggerKey(triggerName, group), newTrigger);
    }

    public void resumeAll() throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().resumeAll();
    }

    public void resumeJob(String jobName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().resumeJob(new JobKey(jobName, group));
    }

    public void resumeTrigger(String triggerName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().resumeTrigger(new TriggerKey(triggerName, group));
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

    public void scheduleOrReplaceJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        if (this.checkIfJobKeyExists(jobDetail.getKey()) || this.checkIfTriggerExists(trigger.getKey())) {
            Set<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(trigger);
            this.schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, triggerSet, true);
        } else {
            this.scheduleJob(jobDetail, trigger);
        }
    }

    public void scheduleIfMissing(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        if (!this.checkIfJobKeyExists(jobDetail.getKey()) || !this.checkIfTriggerExists(trigger.getKey())) {
            this.scheduleJob(jobDetail, trigger);
        }
    }

    public void triggerJob(String jobName, String group) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().triggerJob(new JobKey(jobName, group));
    }

    public void triggerJob(String jobName, String group, JobDataMap data) throws SchedulerException {
        this.schedulerFactoryBean.getScheduler().triggerJob(new JobKey(jobName, group), data);
    }

    public boolean unscheduleJob(String triggerName, String group) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().unscheduleJob(new TriggerKey(triggerName, group));
    }

    public boolean isJobRunning(String jobName, String group) throws SchedulerException {
        return ListUtils.safe(this.schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs())
                .stream()
                .filter(context -> context.getJobDetail().getKey().getGroup().equals(group))
                .anyMatch(context -> context.getJobDetail().getKey().getName().equals(jobName));
    }

    public String getJobState(String jobName, String group) throws SchedulerException {

        List<? extends Trigger> triggers = this.getTriggersOfJob(jobName, group);

        if (triggers != null && triggers.size() > 0) {
            for (Trigger trigger : triggers) {
                Trigger.TriggerState triggerState = this.schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());

                if (Trigger.TriggerState.PAUSED.equals(triggerState)) {
                    return "PAUSED";
                } else if (Trigger.TriggerState.BLOCKED.equals(triggerState)) {
                    return "BLOCKED";
                } else if (Trigger.TriggerState.COMPLETE.equals(triggerState)) {
                    return "COMPLETE";
                } else if (Trigger.TriggerState.ERROR.equals(triggerState)) {
                    return "ERROR";
                } else if (Trigger.TriggerState.NONE.equals(triggerState)) {
                    return "NONE";
                } else if (Trigger.TriggerState.NORMAL.equals(triggerState)) {
                    if (this.isJobRunning(jobName, group)) {
                        return "RUNNING";
                    } else {
                        return "SCHEDULED";
                    }
                }
            }
        }

        return null;
    }

    public List<String> getAllJobNames() throws SchedulerException {
        List<String> info = new ArrayList<>();

        Scheduler scheduler = this.schedulerFactoryBean.getScheduler();

        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                info.add(jobKey.getName());
            }
        }

        return info;
    }

    public List<QuartzJobInfo> getAllJobs() throws SchedulerException {
        List<QuartzJobInfo> info = new ArrayList<>();

        Scheduler scheduler = this.schedulerFactoryBean.getScheduler();

        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (triggers.size() > 0) {
                    for (Trigger trigger : triggers) {
                        info.add(new QuartzJobInfo(jobDetail,
                                trigger,
                                this.getJobState(jobKey.getName(),
                                        jobKey.getGroup())));
                    }
                } else {
                    info.add(new QuartzJobInfo(jobDetail,
                            this.getJobState(jobKey.getName(),
                                    jobKey.getGroup())));
                }
            }
        }

        return info.stream()
                .sorted(Comparator.comparing(QuartzJobInfo::getGroup).thenComparing(QuartzJobInfo::getJobName))
                .collect(Collectors.toList());
    }


    private Calendar getCalendar(String calendarName) throws SchedulerException {
        return this.schedulerFactoryBean.getScheduler().getCalendar(calendarName);
    }

    public StatusMessage createJob(String jobName, String group, QuartzCreateJob createJob) throws SchedulerException {
        this.logger.info("Creating Job: {}  Group: {}  Details: {}", jobName, group, createJob);
        JobBuilder jobBuilder = JobBuilder.newJob(SqlJob.class)
                .storeDurably()
                .withIdentity(jobName, group)
                .withDescription(createJob.getDescription());

        JobDetail jobDetail = this.getJobDetail(jobName, group);

        if (createJob.getCode() == null && jobDetail != null) {
            String sql = (String) jobDetail.getJobDataMap().get("SQL");
            jobBuilder.usingJobData("SQL", sql);
        } else if (createJob.getCode() != null) {
            jobBuilder = jobBuilder.usingJobData("SQL", createJob.getCode());
        }
        JobDetail job = jobBuilder.build();

        this.addJob(job, true);

        Trigger trigger = null;

        if (createJob.getCalendar() != null) {
            trigger = TriggerBuilder.newTrigger()
                    .withDescription(createJob.getDescription())
                    .startAt(Date.from(createJob.getCalendar().toInstant()))
                    .forJob(job)
                    .withIdentity(jobName, group).build();

        } else if (createJob.getCronExpression() != null) {
            trigger = TriggerBuilder.newTrigger()
                    .withDescription(createJob.getDescription())
                    .withSchedule(CronScheduleBuilder.cronSchedule(createJob.getCronExpression()))
                    .forJob(job)
                    .withIdentity(jobName, group).build();
        }

        this.scheduleOrReplaceJob(job, trigger);

        return new StatusMessage().setMessage("Job Scheduled").setOk(true);
    }

    public void executeSQL(String sql) {
        this.logger.debug("SQL: {}", sql);
        this.entityManager.createNativeQuery(sql).getResultList();
    }
}
