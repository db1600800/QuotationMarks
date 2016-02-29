package com.compoment.jspcss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cssToJsp {

	public cssToJsp() {

	}

	public void commonCssToJsp() {

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
					// img a ol li body table input h1 h2 h3 h4 textarea select
					// strong span th td header dd dt
				} else if (jspline.length() >= 1 && jspline.contains("class=\"")) {

					int startP = jspline.indexOf("class=\"");
					String temp = jspline.substring(startP + 7);
					int endP = jspline.substring(startP + 7).indexOf("\"") + startP + 8;

					String classDetail = jspline.subSequence(startP, endP).toString();
					String styleName = (String) jspline.subSequence(startP + 7, endP - 1);
					String styleNames[] = styleName.split(" ");

					String tempStyle = "";
					for (String name : styleNames) {

						String style = "";
						style = css1(pathCss, name);

						if (!style.equals("")) {
							if (style.indexOf(";") != -1) {
								tempStyle += style;
							} else {
								tempStyle += style + ";";
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
		cssToJsp.cssToObject();
	}

	/**
	 * .swiper { positionk };
	 */
	public String css1(String pathCss, String keyName) {

		FileReader frCss = null;
		BufferedReader brCss = null;
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

	/**
	 * .swiper { positionk };
	 */

	public String cssToObject() {

		
		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String pathCss = courseFile + "/src/com/compoment/jspcss/css.txt";
		
		
		FileReader frCss = null;
		BufferedReader brCss = null;
		try {

			frCss = new FileReader(pathCss);

			brCss = new BufferedReader(frCss);
			List csss = new ArrayList();

			while (brCss.ready()) {
				String txt = brCss.readLine();// ".swiper { positionk }";

				// 1.没有点 . (纯标签)  a{} 
				if (txt.indexOf(".") == -1) {
					String re1 = "(.*?)"; // Non-greedy match on filler
					String re2 = "(\\{)"; // Any Single Character 1
					String re3 = "(.*)"; // Variable Name 1
					String re4 = "(\\})"; // Any Single Character 2

					Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
					Matcher m = p.matcher(txt);
					if (m.find()) {
						String c1 = m.group(1);
						String var1 = m.group(2);
						String c2 = m.group(3);
						String c3 = m.group(4);

						String cleanTag[] = c1.split(",");

						for (int i = 0; i < cleanTag.length; i++) {

							CssNode cssNode = new CssNode();
							cssNode.name = cleanTag[i].trim();
							String names[]=cssNode.name.split(" ");
							String newname="";
							if(names.length==1)
							{
								newname+=names[0];
							}else
							{
							for(int j=0;j<names.length;j++)
							{
								
								newname+=names[j]+"/";
							}
							}
							cssNode.name=newname;
							cssNode.cssString = c2;
							csss.add(cssNode);

						}
					}

				}else
				{
					
				// 2.只有点
					
					List keys=new ArrayList();
					
				//情况1 ".showmenu a .menuwrap { ddb }";
					
					
					
				    String re1="(\\.)";	// Any Single Character 1
				    String re2="(.*)";	// Variable Name 1 
				    String re3="(\\.)";	// White Space 1
				
				    
				    Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				    Matcher m = p.matcher(txt);
				    while (m.find())
				    {
				        String c1=m.group(2);
				    
				     
				      
						String names[]=c1.trim().split(" ");
						String newname="";
						if(names.length==1)
						{
							newname+=names[0];
						}else
						{
						for(int j=0;j<names.length;j++)
						{
							
							newname+=names[j]+"/";
						}
						}
						
						keys.add("."+newname);
				    }
				    
				    
				    
				    //情况2 ".showmenu a .menuwrap-df df{ a }";
				    
				    String re11="(\\.)";	// Any Single Character 1
				    String re21="([\\w\\-]+\\s?\\w*)";	// Variable Name 1 
				    String re31="(\\{)";	// White Space 1
					String re41 = "(.*)"; // Variable Name 1
					String re51 = "(\\})"; // Any Single Character 2
					
				    Pattern p1 = Pattern.compile(re11+re21+re31+re41+re51,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				    Matcher m1 = p1.matcher(txt);
				    String cssvalue="";
				    if (m1.find())
				    {
				        String c1=m1.group(2);
				 
				        cssvalue=m1.group(4);
				     

						String names[]=c1.trim().split(" ");
						String newname="";
						if(names.length==1)
						{
							newname+=names[0];
						}else
						{
						for(int j=0;j<names.length;j++)
						{
							
							newname+=names[j]+"/";
						}
						}
						
						keys.add("."+newname);
				   
				    }
				    
				    
				
				    for(int i=0;i<keys.size();i++)
				    {
				    	
				    	CssNode cssNode = new CssNode();
						cssNode.name =(String) keys.get(i);
						cssNode.cssString = cssvalue;
						csss.add(cssNode);
				    }
				
				
				
				
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

	public class CssNode {
		String name;
		boolean hasChirld;
		String cssString;
		List<CssNode> nodes;
	}

}