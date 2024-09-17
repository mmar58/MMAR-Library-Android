package com.mmar.util;
import java.text.SimpleDateFormat;
import java.util.Date;
public class dateutil
{public static String day="EEE";
static long secs=1000;
static long mins=secs*60;
static long hours=60*mins;
static long days=24*hours;
static long years=days*365;
public static String date="dd MMMM yyyy";
public static String time="h:mm a";
	static String fullform=day+","+time+","+date;
	public static String print(){
		return new SimpleDateFormat(fullform).format(new Date());
	}
	public static String print(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	public static String print(Date date){
		return new SimpleDateFormat(fullform).format(date);
	}
	public static String print(long mill){
		return new SimpleDateFormat(fullform).format(new Date(mill));
	}
	public static String print(Date date,String formate){
		return new SimpleDateFormat(formate).format(date);
	}
	public static String print(long mill,String format){
		return new SimpleDateFormat(format).format(new Date(mill));
	}
	public static long getTime(long year,long day,long hour,long min,long sec){
		return year*years+day*days+hour*hours+min*mins+sec*secs;
	}
	}
