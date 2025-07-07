package org.airat.travel.insurance.core.underwriting.calculators;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.airat.travel.insurance.core.DateTimeService;
import org.airat.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.airat.travel.insurance.core.repositories.CountryRepository;
import org.airat.travel.insurance.core.repositories.InsuranceLimitCoefficientRepository;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
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
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal countryDefaultDayPremium = BigDecimal.valueOf( countryRepository.findByTitle(request.getCountry()).getDaypremium());
        BigDecimal dayCount = BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo()));
        BigDecimal ageCoefficient = (medicalRiskAgeCoefEnabled)
                ?BigDecimal.valueOf(ageCoefficientRepository.findAgeCoefficientByAge(dateTimeService.calculateAge(request.getBirthDate())).getCoefficient())
                : BigDecimal.ONE;
        BigDecimal insuranceLimitCoefficient =(medicalRiskLimitEnabled)
                ? BigDecimal.valueOf(limitCoefficientRepository.findInsuranceLimitCoefficientByLimit(request.getMedicalRiskLimitLevel()).getCoefficient())
                : BigDecimal.ONE;

        return countryDefaultDayPremium.multiply(dayCount).multiply(ageCoefficient).multiply(insuranceLimitCoefficient);
    }


    @Override
    public String getRisk() {
        return "Медицинские расходы";
    }
}
