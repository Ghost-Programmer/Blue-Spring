package com.nrha.reinersuite;

import com.nrha.reinersuite.config.PortalConfig;
import name.mymiller.nadia.Nadia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@EnableConfigurationProperties({PortalConfig.class})
@SpringBootApplication
public class ReinerSuiteApplication {



    public static void main(String[] args) {
        Nadia.getInstance();
        SpringApplication.run(ReinerSuiteApplication.class, args);
    }

}
