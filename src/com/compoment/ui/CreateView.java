package com.compoment.ui;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreateView {

	String xmlfile = "order_deliverway.xml";// 修改就行
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String className = null;
	String[] controls = { "Button", "TextView", "EditText", "ImageView",
			"ExpandableListView", "ListView", "GridView", "Spinner" };
	Element root = null;
	String m = "";

	public static void main(String[] args) throws SAXException, IOException {
		CreateView createView=new CreateView();
		createView.create();
	}

	public CreateView() {
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
		m += "import android.content.Context;\n";
		m += "import android.os.AsyncTask;\n";
		m += "import android.os.Handler;\n";
		m += "import android.os.Message;\n";
		m += "import android.os.SystemClock;\n";
		m += "import android.view.LayoutInflater;\n";
		m += "import android.view.View;\n";
		m += "import android.widget.AbsListView;\n";
		m += "import android.widget.Button;\n";
		m += "import android.widget.EditText;\n";
		m += "import android.widget.GridView;\n";
		m += "import android.widget.ListView;\n";
		m += "import android.widget.RelativeLayout;\n";
		m += "import android.widget.TextView;\n";
		m += "import android.widget.AbsListView.OnScrollListener;\n";

		m += "public class " + className + " implements OnScrollListener {\n";

		m += "	Context context;\n";
		m += "	public View containView;\n";

		// m += "	private OrderTypelistAdapter adapter;\n";
		// m += "	private ListView list;\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m+="/**"+text+"*/\n";
				if (control.equals("ListView") || control.equals("GridView")) {
					m += control + " "
							+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";
					m += className + "Adapter " + "adapter;\n";
				} else {
					m += control + " "
							+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";
				}
			}
		}

		m += "	public void clean() {\n";
		m += "		containView = null;\n";
		// m += "		containView = null;\n";
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "//" + text + "\n";
				if (control.equals("ListView")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "=null;\n";
					m += "adapter=null;\n";
				} else {
					m += firstCharToLowerAndJavaName(idToName[1]) + "=null;\n";
				}
			}
		}

		m += "}\n";

		m += "	Handler handler = new Handler() {\n";
		m += "		public void handleMessage(Message msg) {\n";
		m += "			switch (msg.what) {\n";
		m += "			// case SHOW_LOCKER:\n";
		m += "			// break;\n";
		m += "			default:\n";
		m += "				super.handleMessage(msg);\n";

		m += "			}\n";

		m += "		}\n";
		m += "	};\n";

		m += "	public " + className + "(final Context context) {\n";

		m += "		this.context = context;\n";
		m += "		init();\n";
		m += "}\n";

		m += "	public void init() {\n";

		m += "		if (containView == null) {\n";
		m += "			containView = inflateView(R.layout." + xmlfilename + ");\n";

		// m +=
		// "			list = (ListView) this.findViewById(R.id.listview); // 联系人ListView\n";
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "//" + text + "\n";
				if (control.equals("Button")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ " = (Button) containView\n";
					m += "					.findViewById(R.id." + idToName[1] + ");\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(new View.OnClickListener() {\n";
					m += "						public void onClick(View v) {\n";
					m += "						}\n";
					m += "					});\n";
				} else {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") containView.findViewById(R.id."
							+ idToName[1] + ");\n";
				}
			}
		}

		m += "		}\n";
		m += "}\n";

		m += "	private class GetNetInfoTask extends\n";
		m += "			AsyncTask<String, Integer, List<"+className+"AdapterBean>> {\n";
		m += "		@Override\n";
		m += "		protected void onPreExecute() {\n";
		m += "		}\n";

		m += "		@Override\n";
		m += "		protected List<"+className+"AdapterBean> doInBackground(String... params) {\n";

		m += "			return null;\n";
		m += "		}\n";

		m += "		@Override\n";
		m += "		protected void onProgressUpdate(Integer... values) {\n";
		m += "			// 更新进度\n";
		m += "		}\n";

		m += "		@Override\n";
		m += "	      protected void onPostExecute(List<"+className+"AdapterBean> list) {\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "//" + text + "\n";
				if (control.equals("ListView") || control.equals("GridView")) {
					m += "adapter = new " + className + "Adapter(context);\n";
					m += "adapter.setList(list);\n";
					m += firstCharToLowerAndJavaName(idToName[1])+".setAdapter(adapter);// 将数据适配器与Activity进行绑定\n";
					m += firstCharToLowerAndJavaName(idToName[1])+".setOnScrollListener("+className+".this);\n";
				} else if (control.equals("TextView")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(\"\");\n";
				}
			}
		}

		m += "	      }\n";

		m += "		@Override\n";
		m += "		protected void onCancelled() {\n";
		m += "			super.onCancelled();\n";
		m += "		}\n";
		m += "}\n";

		m += "	private View inflateView(int resource) {\n";
		m += "		LayoutInflater vi = (LayoutInflater) context\n";
		m += "				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);\n";
		m += "		return vi.inflate(resource, null);\n";
		m += "}\n";

		m += "	@Override\n";
		m += "	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {\n";
		m += "		// TODO Auto-generated method stub\n";

		m += "}\n";

		m += "	@Override\n";
		m += "	public void onScrollStateChanged(AbsListView arg0, int arg1) {\n";
		m += "		// TODO Auto-generated method stub\n";

		m += "}\n";

		m += "	public void tread() {\n";
		m += "		Thread thread = new Thread(new Runnable() {\n";
		m += "			public void run() {\n";

		m += "			}\n";
		m += "		});\n";
		m += "		thread.start();\n";
		m += "}\n";
		m += "}\n";

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
		int i = 0;
		for (String s : ss) {
			if (i == 0) {
				temp += s.substring(0, 1).toLowerCase() + s.substring(1);
			} else {
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
			}
			i++;
		}
		return temp;
	}
}
