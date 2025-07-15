package org.airat.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.*;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    private final TravelPremiumUnderwriting premiumUnderwriting;
    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final AgreementsEntityService agreementsEntityService;


    @Override
    public TravelCalculatePremiumResponseV2 calculatePremium(TravelCalculatePremiumRequestV2 request) {

        List<ValidationError> errors = requestValidator.validate(request);
        if (errors.isEmpty()) {
            TravelCalculatePremiumResponseV2 result = fillResponse(request);

            return result;
        }
        else {
            return fillResponse(errors);
        }
    }

    private TravelCalculatePremiumResponseV2 fillResponse(List<ValidationError> errors){
        return new TravelCalculatePremiumResponseV2(errors);
    }

    private TravelCalculatePremiumResponseV2 fillResponse(TravelCalculatePremiumRequestV2 request){
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setCountry(request.getCountry());
        final BigDecimal[] totalPremium = {BigDecimal.ZERO};
        request.getPeople().forEach(person -> {
            person.setResult(premiumUnderwriting.calculatePremiumForSelectedRisks(request,person));
            totalPremium[0] = totalPremium[0].add(person.getResult().totalPremium());
        });
        response.setPeople(request.getPeople());
        response.setAgreementPremium(totalPremium[0]);
        response.setUuid(agreementsEntityService.saveAll(response,request.getRisks()).getUuid());


        return response;
    }




}
