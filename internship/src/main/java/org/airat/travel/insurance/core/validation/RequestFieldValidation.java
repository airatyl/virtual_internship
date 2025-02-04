package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface RequestFieldValidation {
    public Optional <ValidationError> validateField (TravelCalculatePremiumRequest request);
}
