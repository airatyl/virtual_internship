package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonFirstNameValidationTest {

    PersonFirstNameValidation personFirstNameValidation = new PersonFirstNameValidation();

    @Test
    public void FirstNameIsNullValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personFirstName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void FirstNameIsEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personFirstName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void FirstNameIsNotEmptyValidate(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Airat");
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}