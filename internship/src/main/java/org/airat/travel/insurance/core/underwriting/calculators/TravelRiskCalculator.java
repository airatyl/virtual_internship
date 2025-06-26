package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelRiskCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);
    String getRisk();
}
