package org.airat.travel.insurance.core;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
