package org.airat.travel.insurance.core.validation;


import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RisksSelectedValidationTest {
    RisksSelectedValidation risksSelectedValidation =new RisksSelectedValidation();

    @Test
    public void RisksIsNullValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(null);
        Optional<ValidationError> errors = risksSelectedValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("risks","Должен быть выбран хотя бы один риск"),errors.get());
    }

    @Test
    public void RisksIsEmptyValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Optional <ValidationError> errors = risksSelectedValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("risks","Должен быть выбран хотя бы один риск"),errors.get());
    }

    @Test
    public void RisksIsNotEmptyValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("ASDasd"));
        Optional <ValidationError> errors = risksSelectedValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }


}