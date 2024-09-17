package com.mmar;
import android.content.Context;
import android.widget.Toast;
import java.util.Locale;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import android.speech.tts.TextToSpeech;
public class tts
{Context con;
public TextToSpeech talk;
	public tts(Context con){
		this.con=con;
		talk=new TextToSpeech(con,new TextToSpeech.OnInitListener(){
			@Override
			public void onInit(int status){
				talk.setLanguage(Locale.US);
			}
		});
	}
	public void toastShort(String text){
		Toast.makeText(con,text,Toast.LENGTH_SHORT).show();
	}
	public void toastLong(String text){
		Toast.makeText(con,text,Toast.LENGTH_LONG).show();
	}
	public static void toastShort(Context con,String text){
		Toast.makeText(con,text,Toast.LENGTH_SHORT).show();
	}
	public static void toastLong(Context con,String text){
		Toast.makeText(con,text,Toast.LENGTH_LONG).show();
	}
	public List<Locale> getAvailableLanguages(){
	   
		return new ArrayList<Locale>(talk.getAvailableLanguages());
	}
	public void speak(String text){
		talk.speak(text,TextToSpeech.QUEUE_FLUSH,null);
	}
}
