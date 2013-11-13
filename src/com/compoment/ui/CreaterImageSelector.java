package com.compoment.ui;

public class CreaterImageSelector {

	
	public void creater()
	{
	     File[] files = dirFile.listFiles(); 

	     for(int i = 0; i < files.length; i++) 
	     { 
	      if(files[i].isFile()) 
	      { 
	       System.out.println(files[i].getAbsolutePath() + " 是文件！");       
	      } 
	      else if (files[i].isDirectory()) 
	      { 
	       System.out.println(files[i].getAbsolutePath() + " 是目录！");    

	      } 
		
		
		File dirFile = new File(dirName); 
	     //如果dir对应的文件不存在，或者不是一个文件夹则退出 
	     if(!dirFile.exists() || (!dirFile.isDirectory())){ 
	      System.out.println("List失败！找不到目录："+dirName); 
	      return luj; 
	     } 
		  File[] files = file.listFiles();  
		
		
		  
		String m="";
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		m+="<selector xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";
		m+="	<item android:state_enabled=\"true\" android:state_pressed=\"true\"\n";
		m+="		android:drawable=\"@drawable/widget_soft_h\"></item>\n";
		m+="	<item android:state_enabled=\"true\" android:state_pressed=\"false\"\n";
		m+="		android:drawable=\"@drawable/widget_soft\" />\n";
		m+="</selector>\n";
		
		
	}
}
