package com.blue.project.controller;


import com.blue.project.dto.StatusMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @GetMapping("/help")
    public StatusMessage getSiteLIst() {
        return new StatusMessage("API is Working",true);
    }
}
