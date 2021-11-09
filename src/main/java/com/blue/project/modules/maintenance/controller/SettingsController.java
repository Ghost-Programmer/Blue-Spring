package com.blue.project.modules.maintenance.controller;

import com.blue.project.modules.maintenance.dto.SettingValue;
import com.blue.project.modules.maintenance.services.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping(value = "/maintenance/settings", produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(value = "/{setting}/{organization}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @PermitAll
    public SettingValue getSettingValue(@PathVariable("setting") String setting, @PathVariable("organization") String organization) {
        return this.settingsService.getSettingValue(setting, organization);
    }
}
