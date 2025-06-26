package org.airat.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;

    private String personLastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private String country;

    private BigDecimal medicalRiskLimitLevel;

    private BigDecimal agreementPremium;


    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCalculatePremiumResponse response = (TravelCalculatePremiumResponse) o;
        return Objects.equals(super.getErrors(),response.getErrors()) && Objects.equals(personFirstName, response.personFirstName) && Objects.equals(personLastName, response.personLastName) && Objects.equals(agreementDateFrom, response.agreementDateFrom) && Objects.equals(agreementDateTo, response.agreementDateTo) && Objects.equals(birthDate, response.birthDate) && Objects.equals(country, response.country) && Objects.equals(medicalRiskLimitLevel, response.medicalRiskLimitLevel) && Objects.equals(agreementPremium, response.agreementPremium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personLastName, agreementDateFrom, agreementDateTo, birthDate, country, medicalRiskLimitLevel, agreementPremium);
    }

    @Override
    public String toString() {
        return "TravelCalculatePremiumResponse{" +
                 super.toString() + '\'' +
                "personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", agreementDateFrom=" + agreementDateFrom +
                ", agreementDateTo=" + agreementDateTo +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                ", medicalRiskLimitLevel=" + medicalRiskLimitLevel +
                ", agreementPremium=" + agreementPremium +
                '}';
    }
}
