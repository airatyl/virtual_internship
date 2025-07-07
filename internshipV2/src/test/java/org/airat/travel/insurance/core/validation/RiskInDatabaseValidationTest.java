package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.core.domain.RiskType;
import org.airat.travel.insurance.core.repositories.RiskTypeRepository;
import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RiskInDatabaseValidationTest {

    @Mock
    RiskTypeRepository riskTypeRepository;

    @InjectMocks
    RiskInDatabaseValidation riskInDatabaseValidation;


    @Test
    public void RisksNotSelected(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of());
        Optional<ValidationError> errors = riskInDatabaseValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void AllRisksNotInDB(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("asdad","dasds"));
        Mockito.when(riskTypeRepository.findByTitle("asdad")).thenReturn(null);
        Mockito.when(riskTypeRepository.findByTitle("dasds")).thenReturn(null);
        Optional<ValidationError> errors = riskInDatabaseValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("risks","Выбранные риски не существуют: asdad, dasds"),errors.get());
    }

    @Test
    public void AllRisksInDB(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы","Отмена поездки"));
        Mockito.when(riskTypeRepository.findByTitle("Медицинские расходы")).thenReturn(new RiskType());
        Mockito.when(riskTypeRepository.findByTitle("Отмена поездки")).thenReturn(new RiskType());
        Optional<ValidationError> errors = riskInDatabaseValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void OneRiskInDB(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские adsрасходы","Отмена поездки"));
        Mockito.when(riskTypeRepository.findByTitle("Медицинские adsрасходы")).thenReturn(null);
        Mockito.when(riskTypeRepository.findByTitle("Отмена поездки")).thenReturn(new RiskType());
        Optional<ValidationError> errors = riskInDatabaseValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("risks","Выбранные риски не существуют: Медицинские adsрасходы"),errors.get());
    }

}