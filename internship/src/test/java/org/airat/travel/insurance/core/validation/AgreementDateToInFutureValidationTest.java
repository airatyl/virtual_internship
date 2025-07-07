package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateToInFutureValidationTest {

    AgreementDateToInFutureValidation validation = new AgreementDateToInFutureValidation();

    @Test
    public void AgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void AgreementDateFromInPast() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 9, 9));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("agreementDateTo", "Дата окончания должна быть в будущем"), error.get());
    }

    @Test
    public void AgreementDateFromInFuture() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2027, 9, 9));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }

}