package com.compoment.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.SAXException;

public class CreaterImageSelector {

	public static void main(String[] args) throws SAXException, IOException {
		CreaterImageSelector createrImageSelector = new CreaterImageSelector();
	

	}
	public  CreaterImageSelector() {

		String path="C:\\Users\\460702000148\\workspace\\切图\\购货__收藏夹v2.0\\android_W480";
		File dirFile = new File(path);
		// 如果dir对应的文件不存在，或者不是一个文件夹则退出
		if (!dirFile.exists() || (!dirFile.isDirectory())) {

			return;
		}

		File[] files = dirFile.listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				String filename = files[i].getName();
				String AbsolutePath=files[i].getAbsolutePath();
				AbsolutePath=AbsolutePath.replace(filename, "");
				int lastIndex=filename.lastIndexOf("_");
				
				if(lastIndex!=-1)
				{
				
				String lastString=filename.substring(filename.lastIndexOf("_"),filename.lastIndexOf("."));
				if (lastString.equals("_press")) {

					xmlCreate(AbsolutePath,filename.substring(0,filename.lastIndexOf("_")));
					
				}else if(lastString.equals("_s"))
				{
					
				}
				}

			}
		}

		
	

	}
	
	public void xmlCreate(String path,String picName)
	{
		String m = "";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		m += "<selector xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";
		m += "	<item  android:state_pressed=\"true\"\n";
		m += "		android:drawable=\"@drawable/"+picName+"_press\"></item>\n";
		m += "	<item  android:state_pressed=\"false\"\n";
		m += "		android:drawable=\"@drawable/"+picName+"\" />\n";
		m += "</selector>\n";
		
		stringToFile(path + picName + "_sl.xml", m);
	}
	
	


public void stringToFile(String fileName, String str) {
	FileWriter fw;
	try {
		fw = new FileWriter(fileName);
		fw.write(str);
		fw.flush();// 加上这句
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
