package com.marsh.weather.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherBitRO {

	@JsonProperty("data")
	private List<TemperatureRO> temperatureRO;
	
	private String state_code;

	

	public List<TemperatureRO> getData() {
		return temperatureRO;
	}



	public void setData(List<TemperatureRO> temperatureRO) {
		this.temperatureRO = temperatureRO;
	}



	public String getState_code() {
		return state_code;
	}



	public void setState_code(String state_code) {
		this.state_code = state_code;
	}



	@Override
	public String toString() {
		return "WeatherBitRO [temperatureRO=" + temperatureRO + ", state_code=" + state_code + "]";
	}
	
	
	
}
