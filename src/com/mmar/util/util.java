package com.mmar.util;
import java.io.File;
import java.util.Random;
import android.graphics.Paint;
import android.graphics.Typeface;
public class util
{
	public static String enterChar=System.getProperty("line.separator");
	public static int getInt(String str){
		return Integer.parseInt(str);
	}
	public static long getLong(String str){
		return Long.parseLong(str);
	}
	public static int getRandomNumber(int max){
		Random r=new Random();
		return r.nextInt(max);
	}
	public static void setPaintStyleBold(Paint p){
		p.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
	}
}
