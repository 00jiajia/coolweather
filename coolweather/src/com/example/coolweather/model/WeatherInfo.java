package com.example.coolweather.model;

public class WeatherInfo {
	private String quality;
	private String temperature;
	private String city;
	private String type;
	
	
	public void setCity(String city){
		this.city=city;
	}
	
	public String getCity(){
		return city;
	}
	public void setQuality(String quality){
		this.quality=quality;
	}
	public String getQuality(){
		return quality;
	}
	public void setTemperature(String temperature){
		this.temperature=temperature;
	}
	public String getTemperature(){
		return temperature;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}

	
}
