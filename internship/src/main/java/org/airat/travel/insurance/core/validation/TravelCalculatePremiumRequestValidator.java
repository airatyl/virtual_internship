package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelCalculatePremiumRequestValidator implements TravelCalculatePremiumRequestValidatorInterface {

    private final List<RequestFieldValidation> requestFieldValidationList;


    @Autowired
    TravelCalculatePremiumRequestValidator(List<RequestFieldValidation> requestFieldValidationList) {
        this.requestFieldValidationList = requestFieldValidationList;
    }


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        requestFieldValidationList.forEach(validation -> validation.validateField(request).ifPresent(errors::add));
        return errors;
    }


}
