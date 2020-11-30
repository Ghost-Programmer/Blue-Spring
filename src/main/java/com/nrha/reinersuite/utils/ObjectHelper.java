package com.nrha.reinersuite.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ObjectHelper {
    public Long parseLong(Object obj,Long value) {
        return obj != null? Long.parseLong(obj.toString()):value;
    }

    public String parseString(Object obj,String value) {
        return obj != null? obj.toString():value;
    }

    public Integer parseInteger(Object obj,Integer value) {
        return obj != null? Integer.parseInt(obj.toString()):value;
    }

    public Double parseDouble(Object obj,Double value) {
        return obj != null? Double.parseDouble(obj.toString()):value;
    }

    public ZonedDateTime parseZonedDateTime(Object obj, ZonedDateTime value) {
        if(obj == null) {
            return value;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        return ZonedDateTime.parse(obj.toString(), dateTimeFormatter.withZone(ZoneId.of("UTC")));
    }

    public LocalDate parseLocalDate(Object obj, LocalDate value) {
        if(obj == null) {
            return value;
        }
        return LocalDateTime.parse(obj.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.SSS")).toLocalDate();
    }
}
