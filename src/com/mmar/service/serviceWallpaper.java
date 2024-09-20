package com.mmar.service;
import android.app.Activity;
import android.content.ServiceConnection;
import android.content.Intent;
import android.content.ComponentName;
import android.os.Handler;
import com.mmar.tts;
import android.os.IBinder;
import java.io.File;
import android.os.Messenger;
import com.mmar.util.dateutil;
import com.mmar.fm;
import android.os.Message;
import android.content.Context;
import com.mmar.mActivity;
import android.service.wallpaper.WallpaperService;
import android.service.wallpaper.WallpaperService.*;

public class serviceWallpaper extends WallpaperService
{
	public final static int RegisterClient=1,UnregisterClient=2,msgIntValue=3,msgStringValue=4,
	msgSpString=101,addString=5,removeString=6;
	public final static String StringCode="text";
	Messenger mservice=null;
	final Messenger msnger=new Messenger(new IncomingHandler());
    public tts talk;
	@Override
	public WallpaperService.Engine onCreateEngine()
	{
		// TODO: Implement this method
		return null;
	}

	
	class IncomingHandler extends Handler
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
            onReceivedMsg(msg);
			switch(msg.what){
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
	
	public void onReceivedMsg(Message msg){

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
	ServiceConnection mconnect=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName p1, IBinder p2)
		{
			// TODO: Implement this method
			mservice=new Messenger(p2);
			try{
				Message msg=Message.obtain(null,myService.RegisterClient);
				msg.replyTo=msnger;
				mservice.send(msg);
			}catch(Exception e){
				Debug(e.toString());
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName p1)
		{
			// TODO: Implement this method
			mservice=null;
		}



	};
	public void sendMsgToService(String msg){
		sendMsgToService(myService.getStringMessage(msg,myService.msgStringValue));
	}
	public void sendMsgToService(Message msg){
		try{
			mservice.send(msg);
		}
		catch(Exception ex){
			Debug(ex.toString());
		}
	}
	public void bindService(Class cls){
		bindService(new Intent(this,cls),mconnect,BIND_AUTO_CREATE);
	}
	public void Debug(String message) {
        this.talk.toastLong(message);
        File file = new File("sdcard/error.txt");
		String fullMessage=dateutil.print("h:mm:ss a, dd/mm/yyyy")+message;
		if(file.exists()){
			fm.save(file, fm.load(file)+"\n"+fullMessage);
		}
		else{
			fm.save(file,fullMessage);
		}

    }
}
