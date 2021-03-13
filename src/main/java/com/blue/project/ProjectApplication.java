package com.blue.project;

import com.blue.project.config.PortalConfig;
import name.mymiller.nadia.Nadia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAuthorizationServer
@EnableConfigurationProperties({PortalConfig.class})
@SpringBootApplication
@EnableSwagger2
public class ProjectApplication {


    public static void main(String[] args) {
        Nadia.getInstance();
        SpringApplication.run(ProjectApplication.class, args);
    }

}
