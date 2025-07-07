package org.airat.travel.insurance.core.services;

import org.airat.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.*;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    TravelPremiumUnderwriting underwriting;
    @Mock
    TravelCalculatePremiumRequestValidator requestValidator;

    private TravelCalculatePremiumRequestV2 request;

    private TravelCalculatePremiumResponseV2 response;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private  Person person = new Person();

    @BeforeEach
    public void initRequestAndResponse(){
        request= new TravelCalculatePremiumRequestV2(LocalDate.now().plusDays(1),LocalDate.now().plusDays(10),
                List.of(new Person("Айрат","Bilyaletdinov",
                LocalDate.of(2005, Month.JANUARY,20),BigDecimal.valueOf(100)),
                        new Person("sad","asd",LocalDate.of(2005, Month.JANUARY,20),BigDecimal.valueOf(100))),
                "Россия",List.of("Медицинские расходы","Отмена поездки"));
    }



    @Test
    public void returnResponseWithErrors(){
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of(new ValidationError("field","message")));
        response=service.calculatePremium(request);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void returnResponseWithCorrectErrors(){
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of(new ValidationError("field","message")));
        response=service.calculatePremium(request);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "message");
    }

    @Test
    public void responseFieldsNullIfHaveErrors(){
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of(new ValidationError("field","message")));
        response=service.calculatePremium(request);
        assertNull(response.getPeople());
        assertNull(response.getCountry());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPremium());
    }




    @Test
    public void responseFillingFirstName() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(Mockito.eq( request),Mockito.any(Person.class))).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getPeople().get(0).getPersonFirstName(),response.getPeople().get(0).getPersonFirstName());
        assertEquals(request.getPeople().get(1).getPersonFirstName(),response.getPeople().get(1).getPersonFirstName());

    }


    @Test
    public void responseFillingLastName() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(Mockito.eq( request),Mockito.any(Person.class))).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getPeople().get(0).getPersonLastName(),response.getPeople().get(0).getPersonLastName());
        assertEquals(request.getPeople().get(1).getPersonLastName(),response.getPeople().get(1).getPersonLastName());

    }

    @Test
    public void responseFillingAgreementDateFrom() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(Mockito.eq( request),Mockito.any(Person.class))).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(),response.getAgreementDateFrom());

    }

    @Test
    public void responseFillingAgreementDateTo() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(Mockito.eq( request),Mockito.any(Person.class))).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());

    }
    @Test
    public void responseCalculateAgreementPrice() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(Mockito.eq( request),Mockito.any(Person.class))).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(new BigDecimal(16),response.getAgreementPremium());

    }



}