package com.mmar;
import java.io.File;
import android.graphics.Bitmap;
public class mproject
{//this will contains all project info
    public File mprojectdir;
	public File manifest,src,res,layout,drawable,values,pdir,mlibdir;
	public static File projectHome=new File("sdcard/appprojects");
	public static boolean isProject(File fpro){
		return new File(fpro+"/AndroidManifest.xml").exists();
	}
	public mproject(File file){
		mprojectdir=file;
	    update();
	}
	public void update(){
		manifest=new File(mprojectdir+"/androidmanifest.xml");
		src=new File(mprojectdir+"/src");
		res=new File(mprojectdir+"/res");
		layout=new File(res+"/layout");
		drawable=new File(res+"/drawable");
		values=new File(res+"/values");
		mlibdir=new File(src+"/com/mmar");
		pdir=new File(src+"/"+getPackage().replace(".","/"));
		
	}
	public String getPackage(){
		String smanifest=fm.load(manifest);
		String[] kkn=smanifest.split("package=\"");
	    kkn=kkn[1].split("\"");
		smanifest=kkn[0];
		return smanifest;
	}
	public boolean isIconAvailable(){
		File icon1=new File(drawable+"/icon.png");
		File icon2=new File(drawable+"/icon.jpg");
		if(icon1.exists()||icon2.exists()){
			return true;
		}
		return false;
	}
	public Bitmap getIcon(){
		File icon1=new File(drawable+"/icon.png");
		File icon2=new File(drawable+"/icon.jpg");
		if(icon1.exists()){
			return fm.loadBitmap(icon1);
		}
		if(icon2.exists()){
			return fm.loadBitmap(icon2);
		}
		return null;
	}
}