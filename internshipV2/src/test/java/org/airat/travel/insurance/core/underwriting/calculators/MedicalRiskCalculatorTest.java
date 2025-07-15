package org.airat.travel.insurance.core.underwriting.calculators;

import org.airat.travel.insurance.core.domain.AgeCoefficient;
import org.airat.travel.insurance.core.domain.Country;
import org.airat.travel.insurance.core.domain.InsuranceLimitCoefficient;
import org.airat.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.airat.travel.insurance.core.repositories.CountryRepository;
import org.airat.travel.insurance.core.repositories.InsuranceLimitCoefficientRepository;

import org.airat.travel.insurance.core.services.DateTimeService;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MedicalRiskCalculatorTest {


    @Mock
    AgeCoefficientRepository ageCoefficientRepository;
    @Mock
    CountryRepository countryRepository;
    @Mock
    InsuranceLimitCoefficientRepository limitCoefficientRepository;
    @Mock
    DateTimeService dateTimeService;


    @InjectMocks
    MedicalRiskCalculator calculator;

    TravelCalculatePremiumRequestV2 request= new TravelCalculatePremiumRequestV2();

    PersonDTO person =new PersonDTO();

//
//    BigDecimal countryDefaultDayPremium = BigDecimal.valueOf( countryRepository.findByTitle(request.getCountry()).getDaypremium());
//    BigDecimal dayCount = BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo()));
//    BigDecimal ageCoefficient = BigDecimal.valueOf(ageCoefficientRepository.findAgeCoefficientByAge(dateTimeService.calculateAge(request.getBirthDate())).getCoefficient());
//    BigDecimal insuranceLimitCoefficient = BigDecimal.valueOf(limitCoefficientRepository.findInsuranceLimitCoefficientByLimit(request.getMedicalRiskLimitLevel()).getCoefficient());

    @Test
    void calculatePremiumIfRiskEnabledIsTrueAgeIsFalse() {
        calculator.setMedicalRiskLimitEnabled(true);
        calculator.setMedicalRiskAgeCoefEnabled(false);
        Mockito.when(countryRepository.findByTitle(request.getCountry())).thenReturn(new Country(1,"aaa",15.0));
        Mockito.when(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(15L);
        Mockito.when(limitCoefficientRepository.findInsuranceLimitCoefficientByLimit(person.getMedicalRiskLimitLevel())).thenReturn(new InsuranceLimitCoefficient(1,1,2,2.0));
        assertTrue(BigDecimal.valueOf(450).compareTo(calculator.calculatePremium(request,person))==0);
    }
    @Test
    void calculatePremiumIfRiskEnabledIsTrueAgeIsTrue() {
        calculator.setMedicalRiskLimitEnabled(true);
        calculator.setMedicalRiskAgeCoefEnabled(true);
        Mockito.when(countryRepository.findByTitle(request.getCountry())).thenReturn(new Country(1,"aaa",15.0));
        Mockito.when(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(15L);
        Mockito.when(ageCoefficientRepository.findAgeCoefficientByAge(Mockito.anyInt())).thenReturn(new AgeCoefficient(1,10,15,3.0));
        Mockito.when(limitCoefficientRepository.findInsuranceLimitCoefficientByLimit(person.getMedicalRiskLimitLevel())).thenReturn(new InsuranceLimitCoefficient(1,1,2,2.0));
        assertTrue(BigDecimal.valueOf(1350).compareTo(calculator.calculatePremium(request,person))==0);
    }

    @Test
    void calculatePremiumIfRiskEnabledIsFalseAgeIsFalse() {
        calculator.setMedicalRiskLimitEnabled(false);
        calculator.setMedicalRiskAgeCoefEnabled(false);
        Mockito.when(countryRepository.findByTitle(request.getCountry())).thenReturn(new Country(1,"aaa",15.0));
        Mockito.when(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(15L);
        assertTrue(BigDecimal.valueOf(225).compareTo(calculator.calculatePremium(request,person))==0);
    }
    @Test
    void calculatePremiumIfRiskEnabledIsFalseAgeIsTrue() {
        calculator.setMedicalRiskLimitEnabled(false);
        calculator.setMedicalRiskAgeCoefEnabled(true);
        Mockito.when(countryRepository.findByTitle(request.getCountry())).thenReturn(new Country(1,"aaa",15.0));
        Mockito.when(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(15L);
        Mockito.when(ageCoefficientRepository.findAgeCoefficientByAge(Mockito.anyInt())).thenReturn(new AgeCoefficient(1,10,15,3.0));
        assertTrue(BigDecimal.valueOf(675).compareTo(calculator.calculatePremium(request,person))==0);
    }
}