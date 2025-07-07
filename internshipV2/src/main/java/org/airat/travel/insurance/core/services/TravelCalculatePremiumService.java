package org.airat.travel.insurance.core.services;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV2 calculatePremium(TravelCalculatePremiumRequestV2 request);

}
