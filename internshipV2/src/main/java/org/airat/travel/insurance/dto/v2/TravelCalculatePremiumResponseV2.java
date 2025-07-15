package org.airat.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.airat.travel.insurance.dto.CoreResponse;
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
public class TravelCalculatePremiumResponseV2 extends CoreResponse {

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;

    private String country;
    private BigDecimal agreementPremium;

    private String uuid;

    private List<PersonDTO> people;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCalculatePremiumResponseV2 that = (TravelCalculatePremiumResponseV2) o;
        return Objects.equals(agreementDateFrom, that.agreementDateFrom) && Objects.equals(agreementDateTo, that.agreementDateTo) && Objects.equals(country, that.country) && (Objects.equals(agreementPremium, that.agreementPremium)|| agreementPremium.compareTo(that.agreementPremium)==0) &&Objects.equals(uuid, that.uuid)&& Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agreementDateFrom, agreementDateTo, country, agreementPremium,uuid, people);
    }

    public TravelCalculatePremiumResponseV2(List<ValidationError> errors) {
        super(errors);
    }
}
