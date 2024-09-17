package com.mmar;
import android.content.Context;
import java.io.File;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class sqlite
{public SQLiteDatabase db;
	Context con;
	public sqlite(Context con,File f)
	{
		File parentFile=f.getParentFile();
        if(!parentFile.exists()){
			fm.mkdirs(parentFile);
		}
		db = con.openOrCreateDatabase(f.getAbsolutePath(), con.MODE_WORLD_READABLE, null);
		this.con = con;
	}

	public sqlite(Context con,String filepath)
	{
		File parentFile=new File(filepath).getParentFile();
        if(!parentFile.exists()){
			fm.mkdirs(parentFile);
		}
		db = con.openOrCreateDatabase(new File(filepath).getAbsolutePath(), con.MODE_WORLD_READABLE, null);
		this.con = con;
	}
	//student(rollno varchar,name varchar,marks varchar);
	public void createTable(String tablecode)
	{
		db.execSQL("create table if not exists " + tablecode + ";");
	}
	//'19','salma','40'
	public void exeSQL(String sqlcode){
		db.execSQL(sqlcode);
	}
	public Cursor raqQuery(String sqlcode){
		return db.rawQuery(sqlcode,null);
	}
	public void addData(String table, String data)
	{
		db.execSQL("insert into " + table + " values(" + data + ");");

	}
	//rollno='19,marks='40
	public void deleteData(String table, String data)
	{
		db.execSQL("delete from " + table + " where " + data);
	}
	public void deleteTable(String table)
	{
		db.execSQL("drop table if exists " + table);
	}
	public void deleteAlldata(String table)
	{
		db.execSQL("delete from " + table);
	}
	public void updateData(String table, String olddata, String ldata)
	{
		db.execSQL("update " + table + " set " + ldata + " where " + olddata);
	}
	//rollno='20'
	//rollno in ('12','20')
	public Cursor findData(String table, String data)
	{
		return db.rawQuery("select * from " + table + " where " + data, null);
	}
	// fdata name,marks
	// mdata rollno='20'
	public Cursor findData(String table, String fdata, String mdata)
	{
		return db.rawQuery("select " + fdata + " from " + table + " where " + mdata, null);
	}
	public Cursor getallData(String table)
	{
		return db.rawQuery("select * from " + table, null);
	}
	public Cursor getallData(String rdata, String table)
	{
		return db.rawQuery("select " + rdata + " from " + table, null);
	}
	public Cursor getAllTables()
	{
		return db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata' order by name", null);
	}
	public String[] getColumns(String table)
	{
		return db.rawQuery("SELECT * FROM " + table + " WHERE 0", null).getColumnNames();
	}
	//mydb.copyTable("backup","path,lastmodified,size","temp(path,lastmodified,size)")
    public void copyTable(String oldtable,String columns, String newtable){
		exeSQL("INSERT INTO "+newtable+" SELECT "+columns+" FROM "+oldtable);
	}
}
