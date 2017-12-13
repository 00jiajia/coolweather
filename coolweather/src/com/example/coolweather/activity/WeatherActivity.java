package com.example.coolweather.activity;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.coolweather.R;
import com.example.coolweather.model.WeatherForecast;
import com.example.coolweather.model.WeatherInfo;
import com.example.coolweather.util.HttpCallbackListener;
import com.example.coolweather.util.HttpUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends Activity {
	private TextView publishText;
	private TextView cityName;
	private TextView currentDate;
	private TextView weatherdesp;
	private TextView weatherquality;
	private Button switchCityButton;
	private Button refreshButton;
	private ListView listForcasting;
	private String countyString;
	private List<String> dataList=new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private WeatherInfo info;
	private List<WeatherForecast> list;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);
		
		publishText=(TextView)findViewById(R.id.publish_text);
		cityName=(TextView)findViewById(R.id.city_name);
		currentDate=(TextView)findViewById(R.id.current_date);
		weatherdesp=(TextView)findViewById(R.id.weather_desp);
		weatherquality=(TextView)findViewById(R.id.weather_quality);
		switchCityButton=(Button)findViewById(R.id.switch_city);
		refreshButton=(Button)findViewById(R.id.refresh_weather);
		listForcasting=(ListView)findViewById(R.id.weather_forcasting);
		switchCityButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(WeatherActivity.this,ChooseAreaActivity.class);
				startActivity(intent);
				finish();
			}
		});
		refreshButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				queryWeahterInfo(countyString);
			}
		});
		
		
		currentDate.setText(getCurrentDate());
		
		String countyName=getIntent().getStringExtra("county_name");
		if(!TextUtils.isEmpty(countyName)){
			countyString=countyName;
			queryWeahterInfo(countyName);
		}
		
		
		
	}
	
	@SuppressLint("SimpleDateFormat")
	private String getCurrentDate(){
		SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd");
		String currentTime=formate.format(new Date());
		return currentTime;
	}
	
	private void queryWeahterInfo(String cityCode){
		String address="http://www.sojson.com/open/api/weather/json.shtml?city="+URLEncoder.encode(cityCode);
		queryFromServer(address);
	}
	private void queryFromServer(final String address){
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				
				if(!TextUtils.isEmpty(response)){
					if(response.indexOf("Success")>-1){
						ParseJsonWeatheToInfo(response);
						if(list!=null&&list.size()>0&&info!=null ){
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									ShowWeatherInfo(info,list);							
								}
							});
						}
					}else {
						Toast.makeText(WeatherActivity.this, response, Toast.LENGTH_SHORT).show();
						Log.d("WeatherActivity_ERROR", response);
					}
				}else {
					Toast.makeText(WeatherActivity.this, "查询天气返回结果为空", Toast.LENGTH_SHORT).show();
					Log.d("WeatherActivity_ERROR", "查询天气返回结果为空");
				}
				
			}
			
			@Override
			public void onError(Exception e) {
				Log.d("ErrorWeatherActivity", e.getMessage());
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						publishText.setText("同步失败");
					}
				});
			}
		});
	}
	
	private void ParseJsonWeatheToInfo(String response){
		try {
			JSONObject object=new JSONObject(response);
			JSONObject dataObject=object.getJSONObject("data");
			
			info=new WeatherInfo();
			list=new ArrayList<WeatherForecast>();
			list.clear();
			info.setCity(getRtString("city",object));
			info.setQuality(getRtString("quality",dataObject));
			info.setTemperature(getRtString("wendu",dataObject));
			JSONArray jsonForecasts=dataObject.getJSONArray("forecast");
			if(jsonForecasts.length()>0){
				for(int i=1;i<jsonForecasts.length();i++){
					JSONObject temp=jsonForecasts.getJSONObject(i);
					WeatherForecast forecasting=new WeatherForecast();
					forecasting.setDtate(getRtString("date",temp));
					forecasting.setSunrise(getRtString("sunrise",temp));
					forecasting.setHight(getRtString("high",temp));
					forecasting.setLow(getRtString("low",temp));
					forecasting.setFx(getRtString("fx",temp));
					forecasting.setNotice(getRtString("notice",temp));
					forecasting.setType(getRtString("type",temp));
					
					if(i==1){
						info.setType(getRtString("type",temp));
					}
					
					list.add(forecasting);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	private String getRtString(String name,JSONObject obj){
		String rtnString="";
		try {
			rtnString=TextUtils.isEmpty(obj.getString(name))?"":obj.getString(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rtnString;
	}
	public void ShowWeatherInfo(WeatherInfo info,List<WeatherForecast> list){
		dataList.clear();
		cityName.setText(info.getCity());
		weatherdesp.setText("温度   "+info.getTemperature()+" ℃");
		weatherquality.setText(info.getType()+" | "+info.getQuality());
		if(list.size()>0){
			for(WeatherForecast f:list){
				String tempString=f.getDate()+"   "+f.getLow()+"~"+f.getHight()+" \n"
						+f.getType()+"   "+f.getFx();
				dataList.add(tempString);
			}
			adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dataList);
			listForcasting.setAdapter(adapter);
		}
	}
	
}
