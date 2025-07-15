package org.airat.travel.insurance.core.validation.person;


import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LimitMustBeIfMedicalRiskLimitEnabledValidationTest {
    LimitMustBeIfMedicalRiskLimitEnabledValidation validation =new LimitMustBeIfMedicalRiskLimitEnabledValidation();

    private final PersonDTO person1 =new PersonDTO();
    private final PersonDTO person2 =new PersonDTO();

    @Test
    public void EnabledIsFalse(){
        validation.setEnabled(false);
        person1.setMedicalRiskLimitLevel(null);
        person2.setMedicalRiskLimitLevel(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void EnabledIsTrueRiskNotSelected(){
        validation.setEnabled(true);
        person1.setMedicalRiskLimitLevel(null);
        person2.setMedicalRiskLimitLevel(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void EnabledIsTrueRiskSelectedLimitOfTwoPersonSelected(){
        validation.setEnabled(true);
        person1.setMedicalRiskLimitLevel(BigDecimal.valueOf(100));
        person2.setMedicalRiskLimitLevel(BigDecimal.valueOf(100));
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void EnabledIsTrueRiskSelectedLimitOfOnePersonNotSelected(){
        validation.setEnabled(true);
        person1.setMedicalRiskLimitLevel(null);
        person2.setMedicalRiskLimitLevel(BigDecimal.valueOf(100));
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("medicalRiskLimitLevel","Должен быть заполнен"),errors.get());
    }
    @Test
    public void EnabledIsTrueRiskSelectedLimitOfTwoPersonNotSelected(){
        validation.setEnabled(true);
        person1.setMedicalRiskLimitLevel(null);
        person2.setMedicalRiskLimitLevel(null);
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getPeople()).thenReturn(List.of(person1,person2));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("medicalRiskLimitLevel","Должен быть заполнен"),errors.get());
    }


}