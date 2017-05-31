package com.compoment.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
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
	
	
	public static String firstCharToLower(String string) {
		// buy_typelist
	 

		string = string.substring(0, 1).toLowerCase() + string.substring(1);
		
		return string;
	}
	
	
	public static String tableName(String str) {
		// buy_typelist
		StringBuilder tableNameTypeString = new StringBuilder(); 
		tableNameTypeString.append("t");
		 int i = 0;  
	        while(i < str.length()){  
	            char chr = str.charAt(i);  
	            if(Character.isUpperCase(chr)){  
	            	tableNameTypeString.append("_"); 
	            	tableNameTypeString.append(chr); 
	            }else
	            {
	            	tableNameTypeString.append(chr); 
	            }
	            i++;  
	        }  
		
		
		return tableNameTypeString.toString().toLowerCase();
	}
	

	
	
	public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
