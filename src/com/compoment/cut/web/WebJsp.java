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

//http://www.ruanyifeng.com/blog/2015/07/flex-examples.html
//http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html

public class WebJsp {

	// <!--
	// http://blog.csdn.net/topviewers/article/details/21644305
	// relative相对自己进行top，right，bottom，left移动 ，占位，文档流不变。
	//
	// absolute
	// 有父辈(父亲或爷爷)为absolute,relative，就相对父辈。没父辈,就相对浏览器。不占位，文档流改变。忽略padding
	// fixed 特殊absolute
	//
	// static 文档流
	// -->

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

	// margin-top:3px;

	String bodym = "\n\n\n";
	String style = "";
	String js = "";
	String connection = "";
	String pageName = "";
	String className = "";

	int rootViewWidth = 320;
	int rootViewHeight = 568;

	CompomentBean newParent;

	String jsString = "";
	String styleString = "";

	String parentChirld = "";
	CompomentBean listItemBean = null;

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

		// FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios",
		// className
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

		// 2.修正属于哪个父亲
		newParent = maxBean;
		parentModifyParent(maxBean);

		Collections.sort(layouts, comparatorDate);
		// changePosition(maxBean);

		bodym += "<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>\n";
		bodym += "<%\n";
		bodym += "String path = request.getContextPath();\n";
		bodym += "String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";\n";
		bodym += "%>\n";
		bodym += "<!DOCTYPE html>\n";
		bodym += "<html>\n";

		bodym += "<head>\n";
		bodym += "	<meta charset='utf-8'>\n";
		bodym += "	<meta name=\"viewport\" content=\"width=device-width,initial-scale=1, maximum-scale=1\">\n";
		bodym += "	<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n";
		bodym += "	<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		bodym += "	<meta Http-Equiv=\"Cache-Control\" Content=\"no-cache\"/>\n";
		bodym += "	<meta Http-Equiv=\"Pragma\" Content=\"no-cache\"/>\n";
		bodym += "	<meta Http-Equiv=\"Expires\" Content=\"-1\"/>\n";

		bodym += "  <link rel=\"stylesheet\" href=\"<%=basePath%>css/frozen.css\">\n";
		bodym += "	<script type=\"text/javascript\" src=\"<%=basePath%>js/jquery.js\"></script>\n";
		bodym += "	<script src=\"<%=basePath%>lib/zepto.min.js\"></script>\n";
		bodym += "	<script src=\"<%=basePath%>js/frozen.js\"></script>\n";

		bodym += "<script>\n";
		bodym += "//js取request值  var contentWidth = <s:property value=\"#request.cut_img_content_info.contentWidth\"/>;\n";
		bodym += "//html取request值  <input type=\"hidden\"  name=\"busiNo\" id=\"busiNo\" value=\"<s:property value=\"#request.busiNo\"/>\" />\n\n";

		bodym += "//appendjs\n";
		bodym += "</script>\n";

		bodym += "</head>\n";

		bodym += "<body id=\"body\" class=\"h-body\" style=\"display:flex;flex-direction: column;\">\n";

		bodym += "<div id=\"emptyOrErrorMsg\"></div>\n";

		parent(maxBean);

		bodym += "</body>\n";

		bodym += styleString;

		bodym += "<script type=\"text/javascript\">\n";
		bodym += "//body宽高等于窗体\n";
		bodym += " var screenHeight=document.documentElement.clientHeight;\n";
		bodym += " var screenWidth=document.documentElement.clientWidth; \n";
		bodym += "var body = $(\".h-body\");\n";
		bodym += "body.width(screenWidth + \"px\");\n";
		bodym += "body.height(screenHeight + \"px\");\n";
		bodym += " </script> \n";

		bodym += jsString;

		bodym += "</html>\n";

		bodym = bodym.replace("//appendjs", js);

