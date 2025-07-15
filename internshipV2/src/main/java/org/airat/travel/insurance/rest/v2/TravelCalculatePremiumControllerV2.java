package org.airat.travel.insurance.rest.v2;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.services.TravelCalculatePremiumService;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel/rest/v2")
public class TravelCalculatePremiumControllerV2 {

	private final TravelCalculatePremiumRequestExecutionTimeLoggerV2 timeLogger;
	private final TravelCalculatePremiumRequestLoggerV2 requestLogger;
	private final TravelCalculatePremiumResponseLoggerV2 responseLogger;

	private final TravelCalculatePremiumService calculatePremiumService;


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		StopWatch watch =new StopWatch();
		watch.start();
		requestLogger.log(request);
		TravelCalculatePremiumResponseV2 response =calculatePremiumService.calculatePremium(request);
		timeLogger.log(watch);
		responseLogger.log(response);
		return response;
	}

}