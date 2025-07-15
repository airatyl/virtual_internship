package org.airat.travel.insurance.core.validation.person;

import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonFirstNameValidationTest {

    PersonFirstNameValidation personFirstNameValidation = new PersonFirstNameValidation();

    private final PersonDTO person1 =new PersonDTO();
    private final PersonDTO person2 =new PersonDTO();

    @Test
    public void FirstNameOfOnePersonIsNullValidate(){
        person1.setPersonFirstName("aaa");
        person2.setPersonFirstName(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personFirstName", "Должно быть заполнено"),errors.get());
    }
    @Test
    public void FirstNameOfTwoPersonIsNullValidate(){
        person1.setPersonFirstName(null);
        person2.setPersonFirstName(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personFirstName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void FirstNameOfOnePersonIsEmptyValidate(){
        person1.setPersonFirstName("");
        person2.setPersonFirstName("aaa");
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personFirstName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void FirstNameOfTwoPersonIsEmptyValidate(){
        person1.setPersonFirstName("");
        person2.setPersonFirstName("");
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("personFirstName", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void FirstNameIsNotEmptyValidate(){
        person1.setPersonFirstName("aaa");
        person2.setPersonFirstName("aaa");
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = personFirstNameValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}