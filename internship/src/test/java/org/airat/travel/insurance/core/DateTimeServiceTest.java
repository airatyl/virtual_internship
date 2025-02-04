package org.airat.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    DateTimeService dateTimeService = new DateTimeService();


    @Test
    void calculateDaysBetweenDates() {
        long answer = dateTimeService.calculateDaysBetweenDates(LocalDate.of(2025, Month.JANUARY,20 ),LocalDate.of(2025, Month.JANUARY,29));
        assertEquals(10,answer);
    }
}