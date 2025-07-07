package org.airat.travel.insurance.core.underwriting;

import lombok.Getter;
import org.airat.travel.insurance.core.underwriting.calculators.TravelRiskCalculator;
import org.airat.travel.insurance.dto.RiskPremium;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.TravelPremiumCalculationResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Getter
public class TravelPremiumUnderwriting {


    private final HashMap<String,TravelRiskCalculator> riskCalculatorsHashMap;

    TravelPremiumUnderwriting(List<TravelRiskCalculator> riskCalculators) {
        this.riskCalculatorsHashMap = new HashMap<>();
        riskCalculators.forEach(risk-> riskCalculatorsHashMap.put(risk.getRisk(),risk));
    }

    public TravelPremiumCalculationResult calculatePremiumForSelectedRisks(TravelCalculatePremiumRequestV2 request, Person person){
        List<RiskPremium> answer = new ArrayList<>();
        final BigDecimal[] summ = {BigDecimal.ZERO};
        request.getRisks().forEach(risk->{
            BigDecimal premiumForRisk =riskCalculatorsHashMap.get(risk).calculatePremium(request,person);
            answer.add
                    (new RiskPremium(risk,premiumForRisk ));
            summ[0] = summ[0].add((premiumForRisk));
        }  );
        return new TravelPremiumCalculationResult(summ[0],answer);
    }


}
