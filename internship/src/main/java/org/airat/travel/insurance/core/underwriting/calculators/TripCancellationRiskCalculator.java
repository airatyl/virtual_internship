package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TripCancellationRiskCalculator implements TravelRiskCalculator{
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ONE;
    }

    @Override
    public String getRisk() {
        return "Отмена поездки";
    }
}
