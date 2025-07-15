package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TripCancellationRiskCalculator implements TravelRiskCalculator{
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV2 request, PersonDTO person) {
        return BigDecimal.ONE;
    }

    @Override
    public String getRisk() {
        return "Отмена поездки";
    }
}
