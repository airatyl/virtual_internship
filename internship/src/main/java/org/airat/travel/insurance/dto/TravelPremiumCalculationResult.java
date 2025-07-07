package org.airat.travel.insurance.dto;


import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskPremium> riskPremiums
) {}
