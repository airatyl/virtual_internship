package org.airat.travel.insurance.core.services;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeService {

    public long calculateDaysBetweenDates(LocalDate date1, LocalDate date2){
        return ChronoUnit.DAYS.between(date1,date2)+1;
    }

    public int calculateAge(LocalDate birthDate){
        return birthDate.until(LocalDate.now()).getYears();
    }
}
