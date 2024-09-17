package com.mmar;
import android.database.Cursor;
import android.content.Context;
public class ai
{Context con;
public sqlite mainDb;
public String question_type_location,question_type_person,question_type_time,question_type_truefalse,question_type_messure;
public String qtable="questions";
	public ai(Context con){
		this.con=con;
		mainDb=new sqlite(con,"sdcard/data/mmar/ai.db");
		mainDb.createTable("questions(id text,type text,question text,answer text,void text)");
		question_type_location="'location'";
		question_type_person="'person'";
		question_type_time="'time'";
		question_type_messure="'messure'";
		question_type_truefalse="'truefalse'";
	}
    public boolean isQidavailable(int id){
		if(mainDb.findData(qtable,"id='"+id+"'").getCount()==0){
			return true;
		}
		return false;
	}
}
