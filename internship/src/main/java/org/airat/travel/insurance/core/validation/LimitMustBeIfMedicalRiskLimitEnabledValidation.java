package org.airat.travel.insurance.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Setter
@Getter
public class LimitMustBeIfMedicalRiskLimitEnabledValidation implements RequestFieldValidation {

    @Value("${medical.risk.limit.level.enabled}")
    private Boolean enabled;
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        if (enabled && request.getRisks()!=null && !request.getRisks().isEmpty() && request.getRisks().contains("Медицинские расходы")){
            if(request.getMedicalRiskLimitLevel() !=null) return Optional.empty();
            else return Optional.of(new ValidationError("medicalRiskLimitLevel","Должен быть заполнен"));
        }
        else return Optional.empty();
    }
}
