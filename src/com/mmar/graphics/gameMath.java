package com.mmar.graphics;
import java.lang.Math;
public class gameMath
{public float x1=0,x2=0,y1=0,y2=0;
	public gameMath(){
		
	}
	public void setArea(float x1,float y1,float x2,float y2){
		if(x1<x2){
			this.x1=x1;
			this.x2=x2;
		}else{
			this.x2=x1;
			this.x1=x2;
		}
		if(y1<y2){
			this.y1=y1;
			this.y2=y2;
		}else{
			this.y2=y1;
			this.y1=y2;
		}
	}
	public boolean isIn(float x,float y){
		if(x1<=x&&x<=x2&&y1<=y&&y<=y2){
			return true;
		}
		return false;
	}
	public static int[] divide(int ma,int divisor){
		int m1=ma/divisor;
		int m2=ma-m1*divisor;
		int[] mm={m1,m2};
		return mm;
	}
	public static long[] divide(long ma,long divisor){
		long m1=ma/divisor;
		long m2=ma-m1*divisor;
		long[] mm={m1,m2};
		return mm;
	}
	public static float[] divide(float ma,float divisor){
		float m1=ma/divisor;
		float m2=ma-m1*divisor;
		float[] mm={m1,m2};
		return mm;
	}
	public static int getDistance(int x1,int y1,int x2,int y2){
		return (int)Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
	}
	public static float GetRandom(float min, float max){
		float randomValue=min+(float)Math.random()*(max-min);
		if(randomValue<min) return min;
		return randomValue;
	}
	public static int GetRandom(int min, int max){
		int randomValue=min+(int)Math.round(Math.random()*(max-min));
		return randomValue;
	}
}
