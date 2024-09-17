package com.mmar;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.io.File;
public class sort
{
	
	public static void sortStringBysizeBtoS(List<String> slist){
		Collections.sort(slist,new Comparator<String>(){
			public int compare(String s1,String s2){
				int i1=s1.length();int i2=s2.length();
				if(i1>i2){
					return -1;
				}
				if(i2>i1){
					return 1;
				}
				return 0;
			}
		});
		
	}
	public static void sortFileByLetter(List<File> list){
		Collections.sort(list,new Comparator<File>(){
			public int compare(File s1,File s2){
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
	}
	
}
