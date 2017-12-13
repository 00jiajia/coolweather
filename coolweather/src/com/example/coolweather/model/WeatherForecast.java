package com.example.coolweather.model;

public class WeatherForecast {
	private String date;
	private String sunrise;
	private String hight;
	private String low;
	private String fx;
	private String type;
	private String notice;
	
	public void setDtate(String date){
		this.date=date;
	}
	public String getDate(){
		return date;
	}
	public void setSunrise(String sunrise){
		this.sunrise=sunrise;
	}
	public String getSunrise(){
		return sunrise;
	}
	public void setHight(String hight){
		this.hight=hight;
	}
	public String getHight(){
		return hight;
	}
	public void setLow(String low){
		this.low=low;
	}
	public String getLow(){
		return this.low;
	}
	public void setFx(String fx){
		this.fx=fx;
	}
	public String getFx(){
		return fx;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setNotice(String notice){
		this.notice=notice;
	}
	public String getNotice(){
		return notice;
	}
}
