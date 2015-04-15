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
	/**
	 * 背景颜色 #FFFFFF
	 * */
	public String bgRgb16;
	public int x;
	public int y;
	public int w;
	public int h;
	public String cnname;
	public String enname;
	public String textSize;
	public boolean isImgCache = false;
	public boolean isPublicCompoment = false;
	public String id;// for iPhone id=jyV-Pf-zRb

	public CompomentBean() {
        id=genID(3)+"-"+genID(2)+"-"+genID(3);
	}

	/**
	 * 控件类型 Button List TextView LinearLayout
	 * */
	public String type;
	/**
	 * LinearLayout 水平 或垂直 android:orientation="vertical"
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
