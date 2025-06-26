package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgeMustBeIfMedicalRiskSelectedValidationTest {

    AgeMustBeIfMedicalRiskSelectedValidation validation =new AgeMustBeIfMedicalRiskSelectedValidation();

    @Test
    public void RiskSelectedAgeSelected(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getBirthDate()).thenReturn(LocalDate.now());
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void RiskSelectedAgeNotSelected(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getBirthDate()).thenReturn(null);
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("birthDate","Должен быть заполнен"),errors.get());
    }
    @Test
    public void RiskNotSelectedAgeSelected(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Mockito.when(request.getBirthDate()).thenReturn(LocalDate.now());
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void RiskNotSelectedAgeNotSelected(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Mockito.when(request.getBirthDate()).thenReturn(null);
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
}