package org.airat.travel.insurance.core.validation;

import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.repositories.RiskTypeRepository;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RiskInDatabaseValidation implements RequestFieldValidation {

    private final RiskTypeRepository riskTypeRepository;

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        StringBuilder message = new StringBuilder("Выбранные риски не существуют:");
        int length1 = message.length();
        if(!(request.getRisks()==null || request.getRisks().isEmpty())){
            request.getRisks().forEach(risk-> {
                if (riskTypeRepository.findByTitle(risk)==null){
                    message.append(" ").append(risk).append(",");
                }
            });}
        message.deleteCharAt(message.length()-1);
        return (message.length() <= length1)
                ? Optional.empty()
                : Optional.of(new ValidationError("risks",message.toString()));
    }
}
