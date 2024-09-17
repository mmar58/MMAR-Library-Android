package com.mmar.text;
import java.util.List;
import java.util.ArrayList;
import com.mmar.oclick;
import android.graphics.Color;
import android.text.style.ClickableSpan;
import android.view.View;
import android.text.SpannableString;

public class spantext
{ 
	public List<spanElement> spanList=new ArrayList<spanElement>();
	public static int ResizeText=1,BoldText=2,UnderlineText=3,
	ItalicText=4,StrikeThroughText=5,ForegroundColorText=6,BackgroundColorText=7,
	SuperscriotText=8,SubscriptText=9,UrlText=10,ClickableText=11,SpecialCommand=12;
	//to make text clickable
	//textView.setMovementMethod(LinkMovementMethod.getInstance());
	public spantext()
	{

	}
	public void addtext(String text)
	{
		spanList.add(new spanElement(text));
	}

	public void addResizetext(String text, float size)
	{
		spanList.add(new spanElement(text, ResizeText, size));
	}
	public void addBoldText(String text)
	{
		spanList.add(new spanElement(text, BoldText));
	}
	public void addUnderlineText(String text)
	{
		spanList.add(new spanElement(text, UnderlineText));
	}
	public void addItalicText(String text)
	{
		spanList.add(new spanElement(text, ItalicText));
	}
	public void addStrikeThroughText(String text)
	{
		spanList.add(new spanElement(text, StrikeThroughText));
	}
	public void addForgroundColorText(String text, int color)
	{
		spanList.add(new spanElement(text, ForegroundColorText, color));
	}
	public void addBackgroundColorText(String text, int color)
	{
		spanList.add(new spanElement(text, BackgroundColorText, color));
	}
	public void addSuperscrptText(String text)
	{
		spanList.add(new spanElement(text, SuperscriotText));
	}
	public void addSubscriptText(String text)
	{
		spanList.add(new spanElement(text, SubscriptText));
	}
	public void addUrlText(String text, String url)
	{
		spanList.add(new spanElement(text, UrlText, url));
	}
	public void addClickText(String text, oclick click)
	{
		spanList.add(new spanElement(text, ClickableText, click));
	}
	public void addSpecialCommand(int start, int end, int command, float size, int color, oclick click, String url)
	{
		spanList.add(new spanElement(start, end, command, size, color, click, url));
	}
    public SpannableString getSpantext()
	{
		String mtext="";
		for (spanElement curspan:spanList)
		{
			if (curspan.command != SpecialCommand)
			{
				curspan.start = mtext.length();
				mtext += curspan.text;
				curspan.end = mtext.length();
			}
		}
		tcolor tspan=new tcolor(mtext);
		for (spanElement curspan:spanList)
		{
			switch (curspan.command)
			{
				case 1:
					tspan.resizeText(curspan.size, curspan.start, curspan.end);
					break;
				case 2:
					tspan.boldText(curspan.start, curspan.end);
					break;
				case 3:
					tspan.underlineText(curspan.start, curspan.end);
					break;
				case 4:
					tspan.italicText(curspan.start, curspan.end);
					break;
				case 5:
					tspan.strikethroughText(curspan.start, curspan.end);
					break;
				case 6:
					tspan.foregroudColorText(curspan.color, curspan.start, curspan.end);
					break;
				case 7:
					tspan.backgroundColorText(curspan.color, curspan.start, curspan.end);
					break;
				case 8:
					tspan.superscriptText(curspan.start, curspan.end);
					break;
				case 9:
					tspan.subscriptText(curspan.start, curspan.end);
					break;
				case 10:
					tspan.urlText(curspan.url, curspan.start, curspan.end);
					break;
				case 11:
					final oclick mclick=curspan.click;
					ClickableSpan clickableSpan = new ClickableSpan() {

						@Override
						public void onClick(View widget)
						{
							mclick.run();
						}
					};

					tspan.clicText(clickableSpan, curspan.start, curspan.end);
					break;
				case 12:
					switch (curspan.extracommand)
					{
						case 1:
							tspan.resizeText(curspan.size, curspan.start, curspan.end);
							break;
						case 2:
							tspan.boldText(curspan.start, curspan.end);
							break;
						case 3:
							tspan.underlineText(curspan.start, curspan.end);
							break;
						case 4:
							tspan.italicText(curspan.start, curspan.end);
							break;
						case 5:
							tspan.strikethroughText(curspan.start, curspan.end);
							break;
						case 6:
							tspan.foregroudColorText(curspan.color, curspan.start, curspan.end);
							break;
						case 7:
							tspan.backgroundColorText(curspan.color, curspan.start, curspan.end);
							break;
						case 8:
							tspan.superscriptText(curspan.start, curspan.end);
							break;
						case 9:
							tspan.subscriptText(curspan.start, curspan.end);
							break;
						case 10:
							tspan.urlText(curspan.url, curspan.start, curspan.end);
							break;
						case 11:
							final oclick mclick2=curspan.click;
							ClickableSpan clickableSpan2 = new ClickableSpan() {

								@Override
								public void onClick(View widget)
								{
									mclick2.run();
								}
							};

							tspan.clicText(clickableSpan2, curspan.start, curspan.end);
							break;}
							break;
			}
		}
		return tspan.toSpan();
	}
	public void clearSpan()
	{
		spanList.clear();
	}
	public class spanElement
	{
		public String text="";
		public int command=0;
		public oclick click=null;
		public int start=0,end=0;
		int extracommand=0;
		public String url="";
		public int color=Color.BLACK;
		float size=0f;
		public spanElement(String text)
		{
			this.text = text;
		}
		public spanElement(String text, int command, float size)
		{
			this.text = text;
			this.command = command;
			this.size = size;
		}
		public spanElement(String text, int command)
		{
			this.text = text;
			this.command = command;
		}
		public spanElement(String text, int command, int color)
		{
			this.text = text;
			this.command = command;
			this.color = color;
		}
		public spanElement(String text, int command, String url)
		{
			this.text = text;
			this.command = command;
			this.url = url;
		}
		public spanElement(String text, int command, oclick click)
		{
			this.text = text;
			this.command = command;
			this.click = click;
		}
		public spanElement(int start, int end, int command, float size, int color, oclick click, String url)
		{
			this.start = start;
			this.end = end;
			this.command = SpecialCommand;
			this.extracommand = command;
			this.size = size;
			this.color = color;
			this.click = click;
			this.url = url;
		}
	}
}
