package com.mmar.util;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.content.Context;
import com.mmar.vid;
import android.widget.Button;
import android.view.View;
import com.mmar.R;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import com.mmar.*;
public class dialogutil
{
	public static Dialog dialog(Context con,int layout,String title){
		Dialog d=new Dialog(con);
		d.setContentView(layout);
		d.setTitle(title);
		return d;
	}
	public static Dialog dialog(Context con,int layout){
		Dialog d=new Dialog(con);
		d.setContentView(layout);
		return d;
	}
	public static DatePickerDialog dialog(Context con,vid onclick,int year,int month,int day){
		DatePickerDialog d=new DatePickerDialog(con,onclick.listener,year,month,day);
		return d;
	}
	public static Dialog yndialog(Context con,String title){
		final Dialog d=dialog(con,R.layout.yntextdialog,title);
		/*Button yyes=d.findViewById(R.id.ynyesButton);
		EditText ynedit=d.findViewById(R.id.ynedit);
		yyes.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onyes.run();
				}
			});
		ynedit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					boolean handled = false;
					if (actionId == EditorInfo.IME_ACTION_SEND) {
						onyes.run();
						handled = true;
					}
					return handled;
				}
			});
			*/
		Button yno=d.findViewById(R.id.ynnoButton);
		yno.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					d.cancel();
				}
			});
		return d;
	}
}
