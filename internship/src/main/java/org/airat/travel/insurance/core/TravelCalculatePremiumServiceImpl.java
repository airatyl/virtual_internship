package org.airat.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    private final DateTimeService dateTimeService;
    private final TravelCalculatePremiumRequestValidator requestValidator;


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        response.setAgreementPrice(new BigDecimal(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())));

        return response;
    }


}
