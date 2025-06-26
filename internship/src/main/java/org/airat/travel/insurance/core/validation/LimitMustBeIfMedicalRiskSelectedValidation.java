package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LimitMustBeIfMedicalRiskSelectedValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        if (request.getRisks()!=null && !request.getRisks().isEmpty() && request.getRisks().contains("Медицинские расходы")){
            if(request.getMedicalRiskLimitLevel() !=null) return Optional.empty();
            else return Optional.of(new ValidationError("medicalRiskLimitLevel","Должен быть заполнен"));
        }
        else return Optional.empty();
    }
}
