package org.airat.travel.insurance.core.validation;

import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.repositories.CountryRepository;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CountryInDBValidation implements RequestFieldValidation {

    private final CountryRepository repository;


    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (!(request.getCountry()==null || request.getCountry().isEmpty()) && repository.findByTitle(request.getCountry())==null)
                ? Optional.of(new ValidationError("country","Нет страны в базе данных"))
                : Optional.empty();
    }
}
