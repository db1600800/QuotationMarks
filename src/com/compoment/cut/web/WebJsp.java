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

public class WebJsp {

	String bodym = "\n\n\n";
	String connection = "";
	String pageName = "";
	String className = "";
	String formmStart="";
	String formmEnd="";

    int rootViewWidth=320;
    int rootViewHeight=568;
	public WebJsp(int cellWidth,int cellHeight) {
		rootViewWidth=cellWidth;
		rootViewHeight=cellHeight;
	}
	
	public WebJsp(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);

		String body = analyse(oldBeans);

		String m = "";
		m+="<%@ page language=\"java\" import=\"java.util.*\" contentType=\"text/html;charset=UTF-8\" isELIgnored=\"false\" %>\n";
		m+="<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>   \n";
		m+="<%@page import=\"java.util.*\"%>\n";

		m+="<!DOCTYPE HTML>\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<meta charset='utf-8'>\n";
		m+="<meta name=\"viewport\" content=\"width=device-width,initial-scale=1, maximum-scale=1, \">\n";
		m+="<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n";
		m+="<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		m+="<title>新增地址</title>\n";
		m+="<link href=\"/chinapost/jiyouwx/css/css.css\" rel=\"stylesheet\" type=\"text/css\">\n";
		m+="<script type=\"text/javascript\" src=\"/chinapost/jiyouwx/js/jquery-1.10.1.min.js\"></script>\n";
		m+="<script type=\"text/javascript\" src=\"/chinapost/jiyouwx/js/swiper-2.1.min.js\"></script>\n";
		
		
		
		m+="<script>\n";

		m+="	var click_once = true;\n";
		
		
		m+="	function save(){	\n";
		m+="		  var name = $(\"#username\").val();\n";
		m+="		  name = name.replace(/\s/ig,'');\n";
		m+="		  if(name == \"\"){\n";
		m+="			   auto_msg(\"请输入姓名！\");\n";
		m+="			   return;\n";
		m+="		  }\n";

		m+="		  var phone = $(\"#phone\").val();\n";
		m+="		  phone = phone.replace(/\s/ig,'');\n";
		m+="		  if(phone == \"\"){\n";
		m+="			   auto_msg(\"请输入手机号码！\");\n";
		m+="			   return;\n";
		m+="		  }\n";

		m+="		  if(!/^1\d{10}$/g.test(phone) || phone.length != 11){\n";
		m+="			    auto_msg(\"手机号码格式不正确!\");  \n";
		m+="		       	return false;\n";
		m+="	      }\n";

		m+="		  var province = $(\"#province\").val();\n";
		m+="		  if(province == \"-1\"){\n";
		m+="			   auto_msg(\"请选择省！\");\n";
		m+="			   return;\n";
		m+="		  }\n";

		m+="		  var city = $(\"#city\").val();\n";
		m+="		  if(cityLength > 0 && city == \"-1\"){\n";
		m+="			   auto_msg(\"请选择市！\");\n";
		m+="			   return;\n";
		m+="		  }\n";

		m+="		  var count = $(\"#count\").val();\n";
		m+="		  if(countLength >1 && count == \"-1\"){\n";
		m+="			   auto_msg(\"请选择区/县！\");\n";
		m+="			   return;\n";
		m+="		  }\n";

		m+="		  var address = $(\"#address\").val();\n";
		m+="		  address = address.replace(/\s/ig,'');\n";
		m+="		  if(address == \"\"){\n";
		m+="			   auto_msg(\"请输入详细地址！\");\n";
		m+="			   return;\n";
		m+="		  }\n";

		m+="		  var postcode = $(\"#postcode\").val();\n";
		m+="		  postcode = postcode.replace(/\s/ig,'');\n";
		m+="		  if(postcode == \"\"){\n";
		m+="			   auto_msg(\"请输入邮编！\");\n";
		m+="			   return;\n";
		m+="		  }\n";
		m+="		  if(postcode != \"\" ){\n";
		m+="			  if(postcode.length !=6 || !/\d{6}$/g.test(postcode) ){\n";
		m+="				 auto_msg(\"邮政编码格式不正确！\");\n";
		m+="			     return;\n";
		m+="			  }\n";
		m+="		  }\n";

		m+="		  if(click_once == false){\n";
		m+="			   return;\n";
		m+="		  }\n";
		m+="		  click_once = false;\n";

		m+="		  var checked = \"\";\n";
		m+="		  if(setAddr){\n";
		m+="			  checked = \"0\";\n";
		m+="		  }\n";
		m+="	  var my_msg = show_hide_msg(\"数据提交中，请稍候...\");\n";
		m+="		  var myform = document.forms[0]; \n";
		m+="		  myform.action='/jiyou/wx/AddressAction!saveAddress.do?checked='+checked;  \n";
		m+="		  myform.submit();\n";
		m+="		my_msg = null;\n";
		m+="	}\n";


