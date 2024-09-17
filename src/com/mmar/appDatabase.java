package com.mmar;
import android.content.Context;
import java.io.File;
import android.graphics.Bitmap;
import java.util.List;
import android.database.Cursor;
import java.util.ArrayList;
import com.mmar.graphics.imagetool;
public class appDatabase
{   public sqlite mainDatabase;
	public Context con;
	public static int type_unknown=0;
	public File databaseFile=new File("sdcard/data/mmar/app.db");
	public appDatabase(Context con){
		this.con=con;
		databaseFile.getParentFile().mkdirs();
		mainDatabase=new sqlite(con,databaseFile);
		mainDatabase.createTable("apps(package text,label text,icon text,uid text)");
	}
	public void addApp(String pack,String name,Bitmap icon,int uid){
		mainDatabase.addData("apps","'"+pack+"','"+name+"','"+imagetool.loadBitmapAsString(icon)+"','"+uid+"'");
	}
	
	public void addApp(String pack,String name,Bitmap icon){
		mainDatabase.addData("apps","'"+pack+"','"+name+"','"+imagetool.loadBitmapAsString(icon)+"','"+0+"'");
	}
	
	public void clearapps(){
		mainDatabase.deleteAlldata("apps");
	}
	public void replaceAppName(String pack,String name){
		mainDatabase.updateData("apps","package='"+pack+"'","label='"+name+"'");
	}
	public void replaceAppIcon(String pack,Bitmap icon){
		mainDatabase.updateData("apps","package='"+pack+"'","icon='"+imagetool.loadBitmapAsString(icon)+"'");
	}
	public Cursor findApp(String pack){
		return mainDatabase.findData("apps","package='"+pack+"'");
	}
	public Cursor findAllApp(){
		return mainDatabase.getallData("apps");
	}
}
