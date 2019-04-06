package com.marsh.weather.model;

public class TemperatureRO {

	private String temp;
	
	private String timestamp_local;

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getTimestamp_local() {
		return timestamp_local;
	}

	public void setTimestamp_local(String timestamp_local) {
		this.timestamp_local = timestamp_local;
	}

	@Override
	public String toString() {
		return "TemperatureRO [temp=" + temp + ", timestamp_local=" + timestamp_local + "]";
	}
	
	
}