		m+="	var setAddr = false;\n";
		m+="	function setDefaultAddr(){\n";
		m+="		if(setAddr){\n";
		m+="			setAddr = false;\n";
		m+="			$(\"#defAddress\").attr(\"class\",\"check05\");\n";
		m+="		}else{\n";
		m+="			setAddr = true;\n";
		m+="			$(\"#defAddress\").attr(\"class\",\"checked05\");\n";
		m+="		}\n";
		m+="	}\n";


		m+="	var cityLength = 0;\n";
		m+="	var countLength = 0;\n";
		m+="	function selectArea(code){\n";
		m+="		\n";
		m+="		 var province = $(\"#province\").val();\n";
		m+="		 var city = $(\"#city\").val();\n";
		m+="		\n";
		m+="	     var urlAddr = '';\n";
		m+="	     if(code == \"city\"){\n";
		m+="	    	 urlAddr = '/jiyou/wx/AddressAction!queryCity.do?province='+province;\n";
		m+="	     }\n";
		m+="	     \n";
		m+="		 if((code == \"count\")){\n";
		m+="	    	 urlAddr = \"/jiyou/wx/AddressAction!queryCount.do?city=\"+city;\n";
		m+="	     }\n";
		m+="		 \n";
		m+="	     $.ajax({ \n";
		m+="		    	url: urlAddr,\n";
		m+="		        type: \"POST\",\n";
		m+="		        async: false,\n";
		m+="		        dataType:'json', //预期服务器返回的数据类型        \n";
		m+="		        success:function (data) {\n";
		m+="		 	        var index = 0;\n";
		m+="		 	        var html = '<div style=\"position:fixed;top:30px;right:35px;z-index:10;\" onclick=\"close_tip_div();\"><img src=\"/chinapost/jiyouwx/images/close.jpg\" width=\"30\" height=\"30\"/></div>';\n";
		m+="		 	        html += '<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"sorts\" ><tr>';\n";
		m+="	 			 $.each(data,function (key, value) {\n";
		m+="	    			 index = index + 1 ;\n";
		m+="	    			 html += \"<td><a onclick='show(\\"\"+code+\"Pump\\");selectName(\\"\"+key+\"\\",\\"\" + code + \"\\",\\"\" + value + \"\\")'>\"+value+\"</a></td>\";\n";
		m+="	    			 if(index % 2 == 0){\n";
		m+="	    				 html += \"</tr><tr>\";\n";
		m+="	    			 }\n";
		m+="		    		 });\n";
		m+="				\n";
		m+="				 if(index % 2 == 0){\n";
		m+="					 html += \"</tr></table>\";\n";
		m+="				 }else{\n";
		m+="					 html += \"<td>&nbsp;</td></tr></table>\"; \n";
		m+="				 }\n";
		m+="					 $(\"#\"+code+\"Pump\").append(html); \n";

		m+="				 //更新省市县个数\n";
		m+="				 if(code == \"city\"){\n";
		m+="		        	 cityLength = index;\n";
		m+="		         }\n";
		m+="		         if(code == \"count\"){\n";
		m+="		        	 countLength = index;\n";
		m+="		        	 if(countLength > 0){\n";
		m+="			        	 $(\"#countName\").show();\n";
		m+="		        	 } else {\n";
		m+="			        	 $(\"#countName\").hide();\n";
		m+="		        	 }\n";
		m+="		         }\n";
		m+="				 \n";
		m+="		        },\n";
		m+="		        error:function () {\n";
		m+="					alert_msg(\"系统出错了!\");  \n";
		m+="		        }\n";
		m+="			});\n";
		m+="	    \n";
		m+="	}\n";

		m+="	function selectName(value,code,name){\n";
		m+="	    $(\"#\"+code).val(value);\n";
		m+="	    $(\"#\"+code+\"Name\").text(name);\n";
		m+="	    if(code == \"province\"){\n";
		m+="		    $(\"#cityPump\").html(\"\");\n";
		m+="		    $(\"#city\").val(\"-1\");\n";
		m+="		    $(\"#cityName\").text(\"请选择\");\n";
		m+="		    cityLength = 0;\n";
		m+="	    }\n";

		m+="	    if(code == \"province\" || code == \"city\" ){\n";
		m+="		    $(\"#countPump\").html(\"\");\n";
		m+="		    $(\"#count\").val(\"-1\");\n";
		m+="		    $(\"#countName\").text(\"请选择\");\n";
		m+="		    countLength = 0;\n";
		m+="	    }\n";

		m+="	    if(code == \"province\"){\n";
		m+="	   		selectArea('city');\n";
		m+="	    	selectCity();\n";
		m+="	    }\n";

		m+="	    if(code == \"city\"){\n";
		m+="	    	selectArea('count');\n";
		m+="	    	selectCount();\n";
		m+="	    }\n";
		m+="	}\n";

		m+="	function selectCity(){\n";
		m+="		 var province = $(\"#province\").val();\n";
		m+="		\n";
		m+="		 //当未选择省时，先点击市\n";
		m+="	     if(province == '-1'){\n";
		m+="	    	 auto_msg(\"请先选择省\");\n";
		m+="			 return;\n";
		m+="	     }\n";
		m+="	     if( cityLength > 0){\n";
		m+="	    	 show('cityPump'); \n";
		m+="	     }\n";
		m+="	    \n";
		m+="	}\n";

		m+="	function selectCount(){\n";
		m+="		 var province = $(\"#province\").val();\n";
		m+="		 var city = $(\"#city\").val();\n";
		m+="		\n";
		m+="		 //当未选择省时，先点击市\n";
		m+="	     if(province == '-1'){\n";
		m+="	    	 auto_msg(\"请先选择省\");\n";
		m+="			 return;\n";
		m+="	     }\n";
		m+="	     if(city == '-1'){\n";
		m+="	    	 auto_msg(\"请先选择市\");\n";
		m+="			 return;\n";
		m+="	     }\n";
		m+="	     if(countLength > 0){\n";
		m+="	    	 show('countPump'); \n";
		m+="	     }\n";
		m+="	}\n";
		m+="</script>\n";
		m+="</head>\n";
		m+="<body>\n";
		
		
		m+="<form action=\"\" id=\"myform\"  method=\"post\">\n";
		m+="<header><img src=\"/chinapost/jiyouwx/images/back.png\" onclick=\" history.go(-1);\" class=\"back\">添加地址</header>\n";
		m+="<div class=\"h45\">&nbsp;</div>\n";
		m+="	<ul class=\"box03 ul01 ul02\">\n";
		m+="	<li><strong class=\"red\">*</strong>姓名：\n";
		m+="	  <input type=\"text\" class=\"input01\" id=\"username\" name=\"username\" placeholder=\"请输入姓名\"></li>\n";
		m+="	<li><strong class=\"red\">*</strong>手机：\n";
		m+="	  <input type=\"text\" class=\"input01\" id=\"phone\" name=\"phone\" maxlength=\"11\" pattern=\"[0-9]\" placeholder=\"请输入手机号码\"></li>\n";
		m+="	  \n";
		m+="    <li class=\"area\"><strong class=\"red\">*</strong>地区：\n";
		m+="	    <div class=\"areaBox\"> \n";
		m+="      <span class=\"arrowBg02\" onClick=\"show('provincePump');\" id=\"provinceName\">请选择</span>\n";
		m+="      <span class=\"arrowBg02\" onClick=\"selectCity();\" id=\"cityName\" >请选择</span>\n";
		m+="      <span class=\"arrowBg02\" onClick=\"selectCount();\" id=\"countName\">请选择</span> \n";
		m+="      \n";
		m+="      <input type=\"hidden\" id=\"province\" name=\"province\" value='-1'>\n";
		m+="      <input type=\"hidden\" id=\"city\" name=\"city\" value='-1'>\n";
		m+="      <input type=\"hidden\" id=\"count\" name=\"count\" value='-1'>\n";
		m+="    </div>\n";
		m+="    </li>\n";
		m+="    \n";
		m+="	<li><strong class=\"red\">*</strong>地址：\n";
		m+="	  <input type=\"text\" class=\"input01\" id=\"address\" name=\"address\" placeholder=\"请输入详细地址\">\n";
		m+="	</li>\n";
		m+="	<li><strong class=\"red\">*</strong>邮编：\n";
		m+="	  <input type=\"text\" class=\"input01\" maxlength=\"6\" id=\"postcode\" name=\"postcode\"  pattern=\"[0-9]\" placeholder=\"请输入邮编\">\n";
		m+="	</li>\n";
		m+="	<li ><span id=\"defAddress\" class=\"check05\" onclick=\"setDefaultAddr()\">设为默认地址</span></li>	\n";
		m+="</ul>\n";
		m+="<a href=\"#\" class=\"btn02\" onclick=\"save()\" >保存</a>\n";
		m+="<jsp:include page=\"/chinapost/jiyouwx/global_error.jsp\"></jsp:include>\n";

		m+="  <div class=\"tipBox\">\n";
		m+="  <div class=\"lightBox\" onclick=\"close_tip_div();\"></div>\n";
		m+="  <div class=\"pump\" id=\"provincePump\">\n";
		m+="  	<div style=\"position:fixed;top:30px;right:35px;z-index:10;\" onclick=\"close_tip_div();\"><img src=\"/chinapost/jiyouwx/images/close.jpg\" width=\"30\" height=\"30\"/></div>\n";
		m+="    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"sorts\">\n";
		m+="      <s:iterator value=\"#request.province\" id=\"prov\" status = \"st\"> \n";
		m+="        <s:if test=\"#st.index % 2 == 0\">\n";
		m+="            <tr><td><a onclick='show(\"provincePump\");selectName(\"<s:property value=\"key\"/>\",\"province\",\"<s:property value=\"value\"/>\")'><s:property value=\"value\"/></a></td>\n";
		m+="        </s:if>\n";
		m+="        <s:else>\n";
		m+="           <td><a onclick='show(\"provincePump\");selectName(\"<s:property value=\"key\"/>\",\"province\",\"<s:property value=\"value\"/>\")'><s:property value=\"value\"/></a></td></tr>\n";
		m+="        </s:else>\n";
		m+="	  </s:iterator>\n";
		m+="   </table>\n";
		m+="  </div>\n";
		m+="   <div class=\"pump\" id=\"cityPump\">\n";
		m+="  </div>\n";
		m+="   <div class=\"pump\" id=\"countPump\">\n";
		m+="  </div>\n";
		m+="</div>\n";
		m+="<script>\n";

		m+="function show(tem){\n";
		m+="	if($(\"#\"+tem).hasClass(\"block\")){\n";
		m+="		$(\".pump\").removeClass(\"block\");\n";
		m+="		$(\".lightBox\").removeClass(\"block\");\n";
		m+="	} else{\n";
		m+="		$(\".lightBox\").addClass(\"block\");\n";
		m+="		$(\".pump\").removeClass(\"block\");\n";
		m+="		$(\"#\"+tem).addClass(\"block\");\n";
		m+="	}\n";
		m+="		\n";
		m+="	$(\"#lightBox\").height(window.screen.availHeight);\n";
		m+="	\n";
		m+="	$(document.body).toggleClass(\"html-body-overflow\");\n";
		m+="}\n";


		m+="function close_tip_div(){\n";
		m+="	$(\".tipBox\").children(\"div\").removeClass(\"block\");\n";
		m+="	$(document.body).toggleClass(\"html-body-overflow\");\n";
		m+="}\n";
		m+="</script>\n";
		m+="<input type=\"hidden\" id=\"flag\" name=\"shoppingCar\" value=\"<%=request.getAttribute(\"shoppingCar\") %>\">\n";
		m+="</form>\n";
		m+="</body>\n";
		m+="</html>\n";

		System.out.println(m);

		FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className
				+ "ViewController", "xib", m);

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

	 CompomentBean maxBean = null;

	public  String analyse(List<CompomentBean> oldBeans) {
		// Collections.sort(oldBeans, comparatorDate);

		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();

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
		
		
		// RelativeLayout 儿子们的位置关系
				for (CompomentBean bean : layouts) {
					if (bean.type.equals("RelativeLayout")) {
						if (bean.chirlds != null && bean.chirlds.size() > 1) {

							if (bean.chirlds.size() == 2) {
								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									
									CompomentBean chirld1 = bean.chirlds.get(0);
									CompomentBean chirld2 = bean.chirlds.get(1);
									if (chirld1.x < chirld2.x) {
										chirld1.relativeForWeb += "float:left; margin-left:"+(chirld1.x-bean.x)+"px;  margin-top:"+(chirld1.y-bean.y)+"px;  margin-bottom:"+(chirld1.x-bean.x)+"px; ";
										chirld2.relativeForWeb += "float:right;  margin-top:"+(chirld2.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld2.x-chirld2.w)+"px; margin-bottom:"+(chirld2.x-bean.x)+"px; ";
									} else if (chirld1.x > chirld2.x) {
										chirld2.relativeForWeb += "float:left; margin-left:"+(chirld2.x-bean.x)+"px;  margin-top:"+(chirld2.y-bean.y)+"px;  margin-bottom:"+(chirld2.x-bean.x)+"px; ";
										chirld1.relativeForWeb += "float:right; margin-top:"+(chirld1.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld1.x-chirld1.w)+"px; margin-bottom:"+(chirld1.x-bean.x)+"px; ";
									}
									
								
									
								} else if (bean.orientation.equals("vertical")) {
//									CompomentBean chirld1 = bean.chirlds.get(0);
//									CompomentBean chirld2 = bean.chirlds.get(1);
//									if (chirld1.y < chirld2.y) {
//									} else if (chirld1.y > chirld2.y) {
//									}

								}

							} else if (bean.chirlds.size() == 3) {

								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									
									Collections.sort(bean.chirlds, comparatorX);

									int i = 0;
									for (CompomentBean chirld : bean.chirlds) {
										if (i == 0) {
											chirld.relativeForWeb += "float:left;  margin-left:"+(chirld.x-bean.x)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(chirld.x-bean.x)+"px; ";

										} else if (i == 1) {
											chirld.relativeForWeb += "float:center; ";

										} else if (i == 2) {
											chirld.relativeForWeb += "float:right;  margin-top:"+(chirld.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px; margin-bottom:"+(chirld.x-bean.x)+"px; ";
										}
										i++;
									}

									Collections.sort(bean.chirlds, comparatorDate);
								} else if (bean.orientation.equals("vertical")) {
//									Collections.sort(bean.chirlds, comparatorY);
//
//									int i = 0;
//									for (CompomentBean chirld : bean.chirlds) {
//										if (i == 0) {
//											chirld.relative += " android:layout_alignParentTop=\"true\"  ";
//
//										} else if (i == 1) {
//											chirld.relative += " android:layout_centerInParent=\"true\"  ";
//
//										} else if (i == 2) {
//											chirld.relative += " android:layout_alignParentBottom=\"true\"  ";
//										}
//
//										if (chirld.type.contains("Layout")) {
//											chirld.relative += " android:layout_centerHorizontal=\"true\"";
//										}
//										i++;
//									}
//
//									Collections.sort(bean.chirlds, comparatorDate);
								}

							} else if (bean.chirlds.size() > 3) {

								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									
									Collections.sort(bean.chirlds, comparatorX);

									int i = 0;
									
									for (CompomentBean chirld : bean.chirlds) {
										if (i == 0) {
											chirld.relativeForWeb += "float:left;  margin-left:"+(chirld.x-bean.x)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(chirld.x-bean.x)+"px; ";
										
										} else if (i == bean.chirlds.size() - 1) {
											chirld.relativeForWeb += "float:right; margin-top:"+(chirld.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px; margin-bottom:"+(chirld.x-bean.x)+"px;";
										} else {
										
										}
										i++;
									}

									Collections.sort(bean.chirlds, comparatorDate);
								} else if (bean.orientation.equals("vertical")) {
//									Collections.sort(bean.chirlds, comparatorY);
//									int i = 0;
//									CompomentBean below = null;
//									for (CompomentBean chirld : bean.chirlds) {
//										if (i == 0) {
//											chirld.relative += " android:layout_alignParentTop=\"true\" android:layout_centerHorizontal=\"true\" ";
//											below = chirld;
//										} else if (i == bean.chirlds.size() - 1) {
//											chirld.relative += " android:layout_alignParentBottom=\"true\" android:layout_centerHorizontal=\"true\" ";
//										} else {
//											chirld.relative += " android:layout_below=\"@id/"
//													+ below.enname
//													+ "\" android:layout_centerHorizontal=\"true\" ";
//
//											below = chirld;
//										}
//										i++;
//									}
//
//									Collections.sort(bean.chirlds, comparatorDate);
								}
							}

						}
					}else
					{
						if (bean.chirlds != null && bean.chirlds.size() > 1) {
							
							

								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									Collections.sort(bean.chirlds, comparatorX);

									int i = 0;
									
									for (CompomentBean chirld : bean.chirlds) {
										if (i == 0) {
											chirld.relativeForWeb += "  margin-left:"+(chirld.x-bean.x)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(chirld.x-bean.x)+"px; ";
										
										} else if (i == bean.chirlds.size() - 1) {
											chirld.relativeForWeb += " margin-top:"+(chirld.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px; margin-bottom:"+(chirld.x-bean.x)+"px;";
										} else {
										
										}
										i++;
									}

									Collections.sort(bean.chirlds, comparatorDate);
								}
						}
						
						
					}
				}

		
		
		
	    bodym+="<html>\n";
		bodym+="<body>\n";
		bodym+="  <div style=\" width:100%; height:"+maxBean.h+"px;  background-color:"+maxBean.bgRgb16+"; \">\n";
		bodym+=formmStart;
		parent(maxBean);

		bodym+=formmEnd;
		bodym+="  </div>\n";
		bodym+="</body>\n";
		bodym+="</html>\n";
		return bodym;
	}
	
	
	public String  getConnection()
	{
		return connection;
	}



	
	public void parent(CompomentBean bean) {


	        Collections.sort(bean.chirlds, comparatorDate);
	    
		//有	儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			
			
		

			for (CompomentBean chirld : bean.chirlds) {
				
				//这个儿子是容器 layout
				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					
					bodym+="  <div style=\" width:100%; height:"+maxBean.h+"px;  background-color:"+maxBean.bgRgb16+"; \">\n";
					
					bodym+="  </div>\n";

				} else {//这个儿子是非容器 
					
					chirld(chirld, bean);
				}
			}

		}
		

	}

	
	
	public void chirld(CompomentBean chirld, CompomentBean parent) {//这个儿子是非容器
		
		
		if (chirld.type.equals("TextView")) {
		
			bodym += " <span style=\""+chirld.relativeForWeb+" \"><font size=\""+chirld.textSize+"px\" color=\""+chirld.rgb16+"\">"+chirld.cnname+"</font></span>\n";
		}
		
		if (chirld.type.equals("Line")) {
			bodym += "                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\""
					
					+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                        <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x)
					+ "\" y=\""
                 	+ (chirld.y - parent.y)
					+ "\" width=\""
					+ "320"
					+ "\" height=\""
					+ "1"
					+ "\"/>\n";

			// bodym+="<color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16ios)+"\" green=\""+chirld.getG(chirld.bgRgb16ios)+"\" blue=\""+chirld.getB(chirld.bgRgb16ios)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"17\"/>\n";
			bodym += "                                        <color key=\"backgroundColor\" red=\""
					+ chirld.getR(chirld.rgb16)
					+ "\" green=\""
					+ chirld.getG(chirld.rgb16)
					+ "\" blue=\""
					+ chirld.getB(chirld.rgb16)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "                                        <nil key=\"highlightedColor\"/>\n";
			bodym += "                                    </label>\n";
			
		}

		
		
		if (chirld.type.equals("Button")) {
			
			
		    if(!chirld.picName.equals("图片名"))
		    {
			
			bodym += "                                <state key=\"normal\" title=\""
					+ chirld.cnname + "\"  backgroundImage=\""+chirld.picName+".png\">\n";
		    }else
		    {
		    
		    	
		    	bodym += "<img src= \"/images/back.png\" onclick=\" history.go(-1);\" styple=\" width: 12px; position: absolute; left: 10px; top:10px;\">\n";
		    }

		}

		if (chirld.type.equals("CheckBox")) {
			bodym += "                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
					+ "\"/>\n";
			bodym += "                                <color key=\"backgroundColor\" red=\""
					+ chirld.getR(chirld.bgRgb16ios)
					+ "\" green=\""
					+ chirld.getG(chirld.bgRgb16ios)
					+ "\" blue=\""
					+ chirld.getB(chirld.bgRgb16ios)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "                                <color key=\"tintColor\" red=\""
					+ chirld.getR(chirld.rgb16)
					+ "\" green=\""
					+ chirld.getG(chirld.rgb16)
					+ "\" blue=\""
					+ chirld.getB(chirld.rgb16)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			
		    if(!chirld.picName.equals("图片名"))
		    {
			
			bodym += "                                <state key=\"normal\" title=\""
					+ chirld.cnname + "\"  backgroundImage=\""+chirld.picName+".png\">\n";
		    }else
		    {
		    	bodym += "                                <state key=\"normal\" title=\""
						+ chirld.cnname + "\">\n";
		    }
			bodym += "                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			bodym += "                                </state>\n";
	
			bodym += "                            </button>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "Cover\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
			
			
			//透明覆盖层
			
			String coverLeverId=id();
			bodym += "                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ coverLeverId+ "\">\n";
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
					+ "\"/>\n";

			bodym += "                            </button>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + coverLeverId
					+ "\" id=\"" + id() + "\"/>\n";
		}
		
		
		if (chirld.type.equals("EditText")) {
			
			if(formmStart.equals(""))
			{
			formmStart+="<form action=\"\" id=\"myform\"  method=\"post\">\n";
			
			formmEnd+="</form>\n";
			}
			
			

		}

		if (chirld.type.equals("CheckBox")) {
			// m += "/**" + chirld.cnname + "*/\n";
			// m += " " + chirld.enname + " = new JCheckBox(\""
			// + chirld.cnname + "\");\n";
			// m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
			bodym += " <tableView clipsSubviews=\"YES\" contentMode=\"scaleToFill\" alwaysBounceVertical=\"YES\" style=\"plain\" separatorStyle=\"default\" rowHeight=\"44\" sectionHeaderHeight=\"22\" sectionFooterHeight=\"22\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += " <rect key=\"frame\" x=\"" + (chirld.x - parent.x)
					+ "\" y=\"" + (chirld.y - parent.y) + "\" width=\""
					+ (chirld.w) + "\" height=\"" + (chirld.h) + "\"/>\n";
//			bodym += " <color key=\"backgroundColor\" red=\""
//					+ chirld.getR(chirld.bgRgb16ios) + "\" green=\""
//					+ chirld.getG(chirld.bgRgb16ios) + "\" blue=\""
//					+ chirld.getB(chirld.bgRgb16ios)
//					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "  </tableView>\n";
			connection += "                        <outlet property=\""
					+ "tableView" + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}


		if (chirld.type.equals("ImageView")) {

			bodym += " <imageView userInteractionEnabled=\"NO\" contentMode=\"scaleToFill\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" image=\""+chirld.picName+".png\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += " <rect key=\"frame\" x=\"" + (chirld.x - parent.x)
					+ "\" y=\"" + (chirld.y - parent.y) + "\" width=\""
					+ (chirld.w) + "\" height=\"" + (chirld.h) + "\"/>\n";
//			bodym += " <constraints>\n";
//			bodym += " <constraint firstAttribute=\"height\" constant=\""+imageHeightConstraint+"\" id=\""
//					+ id() + "\"/>\n";
//			bodym += " <constraint firstAttribute=\"width\" constant=\""+imageWidthConstraint+"\" id=\""
//					+ id() + "\"/>\n";
//			bodym += " </constraints>\n";
			bodym += " </imageView>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}
		
	

		if (chirld.type.equals("ExpandableListView")) {

		}
	}

