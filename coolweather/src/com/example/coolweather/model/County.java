package com.example.coolweather.model;

public class County {
	private int id;
	private String countyName;
	private String countyCode;
	private int cityId;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public void setCountyName(String name){
		this.countyName=name;
	}
	public String getCountyName(){
		return countyName;
	}
	public void setCountyCode(String code){
		this.countyCode=code;
	}
	public String getCountyCode(){
		return countyCode;
	}
	public void setCityId(int id){
		this.cityId=id;
	}
	public int getCityId(){
		return cityId;
	}
}
