package org.airat.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.*;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    private final TravelPremiumUnderwriting premiumUnderwriting;
    private final TravelCalculatePremiumRequestValidator requestValidator;


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> errors = requestValidator.validate(request);
        if (errors.isEmpty()) {
            return fillResponse(request);
        }
        else {
            return fillResponse(errors);
        }
    }

    private TravelCalculatePremiumResponse fillResponse(List<ValidationError> errors){
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse fillResponse(TravelCalculatePremiumRequest request){
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setBirthDate(request.getBirthDate());
        response.setCountry(request.getCountry());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        TravelPremiumCalculationResult premiums = premiumUnderwriting.calculatePremiumForSelectedRisks(request);
        System.out.println(request.getCountry());
        System.out.println(response.getCountry());
        response.setAgreementPremium(premiums.totalPremium());
        response.setRiskPremiums(premiums.riskPremiums());

        return response;
    }


}
