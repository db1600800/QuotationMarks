package com.compoment.jspcss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class cssToJsp {

	public void cssToJsp()
	{
		
		
		try {
		String courseFile = null;
		File directory = new File("");// 参数为空
		courseFile = directory.getCanonicalPath();
		
		String pathCss = courseFile
				+ "/src/com/compoment/jspcss/css.txt";
		String pathJsp = courseFile
				+ "/src/com/compoment/jspcss/jsp.txt";

			
		FileReader frCss = new FileReader(pathCss);
		BufferedReader brCss = new BufferedReader(frCss);
		
		FileReader frJsp = new FileReader(pathJsp);
		BufferedReader brJsp = new BufferedReader(frJsp);

		String cssline;
		String jspline;
		while (brJsp.ready()) {
			jspline = brJsp.readLine();

			if (jspline.length() >= 1 && jspline.contains("class=\"") ) {

				int p=jspline.indexOf("class=\"");
				
				int p2=jspline.substring(p+8).indexOf("\"")+p+8;
				
				String stypeName=(String) jspline.subSequence(p+8, p2);
				
				
				while (brCss.ready()) {
					cssline = brCss.readLine();
					
					if(cssline.contains("."+stypeName))
					{
						
						
					}
				}
				
			}

		
		}

		brCss.close();
		frCss.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	}
//
//	public static void main(String[] args) {
//		
//		cssToJsp cssToJsp = new cssToJsp();
//
//		
//		
//			
//	}

	
	
	public static void main(String[] args)
	  {
		String txt=".swiper dfd{ position }";

	    String re1="(\\.)";	// Any Single Character 1
	    String re2="(swiper)";	// Variable Name 1
	    String re3="(\\s+)";	// White Space 1
	    String re4="(\\{)";	// Any Single Character 2
	    String re5=".*?";	// Non-greedy match on filler
	    String re6="((?:[a-z][a-z]+))";	// Word 1
	    String re7=".*?";	// Non-greedy match on filler
	    String re8="(\\})";	// Any Single Character 3

	    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(txt);
	    if (m.find())
	    {
	        String c1=m.group(1);
	        String var1=m.group(2);
	        String ws1=m.group(3);
	        String c2=m.group(4);
	        String word1=m.group(5);
	        String c3=m.group(6);
	        System.out.print("("+c1.toString()+")"+"("+var1.toString()+")"+"("+ws1.toString()+")"+"("+c2.toString()+")"+"("+word1.toString()+")"+"("+c3.toString()+")"+"\n");
	    }
	  }
	public static boolean isinteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}