package org.airat.travel.insurance.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.TravelCalculatePremiumService;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel/rest")
public class TravelCalculatePremiumController {

	private final TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;
	private final TravelCalculatePremiumRequestLogger requestLogger;
	private final TravelCalculatePremiumResponseLogger responseLogger;

	private final TravelCalculatePremiumService calculatePremiumService;


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		StopWatch watch =new StopWatch();
		watch.start();
		requestLogger.log(request);
		TravelCalculatePremiumResponse response =calculatePremiumService.calculatePremium(request);
		timeLogger.log(watch);
		responseLogger.log(response);
		return response;
	}

}