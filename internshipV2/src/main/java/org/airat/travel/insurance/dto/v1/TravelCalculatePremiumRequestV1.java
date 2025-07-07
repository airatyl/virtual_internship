package org.airat.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequestV1 {



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

    private List<String> risks;


    public TravelCalculatePremiumRequestV1(String personFirstName, String personLastName, LocalDate agreementDateFrom, LocalDate agreementDateTo) {
    }

}
