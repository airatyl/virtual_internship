package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;

import java.math.BigDecimal;

public interface TravelRiskCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequestV2 request, PersonDTO person);
    String getRisk();
}
