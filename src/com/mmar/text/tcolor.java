package com.mmar.text;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.text.style.*;
public class tcolor
{public String text="";
public SpannableString sString= new SpannableString(text);
	public static String gettext(String text,String Color){
		String s="<font color="+Color+">"+text+"</font>";
		return s;
	}
	public tcolor(){
		
	}
	public tcolor(String text){
		this.text=text;
		resetSpan();
	}
	public void resetSpan(){
		sString= new SpannableString(text);
	}
	public void setText(String text){
		this.text=text;
		resetSpan();
	}
	// resizeText(2f,0,5)
	public void resizeText(float sizetf,int start,int off){
		sString.setSpan(new RelativeSizeSpan(sizetf),start,off,0);
	}
	public void boldText(int start,int off){
		sString.setSpan(new StyleSpan(Typeface.BOLD),start,off,0);
	}
	public void underlineText(int start,int off){
		sString.setSpan(new UnderlineSpan(),start,off,0);
	}
	public void italicText(int start,int off){
		sString.setSpan(new StyleSpan(Typeface.ITALIC),start,off,0);
	}
	public void strikethroughText(int start,int off){
		sString.setSpan(new StrikethroughSpan(),start,off,0);
	}
	public void foregroudColorText(int color,int start,int off){
		sString.setSpan(new ForegroundColorSpan(color),start,off,0);
	}
	public void backgroundColorText(int color,int start,int off){
		sString.setSpan(new BackgroundColorSpan(color),start,off,0);
	}
	public void superscriptText(int start,int off){
		sString.setSpan(new SuperscriptSpan(),start,off,0);
	}
	public void subscriptText(int start,int off){
		sString.setSpan(new SubscriptSpan(),start,off,0);
	}
	//www.google.com
	public void urlText(String url,int start,int off){
		sString.setSpan(new URLSpan("http://"+url),start,off,0);
	}
    // clickable text
	//ClickableSpan clickableSpan = new ClickableSpan() {

	//@Override
	//public void onClick(View widget) {
	// We display a Toast. You could do anything you want here.
	//Toast.makeText(SpanExample.this, "Clicked", Toast.LENGTH_SHORT).show();

		//}
	//};
	public void clicText(ClickableSpan click,int start ,int off){
		sString.setSpan(click,start,off,0);
	}
	// for url and click do this
	//textView.setMovementMethod(LinkMovementMethod.getInstance());

@Override
public String toString()
{
 // TODO: Implement this method
return text;
}
public SpannableString toSpan(){
	return sString;
}
}
