package org.airat.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {

    private final static Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    void log (TravelCalculatePremiumRequest request){
        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String result = objectMapper.writeValueAsString(request);
            logger.info("Request: {}",result);
        } catch (JsonProcessingException e) {
            logger.error("Ошибка конвертировать запрос в JSON",e);
        }
    }
}
