package com.blue.project.modules.calendar.services;

import com.blue.project.modules.calendar.dto.EventContext;
import com.blue.project.modules.calendar.dto.EventData;

import java.time.ZonedDateTime;
import java.util.List;

public interface CalendarService {
    public List<EventData> getEventData(ZonedDateTime start, ZonedDateTime end);
    public List<EventContext> getEventContext();
}
