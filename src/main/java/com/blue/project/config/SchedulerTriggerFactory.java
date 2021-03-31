package com.blue.project.config;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class SchedulerTriggerFactory
{
    public CronTriggerFactoryBean jobTrigger(JobDetail jobDetail, String frequency, String triggerName) throws ParseException {
        CronTriggerFactoryBean tBean =  createCronTrigger(jobDetail,frequency);
        tBean.setBeanName(triggerName);
        tBean.afterPropertiesSet();
        return tBean;
    }

    private CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
        return factoryBean;
    }

    public SimpleTriggerFactoryBean startupJobTrigger(JobDetail jobDetail, String triggerName) throws ParseException {
        SimpleTriggerFactoryBean tBean =  createStartupTrigger(jobDetail);
        tBean.setBeanName(triggerName);
        tBean.afterPropertiesSet();
        return tBean;
    }

    public SimpleTriggerFactoryBean createStartupTrigger(JobDetail jobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        factoryBean.setRepeatCount(0);
        return factoryBean;
    }
}
