package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dto.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
