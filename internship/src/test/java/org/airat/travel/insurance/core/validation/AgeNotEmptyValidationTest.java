package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgeNotEmptyValidationTest {

    AgeNotEmptyValidation validation =new AgeNotEmptyValidation();

    @Test
    public void birthDateIsNullValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getBirthDate()).thenReturn(null);
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("birthDate", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void birthDateIsNotEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getBirthDate()).thenReturn(LocalDate.of(2025,1,12));
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
}