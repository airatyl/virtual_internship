package org.airat.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.airat.travel.insurance.dto.TravelPremiumCalculationResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private String personFirstName;

    private String personLastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private BigDecimal medicalRiskLimitLevel;
    private TravelPremiumCalculationResult result;

    public Person(String personFirstName, String personLastName, LocalDate birthDate, BigDecimal medicalRiskLimitLevel) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.birthDate = birthDate;
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personFirstName, person.personFirstName) && Objects.equals(personLastName, person.personLastName) && Objects.equals(birthDate, person.birthDate) && (Objects.equals(medicalRiskLimitLevel, person.medicalRiskLimitLevel) || medicalRiskLimitLevel.compareTo(person.medicalRiskLimitLevel)==0) && Objects.equals(result, person.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personLastName, birthDate, medicalRiskLimitLevel, result);
    }
}
