package org.airat.travel.insurance.core.validation.person;


import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonLastNameValidationTest {

    PersonLastNameValidation personLastNameValidation = new PersonLastNameValidation();

    private final PersonDTO person1 =new PersonDTO();
    private final PersonDTO person2 =new PersonDTO();

    @Test
    public void LastNameOfOnePersonIsNullValidate(){
        person1.setPersonLastName("aaa");
        person2.setPersonLastName(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personLastName", "Должно быть заполнено"),errors.get());
    }
    @Test
    public void LastNameOfTwoPersonIsNullValidate(){
        person1.setPersonLastName(null);
        person2.setPersonLastName(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personLastName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void LastNameOfOnePersonIsEmptyValidate(){
        person1.setPersonLastName("");
        person2.setPersonLastName("aaa");
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personLastName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void LastNameOfTwoPersonIsEmptyValidate(){
        person1.setPersonLastName("");
        person2.setPersonLastName("");
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personLastName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void LastNameIsNotEmptyValidate(){
        person1.setPersonLastName("aaa");
        person2.setPersonLastName("aaa");
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personLastNameValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}