package com.blue.project.modules.quartz.controller;

import com.blue.project.modules.quartz.dto.QuartzJobInfo;
import com.blue.project.modules.quartz.services.QuartzService;
import com.blue.project.modules.users.dto.UserRole;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/quartz", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @RolesAllowed("ROLE_ADMIN_QUARTZ")
    @GetMapping("/jobs")
    public List<QuartzJobInfo> getUserRoles() throws SchedulerException {
        return this.quartzService.getAllJobs();
    }
}
