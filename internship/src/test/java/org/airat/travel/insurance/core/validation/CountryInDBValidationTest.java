package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.core.domain.Country;
import org.airat.travel.insurance.core.repositories.CountryRepository;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountryInDBValidationTest {
    @Mock
    CountryRepository repository;

    @InjectMocks
    CountryInDBValidation countryInDBValidation;

    @Test
    public void countryNotSelected(){
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getCountry()).thenReturn(null);
        Optional<ValidationError> error = countryInDBValidation.validateField(request);
        assertTrue(error.isEmpty());
    }
    @Test
    public void countryInDB(){
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getCountry()).thenReturn("Россия");
        Mockito.when(repository.findByTitle("Россия")).thenReturn(new Country());
        Optional<ValidationError> error = countryInDBValidation.validateField(request);
        assertTrue(error.isEmpty());
    }
    @Test
    public void countryNotInDB(){
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getCountry()).thenReturn("Россия");
        Mockito.when(repository.findByTitle("Россия")).thenReturn(null);
        Optional<ValidationError> error = countryInDBValidation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("country","Нет страны в базе данных"),error.get());
    }

}