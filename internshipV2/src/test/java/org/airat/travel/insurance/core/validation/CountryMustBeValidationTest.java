package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
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
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getCountry()).thenReturn(null);
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("country", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void CountryIsEmptyValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getCountry()).thenReturn("");
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("country", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void CountryIsNotEmptyValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getCountry()).thenReturn("Россия");
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
}