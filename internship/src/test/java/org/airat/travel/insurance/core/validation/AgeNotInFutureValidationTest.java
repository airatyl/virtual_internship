package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgeNotInFutureValidationTest {
    AgeNotInFutureValidation validation = new AgeNotInFutureValidation();

    @Test
    public void birthDateIsNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getBirthDate()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void birthDateInPast() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getBirthDate()).thenReturn(LocalDate.of(2024, 9, 9));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
        }

    @Test
    public void birthDateInFuture() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getBirthDate()).thenReturn(LocalDate.of(2027, 9, 9));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("birthDate","Дата рождения должна быть в прошлом"), error.get());
    }

}