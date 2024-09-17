
package com.mmar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mmar.fm;
import com.mmar.tts;
import com.mmar.util.dateutil;
import java.io.File;

public class mActivity
extends Activity {
    public tts talk;
    public static int request_String=133;
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
    public void toastShort(String text){
		tts.toastShort(this,text);
	}
	public void toastLong(String text){
		tts.toastLong(this,text);
	}
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.talk = new tts(this);
    }
}


