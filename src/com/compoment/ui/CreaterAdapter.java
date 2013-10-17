package com.compoment.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//http://www.189works.com/article-37835-1.html

public class CreaterAdapter {

	String xmlfile = "order_productlist_item.xml";// 修改就行
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String className = null;
	String[] controls = { "Button", "TextView", "EditText", "ImageView",
			"ExpandableListView", "ListView", "GridView", "Spinner" };
	Element root = null;
	String m = "";

	public static void main(String[] args) throws SAXException, IOException {
		CreaterAdapter createrAdapter = new CreaterAdapter();
		createrAdapter.create();

	}

	public CreaterAdapter() {

		classDir = this.getClass().getResource("/").getPath();
		int pos = xmlfile.indexOf(".");
		xmlfilename = xmlfile.substring(0, pos);
		xmlFilePath = classDir + "com/compoment/ui/" + xmlfilename;
		className = firstCharToUpperAndJavaName(xmlfilename);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(xmlFilePath + ".xml");
			root = doc.getDocumentElement();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void create() {
		m += "import java.util.List;\n";
		m += "import android.content.Context;\n";
		m += "import android.view.LayoutInflater;\n";
		m += "import android.view.View;\n";
		m += "import android.view.ViewGroup;\n";
		m += "import android.widget.BaseAdapter;\n";
		m += "import android.widget.ImageView;\n";
		m += "import android.widget.TextView;\n";
		m+="import android.widget.Button;\n";
		m+="import android.widget.EditText;\n";

		m += "public class " + className + "Adapter extends BaseAdapter {\n";

		m += "	private static final String TAG = \""+className+"Adapter\";\n";
		m += "	/** 列表是否滑动中，如果是滑动状态则仅从内存中获取图片，否则开启线程去外存或网络获取图片。 */\n";
		m += "	private boolean isScrolling = false;\n";
		m += "	private List<" + className + "AdapterBean> list;\n";
		m += "	private ImageLoader mImageLoader;\n";
		m += "	private Context mContext;\n";
		m += "	int defaultImg = R.drawable.ic_launcher;\n";

		m += "	public void setFlagIsScrolling(boolean isScrolling) {\n";
		m += "		this.isScrolling = isScrolling;\n";
		m += "	}\n";

		m += "	public " + className + "Adapter(Context context) {\n";
		m += "		this.mContext = context;\n";
		m += "		mImageLoader = new ImageLoader(context);\n";
		m += "	}\n";

		m += "	public ImageLoader getImageLoader() {\n";
		m += "		return mImageLoader;\n";
		m += "	}\n";

		m += "	public void setList(List<" + className
				+ "AdapterBean> list) {\n";
		m += "		this.list = list;\n";

		m += "	}\n";

		m += "	@Override\n";
		m += "	public int getCount() {\n";
		m += "		return list.size();\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public Object getItem(int position) {\n";
		m += "		return list.get(position);\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public long getItemId(int position) {\n";
		m += "		return position;\n";
		m += "	}\n";

		m += "	@Override\n";
		m += "	public View getView(int position, View convertView, ViewGroup parent) {\n";

		m += "		ViewHolder viewHolder = null;\n";
		m += "		if (convertView == null) {\n";
		m += "			convertView = LayoutInflater.from(mContext).inflate(\n";
		m += "					R.layout." + xmlfilename + ", null);\n";
		m += "			viewHolder = new ViewHolder();\n";

		// m += "			viewHolder.mTextView = (TextView) convertView\n";
		// m += "					.findViewById(R.id.tv_tips);\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				 m+="//"+text+"\n";
				m += "			viewHolder."
						+ firstCharToLowerAndJavaName(idToName[1]) + " = ("
						+ control + ") convertView";
				m += ".findViewById(R.id." + idToName[1] + ");\n";

				if (control.equals("CheckBox")) {
					m += " viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(new View.OnClickListener() {\n";
					m += "	                      @Override \n";
					m += "	                      public void onClick(View v) {\n";
					m += "	                          CheckBox cb = (CheckBox)v;\n";

					m += "bean." + firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState=cb.isChecked()" + ";\n";
					m += "}\n";
					m += "});\n";
				} else if (control.equals("Button")) {
					m += " viewHolder."+firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener( \n new View.OnClickListener() {\n"
							+ "public void onClick(View v) {}\n});\n\n";
				}
			}
		}

		m += "			convertView.setTag(viewHolder);\n";
		m += "		} else {\n";
		m += "			viewHolder = (ViewHolder) convertView.getTag();\n";
		m += "		}\n";

		m += "		" + className + "AdapterBean bean = list.get(position);\n";
		m += "		if (bean != null) {\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				 m+="//"+text+"\n";
				if (control.equals("ImageView")) {
					m += "			mImageLoader.setImgToImageView(bean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Url,\n";
					m += "					viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ", defaultImg, isScrolling);\n";
				} else if (control.equals("CheckBox")) {

					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setChecked(bean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState);\n";

				} else if (control.equals("EditText")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(bean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value);\n";
				} else if (control.equals("Button")) {

				} else if (control.equals("TextView")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(bean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value);\n";
				}

			}
		}

		m += "		}\n";

		m += "		return convertView;\n";
		m += "	}\n";

		m += "	static class ViewHolder {\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m+="//"+text+"\n";
				m += control + " " + firstCharToLowerAndJavaName(idToName[1])
						+ ";\n";
			}
		}

		m += "	}\n";

		m += "public	static class " + className + "AdapterBean {\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
                m+="//"+text+"\n";
				if (control.equals("ImageView")) {
					m += "		public String "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Url;\n";
				} else if (control.equals("TextView")) {
					m += "		public String "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value;\n";
				} else if (control.equals("EditText")) {
					m += "		public String "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value;\n";
				} else if (control.equals("CheckBox")) {
					m += "		public boolean "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState;\n";
				}
			}
		}

		m += "	}\n";

		m += "	}\n";

		System.out.println(m);
	}

	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (!s.equals("item"))
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return temp;
	}

	public static String firstCharToLowerAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		int i=0;
		for (String s : ss) {
			if(i==0)
			{
			temp += s.substring(0, 1).toLowerCase() + s.substring(1);
			}else
			{
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
			}
			i++;
		}
		return temp;
	}

}
