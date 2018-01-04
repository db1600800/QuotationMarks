
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

//	<!--
//	http://blog.csdn.net/topviewers/article/details/21644305
//    relative相对自己进行top，right，bottom，left移动 ，占位，文档流不变。
//
//	absolute 有父辈(父亲或爷爷)为absolute,relative，就相对父辈。没父辈,就相对浏览器。不占位，文档流改变。忽略padding
//	fixed 特殊absolute
//	
//	static 文档流 
//	-->
	
	
	// visibility:hidden;隐藏占位 display:none; 隐藏不占位
	// height: 42px; line-height: 42px; 多行时行高,单行时垂直居中
	// background-color: #fd5f28;背景色 font-size: 18px;字体大小 color: #fff;字体颜色
	// text-align: center;文本居中

	// float:left;right center

	// width: 100%;

	// position: relative; 正常占位

	// position: absolute; 相对父亲浮起来，不占位，父亲不指定position: relative则相对<body>浮起来
	// z-index:1; 多个浮起来，数值大的在上面

	// position: fixed; 相对浏览器浮起来，不占位
	// 后跟 top:0; left:0; bottom:0; right:0;

	// overflow: scroll;滚动 auto自动处理 hidden隐藏
	
	//margin-top:3px;

	String bodym = "\n\n\n";
	String style="";
	String js="";
	String connection = "";
	String pageName = "";
	String className = "";
	String formmStart = "";
	String formmEnd = "";

	int rootViewWidth = 320;
	int rootViewHeight = 568;

	public WebJsp(int cellWidth, int cellHeight) {
		rootViewWidth = cellWidth;
		rootViewHeight = cellHeight;
	}

	public WebJsp(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);

		String body = analyse(oldBeans);

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web", className + "Jsp", "jsp", bodym);
		
		System.out.println(bodym);

		// FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", className
		// + "ViewController", "xib", m);

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

	public String analyse(List<CompomentBean> oldBeans) {
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

		Collections.sort(layouts, comparatorDate);

		// RelativeLayout 儿子们的位置关系
		for (CompomentBean bean : layouts) {
			if (bean.type.equals("RelativeLayout")||bean.type.equals("LinearLayout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {

					if (bean.chirlds.size() == 2) {
						if (bean.orientation.equals("horizontal")) {

							bean.relativeForWeb += "";
							

							CompomentBean chirld1 = bean.chirlds.get(0); 
							CompomentBean chirld2 = bean.chirlds.get(1);
							if (chirld1.x < chirld2.x) {
								chirld1.relativeForWeb += "display:inline-block;width:50%;margin:10px";
								chirld2.relativeForWeb += "display:inline-block;margin:10px";
							} else if (chirld1.x > chirld2.x) {
								chirld2.relativeForWeb += "display:inline-block;margin:10px";
								chirld1.relativeForWeb += "display:inline-block;width:50%;margin:10px";
							}

						} else if (bean.orientation.equals("vertical")) {

						}

					}  else if (bean.chirlds.size() >= 3) {

						if (bean.orientation.equals("horizontal")) {
							bean.relativeForWeb += "";

							Collections.sort(bean.chirlds, comparatorX);

							int i = 0;

							for (CompomentBean chirld : bean.chirlds) {
							 if (i == bean.chirlds.size() - 1) {
									chirld.relativeForWeb += "display:inline-block;margin:10px";
								} else {
									chirld.relativeForWeb += "display:inline-block;width:"+100/bean.chirlds.size()+"%;margin:10px";
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {

						}
					}
				}
			} 
		}

		bodym += "<html>\n";

		bodym += "<head>\n";
		bodym += "	<meta charset='utf-8'>\n";
		bodym += "	<meta name=\"viewport\" content=\"width=device-width,initial-scale=1, maximum-scale=1\">\n";
		bodym += "	<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n";
		bodym += "	<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		bodym += "	<META Http-Equiv=\"Cache-Control\" Content=\"no-cache\"/>\n";
		bodym += "	<META Http-Equiv=\"Pragma\" Content=\"no-cache\"/>\n";
		bodym += "	<META Http-Equiv=\"Expires\" Content=\"-1\"/>\n";

	
		

		bodym += "	<script type=\"text/javascript\" src=\"js/jquery-1.10.1.min.js\"></script>\n";
		bodym+="<script type=\"text/javascript\"  id=\"myJs\">\n\n";
		bodym+="//js取request值  var contentWidth = <s:property value=\"#request.cut_img_content_info.contentWidth\"/>;\n";
		bodym+="//html取request值  <input type=\"hidden\"  name=\"busiNo\" id=\"busiNo\" value=\"<s:property value=\"#request.busiNo\"/>\" />\n\n";
	
		bodym+="//appendjs\n";
		bodym+="</script>\n";

		
		bodym += "</head>\n";

		bodym += "<body>\n";
		bodym += "<div style=\" width:100%; height:" + maxBean.h + "px;  background-color:" + maxBean.bgRgb16
				+ "; \">\n";
		bodym += "<div id=\"emptyOrErrorMsg\"></div>";
		
		bodym += formmStart;
		parent(maxBean);

		bodym += formmEnd;

		bodym += "  </div>\n";
		bodym += "</body>\n";
	
		bodym += "</html>\n";
		
		bodym=bodym.replace("//appendjs", js);
		
		
		return bodym;
	}

	public String getConnection() {
		return connection;
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
					omponents.add("DivLayout");
					components.add("HeaderLayout");
					components.add("FooterLayout");
					components.add("SectionLayout");
					components.add("TableLayout");
					components.add("Table_TRLayout");
					components.add("Table_THLayout");
					components.add("FormLayout");
					components.add("Form_ItemLayout");
					components.add("ListLayout");
					components.add("List_linkLayout");
					components.add("List_ItemLayout");
					components.add("GridLayout");
					components.add("Grid_ItemLayout");
					components.add("DialogLayout");
					components.add("TabLayout");
					components.add("SliderLayout");//轮播
					
					if (chirld.type.equals("DivLayout")) {
						
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}else if (chirld.type.equals("HeaderLayout")) {
						start="<header id=\"" + chirld.enname + "\" class=\"ui-header ui-header-stable ui-border-b\">\n";

						end = "</header>\n";
					}
					else if(chirld.type.equals("FooterLayout"))
					{
						start="<footer id=\"" + chirld.enname + "\" class=\"ui-footer ui-footer-stable ui-border-t\">\n";

						end = "</footer>\n";
					}else if(chirld.type.equals("SectionLayout"))
					{
						start = "<section id=\"" + chirld.enname + "\" class=\"ui-container ui-center\">\n";

						end = "  </section>\n";
					}else if(chirld.type.equals("TableLayout"))
					{
						
						start="<table id=\"" + chirld.enname + "\" class=\"ui-table ui-border\">\n";

						end = "</table>\n";
					}else if(chirld.type.equals("Table_TRLayout"))
					{
						start = "<tr id=\"" + chirld.enname + "\" >\n";


						end = "  </tr>\n";
					}else if(chirld.type.equals("Table_THLayout"))
					{
						start = "<td id=\"" + chirld.enname + "\" >\n";


						end = "  </td>\n";
					}
					else if(chirld.type.equals("FormLayout"))
					{
						
						start="<div id=\"" + chirld.enname + "\" class=\"ui-form ui-border-t\">\n";
					    start="<form action=\"#\">\n";

					    end=" </form>\n";
						end = "  </div>\n";
					}
					else if(chirld.type.equals("Form_ItemLayout"))
					{
						
                        start=" <div id=\"" + chirld.enname + "\"  class=\"ui-form-item ui-border-b\">\n";

						end = "  </div>\n";
					}else if(chirld.type.equals("ListLayout"))
					{
						
                        start="<ul id=\"" + chirld.enname + "\" class=\"ui-list ui-list-pure ui-border-tb\">\n";

						end = "  </ul>\n";
					}
					else if(chirld.type.equals("List_linkLayout"))
					{
						 start="<ul id=\"" + chirld.enname + "\" class=\"ui-list  ui-list-text ui-list-link ui-border-tb\">\n";

							end = "  </ul>\n";
					}
					else if(chirld.type.equals("List_ItemLayout"))
					{
						
                        start=" <li id=\"" + chirld.enname + "\" class=\"ui-border-t\">\n";

						end = "  </li>\n";
					}
					else if(chirld.type.equals("GridLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else if(chirld.type.equals("Grid_ItemLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else if(chirld.type.equals("DialogLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else if(chirld.type.equals("TabLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else if(chirld.type.equals("DialogLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else if(chirld.type.equals("SliderLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else if(chirld.type.equals("DialogLayout"))
					{
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}
					else
					{
						style+="."+chirld.enname+"Style {\n";
						style+="height:"+chirld.h+"px;\n";
						style+="line-height: " + chirld.h + "px;";
						style+="background-color:" + chirld.bgRgb16+"\"";
						style+="}\n";
						
						
						start = "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";


						end = "  </div>\n";
					}

					bodym += start;

					parent(chirld);

					bodym += end;
				} else {// 这个儿子是非容器

//					if (bean.compomentForWeb.equals("tr")) {
//						bodym += "  <td style=\"width:"+((float)1/(float)bean.chirlds.size())*100+"%; text-align: center;\">\n";
//					}

					chirld(chirld, bean);

//					if (bean.compomentForWeb.equals("tr")) {
//						bodym += "  </td>\n";
//					}
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {// 这个儿子是非容器

	
		
		
		components.add("Line");
		
		components.add("TextView");
		components.add("EditText");
		
		components.add("Button");
		components.add("Button_Close");
		
		components.add("CheckBox");
		components.add("CheckBox_Switch");
		components.add("Radio");
		
		components.add("PopTips");
		
		
		components.add("LeftMenu");
		
		components.add("Loading");
		
		components.add("ImageView");
		
		components.add("Selecter");
		
		
		if (chirld.type.equals("TextView")) {

			bodym += "<div style="+chirld.relativeForWeb+"> <span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" style=\" font-size: " + chirld.textSize + "px; color: " + chirld.rgb16
					+ ";  \">" + chirld.cnname + "</span></div>\n";

		}

		if (chirld.type.equals("Line")) {
			
			style+=".父亲的LayoutId:after {\n";
			style+="position: absolute;\n";
			style+="top: 0px;\n";
			style+="right: 0px;\n";
			style+="left: 0px;\n";
		    
			style+=" height: 1px;\n";
			style+=" content: '';\n";
			style+="  -webkit-transform: scaleY(.5);\n";
			style+=" transform: scaleY(.5);\n";
			style+="background-color: #c8c7cc;\n";
			style+="}\n";
			
			

		}

		if (chirld.type.equals("Spinner")) {

			// <span style="background:url(../images/next.png) no-repeat right
			// center #fff; background-size:8px; line-height:20px;
			// display:block; float:left; padding:3px 15px 3px 5px;
			// margin-right:2px; margin-bottom:5px" onClick="selectCount();"
			// id="count` `Name">请选择</span>

			bodym += "<div id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" onclick=\""
					+ chirld.actionString + "\" style=\" font-size: " + chirld.textSize + "px; color: " + chirld.rgb16
					+ ";" + chirld.relativeForWeb + " \">"

					+ "" + chirld.cnname + "" + "<img  src= \"/images/" + chirld.picName
					+ ".png\"  style=\" width: 12px; \">" + "</div>\n";
		}

		if (chirld.type.equals("Button")) {

			if (chirld.picName.equals("图片名")) {

				bodym += "<div style="+chirld.relativeForWeb+"><a href=\"#\" id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\"  onclick=\""
						+ chirld.actionString + "\"  style=\"text-align: center; height:" + chirld.h
						+ "px; line-height: " + chirld.h + "px; border-radius: 8px; color:" + chirld.rgb16
						+ "; background-color:" + chirld.bgRgb16 + ";  font-size: " + chirld.textSize + "px ; margin:1px;  padding: 1px; text-decoration: none;\" >" + chirld.cnname + "</a> </div>\n";
				

			} else {

				bodym += "<div style="+chirld.relativeForWeb+"><img id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" src= \"/images/"
						+ chirld.picName + ".png\" onclick=\"" + chirld.actionString + ";\" style=\" width: 12px; \"></div>\n";
			}

		}

		if (chirld.type.equals("CheckBox")) {

			js +="//checkbox \n";
			js += "var setAddr = false;\n";
			js += "function setDefaultAddr(){\n";
			js += "	if(setAddr){\n";
			js += "	setAddr = false;\n";
			js += "	$(\"#"+chirld.enname+"\").attr(\"style\",\" background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;\");\n";
			js += "}else{\n";
			js += "	setAddr = true;\n";
			js += "	$(\"#"+chirld.enname+"\").attr(\"style\",\" background: url(images/checked.png) no-repeat 2px center;  padding-left: 35px; padding-top:0px; padding-bottom:0px;\");\n";
			js += "}\n";
			js += "}\n\n";

			
			bodym += "<div style="+chirld.relativeForWeb+"><span id=\""+chirld.enname+"\" style=\"  background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;\"  onclick=\"setDefaultAddr()\">"+chirld.cnname+"</span></div>";

			
		}

		if (chirld.type.equals("EditText")) {

			
			bodym += "<div style="+chirld.relativeForWeb+"> <input style=\" border: 0; line-height: "+chirld.h+"px; height: "+chirld.h+"px;  font-size: "+chirld.textSize+"px;\"  type=\"text\"  id=\""+chirld.enname+"\" name=\""+chirld.enname+"\" placeholder=\""+chirld.cnname+"\"></div>";

			

		}

	
		if (chirld.type.equals("List")) {
		
			bodym += "<ul class=\"ui-list ui-list-pure ui-border-tb\">\n";
			bodym+="</ul>\n";
		}

		if (chirld.type.equals("ImageView")) {

			bodym += " <imageView userInteractionEnabled=\"NO\" contentMode=\"scaleToFill\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" image=\""
					+ chirld.picName + ".png\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"" + chirld.id
					+ "\">\n";
			bodym += " <rect key=\"frame\" x=\"" + (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + (chirld.w) + "\" height=\"" + (chirld.h) + "\"/>\n";
			// bodym += " <constraints>\n";
			// bodym += " <constraint firstAttribute=\"height\"
			// constant=\""+imageHeightConstraint+"\" id=\""
			// + id() + "\"/>\n";
			// bodym += " <constraint firstAttribute=\"width\"
			// constant=\""+imageWidthConstraint+"\" id=\""
			// + id() + "\"/>\n";
			// bodym += " </constraints>\n";
			bodym += " </imageView>\n";
			connection += "                        <outlet property=\"" + chirld.enname + "\" destination=\""
					+ chirld.id + "\" id=\"" + id() + "\"/>\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
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

//	function select_invoice(obj){//obj==this
//		var invoiceTypeVal = $(obj).find("input[name='invoiceType']").val();
//		var invoiceTypeVals = invoiceTypeVal.split("#", 2);
//		var dw = invoiceTypeVals[1];
//		
//		$(obj).find("input[name='invoiceType']").prop("checked", true);
//		$(obj).prop("class","checked04");
//		$(obj).parent().find("input[name='invoiceType']").each(function () {
//			if($(this).val() != invoiceTypeVal){
//				ck_or_no_class(this, false);
//			}
//		});
//		if(dw == "2"){
//			$(obj).parent().parent().find("li[name='invoice_title_li']").show();
//		} else {
//			$(obj).parent().parent().find("li[name='invoice_title_li']").hide();
//		}
//	}
	
	
	
}
