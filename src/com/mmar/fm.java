package com.mmar;
import java.io.File;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import android.net.Uri;
import java.io.BufferedReader;
import android.content.ClipboardManager;
import android.content.ClipData;
import java.io.FileInputStream;
import android.content.Context;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.content.Intent;
import java.util.List;
import java.util.ArrayList;
import java.io.OutputStream;
import com.mmar.standout.ui.Window;

import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.content.res.AssetManager;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.MimeTypeMap;

import androidx.documentfile.provider.DocumentFile;

public class fm
{   public static String[] day={"Sun","Mon","Tue","Wed","Thus","Fri","Sat"};
    public static String[] month={"Jan","Feb","March","April","May","Jun","July","Aug","Sep","Oct","Nov","Dec"};
	public static String[] picFormats={"jpg","gif","png","bmp","jpeg"};
	public static String filedocWordtype="application/msword";
	//Specific file paths
	public static String syncedFileDatabasePath="sdcard/data/mmar/syncFileDB.db";

	// Loading info from URI
	public static String getFileName(Uri uri,Context context){
		DocumentFile documentFile = DocumentFile.fromSingleUri(context, uri);
		return documentFile != null ? documentFile.getName() : null;
	}
	public static String getPathFromUri(Context context, Uri uri) {
		String[] projection = {MediaStore.Images.Media.DATA};
		Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			String filePath = cursor.getString(columnIndex);
			cursor.close();
			return filePath;
		}
		return null;
	}
	public static String save(File file,String string){
		File pr=file.getParentFile();
		if(pr.exists()){}else{pr.mkdirs();}
		try{FileOutputStream fout=new FileOutputStream(file);
		OutputStreamWriter writer=new OutputStreamWriter(fout);
		writer.append(string);
		writer.close();}
		
	catch(Exception e){return e.getMessage();}
	return "done";}
	public static Intent openFile(File f){
		Intent i=new Intent();
		i.setAction(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.fromFile(f),getFileType(f).toLowerCase()+"/*");
		return i;
	}
	public static String save(File f){
		return fm.save(f,"");
	}
	public static Intent openFile(File f,String datatype){
		Intent i=new Intent();
		i.setAction(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.fromFile(f),datatype);

		return i;
	}public static Intent editFile(File f,String datatype){
		Intent i=new Intent();
		i.setAction(Intent.ACTION_EDIT);
		i.setDataAndType(Uri.fromFile(f),datatype);

		return i;
	}
	public static String load(File file){
		try{
			FileInputStream fin=new FileInputStream(file);
			BufferedReader reader=new BufferedReader(new InputStreamReader(fin));
			String adata="",buffer="";
			while((adata=reader.readLine())!=null){
				buffer+=adata+"\n";
			}
			reader.close();
			return buffer;
		}catch(Exception e){return e.getMessage();}
	}
	public static String getFileType(File file) {
        // Get the file extension
        String extension = getFileExtension(file);

        if (extension != null) {
            // Get the MIME type based on the file extension
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());

            if (mimeType != null) {
                if (mimeType.startsWith("image")) {
                    return "Image";
                } else if (mimeType.startsWith("audio")) {
                    return "Audio";
                } else if (mimeType.startsWith("video")) {
                    return "Video";
                } else if (mimeType.startsWith("application")) {
                    return "Document";
                }
            }
        }

        // If no type is detected, return "Other"
        return "Other";
    }
    public static String getFileType(String extension) {
        // Get the file extension

        if (extension != null) {
            // Get the MIME type based on the file extension
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());

            if (mimeType != null) {
                if (mimeType.startsWith("image")) {
                    return "Image";
                } else if (mimeType.startsWith("audio")) {
                    return "Audio";
                } else if (mimeType.startsWith("video")) {
                    return "Video";
                } else if (mimeType.startsWith("application")) {
                    return "Document";
                }
            }
        }

        // If no type is detected, return "Other"
        return "Other";
    }
    // Helper function to get the file extension
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot != -1 && lastDot != 0) {
            return fileName.substring(lastDot + 1);
        } else {
            return null;
        }
    }
	public static void copy(File src,File dst,int mod){
		if(mod==1){
			copy(src,new File(dst+"/"+src.getName()));
			}
	
	}
	public static void copy(File sourceLocation, File targetLocation)
    {

		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}

			String[] children = sourceLocation.list();
			for (int i = 0; i < sourceLocation.listFiles().length; i++) {

				copy(new File(sourceLocation, children[i]),
														  new File(targetLocation, children[i]));
			}
		} else {
             try{
			InputStream in = new FileInputStream(sourceLocation);

			OutputStream out = new FileOutputStream(targetLocation);

			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}in.close();
			out.close();
			}catch(Exception e){}
			
		}

	}
	public static void copytoClipboard(String text,Context con){
		ClipboardManager myclip=(ClipboardManager)con.getSystemService(con.CLIPBOARD_SERVICE);
		ClipData clip=ClipData.newPlainText("text",text);
		myclip.setPrimaryClip(clip);
	}
	public static String pasteFromClipboard(Context con){
		ClipboardManager myclip=(ClipboardManager)con.getSystemService(con.CLIPBOARD_SERVICE);
		ClipData clip=myclip.getPrimaryClip();
		ClipData.Item item=clip.getItemAt(0);
		return item.getText().toString();
	}
	public static void saveWindowProperties(File f,Window window){
		save(f,"X "+window.getX()+",Y "+window.getY()+",width "+window.getWidth()+",height "+window.getHeight());
	}
	public static Bitmap loadBitmap(File f){
		return BitmapFactory.decodeFile(f.getAbsolutePath());
	}
	public static void delete(File f){
		if(f.isDirectory()){
			for(File ff:f.listFiles()){
				delete(ff);
			}
		}
		f.delete();
	}
	public static boolean isMatch(String t1,String t2){
		char[] ts1=t1.toLowerCase().replace(" ","").toCharArray();
		char[] ts2=t2.toLowerCase().replace(" ","").toCharArray();
	    if(ts1.length==ts2.length){
			int i=0;
			while(i<ts1.length-1){
				if(ts1[i]!=ts2[i]){
					return false;
				}
				i=i+1;
			}
		}
		else{return false;}
		return true;
	}
	public static File getExternalStorage(){
		File f=new File("/storage");
		for(File ff:f.listFiles()){
			if(isMatch(ff.getName(),"self")||isMatch(ff.getName(),"emulated")){
				
			}else{
				return ff;
			}
		}
		return new File("/storages/mines");
	}
	public static String format(String filename){

		String[] tt=filename.split("\\.");
		return tt[tt.length-1];
	}
	public static String format(File file){
		return format(file.getName());
	}
	public static boolean isMatch(String s1,String[] s2){
		for(String ss:s2){
			if(isMatch(ss,s1)){return true;}
		}
		return false;
	}
	public static boolean iscontainsAll(String s1,String[] s2){
		s1=s1.toLowerCase();
		for(String s:s2){
			if(!s1.contains(s.toLowerCase())){
				return false;
			}
		}
		return true;
	}
	public static boolean mkdirs(File dir){
		if(dir.exists()){
			return true;
		}else{
			dir.mkdirs();
			return false;
		}
	}
	public static boolean mkdirs(String dir){
		return mkdirs(new File(dir));
	}
	public static String[] getAssetList(Context con){
		AssetManager aman=con.getAssets();
		try{return aman.list("");}catch(Exception e){return null;}
	}
	public static void copyAsset(Context con,File target,String src){
		AssetManager aman=con.getAssets();
		try{
			InputStream in=aman.open(src);
			OutputStream out=new FileOutputStream(target);
			byte[] buffer=new byte[1024];
			int len;
			while ((len=in.read(buffer))>0) {
				out.write(buffer, 0, len);
			}in.close();
			out.close();
		}catch(Exception e){}
	}
	public static Intent getVoiceRecognization(Context con,String prompt){
		Intent in=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		in.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,con.getClass().getPackage().getName());
		in.putExtra(RecognizerIntent.EXTRA_PROMPT,prompt);
		in.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		in.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
		return in;
	}
	public static String getVoiceResult(Intent data){
		return data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
	}
	public static File file(String s){
		return new File(s);
	}
	public static void prepareWeview(WebView web){
		WebSettings websetting=web.getSettings();
		websetting.setJavaScriptEnabled(true);
		websetting.setBuiltInZoomControls(true);
		websetting.setDisplayZoomControls(false);
	}
}
