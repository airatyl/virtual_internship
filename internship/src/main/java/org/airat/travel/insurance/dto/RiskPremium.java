package org.airat.travel.insurance.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RiskPremium {
    String risk;
    BigDecimal premium;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskPremium that = (RiskPremium) o;
        return Objects.equals(risk, that.risk) &&(Objects.equals(premium, that.premium) || premium.compareTo(that.premium)==0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(risk, premium);
    }
}
