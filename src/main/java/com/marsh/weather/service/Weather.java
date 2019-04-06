package com.marsh.weather.service;
/*
 * @author kamal - Initial Version - 03/31/2019
 * */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsh.weather.model.TemperatureRO;
import com.marsh.weather.model.WeatherBitRO;
import com.marsh.weather.utils.ExternalClientUtil;

@Service
public class Weather {

	@Autowired
	private ExternalClientUtil externalClientUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(Weather.class);
	
	/*
	 * 
	 */
	public TemperatureRO getCoolestHourOftheDayForAZipcode(String zipcode,String unit)
	{
		logger.info("Requesting current weather for zip - {}", zipcode);
		
		WeatherBitRO weatherBitRO = filterData(externalClientUtil.invokeWeatherBitService(zipcode,unit));
		
		Optional<TemperatureRO> coolestHourRO = 
				weatherBitRO.getData().stream().min(Comparator.comparing((TemperatureRO temperatureRO) -> Double.parseDouble(temperatureRO.getTemp())));
		
		return coolestHourRO.get();
		
	}
	
	/*
	 * 
	 */
	public WeatherBitRO getForecastForAZipcode(String zipcode)
	{
		logger.info("Requesting current weather for zip - {}", zipcode);
		
		WeatherBitRO weatherBitRO = externalClientUtil.invokeWeatherBitService(zipcode,null);

		return filterData(weatherBitRO);
	}
	
	/**
	 * 
	 */
	public WeatherBitRO filterData(WeatherBitRO weatherBitRO)
	{
		LocalDateTime currentTime = LocalDateTime.now().plusHours(24);
		
		weatherBitRO.getData().removeIf(temperatureRO -> LocalDateTime
						.parse(temperatureRO.getTimestamp_local(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
						.compareTo(currentTime) > 0);
		
		return weatherBitRO;
	}
}