//	public String constraint(CompomentBean bean) {
////一组里边的约束条件
//		
//		String m = "<constraints>\n";
//		String n = " <variation key=\"widthClass=compact\">\n";
//		n += " <mask key=\"constraints\">\n";
//
//		CompomentBean leftfirst = null;
//		
//		
//	
//
//		for (CompomentBean chirld1 : bean.chirlds) {
//			
//			boolean left = false;
//			int leftvalue = 0;
//			boolean right = false;
//		
//			boolean top = false;
//			int topvalue = 0;
//			boolean bottom = false;
//		
//
//			for (CompomentBean chirld2 : bean.chirlds) {
//				if (!chirld1.enname.equals(chirld2.enname)) {
//					if (chirld1.x > (chirld2.x + chirld2.w)) {// 有人在你(chirld1)左边
//
//						if (left == true) {
//							if (leftvalue > chirld1.x - (chirld2.x + chirld2.w)) {// 上一个距离远
//																					// 换现在这个近的
//
//								String id = id();
//								leftvalue = chirld1.x - (chirld2.x + chirld2.w);
//								String newString = "<constraint firstItem=\""
//										+ chirld1.id
//										+ "\" firstAttribute=\"leading\" secondItem=\""
//										+ chirld2.id
//										+ "\" secondAttribute=\"trailing\" constant=\""
//										+ (leftvalue) + "\" id=\"" + id
//										+ "\"/>\n";
//								;
//
//								String newStringn = " <include reference=\""
//										+ id + "\"/>\n";
//
//								String rows[] = m.split("\n");
//								String lastrow = rows[rows.length - 1];
//
//								m = m.replace(lastrow, newString);
//
//								String rowsn[] = n.split("\n");
//								String lastrown = rowsn[rowsn.length - 1];
//
//								n = n.replace(lastrown, newStringn);
//
//							}
//						} else {
//							leftvalue = chirld1.x - (chirld2.x + chirld2.w);
//							String id = id();
//							m += "<constraint firstItem=\""
//									+ chirld1.id
//									+ "\" firstAttribute=\"leading\" secondItem=\""
//									+ chirld2.id
//									+ "\" secondAttribute=\"trailing\" constant=\""
//									+ chirldleftspace + "\" id=\"" + id + "\"/>\n";
//							n += " <include reference=\"" + id + "\"/>\n";
//
//							left = true;
//						}
//
//					}
//
//					if ((chirld1.x + chirld1.w) < chirld2.x) {// 有人在你(chirld1)右边
//
//						if (right == true) {
//							
//						} else {
//							
//							right = true;
//						}
//					}
//
//					if (chirld1.y > (chirld2.y + chirld2.h)) {// 有人在你(chirld1)上边
//
//						if (top == true) {
//							if (topvalue > chirld1.y - (chirld2.y + chirld2.h)) {// 上一个距离远
//																					// 换现在这个近的
//
//								String id = id();
//
//								topvalue = chirld1.y - (chirld2.y + chirld2.h);
//								String newString = "<constraint firstItem=\""
//										+ chirld1.id
//										+ "\" firstAttribute=\"top\" secondItem=\""
//										+ chirld2.id
//										+ "\" secondAttribute=\"bottom\" constant=\""
//										+ (topvalue) + "\"  id=\"" + id
//										+ "\"/>\n";
//
//								String newStringn = " <include reference=\""
//										+ id + "\"/>\n";
//
//								String rows[] = m.split("\n");
//								String lastrow = rows[rows.length - 1];
//
//								m = m.replace(lastrow, newString);
//
//								String rowsn[] = n.split("\n");
//								String lastrown = rowsn[rowsn.length - 1];
//
//								n = n.replace(lastrown, newStringn);
//
//							}
//						} else {
//							topvalue = chirld1.y - (chirld2.y + chirld2.h);
//							String id = id();
//							m += "<constraint firstItem=\""
//									+ chirld1.id
//									+ "\" firstAttribute=\"top\" secondItem=\""
//									+ chirld2.id
//									+ "\" secondAttribute=\"bottom\" constant=\""
//									+ (topvalue) + "\" id=\"" + id + "\"/>\n";
//							n += " <include reference=\"" + id + "\"/>\n";
//							top = true;
//						}
//					}
//
//					if ((chirld1.y + chirld1.h) < chirld2.y) {// 有人在你(chirld1)下边
//
//						if (bottom == true) {
//							
//						} else {
//							
//							bottom = true;
//						}
//					}
//
//				}
//
//			}
//
//
//			
//			
//
//				
//				
//				// 没其他孩子在这孩子的上方
//				if (top == false) {
//					if(chirld1.type.contains("Layout"))
//					{
//						m += "<constraint firstItem=\"" + chirld1.id
//								+ "\" firstAttribute=\"top\" secondItem=\""
//								+ bean.id
//								+ "\" secondAttribute=\"top\" constant=\"0\" id=\"" + id()
//								+ "\"/>\n";
//					}else
//					{
//						if(left==false)
//						{//左边第一个
//							leftfirst=chirld1;
//							m += "<constraint firstItem=\"" + chirld1.id
//									+ "\" firstAttribute=\"top\" secondItem=\""
//									+ bean.id
//									+ "\" secondAttribute=\"top\" constant=\"9\" id=\"" + id()
//									+ "\"/>\n";
//						}else
//						{
//							
//							 m+="<constraint firstItem=\""+leftfirst.id+"\" firstAttribute=\"centerY\" secondItem=\""+chirld1.id+"\" secondAttribute=\"centerY\" id=\""+id()+"\"/>\n";
//
//						}
//					}
//
//				}
//
//				// 没其他孩子在这孩子的下方
//				if (bottom == false) {
//					if(chirld1.type.contains("Layout"))
//					{
//						
//						
//					}else
//					{
//						if(left==false)
//						{//左边第一个
//							m += "<constraint firstItem=\"" + chirld1.id
//									+ "\" firstAttribute=\"bottom\" secondItem=\""
//									+ bean.id
//									+ "\" secondAttribute=\"bottom\" constant=\"9\" id=\"" + id() + "\"/>\n";
//						}
//					
//					}
//
//				}
//			
//
//			// 没其他孩子在这孩子的左方
//			if (left == false) {
//				if(chirld1.type.contains("Layout"))
//				{
//					m += "<constraint firstItem=\"" + chirld1.id
//							+ "\" firstAttribute=\"leading\" secondItem=\""
//							+ bean.id
//							+ "\" secondAttribute=\"leading\" constant=\"0\" id=\"" + id() + "\"/>\n";
//				}else
//				{
//				m += "<constraint firstItem=\"" + chirld1.id
//						+ "\" firstAttribute=\"leading\" secondItem=\""
//						+ bean.id
//						+ "\" secondAttribute=\"leading\" constant=\""+chirldleftspaceConstaraint+"\" id=\"" + id() + "\"/>\n";
//				}
//
//			}
//
//			// 没其他孩子在这孩子的右方
//			if (right == false) {
//				if(chirld1.type.contains("Layout"))
//				{
//					m += "<constraint firstItem=\"" + chirld1.id
//							+ "\" firstAttribute=\"trailing\" secondItem=\""
//							+ bean.id
//							+ "\" secondAttribute=\"trailing\" constant=\"0\" id=\"" + id() + "\"/>\n";
//				}else
//				{
//				m += "<constraint firstItem=\"" + chirld1.id
//						+ "\" firstAttribute=\"trailing\" secondItem=\""
//						+ bean.id
//						+ "\" secondAttribute=\"trailing\" constant=\"20\" id=\"" + id() + "\"/>\n";
//				}
//			}
//
//		}
//
//		n += " </mask>\n";
//		n += " </variation>\n";
//		m += "</constraints>\n";
//		//m += n;添加小屏幕限制
//
//		return m;
//	}

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s2.time - s1.time);
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
}
