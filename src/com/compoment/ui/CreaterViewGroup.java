package com.compoment.ui;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class CreaterViewGroup {


	 static String classDir =null;
	 static String xmlFilePath=null;
	 static String xmlfilename = null;
     static String viewGroupName=null;

	private static String readStr = "";

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		// TODO Auto-generated method stub

		 String xmlfile="order_typelist.xml";//修改就行


		CreaterViewGroup parser = new CreaterViewGroup();
		classDir = parser.getClass().getResource("/").getPath();
		int pos= xmlfile.indexOf(".");
		xmlfilename=xmlfile.substring(0, pos);
		xmlFilePath=classDir+"com/compoment/ui/"+xmlfilename;
		viewGroupName=xmlfilename;
		parser.dom();
	}



	public void dom() {
		String tempjavaFilePartOne = "" + "" + "public class " + firstCharToUpper(viewGroupName)
				+ " extends RelativeLayout {\n" + "" + "   Context context;\n"
				+ "   View containView;\n" + "" + "" + "	public " + firstCharToUpper(viewGroupName)
				+ "(final Context context) {\n" + "      super(context);\n"
				+ "      this.context = context;}\n" + ""
				+ "	public void showView(){\n"
				+ "       if( containView ==null){\n"
				+ "        containView = inflateView(R.layout." + xmlfilename
				+ ");\n\n";
		StringBuffer javaFilePartOne = new StringBuffer(tempjavaFilePartOne);

		String javaFilePartTwo = "}}\n"
				+ "public View inflateView(int resource) {\n"
				+ "      LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);\n"
				+ "      return vi.inflate(resource, null);" + "}" + "}\n";

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
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				String temp = "Button " + idToName[1]
						+ "=(Button)containView.findViewById(R.id."
						+ idToName[1] + ");\n" +
								idToName[1] +
								".setOnClickListener( \n new View.OnClickListener() {\n" +
								"public void onClick(View v) {}\n});\n\n";

				javaFilePartOne.append("\n // "+text+" Btn\n");
				javaFilePartOne.append(temp);
			}

			//text
			NodeList textViewItems = root.getElementsByTagName("TextView");// ��������TextView�ڵ�
			for (int i = 0; i < textViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) textViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ

				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				String temp = "TextView " + idToName[1]
						+ "=(TextView)containView.findViewById(R.id."
						+ idToName[1] + ");\n\n";
				javaFilePartOne.append("\n // "+text+" TextView\n");
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
						+ idToName[1] + ");\n\n";
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
						+ idToName[1] + ");\n\n";
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
						+ idToName[1] + ");\n\n";
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
						+ idToName[1] + ");\n\n" +idToName[1]+
								".setOnItemClickListener(\n new OnItemClickListener() {\n" +
								"  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) \n { } });\n";
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
						+ idToName[1] + ");\n";
				javaFilePartOne.append(temp);
			}

			//GridView
			NodeList gridViewItems = root.getElementsByTagName("GridView");// ��������Spinner�ڵ�
			for (int i = 0; i < gridViewItems.getLength(); i++) {
				// �õ���һ��Button�ڵ�
				Element personNode = (Element) gridViewItems.item(i);
				// ��ȡButton�ڵ��id����ֵ
				String id = personNode.getAttribute("android:id");
				String[] idToName = id.split("/");
				String temp = "GridView " + idToName[1]
						+ "=(GridView)containView.findViewById(R.id."
						+ idToName[1] + ");\n";
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
		//buy_typelist
		String []ss=string.split("_");
		String temp="";
		for(String s:ss)
		{
			 temp+=s.substring(0, 1).toUpperCase()+s.substring(1);
		}
		return temp
				;
	}

}
