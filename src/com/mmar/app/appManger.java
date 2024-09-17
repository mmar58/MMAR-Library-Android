package com.mmar.app;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;
import android.net.TrafficStats;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
public class appManger
{Context con;
PackageManager manger;
	public appManger(Context con){
		this.con=con;
		manger=con.getPackageManager();
		}
	public List<ResolveInfo> loadApps(){
		Intent i=new Intent(Intent.ACTION_MAIN,null);
		i.addCategory(Intent.CATEGORY_LAUNCHER);
		return manger.queryIntentActivities(i,0);
	}
	public String getAppPackage(ResolveInfo ri){
		return ri.activityInfo.packageName;
	}
	public String getAppName(ResolveInfo ri){
		return ri.loadLabel(manger).toString();
	}
	public Drawable getAppIcon(ResolveInfo ri){
		return ri.activityInfo.loadIcon(manger);
	}
	public void startApp(String pack){
		Intent in=manger.getLaunchIntentForPackage(pack);
		con.startActivity(in);
	}
	public Intent startAppIntent(String pack){
		return manger.getLaunchIntentForPackage(pack);
	}
	public static void startApp(Context con,String pack){
		Intent in=con.getPackageManager().getLaunchIntentForPackage(pack);
		con.startActivity(in);
	}
	public static Intent startAppIntent(Context con,String pack){
		return con.getPackageManager().getLaunchIntentForPackage(pack);
	}
	public int getAppUid(String pack){
		try{
		ApplicationInfo ain=manger.getApplicationInfo(pack,0);
		return ain.uid;}catch(Exception e){
			
		}
		return 0;
	}
	public long getReceivedNetByte(int uid){
		return TrafficStats.getUidRxBytes(uid);
	}
	public long getSentNetByte(int uid){
		return TrafficStats.getUidTxBytes(uid);
	}
	public long getToalSentNet(){
		return TrafficStats.getMobileTxBytes();
	}
	public long getTotalReceivedNet(){
		return TrafficStats.getMobileRxBytes();
	}
}
