package com.example.coolweather.util;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.bool;
import android.text.TextUtils;
import android.util.Log;

import com.example.coolweather.db.CoolWeatherDB;
import com.example.coolweather.model.City;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

public class Utility {

	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			try {
				JSONArray allProvinces=new JSONArray(response);
				for(int i=0;i<allProvinces.length();i++){
					JSONObject provinceObject=allProvinces.getJSONObject(i);
					Province province=new Province();
					province.setProvinceName(provinceObject.getString("name"));
					province.setProvinceCode(""+provinceObject.getInt("id"));
					coolWeatherDB.saveProvince(province);
				}
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int ProvinceId){
		Log.d("handleCitiesResponse", "response");
		if(!TextUtils.isEmpty(response)){
			try {
				Log.d("hanleCity", response);
				JSONArray allcities=new JSONArray(response);
				for(int i=0;i<allcities.length();i++){
					JSONObject obj=allcities.getJSONObject(i);
					City city=new City();
					city.setCityName(obj.getString("name"));
					city.setCityCode(""+obj.getInt("id"));
					city.setProvinceId(ProvinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			try {
				JSONArray allCounties=new JSONArray(response);
				for(int i=0;i<allCounties.length();i++){
					JSONObject obj=allCounties.getJSONObject(i);
					County county=new County();
					county.setCountyName(obj.getString("name"));
					county.setCountyCode(""+obj.getInt("id"));
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean handleWeatherResponse(String response){
		if(!TextUtils.isEmpty(response)){
			try {
				JSONObject object=new JSONObject(response);
				
				
			} catch (Exception e) {
			}
		}
		return false;
		
	}
	
}
