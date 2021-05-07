package com.blue.project.modules.calendar.controller;

import com.blue.project.modules.calendar.dto.EventRange;
import com.blue.project.modules.calendar.services.CalendarService;

import com.blue.project.modules.calendar.dto.EventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/public/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicCalendarController {

    @Autowired
    private CalendarService calendarService;

    @PreAuthorize("permitAll()")
    @PostMapping("/events")
    public List<EventData> getCalendarEventData(@RequestBody EventRange range) {
        return this.calendarService.getEventData(range.getStart(),range.getEnd());
    }
}
