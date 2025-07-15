package org.airat.travel.insurance.core.validation.person;

import lombok.Getter;
import lombok.Setter;
import org.airat.travel.insurance.core.validation.RequestFieldValidation;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
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
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {

        for (PersonDTO person : request.getPeople()) {
            if (enabled && request.getRisks()!=null && !request.getRisks().isEmpty() && request.getRisks().contains("Медицинские расходы")){
                if(person.getMedicalRiskLimitLevel() ==null) return Optional.of(new ValidationError("medicalRiskLimitLevel","Должен быть заполнен"));
            }
        }
        return Optional.empty();
    }
}
