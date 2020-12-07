package com.nrha.reinersuite.dto;

import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SystemInfo {

    String name;
    String artifact;
    String group;
    String version;
    ZonedDateTime time;
    String[] profiles;

    public SystemInfo(BuildProperties buildProperties, Environment environment) {
        this.version = buildProperties.getVersion();
        this.name = buildProperties.getName();
        this.artifact = buildProperties.getArtifact();
        this.group = buildProperties.getGroup();
        this.time = buildProperties.getTime().atZone(ZoneId.systemDefault());
        this.profiles = environment.getActiveProfiles();
    }

    public String getName() {
        return name;
    }

    public String getArtifact() {
        return artifact;
    }

    public String getGroup() {
        return group;
    }

    public String getVersion() {
        return version;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public String[] getProfiles() {
        return profiles;
    }
}
