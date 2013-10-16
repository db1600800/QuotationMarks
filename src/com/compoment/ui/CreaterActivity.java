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
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String viewGroupName = null;

	private static String readStr = "";

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		// TODO Auto-generated method stub
		CreaterActivity parser = new CreaterActivity();

		String xmlfile = "buy_typelist.xml";// 修改就行
		classDir = parser.getClass().getResource("/").getPath();
		int pos = xmlfile.indexOf(".");
		xmlfilename = xmlfile.substring(0, pos);
		xmlFilePath = classDir + "com/compoment/ui/" + xmlfilename;
		viewGroupName = xmlfilename;

		parser.dom();
	}


	public void dom() {
		String[] controls = { "Button", "TextView", "EditText", "ImageView",
				"ExpandableListView", "ListView", "GridView", "Spinner" };

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(xmlFilePath + ".xml");
			Element root = doc.getDocumentElement();

			for (String control : controls) {

				// button
				NodeList buttonItems = root.getElementsByTagName(control);
				for (int i = 0; i < buttonItems.getLength(); i++) {
					
					Element personNode = (Element) buttonItems.item(i);
				
					String id = personNode.getAttribute("android:id");
					String text = personNode.getAttribute("android:text");
					String[] idToName = id.split("/");
					String temp = control
							+ idToName[1]
							+ "=("
							+ control
							+ ")containView.findViewById(R.id."
							+ idToName[1]
							+ ");\n"
							+ idToName[1]
							+ ".setOnClickListener( \n new View.OnClickListener() {\n"
							+ "public void onClick(View v) {}\n});\n\n";

			
				}
			}

			
			System.out.println(javaFilePartOne.toString());
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
