package com.compoment.addfunction.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;

public class Dialog {


	String sourceAddress = KeyValue.readCache("compomentProjectAddress");//"C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress = KeyValue.readCache("projectPath");
	String waitByModifyFileName;
    int appendline;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Paging("");
	}

	public Dialog(String waitByModifyFileName,int appendline) {
		this.waitByModifyFileName = waitByModifyFileName;
		this.appendline=appendline;
		add();
	}



	

	public void add() {
	
		
		
		List addedLines =new ArrayList();
	
		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);


		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

		
	     
			
			content += line + "\n";
			
			  if (appendline==i){
			    	 
			    	String m="";
			    	m+="	AlertDialog.Builder builder = new AlertDialog.Builder(this);\n";
			    	m+="		builder.setTitle(\"title\");\n";
			    	m+="		builder.setMessage(\"msg\").setCancelable(false);\n";
			    	m+="		builder.setPositiveButton(\"确定\",\n";
			    	m+="				new DialogInterface.OnClickListener() {\n";
			    	m+="					public void onClick(DialogInterface dialog, int id) {\n";
			    	m+="						\n";
			    	m+="					}\n";
			    	m+="				});\n";
			    	m+="		builder.setNegativeButton(\"取消\",\n";
			    	m+="				new DialogInterface.OnClickListener() {\n";
			    	m+="					public void onClick(DialogInterface dialog, int id) {\n";
			    	m+="						dialog.cancel();\n";

			    	m+="					}\n";
			    	m+="				});\n";
			    	m+="		builder.show();\n";
			    	
					content += m;
				}
			
			

		}

	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}
}
