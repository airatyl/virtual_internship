package org.airat.travel.insurance.core.validation.person;


import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgeNotEmptyValidationTest {

    private final PersonDTO person1 =new PersonDTO();
    private final PersonDTO person2 =new PersonDTO();

    AgeNotEmptyValidation validation =new AgeNotEmptyValidation();

    @Test
    public void birthDateOfOnePersonIsNullValidate(){
        person1.setBirthDate(null);
        person2.setBirthDate(LocalDate.of(2025,1,12));
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("birthDate", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void birthDateOfTwoPersonIsNullValidate(){
        person1.setBirthDate(null);
        person2.setBirthDate(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("birthDate", "Должен быть заполнен"),errors.get());
    }

    @Test
    public void birthDateOfTwoPersonIsNotEmptyValidate(){
        person1.setBirthDate(LocalDate.of(2025,1,12));
        person2.setBirthDate(LocalDate.of(2025,1,12));
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional <ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
}