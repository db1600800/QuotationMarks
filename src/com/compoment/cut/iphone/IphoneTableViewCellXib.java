package com.compoment.cut.iphone;

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

import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class IphoneTableViewCellXib {

	String bodym= "\n\n\n";
	String connection = "";
	String pageName = "";
    String className="";
    String tableViewCellContentViewId="";
    String tableViewCellid="";

    
	public IphoneTableViewCellXib(String pageName,List<CompomentBean> oldBeans) {
		this.pageName=pageName;
		className=firstCharToUpperAndJavaName(pageName);
		
		
		String body=analyse(oldBeans);


		String m="";
		
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m+="<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.XIB\" version=\"3.0\" toolsVersion=\"6751\" systemVersion=\"14D131\" targetRuntime=\"iOS.CocoaTouch\" propertyAccessControl=\"none\" useAutolayout=\"YES\" useTraitCollections=\"YES\">\n";
		m+="    <dependencies>\n";
		m+="        <deployment identifier=\"iOS\"/>\n";
		m+="        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"6736\"/>\n";
		m+="        <capability name=\"Constraints to layout margins\" minToolsVersion=\"6.0\"/>\n";
		m+="        <capability name=\"Constraints with non-1.0 multipliers\" minToolsVersion=\"5.1\"/>\n";
		m+="    </dependencies>\n";
		m+="    <objects>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\"/>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";
		 tableViewCellid=id();
		tableViewCellContentViewId=id();
		m+="        <tableViewCell contentMode=\"scaleToFill\" selectionStyle=\"blue\" indentationWidth=\"0.0\" rowHeight=\""+maxBean.h+"\" id=\""+tableViewCellid+"\" customClass=\""+className+"TableViewCell\">\n";
		m+="            <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\""+maxBean.h+"\"/>\n";
		m+="            <autoresizingMask key=\"autoresizingMask\"/>\n";
		m+="            <tableViewCellContentView key=\"contentView\" opaque=\"NO\" clipsSubviews=\"YES\" multipleTouchEnabled=\"YES\" contentMode=\"center\" tableViewCell=\""+tableViewCellid+"\" id=\""+tableViewCellContentViewId+"\">\n";
		m+="                <autoresizingMask key=\"autoresizingMask\"/>\n";
		m+="                <subviews>\n";

		m+=body;
		
		m+=" </subviews>\n";
		
		m+="         <constraints>\n";
		m+="                    <constraint firstItem=\""+maxBean.id+"\" firstAttribute=\"leading\" secondItem=\""+tableViewCellContentViewId+"\" secondAttribute=\"leadingMargin\" id=\""+id()+"\"/>\n";
		m+="                    <constraint firstItem=\""+tableViewCellContentViewId+"\" firstAttribute=\"bottomMargin\" secondItem=\""+maxBean.id+"\" secondAttribute=\"bottom\" id=\""+id()+"\"/>\n";
		m+="                    <constraint firstItem=\""+maxBean.id+"\" firstAttribute=\"trailing\" secondItem=\""+tableViewCellContentViewId+"\" secondAttribute=\"trailingMargin\" id=\""+id()+"\"/>\n";
		m+="                    <constraint firstItem=\""+maxBean.id+"\" firstAttribute=\"top\" secondItem=\""+tableViewCellContentViewId+"\" secondAttribute=\"topMargin\" id=\""+id()+"\"/>\n";
		m+="         </constraints>\n";
		
		m+="   </tableViewCellContentView>\n";
		m+="            <inset key=\"separatorInset\" minX=\"0.0\" minY=\"0.0\" maxX=\"0.0\" maxY=\"0.0\"/>\n";
		m+=" <connections>\n";
        m+=connection;
        m+="</connections>\n";
		m+="        </tableViewCell>\n";
		m+="    </objects>\n";
		m+="</document>\n";

		
		System.out.println(m);
		
		FileUtil.makeFile(KeyValue.readCache("picPath"), "java", className+"TableViewCell",
				"xib", m);

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
		

			

		
		bodym += "                    <view key=\"view\" contentMode=\"scaleToFill\" id=\""
				+ maxBean.id + "\">\n";
		bodym += "<rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\""+maxBean.h+"\"/>\n";
		bodym += "                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		bodym += "                        <subviews>\n";

		parent(maxBean);

		bodym += "                        </subviews>\n";
		bodym += "                        <color key=\"backgroundColor\" red=\""
				+ maxBean.getR(maxBean.bgRgb16) + "\" green=\""
				+ maxBean.getG(maxBean.bgRgb16) + "\" blue=\""
				+ maxBean.getB(maxBean.bgRgb16)
				+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

	
		bodym += constraint(maxBean);
		bodym += "                    </view>\n";
	

      return bodym;
	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					bodym += "                            <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
							+ chirld.id + "\">\n";
					bodym += "<rect key=\"frame\" x=\"" + (chirld.x - bean.x)
							+ "\" y=\"" + (chirld.y - bean.y) + "\" width=\""
							+ chirld.w + "\" height=\"" + chirld.h + "\"/>\n";
					bodym += "                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
					bodym += "                                <subviews>\n";
					parent(chirld);
					bodym += "                                </subviews>\n";
					bodym += "                                <color key=\"backgroundColor\" red=\""
							+ bean.getR(bean.bgRgb16)
							+ "\" green=\""
							+ bean.getG(bean.bgRgb16)
							+ "\" blue=\""
							+ bean.getB(bean.bgRgb16)
							+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
					// m+="                                <constraints>\n";
					// m+="                                    <constraint firstItem=\"COT-hb-yaP\" firstAttribute=\"centerY\" secondItem=\"itq-au-h9W\" secondAttribute=\"centerY\" constant=\"-0.75\" id=\"4dZ-Um-rQ1\"/>\n";
					// m+="                                </constraints>\n";
					bodym += constraint(chirld);

					if (bean.type.contains("Layout") && bean.rgb16 != null
							&& bean.isFilletedCorner) {// 圆角边框颜色
						bodym += "                                <userDefinedRuntimeAttributes>\n";
						bodym += "                                    <userDefinedRuntimeAttribute type=\"number\" keyPath=\"layer.borderWidth\">\n";
						bodym += "                                        <integer key=\"value\" value=\"1\"/>\n";
						bodym += "                                    </userDefinedRuntimeAttribute>\n";
						bodym += "                                    <userDefinedRuntimeAttribute type=\"color\" keyPath=\"layer.borderColor\">\n";
						bodym += "                                        <color key=\"value\" red=\""
								+ bean.getR(bean.rgb16)
								+ "\" green=\""
								+ bean.getG(bean.rgb16)
								+ "\" blue=\""
								+ bean.getB(bean.rgb16)
								+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
						bodym += "                                    </userDefinedRuntimeAttribute>\n";
						bodym += "                                    <userDefinedRuntimeAttribute type=\"number\" keyPath=\"layer.cornerRadius\">\n";
						bodym += "                                        <integer key=\"value\" value=\"10\"/>\n";
						bodym += "                                    </userDefinedRuntimeAttribute>\n";
						bodym += "                                </userDefinedRuntimeAttributes>\n";
					}

					// m+="                                <variation key=\"default\">\n";
					// m+="                                    <mask key=\"subviews\">\n";
					// m+="                                        <exclude reference=\"COT-hb-yaP\"/>\n";
					// m+="                                    </mask>\n";
					// m+="                                    <mask key=\"constraints\">\n";
					// m+="                                        <exclude reference=\"Fc6-1K-XdQ\"/>\n";
					// m+="                                    </mask>\n";
					// m+="                                </variation>\n";

					// m+="                                <variation key=\"widthClass=compact\">\n";
					// m+="                                    <mask key=\"subviews\">\n";
					// for(int i=0;i<bean.chirlds.size();i++)
					// {
					// m+="                                        <include reference=\""+bean.chirlds.get(i).id+"\"/>\n";
					// }
					// m+="                                    </mask>\n";

					// m+="                                    <mask key=\"constraints\">\n";
							// m+="                                        <include reference=\"lmb-pJ-JUc\"/>\n";
					// m+="                                    </mask>\n";
					// m+="                                </variation>\n";
					bodym += "                            </view>\n";

				} else {
					chirld(chirld, bean);
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			bodym += "                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\""
					+ chirld.cnname
					+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                        <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x)
					+ "\" y=\""
					+ (chirld.y - parent.y)
					+ "\" width=\""
					+ chirld.w
					+ "\" height=\""
					+ chirld.h
					+ "\"/>\n";
			
			bodym+="<color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16)+"\" green=\""+chirld.getG(chirld.bgRgb16)+"\" blue=\""+chirld.getB(chirld.bgRgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			
			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"17\"/>\n";
			bodym += "                                        <color key=\"textColor\" red=\""
					+ chirld.getR(chirld.rgb16)
					+ "\" green=\""
					+ chirld.getG(chirld.rgb16)
					+ "\" blue=\""
					+ chirld.getB(chirld.rgb16)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "                                        <nil key=\"highlightedColor\"/>\n";
			bodym += "                                    </label>\n";
			connection += "                        <outlet property=\"" + chirld.enname
					+ "\" destination=\"" + chirld.id + "\" id=\"" + id()
					+ "\"/>\n";
		}

		if (chirld.type.equals("Button")) {
			bodym += "                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
					+ "\"/>\n";
			bodym += "                                <color key=\"backgroundColor\" red=\""
					+ chirld.getR(chirld.bgRgb16)
					+ "\" green=\""
					+ chirld.getG(chirld.bgRgb16)
					+ "\" blue=\""
					+ chirld.getB(chirld.bgRgb16)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "                                <color key=\"tintColor\" red=\""
					+ chirld.getR(chirld.rgb16)
					+ "\" green=\""
					+ chirld.getG(chirld.rgb16)
					+ "\" blue=\""
					+ chirld.getB(chirld.rgb16)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "                                <state key=\"normal\" title=\""
					+ chirld.cnname + "\">\n";
			bodym += "                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			bodym += "                                </state>\n";
			bodym += "                            </button>\n";
			connection += "                        <outlet property=\"" + chirld.enname
					+ "\" destination=\"" + chirld.id + "\" id=\"" + id()
					+ "\"/>\n";
		}

		if (chirld.type.equals("EditText")) {
			bodym += "                         <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
					+ "\"/>\n";
			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\""
					+ chirld.textSize + "\"/>\n";
			bodym += "                                        <textInputTraits key=\"textInputTraits\"/>\n";
			bodym += "                                    </textField>\n";
			connection += "                        <outlet property=\"" + chirld.enname
					+ "\" destination=\"" + chirld.id + "\" id=\"" + id()
					+ "\"/>\n";

		}

		if (chirld.type.equals("CheckBox")) {
			// m += "/**" + chirld.cnname + "*/\n";
			// m += " " + chirld.enname + " = new JCheckBox(\""
			// + chirld.cnname + "\");\n";
			// m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
			 bodym+=" <tableView clipsSubviews=\"YES\" contentMode=\"scaleToFill\" alwaysBounceVertical=\"YES\" style=\"plain\" separatorStyle=\"default\" rowHeight=\"44\" sectionHeaderHeight=\"22\" sectionFooterHeight=\"22\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+chirld.id+"\">\n";
	    		bodym += " <rect key=\"frame\" x=\"" + (chirld.x - parent.x)
				+ "\" y=\"" + (chirld.y - parent.y) + "\" width=\""
				+ (chirld.w) + "\" height=\"" + (chirld.h) + "\"/>\n";
		bodym += " <color key=\"backgroundColor\" red=\""
				+ chirld.getR(chirld.bgRgb16)
				+ "\" green=\""
				+ chirld.getG(chirld.bgRgb16)
				+ "\" blue=\""
				+ chirld.getB(chirld.bgRgb16)
				+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
	    bodym+="  </tableView>\n";
	    connection += "                        <outlet property=\"" + chirld.enname
				+ "\" destination=\"" + chirld.id + "\" id=\"" + id()
				+ "\"/>\n";
		}

		if (chirld.type.equals("ImageView")) {

			bodym += " <imageView userInteractionEnabled=\"NO\" contentMode=\"scaleToFill\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += " <rect key=\"frame\" x=\"" + (chirld.x - parent.x)
					+ "\" y=\"" + (chirld.y - parent.y) + "\" width=\""
					+ (chirld.w) + "\" height=\"" + (chirld.h) + "\"/>\n";
			bodym += " </imageView>\n";
			connection += "                        <outlet property=\"" + chirld.enname
					+ "\" destination=\"" + chirld.id + "\" id=\"" + id()
					+ "\"/>\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}

	public String constraint(CompomentBean bean) {

		String m = "<constraints>\n";
        String n=" <variation key=\"widthClass=compact\">\n";
		       n+=" <mask key=\"constraints\">\n";
		       
        
		for (CompomentBean chirld1 : bean.chirlds) {
			boolean left = false;
			int leftvalue = 0;
			boolean right = false;
			int rightvalue = 0;
			boolean top = false;
			int topvalue = 0;
			boolean bottom = false;
			int bottomvalue = 0;

			for (CompomentBean chirld2 : bean.chirlds) {
				if (!chirld1.enname.equals(chirld2.enname)) {
					if (chirld1.x > (chirld2.x + chirld2.w)) {// 有人在你(chirld1)左边

						if (left == true) {
							if (leftvalue > chirld1.x - (chirld2.x + chirld2.w)) {// 上一个距离远
																					// 换现在这个近的

								String oldString = "<constraint firstItem=\""
										+ chirld1.id
										+ "\" firstAttribute=\"leading\" secondItem=\""
										+ chirld2.id
										+ "\" secondAttribute=\"trailing\" constant=\""
										+ (leftvalue) + "\"";
								leftvalue = chirld1.x - (chirld2.x + chirld2.w);
								String newString = "<constraint firstItem=\""
										+ chirld1.id
										+ "\" firstAttribute=\"leading\" secondItem=\""
										+ chirld2.id
										+ "\" secondAttribute=\"trailing\" constant=\""
										+ (leftvalue) + "\"";

								m = m.replace(oldString, newString);

							}
						} else {
							leftvalue = chirld1.x - (chirld2.x + chirld2.w);
							String id=id();
							m += "<constraint firstItem=\""
									+ chirld1.id
									+ "\" firstAttribute=\"leading\" secondItem=\""
									+ chirld2.id
									+ "\" secondAttribute=\"trailing\" constant=\""
									+ (leftvalue) + "\" id=\"" + id
									+ "\"/>\n";
							n+=" <include reference=\""+id+"\"/>\n";
							
							left = true;
						}

					}

					if ((chirld1.x + chirld1.w) < chirld2.x) {// 有人在你(chirld1)右边

						if (right == true) {
//							if (rightvalue > chirld2.x
//									- (chirld1.x + chirld1.w)) {// 上一个距离远
//																// 换现在这个近的
//
//								String oldString = "<constraint firstItem=\""
//										+ chirld1.id
//										+ "\" firstAttribute=\"trailing\" secondItem=\""
//										+ chirld2.id
//										+ "\" secondAttribute=\"leading\" constant=\""
//										+ (rightvalue) + "\"";
//								leftvalue = chirld2.x - (chirld1.x + chirld1.w);
//								String newString = "<constraint firstItem=\""
//										+ chirld1.id
//										+ "\" firstAttribute=\"trailing\" secondItem=\""
//										+ chirld2.id
//										+ "\" secondAttribute=\"leading\" constant=\""
//										+ (rightvalue) + "\"";
//
//								m = m.replace(oldString, newString);
//
//							}
						} else {
//							rightvalue = chirld1.x - (chirld2.x + chirld2.w);
//							String id=id();
//							m += "<constraint firstItem=\""
//									+ chirld1.id
//									+ "\" firstAttribute=\"trailing\" secondItem=\""
//									+ chirld2.id
//									+ "\" secondAttribute=\"leading\" constant=\""
//									+ (rightvalue) + "\" id=\"" + id
//									+ "\"/>\n";
//							n+=" <include reference=\""+id+"\"/>\n";
							right = true;
						}
					}

					if (chirld1.y > (chirld2.y + chirld2.h)) {// 有人在你(chirld1)上边

						if (top == true) {
							if (topvalue > chirld1.y - (chirld2.y + chirld2.h)) {// 上一个距离远
																					// 换现在这个近的

								String oldString = "<constraint firstItem=\""
										+ chirld1.id
										+ "\" firstAttribute=\"top\" secondItem=\""
										+ chirld2.id
										+ "\" secondAttribute=\"bottom\" constant=\""
										+ (topvalue) + "\"";
								topvalue = chirld1.y - (chirld2.y + chirld2.h);
								String newString = "<constraint firstItem=\""
										+ chirld1.id
										+ "\" firstAttribute=\"top\" secondItem=\""
										+ chirld2.id
										+ "\" secondAttribute=\"bottom\" constant=\""
										+ (topvalue) + "\"";

								m = m.replace(oldString, newString);

							}
						} else {
							topvalue = chirld1.y - (chirld2.y + chirld2.h);
							String id=id();
							m += "<constraint firstItem=\""
									+ chirld1.id
									+ "\" firstAttribute=\"top\" secondItem=\""
									+ chirld2.id
									+ "\" secondAttribute=\"bottom\" constant=\""
									+ (topvalue) + "\" id=\"" + id+ "\"/>\n";
							n+=" <include reference=\""+id+"\"/>\n";
							top = true;
						}
					}

					if ((chirld1.y + chirld1.h) < chirld2.y) {// 有人在你(chirld1)下边

						if (bottom == true) {
//							if (bottomvalue > chirld2.y
//									- (chirld1.y + chirld1.h)) {// 上一个距离远
//																// 换现在这个近的
//
//								String oldString = "<constraint firstItem=\""
//										+ chirld1.id
//										+ "\" firstAttribute=\"bottom\" secondItem=\""
//										+ chirld2.id
//										+ "\" secondAttribute=\"top\" constant=\""
//										+ (bottomvalue) + "\"";
//								bottomvalue = chirld2.y
//										- (chirld1.y + chirld1.h);
//								String newString = "<constraint firstItem=\""
//										+ chirld1.id
//										+ "\" firstAttribute=\"bottom\" secondItem=\""
//										+ chirld2.id
//										+ "\" secondAttribute=\"top\" constant=\""
//										+ (bottomvalue) + "\"";
//
//								m = m.replace(oldString, newString);
//
//							}
						} else {
//							bottomvalue = chirld2.y - (chirld1.y + chirld1.h);
//							String id=id();
//							m += "<constraint firstItem=\""
//									+ chirld1.id
//									+ "\" firstAttribute=\"bottom\" secondItem=\""
//									+ chirld2.id
//									+ "\" secondAttribute=\"top\" constant=\""
//									+ (bottomvalue) + "\" id=\"" + id
//									+ "\"/>\n";
//							n+=" <include reference=\""+id+"\"/>\n";
							bottom = true;
						}
					}

				}

			}

			if (top == false) {
				m += "<constraint firstItem=\"" + chirld1.id
						+ "\" firstAttribute=\"top\" secondItem=\"" + bean.id
						+ "\" secondAttribute=\"top\" constant=\""
						+ (chirld1.y - bean.y) + "\" id=\"" + id() + "\"/>\n";
			}

			if (bottom == false) {
				m += "<constraint firstItem=\"" + chirld1.id
						+ "\" firstAttribute=\"bottom\" secondItem=\""
						+ bean.id + "\" secondAttribute=\"bottom\" constant=\""
						+ ((bean.y + bean.h) - (chirld1.y + chirld1.h))
						+ "\" id=\"" + id() + "\"/>\n";

			}

			if (left == false) {
				m += "<constraint firstItem=\"" + chirld1.id
						+ "\" firstAttribute=\"leading\" secondItem=\""
						+ bean.id
						+ "\" secondAttribute=\"leading\" constant=\""
						+ (chirld1.x - bean.x) + "\" id=\"" + id() + "\"/>\n";

			}

			if (right == false) {
				m += "<constraint firstItem=\"" + chirld1.id
						+ "\" firstAttribute=\"trailing\" secondItem=\""
						+ bean.id
						+ "\" secondAttribute=\"trailing\" constant=\""
						+ ((bean.x + bean.w) - (chirld1.x + chirld1.w))
						+ "\" id=\"" + id() + "\"/>\n";
			}

		}

		
	    n+=" </mask>\n";
	    n+=" </variation>\n";
		m += "</constraints>\n";
         m+=n;
		return m;
	}

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.time != s2.time) {
				return (int) (s2.time - s1.time);
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