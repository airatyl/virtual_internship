package org.airat.travel.insurance.core.validation;


import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TravelCalculatePremiumRequestValidatorTest {



    @Test
    public void validationWithErrors(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        RequestFieldValidation validation1 = Mockito.mock(RequestFieldValidation.class);
        RequestFieldValidation validation2 = Mockito.mock(RequestFieldValidation.class);
        Mockito.when(validation1.validateField(request)).thenReturn(Optional.of(new ValidationError()));
        Mockito.when(validation2.validateField(request)).thenReturn(Optional.of(new ValidationError()));
        List<RequestFieldValidation> fieldValidations = List.of(validation1,validation2);
        TravelCalculatePremiumRequestValidator validator =new TravelCalculatePremiumRequestValidator(fieldValidations);
        List<ValidationError>  errors= validator.validate(request);
        assertEquals(2,errors.size());

    }

    @Test
    public void validationWithoutErrors(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        RequestFieldValidation validation1 = Mockito.mock(RequestFieldValidation.class);
        RequestFieldValidation validation2 = Mockito.mock(RequestFieldValidation.class);
        Mockito.when(validation1.validateField(request)).thenReturn(Optional.empty());
        Mockito.when(validation2.validateField(request)).thenReturn(Optional.empty());
        List<RequestFieldValidation> fieldValidations = List.of(validation1,validation2);
        TravelCalculatePremiumRequestValidator validator =new TravelCalculatePremiumRequestValidator(fieldValidations);
        List<ValidationError>  errors= validator.validate(request);
        assertTrue(errors.isEmpty());

    }


}