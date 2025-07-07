package org.airat.travel.insurance.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskPremium> riskPremiums

) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelPremiumCalculationResult that = (TravelPremiumCalculationResult) o;
        return (Objects.equals(totalPremium, that.totalPremium) || totalPremium.compareTo(that.totalPremium)==0)&& Objects.equals(riskPremiums, that.riskPremiums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPremium, riskPremiums);
    }
}
