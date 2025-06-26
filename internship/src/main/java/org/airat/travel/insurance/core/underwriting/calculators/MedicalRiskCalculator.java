package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MedicalRiskCalculator implements TravelRiskCalculator{



    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return null;
    }



    @Override
    public String getRisk() {
        return "Медицинские расходы";
    }
}
