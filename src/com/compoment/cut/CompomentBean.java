package com.compoment.cut;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompomentBean implements Serializable {

	/**
	 * 颜色 #FFFFFF
	 * */
	public String rgb16;
	public String rgb16ios;
	/**
	 * 背景颜色 #FFFFFF
	 * */
	public String bgRgb16;
	public String bgRgb16ios;
	public float r;
	public float g;
	public float b;
	
	
	
	public int x;
	public int y;
	public int w;
	public int h;
	
	public String cnname;
	public String enname;
	public String textSize;
	public boolean isImgCache = false;
	public boolean isPublicCompoment = false;
	public boolean isFilletedCorner=false;//是否是圆角
	public String id;// for iPhone id=jyV-Pf-zRb

	/**
	 * 控件类型 Button List TextView LinearLayout
	 * */
	public String type;
	/**
	 * LinearLayout 水平 或垂直 android:orientation="vertical" horizontal
	 * */
	public String orientation = "";
	public String gravity = "";
	/**
	 * 相对布局 使用 android:layout_alignParentLeft="true"
	 * android:layout_toLeftOf="@id/"
	 * */
	public String relative = "";
	public String picName;
	/**
	 * 控件加入的时间
	 * */
	public long time;
	public List<CompomentBean> chirlds;
	
	public CompomentBean() {
        id=genID(3)+"-"+genID(2)+"-"+genID(3);
	}
	
	public float getR(String rgb)
	{
	        if(!rgb.contains("#"))
	        {
	        	rgb="#ffffff";
	        }
			float first=Integer.parseInt(rgb.substring(1, 2),16);
			float second=Integer.parseInt(rgb.substring(2, 3),16);
			return (first*16+second)/255;
		
	}
	
	public float getG(String rgb)
	{
		  if(!rgb.contains("#"))
	        {
	        	rgb="#ffffff";
	        }
			float first=Integer.parseInt(rgb.substring(3, 4),16);
			float second=Integer.parseInt(rgb.substring(4, 5),16);
			return (first*16+second)/255;
		
	}
	
	public float getB(String rgb)
	{
		  if(!rgb.contains("#"))
	        {
	        	rgb="#ffffff";
	        }
			float first=Integer.parseInt(rgb.substring(5, 6),16);
			float second=Integer.parseInt(rgb.substring(6, 7),16);
			return (first*16+second)/255;
		
	}

	

	public void setChirld(CompomentBean chirld) {
		if (chirlds == null) {
			chirlds = new ArrayList<CompomentBean>();
		}
		chirlds.add(chirld);
	}

	public String genID(int length) // 参数为返回随机数的长度
	{
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	
  
}
