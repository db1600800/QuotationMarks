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

public class IphoneViewControllerXibForHorizontallayout {

	String bodym = "\n\n\n";
	String connection = "";
	String pageName = "";
	String className = "";

	public IphoneViewControllerXibForHorizontallayout(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);

		String body = analyse(oldBeans);

		String m = "";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m += "<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.XIB\" version=\"3.0\" toolsVersion=\"6751\" systemVersion=\"14D136\" targetRuntime=\"iOS.CocoaTouch\" propertyAccessControl=\"none\" useAutolayout=\"YES\" useTraitCollections=\"YES\">\n";
		m += "    <dependencies>\n";
		m += "        <deployment identifier=\"iOS\"/>\n";
		m += "        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"6736\"/>\n";
		m += "    </dependencies>\n";
		m += "    <objects>\n";
		m += "        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\" customClass=\""
				+ className + "ViewController\">\n";
		m += "            <connections>\n";
		m += connection;
		m += "                <outlet property=\"view\" destination=\""
				+ maxBean.id + "\" id=\"" + id() + "\"/>\n";
		m += "            </connections>\n";
		m += "        </placeholder>\n";
		m += "        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";

		m += body;

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
		bodym += "<rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"600\" height=\"600\"/>\n";
		bodym += "                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		bodym += "                        <subviews>\n";

		parent(maxBean);

		bodym += "                        </subviews>\n";
		bodym += "                        <color key=\"backgroundColor\" red=\""
				+ maxBean.getR(maxBean.bgRgb16ios)
				+ "\" green=\""
				+ maxBean.getG(maxBean.bgRgb16ios)
				+ "\" blue=\""
				+ maxBean.getB(maxBean.bgRgb16ios)
				+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

		bodym += constraint(maxBean);
		bodym += "                    </view>\n";
		bodym += "</objects>\n";
		bodym += "</document>\n";

		return bodym;
	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					bodym += "                            <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
							+ chirld.id + "\">\n";
					bodym += "<rect key=\"frame\" x=\"0\" y=\"" + (chirld.y - bean.y) + "\" width=\"600\" height=\"40\"/>\n";
					bodym += "                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
					bodym += "                                <subviews>\n";
					parent(chirld);
					bodym += "                                </subviews>\n";
					bodym += "                                <color key=\"backgroundColor\" red=\""
							+ bean.getR(chirld.bgRgb16ios)
							+ "\" green=\""
							+ bean.getG(chirld.bgRgb16ios)
							+ "\" blue=\""
							+ bean.getB(chirld.bgRgb16ios)
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

