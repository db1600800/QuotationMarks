
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



		System.out.println(bodym);

		//FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className
			//	+ "ViewController", "xib", m);

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
										chirld1.relativeForWeb += "float:left; margin-left:"+(chirld1.x-bean.x)+"px;  margin-top:"+(chirld1.y-bean.y)+"px;  margin-bottom:"+(bean.y+bean.h-chirld1.y-chirld1.h)+"px; ";
										chirld2.relativeForWeb += "float:right;  margin-top:"+(chirld2.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld2.x-chirld2.w)+"px; margin-bottom:"+(bean.y+bean.h-chirld2.y-chirld2.h)+"px; ";
									} else if (chirld1.x > chirld2.x) {
										chirld2.relativeForWeb += "float:left; margin-left:"+(chirld2.x-bean.x)+"px;  margin-top:"+(chirld2.y-bean.y)+"px;  margin-bottom:"+(bean.y+bean.h-chirld2.y-chirld2.h)+"px; ";
										chirld1.relativeForWeb += "float:right; margin-top:"+(chirld1.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld1.x-chirld1.w)+"px; margin-bottom:"+(bean.y+bean.h-chirld1.y-chirld1.h)+"px; ";
									}
									
								} else if (bean.orientation.equals("vertical")) {

								}

							} else if (bean.chirlds.size() == 3) {

								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									
									Collections.sort(bean.chirlds, comparatorX);
									int i = 0;
									for (CompomentBean chirld : bean.chirlds) {
										if (i == 0) {
											chirld.relativeForWeb += "float:left;  margin-left:"+(chirld.x-bean.x)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px; ";

										} else if (i == 1) {
											chirld.relativeForWeb += "float:center; ";

										} else if (i == 2) {
											chirld.relativeForWeb += "float:right;  margin-top:"+(chirld.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px; margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px; ";
										}
										i++;
									}

									Collections.sort(bean.chirlds, comparatorDate);
								} else if (bean.orientation.equals("vertical")) {
									
								}

							} else if (bean.chirlds.size() > 3) {

								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									
									Collections.sort(bean.chirlds, comparatorX);

									int i = 0;
									
									for (CompomentBean chirld : bean.chirlds) {
										if (i == 0) {
											chirld.relativeForWeb += "float:left;  margin-left:"+(chirld.x-bean.x)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px; ";
										
										} else if (i == bean.chirlds.size() - 1) {
											chirld.relativeForWeb += "float:right; margin-top:"+(chirld.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px; margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px;";
										} else {
										
										}
										i++;
									}

									Collections.sort(bean.chirlds, comparatorDate);
								} else if (bean.orientation.equals("vertical")) {

								}
							}
						}
					}else if (bean.type.equals("LinearLayout"))
					{
						if (bean.chirlds != null && bean.chirlds.size() > 1) {
							
								if (bean.orientation.equals("horizontal")) {
									bean.relativeForWeb+="vertical-align:middle;";
									Collections.sort(bean.chirlds, comparatorX);
									
									int i = 0;
									CompomentBean beforeChirld = null;
									for (CompomentBean chirld : bean.chirlds) {
										if (i == 0) {
											chirld.relativeForWeb += "  margin-left:"+(chirld.x-bean.x)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px; ";
											beforeChirld=chirld;
										} else if (i == bean.chirlds.size() - 1) {
											chirld.relativeForWeb += " margin-top:"+(chirld.y-bean.y)+"px; margin-right:"+(bean.x+bean.w-chirld.x-chirld.w)+"px; margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px;";
										} else {
										
											chirld.relativeForWeb += "  margin-left:"+(chirld.x-beforeChirld.x-beforeChirld.w)+"px;  margin-top:"+(chirld.y-bean.y)+"px;  margin-bottom:"+(bean.y+bean.h-chirld.y-chirld.h)+"px; ";
											beforeChirld=chirld;
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
		bodym+="<div style=\" width:100%; height:"+maxBean.h+"px;  background-color:"+maxBean.bgRgb16+"; \">\n";
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
			
			
		}

		
		
		if (chirld.type.equals("Button")) {
			
			
		    if(!chirld.picName.equals("图片名"))
		    {
			
			bodym += " <state key=\"normal\" title=\""
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
	
	
	
	
	
	
	
	
	
//	<html>
//	<body>
//	<div style=" width:100%;height:100%;position:absolute; left:0; top:0; " >
//
//	<div style=" width:100%; height:37px;  background-color:#FC5F28; ">
//
//	<span style=" margin-left:38px; " >新油品</span>
//
//
//
//	  </div>
//
//	<div style=" width:100%; height:37px;  background-color:#FC5F28; ">
//
//	<span style=" margin-left:38px; " >新油品</span>
//
//
//
//	  </div>
//
//	  </div>
//	</body>
//	</html>
}
