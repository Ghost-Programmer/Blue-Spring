package com.blue.project.modules.maintenance.services;

import com.blue.project.modules.maintenance.dto.SettingValue;

public interface SettingsService {

    SettingValue getSettingValue(String setting, String organization);
}
