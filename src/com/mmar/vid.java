package com.mmar;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;
public class vid
{public void onDatePick(int year,int month,int day){
	
}
	public OnDateSetListener listener=new OnDateSetListener(){

		@Override
		public void onDateSet(DatePicker p1, int p2, int p3, int p4)
		{
			onDatePick(p2,p3,p4);
		}

	
};
}
