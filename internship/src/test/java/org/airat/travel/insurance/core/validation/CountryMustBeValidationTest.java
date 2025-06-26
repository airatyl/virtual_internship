package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CountryMustBeValidationTest {

    CountryMustBeValidation validation =new CountryMustBeValidation();

    @Test
    public void CountryIsNullValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getCountry()).thenReturn(null);
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("country", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void CountryIsEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getCountry()).thenReturn("");
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("country", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void CountryIsNotEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getCountry()).thenReturn("Россия");
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
}