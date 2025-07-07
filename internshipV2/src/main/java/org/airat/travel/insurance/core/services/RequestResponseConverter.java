package org.airat.travel.insurance.core.services;

import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Component;

import java.util.List;


public class RequestResponseConverter {

    public static TravelCalculatePremiumRequestV2 convertRequestV1ToRequestV2(TravelCalculatePremiumRequestV1 requestV1){
        TravelCalculatePremiumRequestV2 requestV2 =new TravelCalculatePremiumRequestV2();
        requestV2.setAgreementDateFrom(requestV1.getAgreementDateFrom());
        requestV2.setAgreementDateTo(requestV1.getAgreementDateTo());
        requestV2.setCountry(requestV1.getCountry());
        requestV2.setRisks(requestV1.getRisks());
        requestV2.setPeople(List.of(new Person(requestV1.getPersonFirstName(),requestV1.getPersonLastName(),requestV1.getBirthDate(),requestV1.getMedicalRiskLimitLevel())));
        return requestV2;
    }

    public static TravelCalculatePremiumResponseV1 convertResponseV2ToResponseV1(TravelCalculatePremiumResponseV2 responseV2){
        if(responseV2.hasErrors()){
            return new TravelCalculatePremiumResponseV1(responseV2.getErrors());
        }
        TravelCalculatePremiumResponseV1 responseV1= new TravelCalculatePremiumResponseV1();
        responseV1.setAgreementDateFrom(responseV2.getAgreementDateFrom());
        responseV1.setAgreementDateTo(responseV2.getAgreementDateTo());
        responseV1.setCountry(responseV2.getCountry());
        responseV1.setAgreementPremium(responseV2.getAgreementPremium());
        responseV1.setMedicalRiskLimitLevel(responseV2.getPeople().get(0).getMedicalRiskLimitLevel());
        responseV1.setBirthDate(responseV2.getPeople().get(0).getBirthDate());
        responseV1.setPersonFirstName(responseV2.getPeople().get(0).getPersonFirstName());
        responseV1.setPersonLastName(responseV2.getPeople().get(0).getPersonLastName());
        responseV1.setRiskPremiums(responseV2.getPeople().get(0).getResult().riskPremiums());
        responseV1.setErrors(responseV2.getErrors());
        return responseV1;
    }
}
