package com.mmar.service;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.mmar.fm;
import java.io.File;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.mmar.tts;
public class myService extends Service
{
	Timer timer=new Timer();
public long updateTime=1;
File servicelog=new File("sdcard/Servicelog.txt");
public static boolean isRunning=false;
ArrayList<Messenger> mclients=new ArrayList<Messenger>();
public final static int RegisterClient=1,UnregisterClient=2,msgIntValue=3,msgStringValue=4,
msgSpString=101,addString=5,removeString=6;
public final static String StringCode="text";
	final Messenger mMessenger=new Messenger(new IncomingHandler());

	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return mMessenger.getBinder();
	}
	class IncomingHandler extends Handler
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			switch(msg.what){
				case RegisterClient:
					mclients.add(msg.replyTo);
					break;
				case UnregisterClient:
					mclients.remove(msg.replyTo);
					break;
				case msgIntValue:
					onreceivedInt(msg.arg1);
					break;
				case msgStringValue:
					onreceivedString(getStringData(msg));
					break;
				case msgSpString:
					onreceiveSpString(getStringData(msg));
					break;
				case addString:
					onaddString(getStringData(msg));break;
				case removeString:
					onremoveString(getStringData(msg));break;
				default:
				    super.handleMessage(msg);
			}
		}
	
}
	public void onreceivedInt(int value){

	}
	public void onaddString(String value){}
	public void onremoveString(String value){}
	public void onreceiveSpString(String value){}
	public void onreceivedString(String value){

	}
	public static String getStringData(Message msg){
		return msg.getData().getString(StringCode);
	}
	public static int getIntData(Message msg){
		
		return msg.arg1;
	}
	public static Message getIntMessage(int value,int type){
		return Message.obtain(null,type,value,0);
	}
	public static Message getStringMessage(String value,int type){
		Message msg=Message.obtain(null,type);
		Bundle b=new Bundle();
		b.putString(StringCode,value);
		msg.setData(b);
		return msg;
	}
	public void setupdateTime(long value){
		updateTime=value;
	}
public void sendMsgToUi(int value){
	for(int i=mclients.size()-1;i>=0;i--){
		try{
			mclients.get(i).send(getIntMessage(value,msgIntValue));
		}catch(Exception e){
			mclients.remove(i);
		}
	}
}
public void sendMsgSpStringToUi(String value){
	for(int i=mclients.size()-1;i>=0;i--){
		try{
			mclients.get(i).send(getStringMessage(value,msgSpString));}
		catch(Exception e){
			mclients.remove(i);
		}
	}
}
public void sendMsgToUi(String value){
	for(int i=mclients.size()-1;i>=0;i--){
		try{
		mclients.get(i).send(getStringMessage(value,msgStringValue));}
		catch(Exception e){
			mclients.remove(i);
		}
	}
}

@Override
public void onCreate()
{
	// TODO: Implement this method
	super.onCreate();
	fm.save(servicelog,"");
	timer.scheduleAtFixedRate(new TimerTask(){public void run(){
	
				try{onUpdate();}
				catch(Exception ex){
					sendMsgToUi(ex.toString());
				}
	
	}},0,updateTime);isRunning=true;}
public void onUpdate(){
	
}

@Override
public int onStartCommand(Intent intent, int flags, int startId)
{
	// TODO: Implement this method
	return START_STICKY;
}
public static boolean isRunning(){
	return isRunning;
}

@Override
public void onDestroy()
{
	// TODO: Implement this method
	super.onDestroy();
	if(timer!=null){timer.cancel();}
	isRunning=false;
}
public void bugreport(String text){
	tts.toastLong(this,text);
	fm.save(servicelog,fm.load(servicelog)+text);
}

}
