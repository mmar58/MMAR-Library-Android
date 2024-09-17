package com.mmar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
public class dialer
{
	public static void dialussd(String code,Context con){
		String s="";
		if(code.contains("#")){
			s=code.replace("#",Uri.encode("#"));
		}else{
			s=code+Uri.encode("#");
		}
		con.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+s)));
	}
	public static void callussd(String code,Context con){
		String s="";
		if(code.contains("#")){
			s=code.replace("#",Uri.encode("#"));
		}else{
			s=code+Uri.encode("#");
		}
		con.startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+s)));
	}
	public static void dial(String num,Context con){
		con.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+num)));
	}
	public static void call(String num,Context con){
		con.startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+num)));
	}
}
