package com.mmar;
import android.content.Context;
import android.content.Intent;
import com.mmar.graphics.imagetool;
import android.view.Display;
import android.app.WallpaperManager;
import android.view.WindowManager;
import java.io.File;
import android.content.ComponentName;
import java.util.List;
import java.util.Iterator;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.app.AlarmManager;
import android.graphics.drawable.Drawable;
import java.lang.Class;
import com.mmar.fm;
import com.mmar.app.appManger;
import android.app.ActivityManager;
import android.widget.RemoteViews;
import android.app.NotificationManager;
import android.media.AudioManager;
import android.app.Notification;
public class MService
{Context con;
NotificationManager nm;
AudioManager am;
WallpaperManager wm;
AlarmManager alm;
public int ringerModeSilent,ringerModeVibrate,ringerModeRing;
	public MService(Context con){
		this.con=con;
		nm=(NotificationManager)con.getSystemService(con.NOTIFICATION_SERVICE);
		alm=(AlarmManager)con.getSystemService(con.ALARM_SERVICE);
		wm=WallpaperManager.getInstance(con);
		am=(AudioManager)con.getSystemService(con.AUDIO_SERVICE);
		ringerModeSilent=am.RINGER_MODE_SILENT;
		ringerModeVibrate=am.RINGER_MODE_VIBRATE;
		ringerModeRing=am.RINGER_MODE_NORMAL;
	}
	public PendingIntent getOpenFile(File f,int code){
		return PendingIntent.getActivity(con,code,fm.openFile(f),0);
	}
	public PendingIntent getActivity(Class<?> cls,int code){
		return PendingIntent.getActivity(con,code,new Intent(con,cls),0);
	}
	public PendingIntent getService(Class<?> cls,int code){
		return PendingIntent.getService(con,code,new Intent(con,cls),0);
	}
	public PendingIntent openApp(String pack,int code){
		return PendingIntent.getActivity(con,code,appManger.startAppIntent(con,pack),0);
	}
    public PendingIntent getBroadcast(Class<?> cls,int code){
		return PendingIntent.getBroadcast(con,code,new Intent(con,cls),0);
	}
	public Notification notification(String title,String text){
		Notification n=new Notification.Builder(con).setSmallIcon(R.drawable.close)
			.setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(text).build();
		return n;
	}
	public Notification notification(int icon,String title,String text){
		Notification n=new Notification.Builder(con).setSmallIcon(icon)
			.setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(text).build();
		return n;
	}
	public Notification notification(int icon,String title,String text,notificationItem ni){
		Notification n=new Notification.Builder(con).setSmallIcon(icon).setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(text).addAction(ni.drawable,ni.text,ni.pi).build();
		return n;
	}
	
	public Notification notification(int icon,String title,String text,notificationItem ni1,notificationItem ni2){
		Notification n=new Notification.Builder(con).setSmallIcon(icon).setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(text).addAction(ni1.drawable,ni1.text,ni1.pi).addAction(ni2.drawable,ni2.text,ni2.pi).build();
		return n;
	}
	public Notification notification(int icon,String title,String text,notificationItem ni1,notificationItem ni2,notificationItem ni3){
		Notification n=new Notification.Builder(con).setSmallIcon(icon).setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(text).addAction(ni1.drawable,ni1.text,ni1.pi).addAction(ni2.drawable,ni2.text,ni2.pi).addAction(ni3.drawable,ni3.text,ni3.pi).build();
		return n;
	}
	public Notification notification(int icon,String title,String text,notificationItem ni1,notificationItem ni2,notificationItem ni3,notificationItem ni4){
		Notification n=new Notification.Builder(con).setSmallIcon(icon).setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(text).addAction(ni1.drawable,ni1.text,ni1.pi).addAction(ni2.drawable,ni2.text,ni2.pi).addAction(ni3.drawable,ni3.text,ni3.pi).addAction(ni4.drawable,ni4.text,ni4.pi).build();
		return n;
	}
	public static notificationItem getNiItem(int icon,String text,PendingIntent pi){
		return new notificationItem(icon,text,pi);
	}
	public void notice(int code,Notification n){
		nm.notify(code,n);
	}
	public void cancelNotice(int code){
		nm.cancel(code);
	}
	public void cancelAllNotice(){
		nm.cancelAll();
	}
	public void setWallpaper(Bitmap b){
		try{
		wm.setBitmap(imagetool.resize(b,2*720,1280));}catch(Exception e){}
	}
	public void setWallpaper(File f){
		setWallpaper(fm.loadBitmap(f));
	}
	public Drawable getWallpaper(){
		return wm.getDrawable();
	}
	public static Drawable getWallpaper(Context con){
		WallpaperManager wm=WallpaperManager.getInstance(con);
		return wm.getDrawable();
	}
	public void setWallpaperBitmap(Bitmap b){
		try{
			wm.setBitmap(b);}catch(Exception e){}
	}
	public void setWallpaper(String f){
		setWallpaper(new File(f));
	}
	public static void SetLiveWallpaper(Context context,java.lang.Class<?> liveClass){
		Intent intent = new Intent(
			WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
						new ComponentName(context, liveClass));

        context.startActivity(intent);
	}
	public void SetLiveWallpaper(java.lang.Class<?> liveClass){
		Intent intent = new Intent(
			WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
						new ComponentName(con, liveClass));

        con.startActivity(intent);
	}
	public Intent getIntent(Class<?> cls){
		Intent i=new Intent(con,cls);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return i;
	}
	public RemoteViews getlayout(int layout){
		return new RemoteViews(con.getPackageName(),layout);
	}
	public void addAlarm(PendingIntent pi,long time){
		alm.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time,pi);
	}
	public void addRepeatingAlarm(PendingIntent pi,long time){
		alm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),time,pi);
	}
	public static String getForgroundAppPackage(Context con){
		
		ActivityManager am=(ActivityManager)con.getSystemService(con.ACTIVITY_SERVICE);
		return am.getRunningTasks(1).get(0).topActivity.getPackageName();
	}
	public String getForgroundAppPackage(){
		ActivityManager am=(ActivityManager)con.getSystemService(con.ACTIVITY_SERVICE);
		return am.getRunningTasks(1).get(0).topActivity.getPackageName();
	}
	public void deleteAlarm(PendingIntent pi){
		alm.cancel(pi);
	}
	public static class notificationItem{
		public String text;
		public int drawable;
		public PendingIntent pi;
		public notificationItem(int drawable,String text,PendingIntent pi){
			this.text=text;
			this.drawable=drawable;
			this.pi=pi;
		}
	}
	public int getRingerMode(){
		return am.getRingerMode();
	}
	public void setRingerModeSilent(){
		am.setRingerMode(ringerModeSilent);
	}
	public void setRingerModeVibrate(){
		am.setRingerMode(ringerModeVibrate);
	}
	public void setRingerModeRing(){
		am.setRingerMode(ringerModeRing);
	}
	public void setRingerMode(int mode){
		am.setRingerMode(mode);
	}
}
