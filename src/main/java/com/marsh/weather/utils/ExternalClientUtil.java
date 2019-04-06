package com.marsh.weather.utils;

/*
 * @author kamal - Initial Version - 03/31/2019
 * */

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.stereotype.Service;

import com.marsh.weather.constants.WeatherConstants;
import com.marsh.weather.model.WeatherBitRO;

@Service
public class ExternalClientUtil {

	
	public WeatherBitRO invokeWeatherBitService(String zipcode,String unit)
	{
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WeatherConstants.weather_url)
		        .queryParam("postal_code", zipcode)
		        .queryParam("country", "US")
		        .queryParam("key", WeatherConstants.api_key);
		
		if(unit!=null && !unit.isEmpty())
		{
			unit = "F".equals(unit)?"I":"M"; 
			builder.queryParam("units", unit);
		}
		RequestEntity<?> request = RequestEntity.get(builder.build().toUri())
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<WeatherBitRO> exchange = new RestTemplate()
				.exchange(request, WeatherBitRO.class);
		WeatherBitRO weatherBitRO= exchange.getBody();
		return weatherBitRO;
	}
}
