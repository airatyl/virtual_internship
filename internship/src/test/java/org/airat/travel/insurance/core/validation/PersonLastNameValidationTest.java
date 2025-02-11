package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonLastNameValidationTest {

    PersonLastNameValidation personLastNameValidation = new PersonLastNameValidation();

    @Test
    public void LastNameIsNullValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personLastName", "Must not be empty!"),errors.get());
    }

    @Test
    public void LastNameIsEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn("");
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personLastName", "Must not be empty!"),errors.get());
    }

    @Test
    public void LastNameIsNotEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn("Airat");
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}