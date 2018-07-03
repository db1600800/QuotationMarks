package com.compoment.cut.android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.compoment.cut.CompomentBean;
import com.compoment.remote.AndroidLayoutXmlInterface;
import com.compoment.remote.IphoneViewControllerXibInterface;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;

public class AndroidLayoutXml extends UnicastRemoteObject implements AndroidLayoutXmlInterface{

	public AndroidLayoutXml() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}




	int maxW = 0;
	int maxH = 0;
	String m = "";

	List<CompomentBean> beans;
	public List  getBeans() throws RemoteException
	{
		
		return beans;
	}
	
	public String analyseRelative(String filename, List<CompomentBean> abeans) throws RemoteException{return "";}

	public void xml(CompomentBean bean) {

		String width = "";
		String height = "";
		if ((maxW - bean.w >= 0) && (maxW - bean.w < 100)) {
			width = "fill_parent";
		} else {
			width = "wrap_content";
		}

		if ((maxH - bean.h >= 0) && (maxH - bean.h < 100)) {
			height = "fill_parent";
		} else {
			height = "wrap_content";
		}

		// if (bean.chirlds != null && bean.chirlds.size() > 0) {
		 if (bean.type.contains("Layout")) {
			if(bean.type.contains("DrawerLayout"))
			{
				m += "<android.support.v4.widget." + bean.type + " android:id=\"@+id/"
					+ bean.enname+"\" android:layout_width=\"" + width
						+ "\"  android:layout_height=\"" + height
						+ "\"    android:background=\"" + bean.bgRgb16.trim()
						+ "\"  >\n   <!-- The main content view 主页布局--> \n";
			}
			else if (bean.bgRgb16.contains("颜色")) {
				m += "<" + bean.type + "  android:layout_width=\"" + width
						+ "\"  android:layout_height=\"" + height
						+ "\"    android:background=\"#" + "ffffff"
						+ "\"  android:orientation=\"" + bean.orientation
						+ "\"  >\n";
			} else {
				m += "<" + bean.type + "  android:layout_width=\"" + width
						+ "\"  android:layout_height=\"" + height
						+ "\"    android:background=\"" + bean.bgRgb16.trim()
						+ "\"   android:orientation=\"" + bean.orientation
						+ "\"  " + bean.relative + "android:gravity=\""
						+ bean.gravity + "\">\n";
			}

		}
		// } else {
		if (bean.type.equals("TextView")) {
			m += "<TextView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:text=\""
					+ bean.cnname + "\"   android:textSize=\"" + bean.textSize
					+ "sp\" android:textColor=\"" + bean.rgb16
					+ "\"   android:gravity=\"center\" " + bean.relative
					+ ">\n";
			m += "</TextView>\n";
		}

		if (bean.type.equals("Button")) {
			m += "<Button   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"10dp\" android:layout_marginRight=\"10dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:text=\""
					+ bean.cnname + "\"   android:textSize=\"" + bean.textSize
					+ "sp\" android:textColor=\"" + bean.rgb16
					+ "\"   android:background=\"" + bean.bgRgb16 + "\"  "
					+ bean.relative + ">\n";
			m += "</Button>\n";
		}

		if (bean.type.equals("EditText")) {
			m += "<EditText   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:hint=\""
					+ bean.cnname
					+ "\"   android:textSize=\""
					+ bean.textSize
					+ "sp\" android:textColor=\""
					+ bean.rgb16
					+ "\"   android:gravity=\"center\"   android:singleLine=\"true\"   android:maxLength=\"40\"  "
					+ bean.relative + ">\n";
			m += "</EditText>\n";
		}

		if (bean.type.equals("CheckBox")) {
			m += "<CheckBox   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:text=\""
					+ bean.cnname + "\"   android:textSize=\"" + bean.textSize
					+ "sp\" android:textColor=\"" + bean.rgb16 + "\"   "
					+ bean.relative + ">\n";
			m += "</CheckBox>\n";
		}

		if (bean.type.equals("ListView")) {
			m += "<ListView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"10dp\" android:layout_marginRight=\"10dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\""
					+ " android:cacheColorHint=\"@null\" android:divider=\""
					+ bean.rgb16
					+ "\"  android:dividerHeight=\"0.5dip\" android:fadingEdge=\"none\"  android:fastScrollEnabled=\"false\" "
					+ bean.relative + ">\n";
			m += "</ListView>\n";
		}

		if (bean.type.equals("ImageView")) {
			m += "<ImageView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" "

					+ "  android:background=\"@drawable/" + bean.picName
					+ "\"  " + bean.relative + ">\n";
			m += "</ImageView>\n";
		}

		if (bean.type.equals("ExpandableListView")) {
			m += "<ExpandableListView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"10dp\" android:layout_marginRight=\"10dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\""
					+ " android:cacheColorHint=\"@null\" android:groupIndicator=\"@null\" android:divider=\""
					+ bean.rgb16
					+ "\"  android:dividerHeight=\"0.5dip\" android:childDivider=\""
					+ bean.rgb16
					+ "\"   android:fadingEdge=\"none\"  android:fastScrollEnabled=\"false\" "
					+ bean.relative + ">\n";
			m += "</ExpandableListView>\n";
		}
		
		if (bean.type.equals("Line")) {
			m+=" <View android:layout_width=\"fill_parent\" android:layout_height=\"1dip\" android:background=\""+bean.bgRgb16+"\" />\n";

		}
		// }

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {
				xml(chirld);
			}

		}

		// if (bean.chirlds != null && bean.chirlds.size() > 0) {
		if (bean.type.contains("Layout")) {
			if(bean.type.contains("DrawerLayout"))
			{
				m+="         <!-- The navigation drawer 左抽屉 由ActivityChirld提供 -->\n";
				m+="         <LinearLayout\n";
				m+="            android:id=\"@+id/leftDrawerContain\"\n";
				m+="            android:layout_width=\"240dp\"\n";
				m+="            android:layout_gravity=\"start\" \n";
				m+="            android:background=\"#111\"\n";
				m+="            android:layout_height=\"fill_parent\"\n";
				m+="            android:orientation=\"vertical\"\n";
				m+="             >\n";
				m+="        </LinearLayout>\n";
				m+="    </android.support.v4.widget."+bean.type+">\n";
			}else
			{
			m += "</" + bean.type + ">\n";
			}
		}
		// }
	}
	
	


	Comparator<CompomentBean> comparatorX = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.x != s2.x) {
				return s1.x - s2.x;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorY = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.y != s2.y) {
				return s1.y - s2.y;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

}
