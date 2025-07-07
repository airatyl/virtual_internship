package org.airat.travel.insurance.rest.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.airat.travel.insurance.dto.RiskPremium;
import org.airat.travel.insurance.dto.TravelPremiumCalculationResult;
import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerV2Test {

    @Autowired
    private MockMvc mockMvc;


    private TravelCalculatePremiumRequestV2 rightRequest;

    private TravelCalculatePremiumResponseV2 rightResponse;

    @BeforeEach
    public void initRequestAndResponse(){
        rightRequest= new TravelCalculatePremiumRequestV2(LocalDate.now().plusDays(1),LocalDate.now().plusDays(10),
                List.of(new Person("Айрат","Bilyaletdinov",
                                LocalDate.of(2005, Month.JANUARY,20),BigDecimal.valueOf(100)),
                        new Person("sad","asd",LocalDate.of(2005, Month.JANUARY,20),BigDecimal.valueOf(100))),
                "Россия",List.of("Медицинские расходы","Отмена поездки"));


    }

    @Test
    public void firstNameNotProvided() throws Exception {

        rightRequest.getPeople().forEach(person -> person.setPersonFirstName(null));
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("personFirstName", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void lastNameNotProvidedTest() throws Exception {

        rightRequest.getPeople().forEach(person -> person.setPersonLastName(null));
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("personLastName", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateFromNotProvidedTest() throws Exception {

        rightRequest.setAgreementDateFrom(null);
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("agreementDateFrom", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateToNotProvidedTest() throws Exception {

        rightRequest.setAgreementDateTo(null);
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("agreementDateTo", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateFromNotInFutureTest() throws Exception {

        rightRequest.setAgreementDateFrom(LocalDate.of(2024,1,1));
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("agreementDateFrom", "Дата начала должна быть в будущем")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateToNotInFutureTest() throws Exception {
        rightRequest.setAgreementDateTo(LocalDate.of(2024,1,1));
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("agreementDates","Дата начала должна быть раньше даты окончания"),new ValidationError("agreementDateTo", "Дата окончания должна быть в будущем")));

        executeAndCompare(rightRequest,rightResponse);
    }

    @Test
    public void agreementDateFromBeforeAgreementDateToTest() throws Exception {

        rightRequest.setAgreementDateFrom(LocalDate.of(2025,9,13));
        rightRequest.setAgreementDateTo(LocalDate.of(2025,9,10));
        rightResponse =new TravelCalculatePremiumResponseV2(List.of(new ValidationError("agreementDates","Дата начала должна быть раньше даты окончания")));

        executeAndCompare(rightRequest,rightResponse);
    }



//    @Test
//    public void allFieldsNotProvidedTest() throws Exception {
//
//
//    }


    @Test
    public void AllFieldsCorrectTest() throws Exception {
        rightResponse = new TravelCalculatePremiumResponseV2(LocalDate.now().plusDays(1),LocalDate.now().plusDays(10),
                "Россия",BigDecimal.valueOf(12002),
                List.of(new Person("Айрат","Bilyaletdinov",
                                LocalDate.of(2005, Month.JANUARY,20),BigDecimal.valueOf(100),new TravelPremiumCalculationResult(BigDecimal.valueOf(6001),List.of(new RiskPremium("Медицинские расходы",BigDecimal.valueOf(6000)),new RiskPremium("Отмена поездки",BigDecimal.ONE)))),
                        new Person("sad","asd",LocalDate.of(2005, Month.JANUARY,20),BigDecimal.valueOf(100),new TravelPremiumCalculationResult(BigDecimal.valueOf(6001),List.of(new RiskPremium("Медицинские расходы",BigDecimal.valueOf(6000)),new RiskPremium("Отмена поездки",BigDecimal.ONE))))));

        executeAndCompare(rightRequest,rightResponse);
    }

    private void executeAndCompare(TravelCalculatePremiumRequestV2 request,
                                   TravelCalculatePremiumResponseV2 expectedResponse) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        MvcResult result = mockMvc.perform(post("/insurance/travel/rest/v2/")
                        .content(mapper.writeValueAsString(request))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(expectedResponse,mapper.readValue(response,TravelCalculatePremiumResponseV2.class));
    }

}