			// bodym+="<color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16ios)+"\" green=\""+chirld.getG(chirld.bgRgb16ios)+"\" blue=\""+chirld.getB(chirld.bgRgb16ios)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

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
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}

		if (chirld.type.equals("Button")) {
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
			bodym += "                                <state key=\"normal\" title=\""
					+ chirld.cnname + "\">\n";
			bodym += "                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			bodym += "                                </state>\n";
			bodym += "                            </button>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}

		if (chirld.type.equals("EditText")) {
			bodym += "                         <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"100\" height=\"30\"/>\n";

//			bodym += " <constraints>\n";
//			bodym += " <constraint firstAttribute=\"height\" constant=\"30\" id=\""
//					+ id() + "\"/>\n";
//			bodym += " <constraint firstAttribute=\"width\" constant=\"100\" id=\""
//					+ id() + "\"/>\n";
//			bodym += " </constraints>\n";

			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\""
					+ chirld.textSize + "\"/>\n";
			bodym += "                                        <textInputTraits key=\"textInputTraits\"/>\n";
			bodym += "                                    </textField>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";

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
			bodym += " <color key=\"backgroundColor\" red=\""
					+ chirld.getR(chirld.bgRgb16ios) + "\" green=\""
					+ chirld.getG(chirld.bgRgb16ios) + "\" blue=\""
					+ chirld.getB(chirld.bgRgb16ios)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "  </tableView>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}

		if (chirld.type.equals("ImageView")) {

			bodym += " <imageView userInteractionEnabled=\"NO\" contentMode=\"scaleToFill\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" image=\""+chirld.picName+".png\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += " <rect key=\"frame\" x=\"" + (chirld.x - parent.x)
					+ "\" y=\"" + (chirld.y - parent.y) + "\" width=\""
					+ (chirld.w) + "\" height=\"" + (chirld.h) + "\"/>\n";
			bodym += " <constraints>\n";
			bodym += " <constraint firstAttribute=\"height\" constant=\"40\" id=\""
					+ id() + "\"/>\n";
			bodym += " <constraint firstAttribute=\"width\" constant=\"40\" id=\""
					+ id() + "\"/>\n";
			bodym += " </constraints>\n";
			bodym += " </imageView>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}

	public String constraint(CompomentBean bean) {
//一组里边的约束条件
		
		String m = "<constraints>\n";
		String n = " <variation key=\"widthClass=compact\">\n";
		n += " <mask key=\"constraints\">\n";

		CompomentBean leftfirst = null;
		boolean parentIshorizontal = false;
		boolean parentHasLayoutChirld = false;
		if (bean.orientation.equals("horizontal")) {
			parentIshorizontal = true;
		}
		
		int maxHeight=0;
		for (CompomentBean chirld1 : bean.chirlds) {
			if (chirld1.type.contains("Layout")) {

				parentHasLayoutChirld = true;
			}else
			{
				if(chirld1.h>maxHeight)
				{
					maxHeight=chirld1.h;
				}
			}
		}

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

								String id = id();
								leftvalue = chirld1.x - (chirld2.x + chirld2.w);
								String newString = "<constraint firstItem=\""
										+ chirld1.id
										+ "\" firstAttribute=\"leading\" secondItem=\""
										+ chirld2.id
										+ "\" secondAttribute=\"trailing\" constant=\""
										+ (leftvalue) + "\" id=\"" + id
										+ "\"/>\n";
								;

								String newStringn = " <include reference=\""
										+ id + "\"/>\n";

								String rows[] = m.split("\n");
								String lastrow = rows[rows.length - 1];

								m = m.replace(lastrow, newString);

								String rowsn[] = n.split("\n");
								String lastrown = rowsn[rowsn.length - 1];

								n = n.replace(lastrown, newStringn);

							}
						} else {
							leftvalue = chirld1.x - (chirld2.x + chirld2.w);
							String id = id();
							m += "<constraint firstItem=\""
									+ chirld1.id
									+ "\" firstAttribute=\"leading\" secondItem=\""
									+ chirld2.id
									+ "\" secondAttribute=\"trailing\" constant=\""
									+ (leftvalue) + "\" id=\"" + id + "\"/>\n";
							n += " <include reference=\"" + id + "\"/>\n";

							left = true;
						}

					}

					if ((chirld1.x + chirld1.w) < chirld2.x) {// 有人在你(chirld1)右边

						if (right == true) {
							// if (rightvalue > chirld2.x
							// - (chirld1.x + chirld1.w)) {// 上一个距离远
							// // 换现在这个近的
							//
							// String oldString = "<constraint firstItem=\""
							// + chirld1.id
							// + "\" firstAttribute=\"trailing\" secondItem=\""
							// + chirld2.id
							// + "\" secondAttribute=\"leading\" constant=\""
							// + (rightvalue) + "\"";
							// leftvalue = chirld2.x - (chirld1.x + chirld1.w);
							// String newString = "<constraint firstItem=\""
							// + chirld1.id
							// + "\" firstAttribute=\"trailing\" secondItem=\""
							// + chirld2.id
							// + "\" secondAttribute=\"leading\" constant=\""
							// + (rightvalue) + "\"";
							//
							// m = m.replace(oldString, newString);
							//
							// }
						} else {
							// rightvalue = chirld1.x - (chirld2.x + chirld2.w);
							// String id=id();
							// m += "<constraint firstItem=\""
							// + chirld1.id
							// + "\" firstAttribute=\"trailing\" secondItem=\""
							// + chirld2.id
							// + "\" secondAttribute=\"leading\" constant=\""
							// + (rightvalue) + "\" id=\"" + id
							// + "\"/>\n";
							// n+=" <include reference=\""+id+"\"/>\n";
							right = true;
						}
					}

					if (chirld1.y > (chirld2.y + chirld2.h)) {// 有人在你(chirld1)上边

						if (top == true) {
							if (topvalue > chirld1.y - (chirld2.y + chirld2.h)) {// 上一个距离远
																					// 换现在这个近的

								String id = id();

								topvalue = chirld1.y - (chirld2.y + chirld2.h);
								String newString = "<constraint firstItem=\""
										+ chirld1.id
										+ "\" firstAttribute=\"top\" secondItem=\""
										+ chirld2.id
										+ "\" secondAttribute=\"bottom\" constant=\""
										+ (topvalue) + "\"  id=\"" + id
										+ "\"/>\n";

								String newStringn = " <include reference=\""
										+ id + "\"/>\n";

								String rows[] = m.split("\n");
								String lastrow = rows[rows.length - 1];

								m = m.replace(lastrow, newString);

								String rowsn[] = n.split("\n");
								String lastrown = rowsn[rowsn.length - 1];

								n = n.replace(lastrown, newStringn);

							}
						} else {
							topvalue = chirld1.y - (chirld2.y + chirld2.h);
							String id = id();
							m += "<constraint firstItem=\""
									+ chirld1.id
									+ "\" firstAttribute=\"top\" secondItem=\""
									+ chirld2.id
									+ "\" secondAttribute=\"bottom\" constant=\""
									+ (topvalue) + "\" id=\"" + id + "\"/>\n";
							n += " <include reference=\"" + id + "\"/>\n";
							top = true;
						}
					}

					if ((chirld1.y + chirld1.h) < chirld2.y) {// 有人在你(chirld1)下边

						if (bottom == true) {
							// if (bottomvalue > chirld2.y
							// - (chirld1.y + chirld1.h)) {// 上一个距离远
							// // 换现在这个近的
							//
							// String oldString = "<constraint firstItem=\""
							// + chirld1.id
							// + "\" firstAttribute=\"bottom\" secondItem=\""
							// + chirld2.id
							// + "\" secondAttribute=\"top\" constant=\""
							// + (bottomvalue) + "\"";
							// bottomvalue = chirld2.y
							// - (chirld1.y + chirld1.h);
							// String newString = "<constraint firstItem=\""
							// + chirld1.id
							// + "\" firstAttribute=\"bottom\" secondItem=\""
							// + chirld2.id
							// + "\" secondAttribute=\"top\" constant=\""
							// + (bottomvalue) + "\"";
							//
							// m = m.replace(oldString, newString);
							//
							// }
						} else {
							// bottomvalue = chirld2.y - (chirld1.y +
							// chirld1.h);
							// String id=id();
							// m += "<constraint firstItem=\""
							// + chirld1.id
							// + "\" firstAttribute=\"bottom\" secondItem=\""
							// + chirld2.id
							// + "\" secondAttribute=\"top\" constant=\""
							// + (bottomvalue) + "\" id=\"" + id
							// + "\"/>\n";
							// n+=" <include reference=\""+id+"\"/>\n";
							bottom = true;
						}
					}

				}

			}