		return bodym;
	}

	public String getConnection() {
		return connection;
	}

	public void parentModifyParent(CompomentBean bean) {

		Collections.sort(bean.chirlds, comparatorDate);

		// 有 儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			if (bean.type.equals("ScrollViewLayout")) {

			} else {

				//
				for (CompomentBean chirld : bean.chirlds) {

					// 这个儿子是容器 layout
					if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

						if (chirld.layoutNoUseForIos == true) {// 隐藏
							newParent = bean;
						} else {
							newParent = chirld;
						}

						parentModifyParent(chirld);

					} else {// 这个儿子是非容器

						chirld.parent = newParent;
					}
				}

			}
		}

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

					String relate = "";
					String border = "";

					String expand = "";

					if ("leftLeft".equals(chirld.compomentForWeb)) {
						relate = "justify-content:flex-start;align-items:center;";
					}
					if ("left_Center_Right".equals(chirld.compomentForWeb)) {
						relate = "justify-content: space-between;align-items:center;";
					}
					if ("leftRight".equals(chirld.compomentForWeb)) {
						relate = "justify-content: space-between;align-items:center;";
					}
					if ("left[C e n t e r]Right".equals(chirld.compomentForWeb)) {
						relate = "justify-content:flex-start;align-items:center;";
					}
					if ("centerCenter".equals(chirld.compomentForWeb)) {
						relate = "justify-content:center;align-items:center;";
					}

					if ("rightRight".equals(chirld.compomentForWeb)) {
						relate = "justify-content:flex-end;align-items:center;";
					}

					if ("topBottom".equals(chirld.compomentForWeb)) {
						relate = "width:100%;justify-content:flex-start;";
					}
					if ("topBottomAndCenter".equals(chirld.compomentForWeb)) {
						relate = "width:100%;justify-content:flex-start;align-items:center;";
					}

					if ("boderTop".equals(chirld.compmentBorderForWeb)) {
						border = "border-top: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderLeft".equals(chirld.compmentBorderForWeb)) {
						border = "border-left: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderRight".equals(chirld.compmentBorderForWeb)) {
						border = "border-right: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderBottom".equals(chirld.compmentBorderForWeb)) {
						border = "border-bottom: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderTopBottom".equals(chirld.compmentBorderForWeb)) {
						border = "border-top: 1px solid " + chirld.rgb16
								+ ";border-bottom: 1px solid \"+chirld.rgb16+\";";
					}

					if ("boderAll".equals(chirld.compmentBorderForWeb)) {
						border = "border: 1px solid " + chirld.rgb16 + ";";
					}

					if ("1bei".equals(chirld.compmentExpandForWeb)) {
						expand = "flex:1;";
					}
					if ("2bei".equals(chirld.compmentExpandForWeb)) {
						expand = "flex:2;";
					}
					if ("3bei".equals(chirld.compmentExpandForWeb)) {
						expand = "flex:3;";
					}

					if (chirld.type.equals("DivLayout_Horizon") || chirld.type.equals("HeaderLayout_Horizon")
							|| chirld.type.equals("FooterLayout_Horizon")) {
						String classString = "";
						if (chirld.type.contains("Header")) {
							classString = "h-header";
						} else if (chirld.type.contains("Footer")) {
							classString = "h-footer";
						}

						start += "<div id=\"" + chirld.enname + "\" class=\"" + classString
								+ "\"  style=\"background-color:" + chirld.bgRgb16 + ";display:flex;flex-direction:row;"
								+ relate + border + expand + "\" >\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("DivLayout_Vertical") || chirld.type.equals("HeaderLayout_Vertical")
							|| chirld.type.equals("FooterLayout_Vertical")) {

						String classString = "";
						if (chirld.type.contains("Header")) {
							classString = "header";
						} else if (chirld.type.contains("Footer")) {
							classString = "footer";
						}

						start += "<div id=\"" + chirld.enname + "\" class=\"" + classString
								+ "\" style=\"background-color:" + chirld.bgRgb16
								+ ";display:flex;flex-direction:column;" + relate + border + expand + "\" >\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("FormLayout")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-form ui-border-t\">\n";
						start += "<form action=\"#\">\n";

						end += " </form>\n";
						end += "  </div>\n";
					} else if (chirld.type.equals("Form_ItemLayout")) {

						start += " <div id=\"" + chirld.enname + "\"  class=\"ui-form-item ui-border-b\">\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("ListLayout")) {

						start += "<ul id=\"" + chirld.enname
								+ "\" class=\"h-list ui-border-tb\" style=\"flex:1;padding:10px;overflow-y:scroll\">\n";

						end += "  </ul>\n";

						if (chirld.chirlds != null && chirld.chirlds.size() > 0) {
							WebJspListViewItem webListItemJsp = new WebJspListViewItem();
							String itemString=webListItemJsp.analyse(chirld.chirlds.get(0));

							jsString += "\n<script type=\"text/javascript\">\n";
							// 分页

							jsString += "window.resultTotal=0;\n";
							jsString += "window.pageSize=10;\n";
							jsString += "window.currentPage=0;\n";
							jsString += "window.requestIng=false;\n";
							jsString+="window.where=new Map();\n";
							jsString += " $(\".h-list\").scroll(function(){\n";
							jsString += " var list = $(\".h-list\");\n";
							jsString += "var listScrollTop = list.scrollTop();//滚动条下滚多少==内容隐藏部分多少\n";
							jsString += "var listScrollHeight = list[0].scrollHeight;//内容总高度\n";
							jsString += "var listClientHeight = list[0].clientHeight;//可见区域高度\n";
							jsString += "var listOffsetHeight = list[0].offsetHeight;//可见区域高度(包含边框高度)\n";
							jsString += "if(listScrollTop + listClientHeight == listScrollHeight){\n";

							jsString += "if(requestIng==false)\n";
							jsString += "{\n";
							jsString += " alert(\"\");\n";
							jsString += "getData();\n";
							jsString += "}\n";
							jsString += "}\n";
							jsString += "});\n";

							/*
							 * { "resultCode": "1001", "resultSize": 1, "resultTotal":10, "resultData": [ {
							 * "name": "bootstrap-table", "stargazers_count": "526", "forks_count": "122",
							 * "description":
							 * "An extended Bootstrap table with radio, checkbox, sort, pagination, and other added features. (supports twitter bootstrap v2 and v3) "
							 * } ] }
							 */
							jsString += " function getData() {\n";
							jsString += "requestIng=true;\n";
							jsString += "$.ajax({\n";
							jsString += "url:url,\n";
							jsString += "type:'post',\n";
							jsString += "dataType:'json',\n";
							jsString += "async:true,\n";
							jsString += "data:{currentPage:currentPage,pageSize:pageSize,where:window.where},\n";
							jsString += "timeout:1000,\n";
							jsString += "error:function(){\n";
							jsString += "requestIng=false;\n";
							jsString += "alert(\"ajax error\");\n";
							jsString += "}, \n";
							jsString += "success:function(rsObj)\n";
							jsString += "{\n";
							jsString += "requestIng=false;\n";
							jsString += "var resultSize=rsObj.resultSize;\n";
							jsString += "window.resultTotal=rsObj.resultTotal;\n";
							jsString += "var resultData=rsObj.resultData;\n";
							jsString += "if(resultSize>0){\n";
							jsString += "for(var i=0;i<resultData.size();i++){ \n";
							
							jsString +="var itemString="+itemString+"\n;";
							jsString += " $(\".h-list\").append(itemString); \n";
							jsString += " }\n";
							jsString += " }\n";
							jsString += " currentPage++;\n";
							jsString += " }\n";
							jsString += "});\n";
							jsString += "}\n";
							jsString += "</script>\n";
						}
					}

					else if (chirld.type.equals("List_ItemLayout_Horizon")) {

						start += " <li id=\"" + chirld.enname
								+ "\" class=\"ui-border-t\" style=\"display:flex;flex-direction:row;width: 100%; "
								+ relate + border + "\">\n";

						end += "  </li>\n";
					} else if (chirld.type.equals("List_ItemLayout_Vertical")) {

						start += " <li id=\"" + chirld.enname
								+ "\" class=\"ui-border-t\" style=\"display:flex;flex-direction:column;width: 100%; "
								+ relate + border + "\">\n";

						end += "  </li>\n";
					}

					else if (chirld.type.equals("DialogLayout")) {

						start += "<div id=\"" + chirld.enname + "\"  class=\"ui-dialog\">\n";
						start += "<div class=\"ui-dialog-cnt\">\n";

						start += " <header class=\"ui-dialog-hd ui-border-b\">\n";
						start += "<h3>新手任务</h3>\n";
						start += "<i class=\"ui-dialog-close\" data-role=\"button\"></i>\n";
						start += "</header>\n";

						start += " <div class=\"ui-dialog-bd\">\n";
						start += " <h4>标题标题</h4>\n";
						start += "<div>开通年费QQ会员即可领取欢乐斗地主感恩节回馈礼包！</div>\n";
						start += "</div>\n";

						start += " <div class=\"ui-dialog-ft\">\n";
						start += " <button type=\"button\" data-role=\"button\">取消</button>\n";
						start += " <button type=\"button\" data-role=\"button\">确定</button>\n";
						start += "</div>\n";

						end += "</div>\n";
						end += "</div>\n";
						jsString += "\n<script class=\"demo-script\">\n";
						jsString += "$(\".ui-dialog\").dialog(\"show\");\n";
						jsString += "</script>\n";

					} else if (chirld.type.equals("TabLayout")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"h-tabbar\" style=\"background-color:"
								+ chirld.bgRgb16 + ";display:flex;flex-direction:row;" + relate + border + expand
								+ "\" >\n";

						end += "  </div>\n";

						jsString += "\n<script type=\"text/javascript\">\n";
						jsString += "//tabbar\n";
						jsString += " $(function () {\n";
						jsString += "     var collection = $(\".h-tabbar\").children();\n";
						jsString += "  $.each(collection, function () {\n";
						jsString += "     $(this).addClass(\"tabBtn\");\n";
						jsString += " });\n";
						jsString += " });\n";
						jsString += " //单击事件\n";
						jsString += " function tabBtnPress(btn) {\n";
						jsString += "   var collection = $(\".h-tabbar\").children();\n";
						jsString += " $.each(collection, function () {\n";
						jsString += "    $(this).removeClass(\"tabBtnPress\");\n";
						jsString += "  $(this).addClass(\"tabBtn\");\n";
						jsString += " });\n";
						jsString += " $(btn).removeClass(\"tabBtn\");\n";
						jsString += " $(btn).addClass(\"tabBtnPress\");\n";
						jsString += " }\n";
						jsString += "</script>\n";

						styleString += "<style>\n";
						styleString += ".tabBtn\n";
						styleString += " {\n";
						styleString += " cursor: pointer;\n";
						styleString += "  border-bottom: 2px solid transparent;\n";
						styleString += "}\n";
						styleString += ".tabBtnPress\n";
						styleString += " {\n";
						styleString += " cursor: pointer;\n";
						styleString += "color: #00a5e0;\n";
						styleString += "background: #ffffff;\n";
						styleString += "border-bottom: 2px #00a5e0 solid;\n";
						styleString += "}\n";
						styleString += "</style>\n";

					} else if (chirld.type.equals("SliderLayout")) {// 轮播
						start += "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";

						end += "  </div>\n";

						start += "<div class=\"ui-slider\" id=\"slider1\">\n";
						start += "<ul class=\"ui-slider-content\" style=\"width: 300%\">\n";
						start += "<li><span style=\"background-image:url(http://placeholder.qiniudn.com/640x200)\"></span></li>\n";
						start += "<li><span style=\"background-image:url(http://placeholder.qiniudn.com/640x200)\"></span></li>\n";

						end += "</ul>\n";
						end += "</div>\n";

					} else if (chirld.type.equals("ActionSheetLayout")) {

						start += "<div class=\"ui-actionsheet\">\n";
						start += "<div class=\"ui-actionsheet-cnt\">\n";

						end += "</div>\n";
						end += "</div>\n";
						end += "<script type=\"text/javascript\">\n";
						end += "$('.ui-actionsheet').addClass('show');\n";
						end += "</script>\n";
					}

					bodym += "\n" + start;

					parent(chirld);

					bodym += end + "\n";
				} else {// 这个儿子是非容器

					// if (bean.compomentForWeb.equals("tr")) {
					// bodym +=
					// " <td
					// style=\"width:"+((float)1/(float)bean.chirlds.size())*100+"%;
					// text-align: center;\">\n";
					// }

					chirld(chirld, bean);

					// if (bean.compomentForWeb.equals("tr")) {
					// bodym += " </td>\n";
					// }
				}

			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {// 这个儿子是非容器

		String expand = "";
		if ("1bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:1;";
		}
		if ("2bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:2;";
		}
		if ("3bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:3;";
		}

		if (chirld.type.equals("Span")) {

			bodym += "<span id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</span>\n";
			// h4 label

		}

		if (chirld.type.equals("H1-9")) {

			bodym += "<h4 id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</h4>\n";

		}

		if (chirld.type.equals("TextView")) {
			// h4 label

		
			bodym += "<span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" style=\"font-size:"
							+ chirld.textSize + ";color:" + chirld.rgb16 + ";" + expand + "\">" + chirld.cnname
							+ "</span>\n";
				

		}

		if (chirld.type.equals("Label")) {

			bodym += "<label id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" >" + chirld.cnname
					+ "</label>\n";
			// h4 label

		}

		if (chirld.type.equals("Progress")) {
			bodym += " <div class=\"ui-progress\">\n";
			bodym += "<span style=\"width:50%\"></span>\n";
			bodym += "</div>\n";
		}
		if (chirld.type.equals("Loading")) {

			bodym += "<div class=\"ui-loading-block show\">\n";
			bodym += "<div class=\"ui-loading-cnt\">\n";
			bodym += "<i class=\"ui-loading-bright\"></i>\n";
			bodym += "<p>正在加载中...</p>\n";
			bodym += "</div>\n";
			bodym += "</div>\n";
			bodym += "<script type=\"text/javascript\" class=\"demo-script\">\n";
			bodym += " // var el = $.loading({content:'加载中...'});\n";
			bodym += " // el.on(\"loading:hide\",function(){\n";
			bodym += " //     console.log(\"loading hide\");\n";
			bodym += " // });\n";
			bodym += "</script>\n";

		}

		if (chirld.type.equals("PopTips")) {

			bodym += "<div class=\"ui-poptips ui-poptips-info\">\n";
			bodym += "<div class=\"ui-poptips-cnt\"><i></i>" + chirld.cnname + "</div>\n";
			bodym += "</div>\n";
		}

		if (chirld.type.equals("Selecter")) {

			// <span style="background:url(../images/next.png) no-repeat right
			// center #fff; background-size:8px; line-height:20px;
			// display:block; float:left; padding:3px 15px 3px 5px;
			// margin-right:2px; margin-bottom:5px" onClick="selectCount();"
			// id="count` `Name">请选择</span>

			bodym += "<div  style=\"display:flex;\">\n";
			bodym += "<div>" + chirld.cnname + "</div>\n";
			bodym += "<div class=\"ui-select\" style=\"margin-left: 2px;margin-right:2px;\">\n";
			bodym += "<select id=\""+chirld.enname+"\">\n";
			bodym += "<option>2014</option>\n";
			bodym += "<option selected>2015</option>\n";
			bodym += "<option>2016</option>\n";
			bodym += "</select>\n";
			bodym += "</div>\n";
			bodym += "</div>\n";
			
			jsString += "\n<script type=\"text/javascript\">\n";
			jsString+="//select选择\n";
			jsString += "$(\"#"+chirld.enname+"\").change(function () {  \n";
			jsString += "var s = $(this).children('option:selected').val();  \n";
			jsString+="window.where.set(\""+chirld.enname+"\",s);\n";
			jsString += " }); \n"; 
			jsString+="</script>\n";

		}

		if (chirld.type.equals("Button")) {

			if (chirld.picName.equals("图片名")) {

				String bgcolor = "";
				if (chirld.bgRgb16.contains("#")) {
					bgcolor = chirld.bgRgb16;
				}
				String actionstring = "";
				if (chirld.actionString != null) {
					actionstring = chirld.actionString;
				}

				if ((chirld.parent != null && chirld.parent.type != null)) {

					if (chirld.parent.type.toLowerCase().contains("tab")) {
						actionstring = "tabBtnPress(this);";
						expand = "flex:1;";
					}
				}
				bodym += "<button  style=\"padding:6px;background-color:" + bgcolor + ";color:" + chirld.rgb16 + ";"
						+ expand + "\"  onclick=\"" + actionstring + "\" >" + chirld.cnname + "</button>\n";

			} else {

				bodym += "<button  src= \"/images/" + chirld.picName + ".png\" onclick=\"" + chirld.actionString
						+ "\"  style=\"" + expand + "\">" + chirld.cnname + "</button>\n";
			}

		}

		if (chirld.type.equals("Button_Close")) {

			bodym += "<a href=\"#\" class=\"ui-icon-close\"></a>\n";

		}

		if (chirld.type.equals("leftArrow")) {

			bodym += "<div onclick=\"history.back()\" style=\"margin:10px; width: 10px; height: 10px; border-top: 2px solid #dfdfdf; border-right: 2px solid #dfdfdf; transform: rotate(225deg);\"></div>\n";

		}

		if (chirld.type.equals("rightArrow")) {
			bodym += "<div style=\"margin:10px; width: 7px; height: 7px; border-top: 2px solid #dfdfdf; border-right: 2px solid #dfdfdf; transform: rotate(45deg);\"></div>\n";

		}

		if (chirld.type.equals("CheckBox")) {
			bodym += " <label class=\"ui-checkbox\" style=\"margin:10px\">\n";
			bodym += "<input type=\"checkbox\" name=\"checkbox\" value=\"\">\n";
			bodym += "</label>\n";
			bodym += "<p>" + chirld.cnname + "</p>\n";
			
			jsString+="\n<script>\n";
			jsString+="//check\n";
			jsString+="function checkWhich()\n";
			jsString+="{\n";
			jsString+="var checkboxs=document.getElementsByName(\"checkbox\");\n";
			jsString+="var count=checkboxs.length;\n";
			jsString+="var chestr=\"\";\n";
			jsString+="for (i=0;i<count;i++)\n";
			jsString+="{\n";
			jsString+="if(checkboxs[i].checked == true)\n";
			jsString+="{\n";
			jsString+="chestr+=checkboxs[i].value+\",\";\n";
			jsString+="}\n";
			jsString+="}\n";
			jsString+="if(chestr == \"\")\n";
			jsString+="{\n";
			jsString+="alert(\"请先选择复选框～！\");\n";
			jsString+="}\n";
			jsString+="}\n\n";
			
			jsString+="function checkAll(){\n";
			jsString+="$(document.getElementsByName(\"checkbox\")).each(function(i){\n";
		    jsString+="  this.checked = true;\n";
			jsString+="})\n";
			jsString+="}\n\n";
			
			jsString+="function unCheckAll(){\n";
			jsString+="$(document.getElementsByName(\"checkbox\")).each(function(i){\n";
		    jsString+="  this.checked = false;\n";
			jsString+="})\n";
			jsString+="}\n";
			
			
			jsString+="</script>\n";

		}

		if (chirld.type.equals("CheckBox_Switch")) {
			bodym += "<label class=\"ui-switch\">\n";
			bodym += "<input type=\"checkbox\">\n";
			bodym += "</label>\n";
		}

		if (chirld.type.equals("Radio")) {
			bodym += "<div class=\"ui-form-item ui-form-item-radio ui-border-b\">\n";
			bodym += " <label class=\"ui-radio\" for=\"radio\">\n";
			bodym += "<input type=\"radio\" name=\"radio\" value=\"\">\n";
			bodym += "</label>\n";
			bodym += " <p>"+chirld.cnname+"</p>\n";
			bodym += "</div>\n";

			
			jsString+="\n<script>\n";
			jsString+="var bFlag = false;\n";
		    jsString+="var valueString=\"\";\n";
			jsString+="var gender = document.getElementsByName('radio');\n";
			jsString+="for (var i = 0; i < gender.length; i++) {\n";
			jsString+="if (gender[i].checked) {\n";
			jsString+="bFlag = true;\n";
			jsString+="valueString=gender[i].value;\n";
			jsString+="break;\n";
			jsString+="}\n";
			jsString+="}\n";
			jsString+="if (bFlag == false) {\n";
			jsString+="alert('不能为空，请选择！')\n";
			jsString+="return false;\n";
			jsString+="}\n";
			jsString+="</script>\n";

		}

		if (chirld.type.equals("EditText")) {

			bodym += "<input style=\" border: 0; line-height: " + chirld.h + "px; height: " + chirld.h
					+ "px;  font-size: " + chirld.textSize + "px;" + expand + "\"  type=\"text\"  id=\"" + chirld.enname
					+ "\" name=\"" + chirld.enname + "\" placeholder=\"" + chirld.cnname + "\">";

		}

		if (chirld.type.equals("ImageView")) {

			if (chirld.parent.type.toLowerCase().contains("grid")
					|| chirld.parent.parent.type.toLowerCase().contains("grid")) {
				String columncount = "trisect";
				String picsize = "190x284";
				if (chirld.parent.type.toLowerCase().contains("halve")
						|| chirld.parent.parent.type.toLowerCase().contains("halve")) {
					columncount = "halve";
					picsize = "290x160";
				}
				bodym += "<div class=\"ui-grid-" + columncount + "-img\">\n";
				bodym += "<span style=\"background-image:url(http://placeholder.qiniudn.com/" + picsize
						+ ")\"></span>\n";
				bodym += "</div>\n";
			}

			if (chirld.parent.type.toLowerCase().contains("list")
					|| chirld.parent.parent.type.toLowerCase().contains("list")) {

				bodym += "<div class=\"ui-list-img\">\n";
				bodym += "<!--class=\"ui-avatar\"  圆形框-->\n";
				bodym += "<span style=\"background-image:url(http://placeholder.qiniudn.com/200x136)\"></span>\n";
				bodym += "</div>\n";
			}

		}

		if (chirld.type.equals("ExpandableListView")) {

			bodym += "<div class=\"ui-selector-content\" style=\"display:\">\n";
			bodym += "<ul>\n";
			bodym += "<li class=\"ui-selector-item active\">\n";
			bodym += "<h3 class=\"ui-border-b\">\n";
			bodym += "<p>最近在玩的好友</p><span class=\"ui-txt-info\">11</span>\n";
			bodym += "</h3>\n";
			bodym += "<ul class=\"ui-list ui-border-b\">\n";
			bodym += "<li>\n";
			bodym += "<div class=\"ui-avatar-s\">\n";
			bodym += "<span style=\"background-image:url(../img/ava1.png)\"></span>\n";
			bodym += "</div>\n";
			bodym += "<div class=\"ui-list-info ui-border-t\"><h4>飞翔的企鹅</h4></div>\n";
			bodym += "</li>\n";
			bodym += "</ul>\n";
			bodym += "</li>\n";
			bodym += "  <li class=\" ui-selector-item\">\n";
			bodym += " <h3 class=\"ui-border-b\">\n";
			bodym += "  <p>最近在玩的好友</p><span class=\"ui-txt-info\">11</span>\n";
			bodym += "</h3>\n";
			bodym += "<ul class=\"ui-list ui-border-b\">\n";
			bodym += "<li>\n";
			bodym += "<div class=\"ui-avatar-s\">\n";
			bodym += "<span style=\"background-image:url(../img/ava1.png)\"></span>\n";
			bodym += " </div>\n";
			bodym += "  <div class=\"ui-list-info ui-border-t\"><h4>飞翔的企鹅</h4></div>\n";
			bodym += " </li>\n";

			bodym += " </ul>\n";
			bodym += " </li>\n";

			bodym += " </ul>\n";
			bodym += " </div>\n";
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

}
