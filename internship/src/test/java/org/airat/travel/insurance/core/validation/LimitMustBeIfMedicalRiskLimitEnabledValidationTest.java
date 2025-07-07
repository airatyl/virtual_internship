package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LimitMustBeIfMedicalRiskLimitEnabledValidationTest {
    LimitMustBeIfMedicalRiskLimitEnabledValidation validation =new LimitMustBeIfMedicalRiskLimitEnabledValidation();

    @Test
    public void EnabledIsFalse(){
        validation.setEnabled(false);
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getMedicalRiskLimitLevel()).thenReturn(BigDecimal.valueOf(12));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void EnabledIsTrueRiskSelectedLimitSelected(){
        validation.setEnabled(true);
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getMedicalRiskLimitLevel()).thenReturn(BigDecimal.valueOf(12));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void EnabledIsTrueRiskSelectedLimitNotSelected(){
        validation.setEnabled(true);
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы"));
        Mockito.when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("medicalRiskLimitLevel","Должен быть заполнен"),errors.get());
    }
    @Test
    public void EnabledIsTrueRiskNotSelectedLimitSelected(){
        validation.setEnabled(true);
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Mockito.when(request.getMedicalRiskLimitLevel()).thenReturn(BigDecimal.valueOf(12));
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void EnabledIsTrueRiskNotSelectedLimitNotSelected(){
        validation.setEnabled(true);
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Mockito.when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        Optional<ValidationError> errors = validation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}