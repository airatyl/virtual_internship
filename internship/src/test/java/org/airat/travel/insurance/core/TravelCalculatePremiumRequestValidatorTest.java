package org.airat.travel.insurance.core;

import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TravelCalculatePremiumRequestValidatorTest {



    private TravelCalculatePremiumRequestValidator requestValidator;

    @BeforeEach
    public void setup(){
        requestValidator=new TravelCalculatePremiumRequestValidator();
    }

    @Test
    public void FirstNameIsNullValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.contains(new ValidationError("personFirstName","Must not be empty!")));
    }

    @Test
    public void FirstNameIsEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.contains(new ValidationError("personFirstName","Must not be empty!")));
    }

    @Test
    public void FirstNameIsNotEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Airat");
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }
}