package com.compoment.addfunction.webmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.KeyValue;

public class QueryJspStruct2 {

	
	public  QueryJspStruct2(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			queryjspStruct2(interfaceBean, "Respond");
		}
	}
	
	

	public void queryjspStruct2(InterfaceBean interfaceBean,String type)
	{
		String inKeyString="";
		String indataKeyString="";
		String ajaxdataKeyString="";
		String urlKeyString="";
		String requestKeyString="";
		String updateKeyString="";
		String deleteKeyString="";
		
		String cacheValueHiddenString="";
		
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
						deleteKeyString+="'+data[i]."+row.enName.toLowerCase()+"+',";
						updateKeyString+=row.enName.toLowerCase()+"='+data[i]."+row.enName.toLowerCase()+"+'&";
								
						indataKeyString+=row.enName.toLowerCase()+":m"+row.enName.toLowerCase()+",\n";
						ajaxdataKeyString+=row.enName.toLowerCase()+":"+"$(\"#"+row.enName.toLowerCase()+"\").val(),\n";
						inKeyString+="m"+row.enName.toLowerCase()+",";
						urlKeyString+=""+row.enName.toLowerCase()+"=${"+row.enName.toLowerCase()+"}&";
						
						requestKeyString+="			var "+row.enName.toLowerCase()+"=\"${"+row.enName.toLowerCase()+"}\";\n";
						requestKeyString+="			$(\"#"+row.enName.toLowerCase()+"\").val("+row.enName.toLowerCase()+");\n";
						
						
						cacheValueHiddenString+="	<input type=\"hidden\" id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"\" />\n";
					}
				}
			}
			}
		inKeyString=inKeyString.substring(0, inKeyString.lastIndexOf(","));
		indataKeyString=indataKeyString.substring(0, indataKeyString.lastIndexOf(","));
		ajaxdataKeyString=ajaxdataKeyString.substring(0, ajaxdataKeyString.lastIndexOf(","));
		urlKeyString=urlKeyString.substring(0, urlKeyString.lastIndexOf("&"));
		updateKeyString=updateKeyString.substring(0, updateKeyString.lastIndexOf("&"));
		deleteKeyString=deleteKeyString.substring(0, deleteKeyString.lastIndexOf(","));
		
		
		
		String m="";
		m+="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\n";
		m+="<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
		m+="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<title>"+interfaceBean.title+"</title>\n";
		m+="<script\n";
		m+="	type=\"text/javascript\" src=\"/chinapost/weixin/lovepackage/js/jquery-1.8.3.min.js\"></script>\n";
		m+="	<script>\n";
		m+="		function toAdd(){\n";
		m+="			window.location.href=\"/chinapost/"+interfaceBean.enName+"Action!toUpdate.do?"+urlKeyString+"\";\n";
		m+="		}\n";
		m+="		\n";
		
		m+="		\n";
		m+="		$(document).on('ready', function() {\n";
		m+=requestKeyString;

		m+="			getAll('/chinapost/"+interfaceBean.enName+"Action!list.do');\n";
		m+="		});\n";
		m+="		\n";
		m+="		\n";
		m+="		function search(){\n";
		m+="			getAll('/chinapost/"+interfaceBean.enName+"Action!list.do');\n";
		m+="		}\n";
		m+="		\n";
		
		
		
		
		
		m+="		function dodel("+inKeyString+"){\n";
		m+="			var r=confirm(\"确定要删除吗？\");\n";
		m+="			if(r){\n";
		m+="				$.ajax({\n";
		m+="					type:'POST',\n";
		m+="					url:'/chinapost/"+interfaceBean.enName+"Action!doDelete.do',\n";
		m+="					data:{\n";
		
		
		m+=indataKeyString;
	
		
		m+="					\n},\n";
		m+="					success:function(k){\n";
		m+="							alert(\"删除成功！\")\n";
		m+="							window.location.href = \"/chinapost/"+interfaceBean.enName+"Action!index.do?"+urlKeyString+"\";\n";
		m+="					},\n";
		m+="					error : function() {\n";
		m+="						alert(\"对不起，系统错误，请稍候重试！\")\n";
		m+="					}\n";
		m+="				});\n";
		m+="			}\n";
		m+="			\n";
		m+="			\n";
		m+="		}\n";
		m+="		function getAll(tzurl){\n";
		
	
		m+="				$.ajax({\n";
		m+="					type:'POST',\n";
		m+="					dataType:'json',\n";
		m+="					url:tzurl,\n";
		m+="					data:{\n";
		m+=ajaxdataKeyString;
	
		m+="					},\n";
		m+="					success:function(result){\n";
		m+="	\n";
		m+="							var divtext = '';\n";
		m+="							var data =result.list;\n";
		m+="							var pagenational = result.pageString;\n";
		m+="						\n";
		m+="							for(var i=0;i<data.length;i++){\n";
		m+="								divtext += '<tr class=\"even\" style=\"white-space:nowrap; overflow:hidden; text-align:center\">';\n";
		
		
	
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
					if(row.remarks.toLowerCase().contains("key"))
					{
						
					}else
					{
						
						if(row.type.toLowerCase().equals("bool")||row.type.toLowerCase().equals("boolean"))
						{
							m+="								if(data[i]."+row.enName.toLowerCase()+"=='01'){\n";
							m+="									divtext +=  '"+row.cnName.replaceAll("", "")+"' ;\n";
							m+="								}else if(data[i]."+row.enName.toLowerCase()+"=='02'){\n";
							m+="									divtext +=  '"+row.cnName.replaceAll("", "")+"' ;\n";
							m+="								}\n";
							m+="								divtext += '</td>';\n";
							
						}else
						{
					
						m+="	 divtext += '<td>' + data[i]."+row.enName.toLowerCase()+" + '</td>';\n";
						
						}
					}
					
					i++;
				}
			}

		}
		
		


		m+="								divtext += '<td ><a href=\"/chinapost/"+interfaceBean.enName+"Action!toUpdate.do?"+updateKeyString+"\"> [修改] </a>'\n";
		m+="								divtext +='|<a href=\"#\" onclick=\"dodel("+deleteKeyString+")\"> [删除] </a></td>';\n";
		m+="								divtext += '</tr>';\n";
		m+="							}\n";
		m+="							//divtext += pagenational;\n";
		m+="							$(\"#newtable tbody\").html(divtext);\n";
		m+="							$(\"#pageContent\").html(pagenational);\n";

		m+="					}\n";
		m+="				});\n";
		m+="		}\n";
		m+="	</script>\n";

		m+="</head>\n";
		m+="<body>\n";
		m+="	<%@ include file=\"/chinapost/weixin/lovepackage/include.jsp\"%>\n";
		m+="	<div style=\"padding-left:20px;margin-bottom:10px;\" >\n";
		
		
		m+=cacheValueHiddenString;
		
		
		m+="	活动名称：<input type=\"text\" id=\"activityName\" style=\"margin-left:10px;width:100px;height:20px; \"/>\n";
		m+="	<input type=\"button\" value=\"查询\" name = \"btn_search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\" class=\"subBtn\" onclick=\"search()\">\n";
		m+="	<input type=\"button\" value=\"新增\" name = \"btn_search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\" class=\"subBtn\" onclick=\"toAdd()\">\n";
	
		m+="	\n";
		m+="	</div>\n";
		m+="	<div id=\"signContent\">\n";
		m+="	  <div class=\"table-list lr10\">\n";
		m+="	      <table width=\"100%\">\n";
		m+="	      <tr>\n";
		m+="	       <td>\n";
		m+="	       <div>\n";
		m+="	       <img src=\"/chinapost/weixin/images/ggk.jpg\" style=\"width:250px;display:block;margin:0 auto;\"/>\n";
		m+="	       </div>\n";
		m+="	       \n";
		m+="	       \n";
		m+="	          <div><p>活动玩法：</p>\n";
		m+="<p>·添加砍价活动，关联砍价商品，设置砍价刀数；</p>\n";
		m+="<p>·微信端查询出砍价列表，选择任一产品，参加砍价；</p>\n";
		m+="<p>·分享给好友，帮忙砍价；</p>\n";
		m+="<p>·砍到底价，下单，支付，购买成功，等待发货；</p>\n";
		m+="	          </div></td>\n";
		m+="	        <td style=\"vertical-align: top;\">\n";
		m+="	        <table id=\"newtable\" width=\"100%\">\n";
		m+="	          <thead class=trhead id=\"tblHeader\">\n";
		m+="	            \n";
		m+="				<tr> ";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
					if(row.remarks.toLowerCase().contains("key"))
					{
						
					}else
					{
						m+="				<th  style=\"text-align:center;\">"+row.cnName.replaceAll("", "")+"</th>\n";
						
					}
					i++;
				}
			}

		}
	
		
		
		m+="				<th  style=\"text-align:center;\">操作</th></tr>\n";
		m+="				</thead>\n";
		m+="				<tbody id=\"records\">\n";
		m+="			    </tbody>\n";
		m+="		</table>\n";
		m+="	       </td>\n";
		m+="	      </tr>\n";
		m+="	      </table>\n";
		m+="	</div>\n";
		m+="	<div id=\"pageContent\"></div>\n";
		m+="  </div>\n";
		m+="	\n";
		m+="</body>\n";
		m+="</html>\n";

		
		
		

		
		
		
		makeFile( interfaceBean.enName.toLowerCase(),m);
		System.out.println(m);
	}
	
	

	
	
	
	
	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long")||type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
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