//			if (parentIshorizontal == true && parentHasLayoutChirld == false
//					&& top == false && bottom == false) {
//				// 没其他孩子在这孩子的上方 下方 
//				m += "<constraint firstAttribute=\"centerY\" secondItem=\""
//						+ chirld1.id + "\" secondAttribute=\"centerY\" id=\""
//						+ id() + "\"/>\n";
//                if(maxHeight==chirld1.h)
//                {
////                	//top
////                	m += "<constraint firstItem=\"" + chirld1.id
////							+ "\" firstAttribute=\"top\" secondItem=\""
////							+ bean.id
////							+ "\" secondAttribute=\"top\" constant=\""
////							+ (chirld1.y - bean.y) + "\" id=\"" + id()
////							+ "\"/>\n";
////                	
////                	//bottom
////                	m += "<constraint firstItem=\"" + chirld1.id
////							+ "\" firstAttribute=\"bottom\" secondItem=\""
////							+ bean.id
////							+ "\" secondAttribute=\"bottom\" constant=\""
////							+ ((bean.y + bean.h) - (chirld1.y + chirld1.h))
////							+ "\" id=\"" + id() + "\"/>\n";
//                }
//				
//			} else 
			
			{

				
				
				// 没其他孩子在这孩子的上方
				if (top == false) {
					if(chirld1.type.contains("Layout"))
					{
						m += "<constraint firstItem=\"" + chirld1.id
								+ "\" firstAttribute=\"top\" secondItem=\""
								+ bean.id
								+ "\" secondAttribute=\"top\" constant=\"0\" id=\"" + id()
								+ "\"/>\n";
					}else
					{
						if(left==false)
						{//左边第一个
							leftfirst=chirld1;
							m += "<constraint firstItem=\"" + chirld1.id
									+ "\" firstAttribute=\"top\" secondItem=\""
									+ bean.id
									+ "\" secondAttribute=\"top\" constant=\"9\" id=\"" + id()
									+ "\"/>\n";
						}else
						{
							
							 m+="<constraint firstItem=\""+leftfirst.id+"\" firstAttribute=\"centerY\" secondItem=\""+chirld1.id+"\" secondAttribute=\"centerY\" id=\""+id()+"\"/>\n";
//					m += "<constraint firstItem=\"" + chirld1.id
//							+ "\" firstAttribute=\"top\" secondItem=\""
//							+ bean.id
//							+ "\" secondAttribute=\"top\" constant=\""
//							+ (chirld1.y - bean.y) + "\" id=\"" + id()
//							+ "\"/>\n";
						}
					}

				}

				// 没其他孩子在这孩子的下方
				if (bottom == false) {
					if(chirld1.type.contains("Layout"))
					{
						
						
					}else
					{
						if(left==false)
						{//左边第一个
							m += "<constraint firstItem=\"" + chirld1.id
									+ "\" firstAttribute=\"bottom\" secondItem=\""
									+ bean.id
									+ "\" secondAttribute=\"bottom\" constant=\"10\" id=\"" + id() + "\"/>\n";
						}
					
					}

				}
			}

			// 没其他孩子在这孩子的左方
			if (left == false) {
				if(chirld1.type.contains("Layout"))
				{
					m += "<constraint firstItem=\"" + chirld1.id
							+ "\" firstAttribute=\"leading\" secondItem=\""
							+ bean.id
							+ "\" secondAttribute=\"leading\" constant=\"0\" id=\"" + id() + "\"/>\n";
				}else
				{
				m += "<constraint firstItem=\"" + chirld1.id
						+ "\" firstAttribute=\"leading\" secondItem=\""
						+ bean.id
						+ "\" secondAttribute=\"leading\" constant=\"40\" id=\"" + id() + "\"/>\n";
				}

			}

			// 没其他孩子在这孩子的右方
			if (right == false) {
				if(chirld1.type.contains("Layout"))
				{
					m += "<constraint firstItem=\"" + chirld1.id
							+ "\" firstAttribute=\"trailing\" secondItem=\""
							+ bean.id
							+ "\" secondAttribute=\"trailing\" constant=\"0\" id=\"" + id() + "\"/>\n";
				}else
				{
				m += "<constraint firstItem=\"" + chirld1.id
						+ "\" firstAttribute=\"trailing\" secondItem=\""
						+ bean.id
						+ "\" secondAttribute=\"trailing\" constant=\"40\" id=\"" + id() + "\"/>\n";
				}
			}

		}

		n += " </mask>\n";
		n += " </variation>\n";
		m += "</constraints>\n";
		//m += n;添加小屏幕限制

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
