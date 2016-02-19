package com.compoment.jspcss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cssToJsp {

	public cssToJsp() {


	}

	
	public void commonCssToJsp()
	{
		

		try {

			String courseFile = null;
			File directory = new File("");// 参数为空
			courseFile = directory.getCanonicalPath();

			String pathCss = courseFile + "/src/com/compoment/jspcss/css.txt";
			String pathJsp = courseFile + "/src/com/compoment/jspcss/jsp.txt";

			FileReader frJsp = new FileReader(pathJsp);
			BufferedReader brJsp = new BufferedReader(frJsp);

			String jspline;

			String newJsp = "";

			while (brJsp.ready()) {
				jspline = brJsp.readLine();

				if (jspline.length() >= 1 && jspline.contains("class=\"")) {
					//img  a  ol  li  body  table  input  h1  h2  h3 h4  textarea  select  strong  span th  td  header  dd  dt
				}
				else if (jspline.length() >= 1 && jspline.contains("class=\"")) {

					int startP = jspline.indexOf("class=\"");
					String temp = jspline.substring(startP + 7);
					int endP = jspline.substring(startP + 7).indexOf("\"") + startP + 8;

					String classDetail = jspline.subSequence(startP, endP).toString();
					String styleName = (String) jspline.subSequence(startP + 7, endP - 1);
					String styleNames[]=styleName.split(" ");
					
					String tempStyle="";
					for(String name :  styleNames)
					{
						
						String style = "";
						style = css1(pathCss, name);
						
						if (!style.equals("")) 
						{
							if(style.indexOf(";")!=-1)
							{
							 tempStyle+=style;
							}else
							{
							 tempStyle+=style+";";
							}
						}
					}
					
					

						if (!tempStyle.equals("")) {
							// System.out.println(style);
							String chage = jspline.replace(classDetail, "style=\"" + tempStyle + "\"") + "\n";
							// System.out.println(chage);
							newJsp += chage;

						} else {
							newJsp += jspline + "\n";

						}
						
					
					
					

				} else {

					newJsp += jspline + "\n";
				}

			}

		

			brJsp.close();
			frJsp.close();

			 System.out.println(newJsp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		cssToJsp cssToJsp = new cssToJsp();
		
		cssToJsp.commonCssToJsp();
	}



	/**
	 * .swiper { positionk };
	 * */
	public String css1(String pathCss, String keyName) {


		FileReader frCss = null;
		BufferedReader brCss=null;
		try {

			frCss = new FileReader(pathCss);

			 brCss = new BufferedReader(frCss);
	

			while (brCss.ready()) {

				

				String txt = brCss.readLine();// ".swiper { positionk }";

				String re1 = "(\\.)"; // Any Single Character 1
				String re2 = "(" + keyName + ")"; // Word 1
				String re3 = "(\\s*)"; // White Space 1
				String re4 = "(\\{)"; // Any Single Character 2
				String re5 = "(\\s*)"; // White Space 2
				String re6 = "(.*)"; // Variable Name 1
				String re7 = "(\\s*)"; // White Space 3
				String re8 = "(\\})"; // Any Single Character 3

				Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8,
						Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				Matcher m = p.matcher(txt);
				if (m.find()) {

					String c1 = m.group(1);
					String word1 = m.group(2);
					String ws1 = m.group(3);
					String c2 = m.group(4);
					String ws2 = m.group(5);
					String var1 = m.group(6);
					String ws3 = m.group(7);
					String c3 = m.group(8);

					brCss.close();
					frCss.close();
					
					return var1;
				}
				
			
			}
			
			brCss.close();
			frCss.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "";
	}
	
	
	
	
	

	
	

	public static boolean isinteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	
	
	
	public class CssNode
	{
		String name;
		boolean hasChirld;
		String cssString;
		List<CssNode> nodes; 	
	}
	
	
	
}