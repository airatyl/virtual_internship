package org.airat.travel.insurance.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    void log(StopWatch watch) {
        watch.stop();
        logger.info("Время обработки запроса = {} мс", watch.getTotalTimeMillis());
    }
}
