package org.airat.travel.insurance.core.underwriting.calculators;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.airat.travel.insurance.core.services.DateTimeService;
import org.airat.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.airat.travel.insurance.core.repositories.CountryRepository;
import org.airat.travel.insurance.core.repositories.InsuranceLimitCoefficientRepository;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Setter
public class MedicalRiskCalculator implements TravelRiskCalculator{

    private final AgeCoefficientRepository ageCoefficientRepository;
    private final CountryRepository countryRepository;
    private final InsuranceLimitCoefficientRepository limitCoefficientRepository;
    private final DateTimeService dateTimeService;

    @Value("${medical.risk.limit.level.enabled}")
    private Boolean medicalRiskLimitEnabled;

    @Value("${medical.risk.age.coefficient.enabled}")
    private Boolean medicalRiskAgeCoefEnabled;


//    premium = CountryDefaultDayPremium * DayCount * AgeCoefficient * InsuranceLimitCoefficient

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV2 request, Person person) {
        BigDecimal countryDefaultDayPremium = BigDecimal.valueOf( countryRepository.findByTitle(request.getCountry()).getDaypremium());
        BigDecimal dayCount = BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo()));
        BigDecimal ageCoefficient = (medicalRiskAgeCoefEnabled)
                ?BigDecimal.valueOf(ageCoefficientRepository.findAgeCoefficientByAge(dateTimeService.calculateAge(person.getBirthDate())).getCoefficient())
                : BigDecimal.ONE;
        BigDecimal insuranceLimitCoefficient =(medicalRiskLimitEnabled)
                ? BigDecimal.valueOf(limitCoefficientRepository.findInsuranceLimitCoefficientByLimit(person.getMedicalRiskLimitLevel()).getCoefficient())
                : BigDecimal.ONE;

        return countryDefaultDayPremium.multiply(dayCount).multiply(ageCoefficient).multiply(insuranceLimitCoefficient);
    }


    @Override
    public String getRisk() {
        return "Медицинские расходы";
    }
}
