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
	String formmStart = "";
	String formmEnd = "";

	int rootViewWidth = 320;
	int rootViewHeight = 568;

	CompomentBean newParent;

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

		bodym += "<body>\n";

		bodym += "<div id=\"emptyOrErrorMsg\"></div>\n";

		bodym += formmStart;
		parent(maxBean);

		bodym += formmEnd;

		bodym += "</body>\n";

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

	// // 用于调整顺序
	// int position = 0;
	// boolean haveHeaderLayout = false;
	// boolean haveFooterLayout = false;
	//
	// public void changePosition(CompomentBean bean) {
	// // 有 儿子
	// if (bean.chirlds != null && bean.chirlds.size() > 0) {
	//
	// for (CompomentBean chirld : bean.chirlds) {
	//
	// // 这个儿子是容器 layout
	// if (chirld.chirlds != null && chirld.chirlds.size() > 0) {
	//
	// if (chirld.type.equals("HeaderLayout")) {
	// if (position != 0) {
	//
	// bean.chirlds.add(0, chirld);
	//
	// bean.chirlds.remove(position + 1);
	// }
	// haveHeaderLayout = true;
	//
	// }
	//
	// if (chirld.type.equals("FooterLayout")) {
	// if (haveHeaderLayout == true && position != 1) {
	//
	// bean.chirlds.add(1, chirld);
	// bean.chirlds.remove(position + 1);
	//
	// } else if (haveHeaderLayout == false && position != 0) {
	// bean.chirlds.add(0, chirld);
	// bean.chirlds.remove(position);
	// }
	// haveFooterLayout = true;
	//
	// }
	//
	// if (chirld.type.equals("SectionLayout")) {
	// if (haveHeaderLayout == true && haveFooterLayout == true && position != 2) {
	// bean.chirlds.add(2, chirld);
	// bean.chirlds.remove(position + 1);
	// } else if (haveHeaderLayout == true && haveFooterLayout != true && position
	// != 1) {
	// bean.chirlds.add(1, chirld);
	// bean.chirlds.remove(position + 1);
	// } else if (haveHeaderLayout != true && haveFooterLayout == true && position
	// != 1) {
	// bean.chirlds.add(1, chirld);
	// bean.chirlds.remove(position + 1);
	// }
	//
	// }
	//
	// } else {// 这个儿子是非容器
	//
	// }
	//
	// position++;
	// }
	//
	// }
	//
	// }

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

					if (chirld.type.equals("DivLayoutHorizon")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-row-flex ui-whitespace\" >\n";

						end += "  </div>\n";
					} else if (chirld.type.equals("DivLayoutVertical")) {

						start += "<div id=\"" + chirld.enname
								+ "\" class=\"ui-row-flex ui-whitespace ui-row-flex-ver\" >\n";

						end += "  </div>\n";
					} else if (chirld.type.equals("DivLayout1fen4")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-col\" >\n";

						end += "  </div>\n";
					} else if (chirld.type.equals("DivLayout2fen4")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-col\" >\n";

						end += "  </div>\n";
					} else if (chirld.type.equals("DivLayout3fen4")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-col ui-col-3\" >\n";

						end += "  </div>\n";
					} else if (chirld.type.equals("DivLayout4fen4")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-col\" >\n";

						end += "  </div>\n";
					} else if (chirld.type.equals("HeaderLayout")) {
						start += "<header id=\"" + chirld.enname
								+ "\" class=\"ui-header ui-header-stable ui-border-b\" style=\"background-color:"
								+ chirld.bgRgb16 + ";\">\n";

						end += "</header>\n";
					} else if (chirld.type.equals("FooterLayout")) {
						start += "<footer id=\"" + chirld.enname
								+ "\" class=\"ui-footer ui-footer-stable ui-border-t\" style=\"background-color:"
								+ chirld.bgRgb16 + ";\">\n";

						end += "</footer>\n";
					} else if (chirld.type.equals("SectionLayout")) {
						start += "<section id=\"" + chirld.enname + "\" class=\"ui-container \">\n";

						end += "  </section>\n";
					} else if (chirld.type.equals("TableLayout")) {

						start += "<table id=\"" + chirld.enname + "\" class=\"ui-table ui-border\">\n";

						end += "</table>\n";
					} else if (chirld.type.equals("Table_TRLayout")) {
						start += "<tr id=\"" + chirld.enname + "\" >\n";

						end += "  </tr>\n";
					} else if (chirld.type.equals("Table_THLayout")) {
						start += "<td id=\"" + chirld.enname + "\" >\n";

						end += "  </td>\n";
					} else if (chirld.type.equals("FormLayout")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-form ui-border-t\">\n";
						start += "<form action=\"#\">\n";

						end += " </form>\n";
						end += "  </div>\n";
					} else if (chirld.type.equals("Form_ItemLayout")) {

						start += " <div id=\"" + chirld.enname + "\"  class=\"ui-form-item ui-border-b\">\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("ListLayout_LeftRight")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-list  ui-list-text ui-border-tb\">\n";
						start += "<!--ui-list-link-->箭头\n";

						end += "  </ul>\n";
					} else if (chirld.type.equals("ListLayout_Left[LeftRight]")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-list ui-list-one ui-border-tb\">\n";
						start += "<!--ui-list-link-->箭头\n";

						end += "  </ul>\n";
					} else if (chirld.type.equals("ListLayout_LeftLeft")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-list  ui-border-tb\">\n";
						start += "<!--ui-list-link-->箭头\n";

						end += "  </ul>\n";
					}

					else if (chirld.type.equals("ListLayout_TopBottom")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-list ui-list-pure ui-border-tb\">\n";

						end += "  </ul>\n";
					} else if (chirld.type.equals("ListLayout_LeftTopBottom")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-list  ui-border-tb\">\n";

						end += "  </ul>\n";
					}

					else if (chirld.type.equals("List_ItemLayout")) {

						start += " <li id=\"" + chirld.enname + "\" class=\"ui-border-t\">\n";

						end += "  </li>\n";
					} else if (chirld.type.equals("GridLayout2column")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-grid-halve\">\n";

						end += "</ul>\n";
					} else if (chirld.type.equals("GridLayout3column")) {

						start += "<ul id=\"" + chirld.enname + "\" class=\"ui-grid-trisect\">\n";

						end += "</ul>\n";
					} else if (chirld.type.equals("Grid_ItemLayoutVertical")) {

						start += "<li id=\"" + chirld.enname + "\"  >\n";
						start += "<div class=\"ui-border\">\n";

						end += "</div>\n";
						end += "</li>\n";
					} else if (chirld.type.equals("DialogLayout")) {

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
						end += "<script class=\"demo-script\">\n";
						end += "$(\".ui-dialog\").dialog(\"show\");\n";
						end += "</script>\n";

					} else if (chirld.type.equals("TabLayout")) {

						start += "<div class=\"ui-tab\" id=\"" + chirld.enname + "\" >\n";
						start += "<ul class=\"ui-tab-nav ui-border-b\">\n";
						start += " <li class=\"current\">热门推荐</li>\n";
						start += " <li>全部表情</li>\n";
						start += " <li>表情</li>\n";
						start += "</ul>\n";
						start += " <ul class=\"ui-tab-content\" style=\"width:300%\">\n";

						end += "</ul>\n";
						end += "</div>\n";

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

		if (chirld.type.equals("Span")) {

			bodym += "<span id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</span>\n";
			// h4 label

		}

		if (chirld.type.equals("H1-9")) {

			bodym += "<h4 id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</h4>\n";

		}

		if (chirld.type.equals("TextView")) {
			// h4 label

			if ((chirld.parent.parent != null && chirld.parent.parent.type != null)) {

				if (chirld.parent.parent.type.toLowerCase().contains("header")) {
					bodym += "<h1 id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</h1>\n";
				} else if (chirld.parent.parent.type.toLowerCase().contains("list")) {

					bodym += "<div class=\"ui-list-info\">\n";
					bodym += "<div class=\"ui-txt-info\">" + chirld.cnname + "</div>\n";
					bodym += "</div>\n";

				} else if (chirld.parent.parent.type.toLowerCase().contains("form")) {
					bodym += "<label id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" >" + chirld.cnname
							+ "</label>\n";
				} else {
					bodym += "<span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" >" + chirld.cnname
							+ "</span>\n";
				}

			}

			if ((chirld.parent != null && chirld.parent.type != null)) {

				if (chirld.parent.type.toLowerCase().contains("header")) {
					bodym += "<h1 id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</h1>\n";
				} else if (chirld.parent.type.toLowerCase().contains("list")) {

					bodym += "<div class=\"ui-list-info\">\n";
					bodym += "<div class=\"ui-txt-info\">" + chirld.cnname + "</div>\n";
					bodym += "</div>\n";

				} else if (chirld.parent.type.toLowerCase().contains("form")) {
					bodym += "<label id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" >" + chirld.cnname
							+ "</label>\n";
				} else {
					bodym += "<span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" >" + chirld.cnname
							+ "</span>\n";
				}

			}
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

			bodym += "<div class=\"ui-select-group\">\n";
			bodym += "<div class=\"ui-select\">\n";
			bodym += "<select>\n";
			bodym += "<option>2014</option>\n";
			bodym += "<option selected>2015</option>\n";
			bodym += "<option>2016</option>\n";
			bodym += "</select>\n";
			bodym += "</div>\n";
			bodym += "</div>\n";
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

				bodym += "<button class=\"ui-btn\"  style=\"background-color:" + bgcolor + ";color:" + chirld.rgb16
						+ ";\"  onclick=\"" + actionstring + "\" >" + chirld.cnname + "</button>\n";

			} else {

				bodym += "<button class=\"ui-btn\" src= \"/images/" + chirld.picName + ".png\" onclick=\""
						+ chirld.actionString + "\" >" + chirld.cnname + "</button>\n";
			}

		}

		if (chirld.type.equals("Button_Close")) {

			bodym += "<a href=\"#\" class=\"ui-icon-close\"></a>\n";

		}

		if (chirld.type.equals("CheckBox")) {
			bodym += " <label class=\"ui-checkbox\">\n";
			bodym += "<input type=\"checkbox\">\n";
			bodym += "</label>\n";
			bodym += "<p>" + chirld.cnname + "</p>\n";

		}

		if (chirld.type.equals("CheckBox_Switch")) {
			bodym += "<label class=\"ui-switch\">\n";
			bodym += "<input type=\"checkbox\">\n";
			bodym += "</label>\n";
		}

		if (chirld.type.equals("Radio")) {
			bodym += "<div class=\"ui-form-item ui-form-item-radio ui-border-b\">\n";
			bodym += " <label class=\"ui-radio\" for=\"radio\">\n";
			bodym += "<input type=\"radio\" name=\"radio\">\n";
			bodym += "</label>\n";
			bodym += " <p>表单中用于单选操作</p>\n";
			bodym += "</div>\n";
			bodym += "<div class=\"ui-form-item ui-form-item-radio ui-border-b\">\n";

			bodym += "<label class=\"ui-radio\" for=\"radio\">\n";
			bodym += "<input type=\"radio\" checked name=\"radio\">\n";
			bodym += "</label>\n";
			bodym += "<p>表单中用于单选操作</p>\n";
			bodym += "</div>\n";

		}

		if (chirld.type.equals("EditText")) {

			bodym += "<div style=" + chirld.relativeForWeb + "> <input style=\" border: 0; line-height: " + chirld.h
					+ "px; height: " + chirld.h + "px;  font-size: " + chirld.textSize + "px;\"  type=\"text\"  id=\""
					+ chirld.enname + "\" name=\"" + chirld.enname + "\" placeholder=\"" + chirld.cnname + "\"></div>";

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
