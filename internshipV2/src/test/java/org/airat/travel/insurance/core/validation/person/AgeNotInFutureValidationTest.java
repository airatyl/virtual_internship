package org.airat.travel.insurance.core.validation.person;

import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgeNotInFutureValidationTest {
    AgeNotInFutureValidation validation = new AgeNotInFutureValidation();

    private final Person person1 =new Person();
    private final Person person2 =new Person();
    @Test
    public void birthDateOfOnePersonIsNull() {
        person1.setBirthDate(null);
        person2.setBirthDate(LocalDate.of(2025,1,12));
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void birthDateOfTwoPersonIsNull() {
        person1.setBirthDate(null);
        person2.setBirthDate(null);
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void birthDateInPast() {
        person1.setBirthDate(LocalDate.of(2025,1,12));
        person2.setBirthDate(LocalDate.of(2025,1,12));
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
        }

    @Test
    public void birthDateOfTwoPersonInFuture() {
        person1.setBirthDate(LocalDate.of(2026,1,12));
        person2.setBirthDate(LocalDate.of(2026,1,12));
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("birthDate","Дата рождения должна быть в прошлом"), error.get());
    }
    @Test
    public void birthDateOfOnePersonInFutureOneIsNull() {
        person1.setBirthDate(LocalDate.of(2026,1,12));
        person2.setBirthDate(null);
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("birthDate","Дата рождения должна быть в прошлом"), error.get());
    }
    @Test
    public void birthDateOfOnePersonInFutureOneInPast() {
        person1.setBirthDate(LocalDate.of(2026,1,12));
        person2.setBirthDate(LocalDate.of(2024,1,12));
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("birthDate","Дата рождения должна быть в прошлом"), error.get());
    }

}