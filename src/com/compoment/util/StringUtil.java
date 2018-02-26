package com.compoment.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (s!=null && !s.equals("item")&&s.length()>1)
			{
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
			}
		}
		return temp;
	}
	
	
	public static String firstCharToLower(String string) {
		// buy_typelist
		if(string!=null&&string.length()>1)
		{
		string = string.substring(0, 1).toLowerCase() + string.substring(1);
		}else if(string!=null&&string.length()>0)
		{
			string = string.substring(0, 1).toLowerCase();
		}
		
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
	
	
	/**
	  * 下划线转驼峰法
	  * @param line 源字符串
	  * @param smallCamel 大小驼峰,是否为小驼峰
	  * @return 转换后的字符串
	  */
	 public static String underline2Camel(String line,boolean smallCamel){
	  if(line==null||"".equals(line)){
	   return "";
	  }
	  StringBuffer sb=new StringBuffer();
	  Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
	  Matcher matcher=pattern.matcher(line);
	  while(matcher.find()){
	   String word=matcher.group();
	   sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
	   int index=word.lastIndexOf('_');
	   if(index>0){
	    sb.append(word.substring(1, index).toLowerCase());
	   }else{
	    sb.append(word.substring(1).toLowerCase());
	   }
	  }
	  return sb.toString();
	 }
}
