package org.airat.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.airat.travel.insurance.dto.CoreResponse;
import org.airat.travel.insurance.dto.RiskPremium;
import org.airat.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelCalculatePremiumResponseV1 extends CoreResponse {


    private String personFirstName;

    private String personLastName;

    private String personCode;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private String country;

    private BigDecimal medicalRiskLimitLevel;

    private BigDecimal agreementPremium;

    private List<RiskPremium> riskPremiums;

    private String uuid;


    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCalculatePremiumResponseV1 response = (TravelCalculatePremiumResponseV1) o;
        return Objects.equals(super.getErrors(),response.getErrors()) && Objects.equals(personFirstName, response.personFirstName) && Objects.equals(personLastName, response.personLastName)&& Objects.equals(personCode, response.personCode) && Objects.equals(agreementDateFrom, response.agreementDateFrom) &&Objects.equals(uuid, response.uuid)&& Objects.equals(agreementDateTo, response.agreementDateTo) && Objects.equals(birthDate, response.birthDate) && Objects.equals(country, response.country) && (Objects.equals(medicalRiskLimitLevel, response.medicalRiskLimitLevel) || medicalRiskLimitLevel.compareTo(response.medicalRiskLimitLevel)==0) && (Objects.equals(agreementPremium, response.agreementPremium) || agreementPremium.compareTo(response.agreementPremium)==0) && Objects.equals(riskPremiums,response.riskPremiums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personLastName,personCode,personCode, agreementDateFrom, agreementDateTo, birthDate, country, medicalRiskLimitLevel, agreementPremium,riskPremiums,uuid);
    }




}
