package com.example.coolweather.model;

public class City {
	private int id;
	private String cityName;
	private String cityCode;
	private int provinceId;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setCityName(String name){
		this.cityName=name;
	}
	public String getCityName(){
		return cityName;
	}
	public void setCityCode(String code){
		this.cityCode=code;
	}
	public String getCityCode(){
		return cityCode;
	}
	public void setProvinceId(int id){
		this.provinceId=id;
	}
	public int getProvinceId(){
		return provinceId;
	}
	

}
