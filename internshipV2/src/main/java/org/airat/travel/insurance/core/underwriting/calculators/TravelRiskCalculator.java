package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;

import java.math.BigDecimal;

public interface TravelRiskCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequestV2 request, Person person);
    String getRisk();
}
