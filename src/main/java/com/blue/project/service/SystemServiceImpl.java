package com.blue.project.service;

import com.blue.project.dto.system.SystemInfo;
import com.blue.project.dto.system.SystemStatus;
import name.mymiller.nadia.Nadia;
import name.mymiller.nadia.constants.Fields;
import name.mymiller.nadia.constants.Types;
import name.mymiller.nadia.system.SystemStatusReport;
import name.mymiller.nadia.utils.ZonedDateTimeGenerator;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
@Transactional
public class SystemServiceImpl implements SystemService{

    @Autowired
    private Environment environment;

    @Autowired
    BuildProperties buildProperties;

    @Override
    public SystemInfo getSystemInfo() {
        return new SystemInfo(buildProperties,environment);
    }

    @Override
    public List<SystemStatus> getSystemStatus() {
        List<Properties> query = ListUtils.safe(Nadia.getInstance().query(Types.NADIA_SYSTEM_STATUS, null, ZonedDateTime.now().minusWeeks(1), ZonedDateTime.now()));
        return ListUtils.safe(query.parallelStream().map(property -> {
            SystemStatus systemStatus = new SystemStatus();
            systemStatus.setDateTime(ZonedDateTimeGenerator.generate(property));
            systemStatus.setPhysicalFreeMemory(Long.parseLong(property.getProperty(Fields.NADIA_PHYSICAL_FREE_MEMORY_SIZE)));
            systemStatus.setPhysicalMemorySize(Long.parseLong(property.getProperty(Fields.NADIA_PHYSICAL_MEMORY_SIZE)));
            systemStatus.setVmMaxMemory(Long.parseLong(property.getProperty(Fields.NADIA_VM_MAX_MEMORY)));
            systemStatus.setVmCurrentMemory(Long.parseLong(property.getProperty(Fields.NADIA_VM_CURRENT_MEMORY)));
            systemStatus.setVmFreeMemory(Long.parseLong(property.getProperty(Fields.NADIA_VM_FREE_MEMORY)));
            systemStatus.setCpuLoad(Double.parseDouble(property.getProperty(Fields.NADIA_CPU_LOAD)));

            return systemStatus;
        }).collect(Collectors.toList()));
    }

    @Override
    public SystemStatusReport getCurrentSystemStatus() {
        return Nadia.getInstance().getSystemStatusReport();
    }
}
