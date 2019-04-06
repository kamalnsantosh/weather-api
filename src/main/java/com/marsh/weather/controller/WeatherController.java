package com.marsh.weather.controller;
/*
 * @author kamal - Initial Version - 03/31/2019
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.marsh.weather.model.TemperatureRO;
import com.marsh.weather.model.WeatherBitRO;
import com.marsh.weather.service.Weather;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/weather/forecast")
@Service
public class WeatherController {

	@Autowired
	private Weather weather;
	
	/*
	 *	Operation to get Weather forecast for the next 24 hours for a zip code
	 */
	@RequestMapping(value="/{zipcode}", method=RequestMethod.GET)
	public ResponseEntity<WeatherBitRO> getForcast(@PathVariable String zipcode)
	{
		WeatherBitRO weatherBitRO = null;
		try
		{
			weatherBitRO = weather.getForecastForAZipcode(zipcode);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(weatherBitRO,HttpStatus.OK);
		
	}
	
	/*
	 *	Operation to get coolest hour of the next 24 hours for a zip code
	 */
	@RequestMapping(value="/{zipcode}/coolest", method=RequestMethod.GET)
	public ResponseEntity<TemperatureRO> getWeather
			(@PathVariable String zipcode,
			@ApiParam(name = "unit", value="F", defaultValue = "C")
			@RequestParam(value = "unit", required=false) String unit)
	{
		TemperatureRO temperatureRO = null;
		try
		{
			System.out.println("--unit-"+unit);
			if(unit!=null && !"F".equals(unit) && !"C".equals(unit)) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			temperatureRO = weather.getCoolestHourOftheDayForAZipcode(zipcode,unit);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(temperatureRO,HttpStatus.OK);
		
	}
}
