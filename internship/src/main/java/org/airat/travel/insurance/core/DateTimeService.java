package org.airat.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeService {

    public long calculateDaysBetweenDates(LocalDate date1, LocalDate date2){
        return date1.until(date2, ChronoUnit.DAYS)+1;
    }
}
