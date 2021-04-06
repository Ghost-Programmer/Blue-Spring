package com.blue.project.modules.maintenance.services;

import com.blue.project.modules.maintenance.dao.SettingsRepository;
import com.blue.project.modules.maintenance.models.Setting;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

public class SettingsServiceImpl implements SettingsService{

    @Autowired
    private SettingsRepository settingsRepository;

    private String getSetting(String name, ZonedDateTime date) {
        List<Setting> allByNameAndDate = ListUtils.safe(this.settingsRepository.findAllByNameAndDate(name, date));

        if(allByNameAndDate.size() > 0) {
            return allByNameAndDate.stream().sorted(Comparator.comparing(Setting::getStartDateTime)).findFirst().get().getValue();
        }

        List<Setting> allByName = ListUtils.safe(this.settingsRepository.findAllByName(name));
        if(allByName.size() > 0) {
            return allByName.stream().sorted(Comparator.comparing(Setting::getDateCreated)).findFirst().get().getValue();
        }

        return null;
    }

}
