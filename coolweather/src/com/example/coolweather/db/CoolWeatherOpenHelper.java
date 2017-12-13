package com.example.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	public static final String Create_province="create table Province ("
			+"id integer primary key autoincrement, "
			+"province_name text, "
			+"province_code text)";
	
	public static final String Create_city="create table City ("
			+"id integer primary key autoincrement, "
			+"city_name text, "
			+"city_code text, "
			+"province_id integer)";
	public static final String Create_county="create table County ("
			+"id integer primary key autoincrement, "
			+"county_name text, "
			+"county_code text, "
			+"city_id integer)";
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Create_province);
		db.execSQL(Create_city);
		db.execSQL(Create_county);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists Province");
		db.execSQL("drop table if exists City");
		db.execSQL("drop table if exists County");
		
		onCreate(db);
	}

}
