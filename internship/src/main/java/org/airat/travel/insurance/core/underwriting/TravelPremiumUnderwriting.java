package org.airat.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.DateTimeService;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPremiumUnderwriting {

    private final DateTimeService dateTimeService;


    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        return new BigDecimal(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }

}
