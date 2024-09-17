package com.mmar.graphics;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.graphics.BitmapFactory;
import java.io.File;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.FileOutputStream;
public class imagetool
{
	public static Bitmap resize(Bitmap b,int width,int height){
		return Bitmap.createScaledBitmap(b,width,height,false);
	}
	public static void saveBitmapJPG(Bitmap b,File f){
		try{FileOutputStream fout=new FileOutputStream(f);
		b.compress(Bitmap.CompressFormat.JPEG,100,fout);
		fout.flush();
		fout.close();
		}
		catch(Exception e){}
		
	}
	public static void saveBitmapPNG(Bitmap b,File f){
		try{FileOutputStream fout=new FileOutputStream(f);
			b.compress(Bitmap.CompressFormat.PNG,100,fout);
			fout.flush();
			fout.close();
		}
		catch(Exception e){}

	}
	public static Bitmap loadBitmapFromDrawable(Context con,int drawable){
		return BitmapFactory.decodeResource(con.getResources(),drawable);
	}
	public static Bitmap loadBitmapFromDrawable(Drawable d){
		return ((BitmapDrawable)d).getBitmap();
	}
	public static Drawable loadDrawable(Context con,int drawable){
		return con.getResources().getDrawable(drawable);
	}
	public static String loadBitmapAsString(Bitmap b){
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		b.compress(Bitmap.CompressFormat.JPEG,100,output);
		
		return Base64.encodeToString(output.toByteArray(),Base64.DEFAULT);
	}
	public static Bitmap loadBitmapfromString(String data){
		byte [] encodeByte=Base64.decode(data,Base64.DEFAULT);
		Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
		return bitmap;
	}
	public static Bitmap CreateBitmap(int width,int height){
		return Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
	}
	public static Bitmap getBitmap(Context con,Uri uri){
		Bitmap b;
		try{
			b=MediaStore.Images.Media.getBitmap(con.getContentResolver(),uri);
			
		}catch(Exception e){
			b=CreateBitmap(10,10);
		}
		return b;
	}
}
