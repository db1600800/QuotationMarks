package com.compoment.ui;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class CreaterActivity {
	 String xmlFilePath;
	 String xmlfilename = "stock_mystock_kline";//ֻ������  �����ö�   run�ͺ�
     String activityName="stockMystockKline";

	private static String readStr = "";

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		// TODO Auto-generated method stub
		CreaterActivity parser = new CreaterActivity();
		parser.dom();
	}

	public CreaterActivity()
	{
		 String classDir = this.getClass().getResource("/").getPath();
		  xmlFilePath =classDir+"com/compoment/ui/stock_mystock_kline";
	}

	public void dom() {
		String tempjavaFilePartOne = "" + "" + "public class " + firstCharToUpper(activityName)
				+ " extends Activity {\n"
				+"Context context;\n"
				+ "View containView;\n"


				+ "	public " + firstCharToUpper(activityName)+"(final Context context) {\n"
				+ "      super(context);"
				+ "      this.context = context;" +
				"}\n"


				+ "	public void showView(){"
				+ "       if( containView ==null){"
				+ "        containView = inflateView(R.layout." + xmlfilename
				+ ");";
		StringBuffer javaFilePartOne = new StringBuffer(tempjavaFilePartOne);

		String javaFilePartTwo = "}}"
				+ "public View inflateView(int resource) {"
				+ "      LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);"
				+ "      return vi.inflate(resource, null);" + "}" + "}";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();

			Document doc = builder.parse(xmlFilePath+".xml");
			Element root = doc.getDocumentElement();

			//button
			NodeList buttonItems = root.getElementsByTagName("Button");// ��������Button�ڵ�
			for (int i = 0; i < buttonItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) buttonItems.item(i);
				// ��ȡButton�ڵ��id����ֵ
				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "Button " + idToName[1]
						+ "=(Button)containView.findViewById(R.id."
						+ idToName[1] + ");\n" +
								idToName[1] +
								".setOnClickListener(new View.OnClickListener() {" +
								"public void onClick(View v) {}});";


				javaFilePartOne.append(temp);
			}

			//text
			NodeList textViewItems = root.getElementsByTagName("TextView");// ��������TextView�ڵ�
			for (int i = 0; i < textViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) textViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ

				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "TextView " + idToName[1]
						+ "=(TextView)containView.findViewById(R.id."
						+ idToName[1] + ");";
				javaFilePartOne.append(temp);
			}

			//edittext
			NodeList editTextViewItems = root.getElementsByTagName("EditText");// ��������TextView�ڵ�
			for (int i = 0; i < editTextViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) editTextViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ

				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "EditText " + idToName[1]
						+ "=(EditText)containView.findViewById(R.id."
						+ idToName[1] + ");";
				javaFilePartOne.append(temp);
			}

			//imageView
			NodeList imageViewItems = root.getElementsByTagName("ImageView");// ��������ImageView�ڵ�
			for (int i = 0; i < imageViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) imageViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ
				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "ImageView " + idToName[1]
						+ "=(ImageView)containView.findViewById(R.id."
						+ idToName[1] + ");";
				javaFilePartOne.append(temp);
			}

			//expandablelistview
			NodeList expandableListViewItems = root
					.getElementsByTagName("ExpandableListView");// ��������ExpandableListView�ڵ�
			for (int i = 0; i < expandableListViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) expandableListViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ
				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "ExpandableListView " + idToName[1]
						+ "=(ExpandableListView)containView.findViewById(R.id."
						+ idToName[1] + ");";
				javaFilePartOne.append(temp);
			}

			//ListView
			NodeList listViewItems = root.getElementsByTagName("ListView");// ��������ListView�ڵ�
			for (int i = 0; i < listViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) listViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ
				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "ListView " + idToName[1]
						+ "=(ListView)containView.findViewById(R.id."
						+ idToName[1] + ");" +idToName[1]+
								".setOnItemClickListener(new OnItemClickListener() {" +
								"  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) { } });";
				javaFilePartOne.append(temp);
			}

			//Spinner
			NodeList spinnerItems = root.getElementsByTagName("Spinner");// ��������Spinner�ڵ�
			for (int i = 0; i < spinnerItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) spinnerItems.item(i);
				// ��ȡButton�ڵ��id����ֵ
				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "Spinner " + idToName[1]
						+ "=(Spinner)containView.findViewById(R.id."
						+ idToName[1] + ");";
				javaFilePartOne.append(temp);
			}

			javaFilePartOne.append(javaFilePartTwo);
			//Util.write(Util.toUpper(viewGroupName) + ".java", javaFilePartOne.toString());
            System.out.println( javaFilePartOne.toString());
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



	public static String firstCharToUpper(String string) {
		return string = string.substring(0, 1).toUpperCase()
				+ string.substring(1);
	}

}
