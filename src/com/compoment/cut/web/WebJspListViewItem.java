
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
	String js="";
	String connection = "";
	String pageName = "";
	String className = "";
	String formmStart = "";
	String formmEnd = "";

	int rootViewWidth = 320;
	int rootViewHeight = 568;

	public WebJspListViewItem(int cellWidth, int cellHeight) {
		rootViewWidth = cellWidth;
		rootViewHeight = cellHeight;
	}

	public WebJspListViewItem(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);

		String body = analyse(oldBeans);

		System.out.println(bodym);

		// FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className
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
			if (bean.type.equals("RelativeLayout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {

					if (bean.chirlds.size() == 2) {
						if (bean.orientation.equals("horizontal")) {

							bean.relativeForWeb += " position:fixed; ";
							

							CompomentBean chirld1 = bean.chirlds.get(0);
							CompomentBean chirld2 = bean.chirlds.get(1);
							if (chirld1.x < chirld2.x) {
								chirld1.relativeForWeb += "position: absolute; left:"+chirld1.x+"px;";
								chirld2.relativeForWeb += "position: absolute;     ";
							} else if (chirld1.x > chirld2.x) {
								chirld2.relativeForWeb += "position: absolute;  left:"+chirld2.x+"px;  ";
								chirld1.relativeForWeb += "position: absolute;    ";
							}

						} else if (bean.orientation.equals("vertical")) {

						}

					} else if (bean.chirlds.size() == 3) {

						if (bean.orientation.equals("horizontal")) {

						
							bean.relativeForWeb += " position:fixed; ";
							
						

							Collections.sort(bean.chirlds, comparatorX);
							int i = 0;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;
									chirld.relativeForWeb += "position: absolute;left:"+chirld.x+";";

								} else if (i == 1) {
									float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;
									chirld.relativeForWeb += " ";

								} else if (i == 2) {
									float rightvalue = (float) (bean.x + bean.w - chirld.x - chirld.w) / (float) 320
											* 100;
									chirld.relativeForWeb += "position: absolute;right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px;";
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {

						}

					} else if (bean.chirlds.size() > 3) {

						if (bean.orientation.equals("horizontal")) {
							bean.relativeForWeb += "";

							Collections.sort(bean.chirlds, comparatorX);

							int i = 0;

							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {

									float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;
									chirld.relativeForWeb += "position: absolute;left:"+chirld.x+";";

								} else if (i == bean.chirlds.size() - 1) {
									float rightvalue = (float) (bean.x + bean.w - chirld.x - chirld.w) / (float) 320
											* 100;
									chirld.relativeForWeb += "position: absolute;right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px;";
								} else {

								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {

						}
					}
				}
			} else if (bean.type.equals("LinearLayout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {

					if (bean.orientation.equals("horizontal")) {
						bean.relativeForWeb += "";
						Collections.sort(bean.chirlds, comparatorX);

						int i = 0;
						CompomentBean beforeChirld = null;
						for (CompomentBean chirld : bean.chirlds) {
							if (i == 0) {
								float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;
								
							
									chirld.relativeForWeb = "";
								
								

								beforeChirld = chirld;
							} else if (i == bean.chirlds.size() - 1) {
								float rightvalue = (float) (bean.x + bean.w - chirld.x - chirld.w) / (float) 320 * 100;
							
									chirld.relativeForWeb = "";
								
							} else {
								float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;
								
									chirld.relativeForWeb = "";
								
								beforeChirld = chirld;
							}
							i++;
						}

						Collections.sort(bean.chirlds, comparatorDate);
					} else if (bean.orientation.equals("vertical")) {
						
							bean.relativeForWeb += "   ";

						Collections.sort(bean.chirlds, comparatorY);

						int i = 0;
						CompomentBean beforeChirld = null;
						for (CompomentBean chirld : bean.chirlds) {
							if (i == 0) {
								float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;

								if ((chirld.chirlds != null && chirld.chirlds.size() > 0)
										&& chirld.compomentForWeb.equals("")) {
									chirld.relativeForWeb += "  ";
									
								}

								beforeChirld = chirld;
							} else if (i == bean.chirlds.size() - 1) {

								float rightvalue = (float) (bean.x + bean.w - chirld.x - chirld.w) / (float) 320 * 100;
								if ((chirld.chirlds != null && chirld.chirlds.size() > 0)
										&& chirld.compomentForWeb.equals("")) {
									chirld.relativeForWeb += "  ";
									
								}
							} else {
								float leftvalue = (float) (chirld.x - bean.x) / (float) 320 * 100;
								if ((chirld.chirlds != null && chirld.chirlds.size() > 0)
										&& chirld.compomentForWeb.equals("")) {
									chirld.relativeForWeb += "  ";
									
								}
								beforeChirld = chirld;
							}
							i++;
						}

						Collections.sort(bean.chirlds, comparatorDate);
					}
				}

			}
		}

		
	
		parent(maxBean);

		
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
					
					
					if(bean.type.equals("ListView"))
					{
						
					}

					String start = "";
					String end = "";
					if (chirld.compomentForWeb.equals("table")) {
						start = "  <" + chirld.compomentForWeb+" style=\"width:100%;\">\n";

						end = "  </" + chirld.compomentForWeb + ">\n";
					} else if (chirld.compomentForWeb.equals("tr")) {
						start = "  <" + chirld.compomentForWeb + ">\n";

						end = "  </" + chirld.compomentForWeb + ">\n";
					} else if (chirld.compomentForWeb.equals("ul")) {
						start = "  <" + chirld.compomentForWeb + " style=\"margin: 0px;padding:0px;\">\n";

						end = "  </" + chirld.compomentForWeb + ">\n";
					} else if (chirld.compomentForWeb.equals("li")) {
						start = "  <" + chirld.compomentForWeb + " style=\" list-style: none  ; padding: 10px; border-bottom: 1px solid #f5f5f5; color: #666;\" >\n";

						end = "  </" + chirld.compomentForWeb + ">\n";
					} else {
						start = "<div style=\" height:"+chirld.h+"px; line-height: " + chirld.h + "px; text-align: center; left:0; background-color:" + chirld.bgRgb16+"\" >\n";


						end = "  </div>\n";
					}

					bodym += start;

					parent(chirld);

					bodym += end;
				} else {// 这个儿子是非容器

					if (bean.compomentForWeb.equals("tr")) {
						bodym += "  <td style=\"width:"+((float)1/(float)bean.chirlds.size())*100+"%; text-align: center;\">\n";
					}

					chirld(chirld, bean);

					if (bean.compomentForWeb.equals("tr")) {
						bodym += "  </td>\n";
					}
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {// 这个儿子是非容器

		if (chirld.type.equals("TextView")) {

			bodym += " <span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" style=\""
					+ chirld.relativeForWeb + "  font-size: " + chirld.textSize + "px; color: " + chirld.rgb16
					+ ";  \">" + chirld.cnname + "</span>\n";

		}

		if (chirld.type.equals("Line")) {

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

				bodym += "<a href=\"#\" id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\"  onclick=\""
						+ chirld.actionString + "\"  style=\"text-align: center; height:" + chirld.h
						+ "px; line-height: " + chirld.h + "px; border-radius: 8px; color:" + chirld.rgb16
						+ "; background-color:" + chirld.bgRgb16 + ";  font-size: " + chirld.textSize + "px ;"
						+ chirld.relativeForWeb + " margin:1px;  padding: 1px; text-decoration: none;\" >" + chirld.cnname + "</a> \n";
				

			} else {

				bodym += "<img id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" src= \"/images/"
						+ chirld.picName + ".png\" onclick=\"" + chirld.actionString + ";\" style=\" width: 12px; "
						+ chirld.relativeForWeb + "\">\n";
			}

		}

		if (chirld.type.equals("CheckBox")) {

			js +="//checkbox \n";
			js += "var setAddr = false;\n";
			js += "function setDefaultAddr(){\n";
			js += "	if(setAddr){\n";
			js += "	setAddr = false;\n";
			js += "	$(\"#"+chirld.enname+"\").attr(\"style\",\""+chirld.relativeForWeb +" background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;\");\n";
			js += "}else{\n";
			js += "	setAddr = true;\n";
			js += "	$(\"#"+chirld.enname+"\").attr(\"style\",\" "+ chirld.relativeForWeb +" background: url(images/checked.png) no-repeat 2px center;  padding-left: 35px; padding-top:0px; padding-bottom:0px;\");\n";
			js += "}\n";
			js += "}\n\n";

			
			bodym += "<span id=\""+chirld.enname+"\" style=\" "+ chirld.relativeForWeb +" background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;\"  onclick=\"setDefaultAddr()\">"+chirld.cnname+"</span>";

			
		}

		if (chirld.type.equals("EditText")) {

			
			bodym += " <input style=\" border: 0; line-height: "+chirld.h+"px; height: "+chirld.h+"px;  font-size: "+chirld.textSize+"px;\"  type=\"text\"  id=\""+chirld.enname+"\" name=\""+chirld.enname+"\" placeholder=\""+chirld.cnname+"\">";

			

		}

		if (chirld.type.equals("CheckBox")) {
			// m += "/**" + chirld.cnname + "*/\n";
			// m += " " + chirld.enname + " = new JCheckBox(\""
			// + chirld.cnname + "\");\n";
			// m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
		
			bodym += "<div id=\"listSpace\"></div>\n";
			bodym += "<input type=\"hidden\"  name=\"page_code\" id=\"page_code\" value=\"1\" />\n";
			bodym += "<input type=\"hidden\"  name=\"page_num\" id=\"page_num\" value=\"10\" />\n";
			bodym += "<input type=\"hidden\" id=\"flag\" name=\"shoppingCar\" value=\"<%=request.getAttribute(\"shoppingCar\") %>\">\n";

			
		

		
		
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
	
	
	
	public void listViewItem(CompomentBean listViewItemBean)
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
		js+="			    if(flg == 0){//按查询时\n";
		js+="			    	$(\"#page_code\").val(1);			    	\n";
		js+="			    }		    \n";
		js+="			   \n";
		js+="			    currentCount = 0;\n";

		js+="			    var flag = $(\"#flag\").val();\n";
		js+="			    var my_msg = show_hide_msg(\"页面加载中...\");\n";
		js+="			    $(\"#emptyOrErrorMsg\").html(\"\");\n";
		js+="			    $.ajax({  \n";
		js+="			        url:'/jiyou/wx/AddressAction!queryAddressList.do?',  \n";
		js+="			        data: { \"page_code\": $(\"#page_code\").val()} ,  \n";
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
		js+="			        		var msg = data.result;\n";
		js+="			     		    if(msg == \"success\"){\n";
		js+="			     		    	\n";
		js+="			         		    var listData = data.rltdata.redo;\n";
		js+="			         		    currentCount = listData.length;\n";

		js+="				        		if(flg == 0){//按查询时\n";
		js+="				        			 $(\"#listSpace\").html(\"\");				    	\n";
		js+="				     		    }\n";
		js+="				            	var opt = \"\";\n";
		js+="								for(var i=0;i<listData.length;i++){\n";
		
		
		
		js+="									opt += '<div class=\"box05\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"cfTable\"><tr class=\"tr01\">';\n";
		js+="									if(flag == \"1\"){//表示从购物车页面进入\n";
		js+="										var param = \"recvName=\"+listData[i].recvName+\"&provCode=\"+listData[i].provCode+\"&cityCode=\"+listData[i].cityCode+\"&countCode=\"+listData[i].countCode+\n";
		js+="										\"&detailAddress=\"+listData[i].detailAddress+\"&mobileNo=\"+listData[i].mobileNo+\"&postCode=\"+listData[i].postCode+\"&addressID=\"+listData[i].addressID;\n";

		js+="										if(listData[i].isDefaultAddress == '0' ){\n";
		js+="											opt += '<td><dd onclick=\"selectAddress(this, \''+ param+'\')\" class=\"checked04\"><input type=\"checkbox\" name=\"addressID_box\" class=\"checkbox_sty\" value=\"'+ listData[i].addressID +'\" checked=\"checked\"/>默认地址</dd></td>';\n";
		js+="										}else{\n";
		js+="											opt += '<td><dd onclick=\"selectAddress(this, \''+ param+'\')\" class=\"check04\"><input type=\"checkbox\" name=\"addressID_box\" class=\"checkbox_sty\" value=\"'+ listData[i].addressID +'\" />选择该地址</dd></td>';\n";
		js+="										}\n";
		js+="									}else{ //表明从会员首页点击\"我的地址\"进入\n";
		js+="										if(listData[i].isDefaultAddress == '0' ){\n";
		js+="											opt += '<td >默认地址</td>';\n";
		js+="										}else{\n";
		js+="											opt += '<td ></td>';\n";
		js+="										}\n";
		js+="										\n";
		js+="									}\n";
		js+="									opt += '<td width=\"60\"><a  href=\"#\" class=\"del02\" onclick=\"deleteAddress(\''+listData[i].addressID+'\')\">删除</a></td>';\n";
		js+="									opt += ' </tr><tr><td><p class=\"p01\">收货人：'+listData[i].recvName+'&nbsp;&nbsp;&nbsp;&nbsp;'+listData[i].mobileNo+'</p>';\n";
		js+="									opt += '<p class=\"p02\">'+listData[i].totalAddress+'&nbsp;&nbsp;&nbsp;&nbsp;'+listData[i].postCode+'</p></td>';\n";
		js+="									opt += '</tr></table></div>';\n";
		
		
		
		
		
		js+="								}\n";

		js+="								$(\"#page_code\").val(Number($(\"#page_code\").val()) + 1);		\n";
		js+="				\n";
		js+="								if(listData.length >= 10){\n";
		js+="					        		stop = true;//到底部时加载判断\n";
		js+="					        	} \n";
		js+="					        	if(listData.length == 0){\n";
		js+="			          				$(\"#emptyOrErrorMsg\").html('<br><br><br><br><div style=\"text-align:center;\">暂无地址信息</div>');\n";
		js+="				            	}\n";
		js+="					        	$(opt).appendTo($(\"#listSpace\"));\n";
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
