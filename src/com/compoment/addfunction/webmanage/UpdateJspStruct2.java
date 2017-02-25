package com.compoment.addfunction.webmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.KeyValue;

public class UpdateJspStruct2 {

	public UpdateJspStruct2(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			updateJspStruct2(interfaceBean, "Respond");
		}
	}
	
	public void updateJspStruct2(InterfaceBean interfaceBean ,String type)

	{
		String m="";
		m+="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\n";
		m+="<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
		m+="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<title>"+interfaceBean.title+"</title>\n";
		m+="<script src=\"/kindeditor/kindeditor.js\" type=\"text/javascript\"></script>\n";
		m+="<script src=\"/kindeditor/lang/zh_CN.js\" type=\"text/javascript\"></script>\n";
		m+="<script type=\"text/javascript\" src=\"/chinapost/weixin/lovepackage/js/jquery-1.8.3.js\"></script>\n";
		m+="<script type=\"text/javascript\" language=\"javascript\" src=\"/js/My97DatePicker/WdatePicker.js\"></script>\n";
		m+="<script type=\"text/javascript\">\n";

		m+="var editor;\n";
		m+="KindEditor.ready(function(K) {\n";
		m+="editor = K.create('#rule', {\n";
		m+="resizeType : 1,\n";
		m+="allowPreviewEmoticons : false,\n";
		m+="allowImageUpload : true,\n";
		m+="afterBlur:function(){this.sync();},   //关键  同步KindEditor的值到textarea文本框   解决了多个editor的取值问题\n";
		m+="uploadJson : '/kindeditor/jsp/upload_json.jsp',\n";
		m+="fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',\n";

		m+="items : [\n";
		m+="'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',\n";
		m+="'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',\n";
		m+="'insertunorderedlist', '|', ,'media' ,'link']\n";
		m+="});\n";
		m+="});\n";

		m+="$(document).on('ready', function() {\n";
		m+="	\n";
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
						m+="	var "+row.enName.toLowerCase()+"='${entity."+row.enName.toLowerCase()+"}';\n";
						m+="	$(\"#"+row.enName.toLowerCase()+"\").val("+row.enName.toLowerCase()+");\n";
					
					}
					i++;
				}
			}

		}
		
		m+="	\n";
		m+="});\n";
		
		
		m+="	function doSave() {\n";
		m+="	  \n";
		
	
	
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						
						
						m+="		if ($(\"#"+row.enName.toLowerCase()+"\").val() == \"\") {\n";
						m+="			alert(\"请输入"+row.cnName+"！\");\n";
						m+="			return false;\n";
						m+="		}\n";
						
						if(row.cnName.contains("开始时间"))
						{
							m+="		if($(\"#end\").val()!=\"\" && $(\"#"+row.enName.toLowerCase()+"\").val() > $(\"#end\").val()){\n";
							m+="				alert(\"开始时间不能大于结束时间\");\n";
							m+="				return ;\n";
							m+="		}\n";
						}
						
						if(row.type.toLowerCase().contains("int")||row.type.contains("整形")||row.type.contains("整数"))
						{
							m+="		var "+row.enName.toLowerCase()+"=$('#"+row.enName.toLowerCase()+"').val();\n";
							m+="		if("+row.enName.toLowerCase()+"==\"\" || !/^\\d+$/.test("+row.enName.toLowerCase()+")){  \n";
							m+="	        alert(\"每个用户帮抢次数必须是正整数!\"); \n";
							m+="	        return false;\n";
							m+="	    }  \n";
							m+="		\n";
						}
					}
					i++;
				}
			}

		}
		


		m+="		 if(editor.count('text')>2000){\n";
		m+="             alert('活动规则字数超过限制');\n";
		m+="             return;\n";
		m+="         }\n";
		m+="		 myForm.submit();\n";
		m+="	}\n";
		m+="	 \n";
		m+="</script>\n";
		m+="</head>\n";
		m+="<body>\n";
		m+="	<%@ include file=\"/chinapost/weixin/lovepackage/include.jsp\"%>\n";

		m+=" <form action=\"/chinapost/....Action!doUpdate.do\" method=\"post\" enctype=\"multipart/form-data\" name=\"myForm\">\n";
		m+="	<div style=\"margin-left: 20px;\">"+interfaceBean.title+"</div>\n";
		m+="	<div class=\"table_form lr10\">\n";
		m+="		<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n";
		m+="			<tbody>\n";
		

		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
						
						m+="	<input type=\"hidden\" id=\""+row.enName.toLowerCase()+"\" name=\"entity."+row.enName.toLowerCase()+"\" value=\"${ entity."+row.enName.toLowerCase()+"}\" />\n";
						
					}
					i++;
				}
			}

		}
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
						
						m+="	<input type=\"hidden\" id=\""+row.enName.toLowerCase()+"\" name=\"entity."+row.enName.toLowerCase()+"\" value=\"${ entity."+row.enName.toLowerCase()+"}\" />\n";
						
					}
					i++;
				}
			}

		}
		
	
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					

						if(row.type.toLowerCase().equals("boolean")||row.type.toLowerCase().equals("bool"))
						{
							m+="				 <tr>\n";
							m+="					<td align=\"right\" style=\"width: 120px\"><font color=\"red\">*</font>"+row.cnName.toLowerCase()+"：</td>\n";
							m+="					<td><select id=\""+row.enName.toLowerCase()+"\" name=\"entity."+row.enName.toLowerCase()+"\"><option value=\"1\">是</option>\n";
							m+="					<option value=\"0\">否</option></select></td>\n";
							m+="					\n";
							m+="				</tr>\n";
						}else if(row.cnName.contains("时间"))
						{
							
							m+="				<tr>\n";
							m+="					<td align=\"right\" style=\"width: 120px\"><font color=\"red\">*</font>"+row.cnName.toLowerCase()+"：</td>\n";
							m+="					<td><input id=\""+row.enName.toLowerCase()+"\" name=\"entity."+row.enName.toLowerCase()+"\" value=\"${ entity."+row.enName.toLowerCase()+"}\" style=\"margin-right:10px;width: 150px\" class=\"Wdate\" \n";
							m+="					 onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})\"/></td>\n";
							m+="				</tr>\n";
						}
						else if(row.type.toLowerCase().equals("img"))
						{
							
							m+="					<tr >\n";
							m+="						<td align=\"right\" style=\"width: 120px\">"+row.cnName.toLowerCase()+"：\n";
							m+="						</td>\n";
							m+="						<td>\n";
							m+="						<img src=\"${activityInfoExt.qrcode }\" style=\"display: block;width:40px;height:30px\"/>\n";
							m+="						    <input type=\"hidden\" id=\""+row.enName.toLowerCase()+"\" name=\"entity."+row.enName.toLowerCase()+"\"  value=\"${entity."+row.enName.toLowerCase()+" }\"/>\n";
							m+="							<input type=\"file\" id=\"file4\" name=\"file4\"  />\n";
							m+="								\n";
							m+="						</td>\n";
							m+="						\n";
							m+="					</tr>\n";
						}
						else
						{
						
							m+="				<tr>\n";
							m+="					<td align=\"right\" style=\"width: 120px\"><font color=\"red\">*</font>"+row.cnName.toLowerCase()+"：</td>\n";
							m+="					<td><input type=\"text\" class=\"input-text wid400 bg\"\n";
							m+="						id=\""+row.enName.toLowerCase()+"\" name=\"entity."+row.enName.toLowerCase()+"\" value=\"${ entity."+row.enName.toLowerCase()+"}\"/></td>\n";
							m+="					\n";
							m+="				</tr>\n";
							
						}
						
					}
					i++;
				}
			}

		}
		


	
		m+="				<tr height=\"60px\">\n";
		m+="					<td align=\"right\" style=\"width: 120px\"></td>\n";
		m+="					<td><input type=\"button\" value=\"保存\" name=\"btn\"\n";
		m+="						onmouseover=\"this.style.cursor='hand'\" class=\"subBtn\"\n";
		m+="						onclick=\"doSave()\"> <input type=\"button\" value=\"返回\"\n";
		m+="						name=\"btn2\" onmouseover=\"this.style.cursor='hand'\" class=\"subBtn\"\n";
		m+="						onclick=\"history.go(-1)\">\n";
		m+="				</tr>\n";
		m+="			</tbody>\n";
		m+="		</table>\n";
		m+="	</div>\n";
		m+="	</form>\n";
		m+="</body>\n";
		m+="</html>\n";
		makeFile( interfaceBean.enName+"AddJsp",m);
		System.out.println(m);
		
	}
	
	public void makeFile(String fileName,String s)
	{
		try {
		String doc=KeyValue.readCache("docPath");
		int p=doc.lastIndexOf("/");
		if(p==-1)
		{
			 p=doc.lastIndexOf("\\");
		}
		
		
		      
		    
		
		File tofile=new File(doc.substring(0, p)+"/java/"+fileName+".jsp");
		  if(! tofile.exists()) {  
	            makeDir(tofile.getParentFile());  
	        }  
	      
		  tofile.createNewFile(); 
		
		FileWriter fw;
		
			fw = new FileWriter(tofile);
			BufferedWriter buffw=new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(buffw);
		
		

	
		pw.println(s);

		pw.close();
		buffw.close();
		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  public  void makeDir(File dir) {  
	        if(! dir.getParentFile().exists()) {  
	            makeDir(dir.getParentFile());  
	        }  
	        dir.mkdir();  
	    }  
}