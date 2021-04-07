package com.blue.project.modules.maintenance.services;

import com.blue.project.modules.maintenance.dao.SettingsRepository;
import com.blue.project.modules.maintenance.dto.SettingValue;
import com.blue.project.modules.maintenance.models.Setting;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    private String getSetting(String name, String organization, ZonedDateTime date) {
        List<Setting> allByNameAndDate = ListUtils.safe(this.settingsRepository.findAllByNameAndDate(name,organization, date));

        if(allByNameAndDate.size() > 0) {
            return allByNameAndDate.stream().max(Comparator.comparing(Setting::getStartDateTime)).get().getValue();
        }

        List<Setting> allByName = ListUtils.safe(this.settingsRepository.findAllByName(name,organization));
        if(allByName.size() > 0) {
            return allByName.stream().max(Comparator.comparing(Setting::getDateCreated)).get().getValue();
        }

        return null;
    }

    @Override
    public SettingValue getSettingValue(String setting, String organization) {
        SettingValue settingValue = new SettingValue();
        settingValue.setSetting(setting);
        settingValue.setValue(this.getSetting(setting,organization, ZonedDateTime.now()));
        return settingValue;
    }
}
