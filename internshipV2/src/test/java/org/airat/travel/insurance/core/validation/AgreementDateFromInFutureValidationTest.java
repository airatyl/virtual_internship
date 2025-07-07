package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromInFutureValidationTest {

    AgreementDateFromInFutureValidation validation = new AgreementDateFromInFutureValidation();

    @Test
    public void AgreementDateFromIsNull() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void AgreementDateFromInPast() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 9, 9));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("agreementDateFrom", "Дата начала должна быть в будущем"), error.get());
    }

    @Test
    public void AgreementDateFromInFuture() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2027, 9, 9));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isEmpty());
    }
}
