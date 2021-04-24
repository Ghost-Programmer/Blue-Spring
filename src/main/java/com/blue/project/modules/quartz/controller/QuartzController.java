package com.blue.project.modules.quartz.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.quartz.dto.QuartzJobInfo;
import com.blue.project.modules.quartz.services.QuartzService;
import com.blue.project.modules.users.dto.UserRole;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/quartz", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @GetMapping("/jobs")
    public List<QuartzJobInfo> getAllJobs() throws SchedulerException {
        return this.quartzService.getAllJobs();
    }

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @GetMapping("/jobNames")
    public List<String> getAllJobNames() throws SchedulerException {
        return this.quartzService.getAllJobNames();
    }

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @GetMapping("/groups")
    public List<String> getGroupNames() throws SchedulerException {
        return this.quartzService.getGroupNames();
    }

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @PostMapping("/group/{group}/job/{jobName}/pause")
    public StatusMessage pauseJob(@PathVariable("jobName") String jobName, @PathVariable("group") String group) throws SchedulerException {
        this.quartzService.pauseJob(jobName,group);

        return new StatusMessage().setOk(true);
    }

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @PostMapping("/group/{group}/job/{jobName}/resume")
    public StatusMessage resumeJob(@PathVariable("jobName") String jobName, @PathVariable("group") String group) throws SchedulerException {
        this.quartzService.resumeJob(jobName,group);

        return new StatusMessage().setOk(true);
    }

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @PostMapping("/group/{group}/job/{jobName}")
    public StatusMessage executeJob(@PathVariable("jobName") String jobName, @PathVariable("group") String group) throws SchedulerException {
        this.quartzService.triggerJob(jobName,group);

        return new StatusMessage().setOk(true);
    }

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @DeleteMapping("/group/{group}/job/{jobName}")
    public StatusMessage deleteJob(@PathVariable("jobName") String jobName, @PathVariable("group") String group) throws SchedulerException {
        return new StatusMessage().setOk(this.quartzService.deleteJob(jobName,group));
    }
}
