package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgeMustBeIfMedicalRiskSelectedValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        if (request.getRisks()!=null && !request.getRisks().isEmpty() && request.getRisks().contains("Медицинские расходы")){
            if(request.getBirthDate() !=null) return Optional.empty();
            else return Optional.of(new ValidationError("birthDate","Должен быть заполнен"));
        }
        else return Optional.empty();
    }
}
