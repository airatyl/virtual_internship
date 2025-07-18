package org.airat.travel.insurance.core.underwriting;

import org.airat.travel.insurance.core.underwriting.calculators.MedicalRiskCalculator;
import org.airat.travel.insurance.core.underwriting.calculators.TravelRiskCalculator;
import org.airat.travel.insurance.core.underwriting.calculators.TripCancellationRiskCalculator;
import org.airat.travel.insurance.dto.RiskPremium;

import org.airat.travel.insurance.dto.TravelPremiumCalculationResult;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingTest {

    @Mock
    MedicalRiskCalculator calculator ;

    @Mock
    TripCancellationRiskCalculator tripCalculator;


    @Mock
    TravelCalculatePremiumRequestV2 request;



    @Test
    void calculatePremiumForSelectedRisks() {
        List<TravelRiskCalculator> calculators = List.of(calculator,tripCalculator);
        Mockito.when(calculator.getRisk()).thenReturn("Медицинские расходы");
        Mockito.when(tripCalculator.getRisk()).thenReturn("Отмена поездки");
        TravelPremiumUnderwriting underwriting =new TravelPremiumUnderwriting(calculators);
        Mockito.when(calculator.calculatePremium(Mockito.eq( request),Mockito.any(PersonDTO.class))).thenReturn(BigDecimal.ONE);
        Mockito.when(tripCalculator.calculatePremium(Mockito.eq(request),Mockito.any(PersonDTO.class))).thenReturn(BigDecimal.ONE);
        Mockito.when(request.getRisks()).thenReturn(List.of("Медицинские расходы","Отмена поездки"));
        TravelPremiumCalculationResult answers= underwriting.calculatePremiumForSelectedRisks(request,new PersonDTO());
        assertEquals(List.of(new RiskPremium("Медицинские расходы",BigDecimal.ONE),
                        new RiskPremium("Отмена поездки",BigDecimal.ONE))
                ,answers.riskPremiums());
        assertTrue(BigDecimal.valueOf(2L).compareTo(answers.totalPremium())==0);
    }
}