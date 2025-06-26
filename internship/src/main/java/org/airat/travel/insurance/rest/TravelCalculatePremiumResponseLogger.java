package org.airat.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {

    private final static Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);

    void log (TravelCalculatePremiumResponse response){
        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String result = objectMapper.writeValueAsString(response);
            logger.info("Response: {}",result);
        } catch (JsonProcessingException e) {
            logger.error("Ошибка конвертировать ответ в JSON",e);
        }
    }
}
