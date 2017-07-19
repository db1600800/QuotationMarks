
package com.compoment.cut.web;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import org.apache.poi.poifs.property.Child;

import com.compoment.cut.CompomentBean;
import com.compoment.ui.ios.creater.ScrollViewCells;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class WebJspListViewItem {


	String bodym = "\n\n\n";
	String js="";

	String pageName = "";
	String className = "";
	



	public  WebJspListViewItem(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);

		  analyse(oldBeans);

	

	}
	
	
public void analyse(List<CompomentBean> oldBeans) {
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : oldBeans) {
			if (bean.type.contains("Layout")) {
				if (bean.w >= maxW) {
					maxW = bean.w;
					maxBean = bean;
				}

				if (bean.h >= maxH) {
					maxH = bean.h;
					maxBean = bean;
				}

				layouts.add(bean);
			}
		}
		
		
		parent(maxBean);
	
		
  
		 jsString();
		
		String lines = FileUtil.fileContent(KeyValue.readCache("projectPath")+"/src/web/"+className+"Jsp.jsp");
		lines=lines.replace("<script type=\"text/javascript\"  id=\"myJs\">\n", "<script type=\"text/javascript\"  id=\"myJs\">\n"+js+"\n\n");
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web", className + "Jsp", "jsp", lines);
		
		System.out.println(lines);
	}

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






	public void parent(CompomentBean bean) {

		Collections.sort(bean.chirlds, comparatorDate);

		// 有 儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			for (CompomentBean chirld : bean.chirlds) {

				// 这个儿子是容器 layout
				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					// visibility:hidden; display:none; height: 42px;
					// line-height: 42px; background-color: #fd5f28; font-size:
					// 18px; color: #fff; text-align: center; z-index: 2;
					// position: relative; width: 100%; position:fixed; top:0;
					// left:0
					
					
					

					String start = "";
					String end = "";
					if (chirld.compomentForWeb.equals("table")) {
						start = "  html+='<" + chirld.compomentForWeb+" style=\"width:100%;\">'\n";

						end = "  html+='</" + chirld.compomentForWeb + ">'\n";
					} else if (chirld.compomentForWeb.equals("tr")) {
						start = "  html+='<" + chirld.compomentForWeb + ">'\n";

						end = " html+='</" + chirld.compomentForWeb + ">'\n";
					} else if (chirld.compomentForWeb.equals("ul")) {
						start = "  html+='<" + chirld.compomentForWeb + " style=\"margin: 0px;padding:0px;\">'\n";

						end = "  html+='</" + chirld.compomentForWeb + ">'\n";
					} else if (chirld.compomentForWeb.equals("li")) {
						start = "  html+='<" + chirld.compomentForWeb + " style=\" list-style: none  ; padding: 10px; border-bottom: 1px solid #f5f5f5; color: #666;\" >'\n";

						end = "  html+='</" + chirld.compomentForWeb + ">'\n";
					} else {
						start = "html+='<div style=\" height:"+chirld.h+"px; line-height: " + chirld.h + "px; text-align: center; left:0; background-color:" + chirld.bgRgb16+"\" >'\n";


						end = "  html+='</div>'\n";
					}

					bodym += start;

					parent(chirld);

					bodym += end;
				} else {// 这个儿子是非容器

					if (bean.compomentForWeb.equals("tr")) {
						bodym += "  html+='<td style=\"width:"+((float)1/(float)bean.chirlds.size())*100+"%; text-align: center;\">'\n";
					}

					chirld(chirld, bean);

					if (bean.compomentForWeb.equals("tr")) {
						bodym += "  html+='</td>'\n";
					}
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {// 这个儿子是非容器

		if (chirld.type.equals("TextView")) {

			bodym += " html+='<span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" style=\""
					+ chirld.relativeForWeb + "  font-size: " + chirld.textSize + "px; color: " + chirld.rgb16
					+ ";  \">" + chirld.cnname + "</span>'\n";

		}

		if (chirld.type.equals("Line")) {

		}

		if (chirld.type.equals("Spinner")) {

			// <span style="background:url(../images/next.png) no-repeat right
			// center #fff; background-size:8px; line-height:20px;
			// display:block; float:left; padding:3px 15px 3px 5px;
			// margin-right:2px; margin-bottom:5px" onClick="selectCount();"
			// id="count` `Name">请选择</span>

			bodym += "html+='<div id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" onclick=\""
					+ chirld.actionString + "\" style=\" font-size: " + chirld.textSize + "px; color: " + chirld.rgb16
					+ ";" + chirld.relativeForWeb + " \">"

					+ "" + chirld.cnname + "" + "<img  src= \"/images/" + chirld.picName
					+ ".png\"  style=\" width: 12px; \">" + "</div>'\n";
		}

		if (chirld.type.equals("Button")) {

			if (chirld.picName.equals("图片名")) {

				bodym += "html+='<a href=\"#\" id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\"  onclick=\""
						+ chirld.actionString + "\"  style=\"text-align: center; height:" + chirld.h
						+ "px; line-height: " + chirld.h + "px; border-radius: 8px; color:" + chirld.rgb16
						+ "; background-color:" + chirld.bgRgb16 + ";  font-size: " + chirld.textSize + "px ;"
						+ chirld.relativeForWeb + " margin:1px;  padding: 1px; text-decoration: none;\" >" + chirld.cnname + "</a>' \n";
				

			} else {

				bodym += "html+='<img id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" src= \"/images/"
						+ chirld.picName + ".png\" onclick=\"" + chirld.actionString + ";\" style=\" width: 12px; "
						+ chirld.relativeForWeb + "\">'\n";
			}

		}

		if (chirld.type.equals("CheckBox")) {

			js +="//checkbox \n";
			js += "var "+chirld.enname+"Bool = false;\n";
			js += "function set"+chirld.enname+"Bool(){\n";
			js += "	if("+chirld.enname+"Bool){\n";
			js += "	"+chirld.enname+"Bool = false;\n";
			js += "	$(\"#"+chirld.enname+"\").attr(\"style\",\""+chirld.relativeForWeb +" background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;\");\n";
			js += "}else{\n";
			js += "	"+chirld.enname+"Bool = true;\n";
			js += "	$(\"#"+chirld.enname+"\").attr(\"style\",\" "+ chirld.relativeForWeb +" background: url(images/checked.png) no-repeat 2px center;  padding-left: 35px; padding-top:0px; padding-bottom:0px;\");\n";
			js += "}\n";
			js += "}\n\n";

			
			bodym += "html+='<span id=\""+chirld.enname+"\" style=\" "+ chirld.relativeForWeb +" background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;\"  onclick=\"setDefaultAddr()\">"+chirld.cnname+"</span>'\n";

			
		}

		if (chirld.type.equals("EditText")) {

			
			bodym += " html+='<input style=\" border: 0; line-height: "+chirld.h+"px; height: "+chirld.h+"px;  font-size: "+chirld.textSize+"px;\"  type=\"text\"  id=\""+chirld.enname+"\" name=\""+chirld.enname+"\" placeholder=\""+chirld.cnname+"\">'\n";

		}

		if (chirld.type.equals("ImageView")) {

			
		
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}
	
	
	
	public void jsString()
	{
		
		js+="\n\n//列表 \n";
		js+="			var stop=false;\n";
		js+="			$(window).scroll(function(){\n";
		js+="			    var totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());\n";
		js+="			    if($(document).height() <= totalheight){\n";
		js+="			        if(stop==true){\n";
		js+="			            stop=false;\n";
		js+="			            query_ajx(1);//下一页\n";
		js+="			        }\n";
		js+="			    }\n";
		js+="			});\n";

		js+="			$(document).ready(function() {\n";
		js+="				//查询\n";
		js+="				query_ajx(0);//首页\n";
		js+="			});\n";

		js+="			var currentCount = 0; //当前页数量\n";
		js+="			var only_one_sub = true; //防重复提交\n";
		js+="			function query_ajx(flg){//0首页  1下一页 \n";
		js+="				  \n";
		js+="			    if(!only_one_sub){\n";
		js+="			        return;//防重复提交\n";
		js+="			    }  \n";
		js+="			    only_one_sub = false;//防重复提交\n";
		js+="			   \n";
		js+="			    if(flg == 0){//0首页  1下一页 \n";
		js+="			    	$(\"#page_code\").val(1);			    	\n";
		js+="			    }		    \n";
		js+="			   \n";
		js+="			    currentCount = 0;\n";

		js+="			    var flag = $(\"#flag\").val();\n";
		js+="			    var my_msg = show_hide_msg(\"页面加载中...\");\n";
		js+="			    $(\"#emptyOrErrorMsg\").html(\"\");\n";
		js+="			    $.ajax({  \n";
		js+="			        url:'/companyname/projectname/"+className+"Action!queryAddressList.do?',  \n";
		js+="			        data: { \"page_code\": $(\"#page_code\").val()} , //请求参数 \n";
		js+="			        type:\"POST\",\n";
		js+="			        cache:false, \n";
		js+="			        dataType:'json', //预期服务器返回的数据类型  \n";
		js+="			        error: function() {\n";
		js+="			        	only_one_sub = true;  ////防重复提交\n";
		js+="			        	alert_msg(\"查询出错了!!!\"); \n";
		js+="			   			$(\"#listSpace\").html(\"\"); \n";
		js+="			   			$(\"#emptyOrErrorMsg\").html(\"\");\n";
		js+="			   			my_msg.close();\n";
		js+="			   			currentCount = 0;\n";
		js+="			        },  \n";
		js+="			        success:function(data){\n";
		js+="			          my_msg.close();\n";
		js+="			        	if (data != null){\n";
		js+="			        		var msg = data.errorMsg;//data为map  errorMsg为key\n";
		js+="			     		    if(msg == \"success\"){\n";
		js+="			     		    	\n";
		js+="			         		    var dataList = data.dataList;\n";
		js+="			         		    currentCount = dataList.length;\n";

		js+="				        		if(flg == 0){//0首页  1下一页 \n";
		js+="				        			 $(\"#listSpace\").html(\"\");				    	\n";
		js+="				     		    }\n";
		js+="				            	var html = \"\";\n";
		js+="								for(var i=0;i<listData.length;i++){\n";
		js+=bodym+";\n";
		
		
		
		js+="								}\n";

		js+="								$(\"#page_code\").val(Number($(\"#page_code\").val()) + 1);		\n";
		js+="				\n";
		js+="								if(dataList.length >= 10){\n";
		js+="					        		stop = true;//到底部时加载判断\n";
		js+="					        	} \n";
		js+="					        	if(dataList.length == 0){\n";
		js+="			          				$(\"#emptyOrErrorMsg\").html('<br><br><br><br><div style=\"text-align:center;\">暂无地址信息</div>');\n";
		js+="				            	}\n";
		js+="					        	$(html).appendTo($(\"#listSpace\"));\n";
		js+="				        		\n";
		js+="						   		only_one_sub = true;  ////防重复提交\n";
		js+="				        	}else{\n";
		js+="				        		only_one_sub = true;  ////防重复提交\n";
		js+="				       			$(\"#listSpace\").html(\"\");  \n";
		js+="				       			alert_msg(msg);\n";
		js+="				       			$(\"#emptyOrErrorMsg\").html(\"\");\n";
		js+="				        	}\n";
		js+="			        	}\n";
		js+="			        }  \n";
		js+="			    });\n";
		js+="			}\n";
	}

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorY = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按y排
			if (s1.y != s2.y) {
				return s1.y - s2.y;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorX = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按X排
			if (s1.x != s2.x) {
				return s1.x - s2.x;
			}
			return 0;
		}
	};

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String genID(int length) // 参数为返回随机数的长度
	{
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	public static String id() {

		return genID(3) + "-" + genID(2) + "-" + genID(3);
	}

	//
	// <html>
	// <body>
	// <div style=" width:100%;height:100%;position:absolute; left:0; top:0; " >
	//
	// <div style=" width:100%; height:37px; background-color:#FC5F28; ">
	//
	// <span style=" margin-left:38px; " >新油品</span>
	//
	//
	//
	// </div>
	//
	// <div style=" width:100%; height:37px; background-color:#FC5F28; ">
	//
	// <span style=" margin-left:38px; " >新油品</span>
	//
	//
	//
	// </div>
	//
	// </div>
	// </body>
	// </html>
}
