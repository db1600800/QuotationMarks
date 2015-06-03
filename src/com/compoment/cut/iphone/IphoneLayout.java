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


public class IphoneLayout {

	String m = "\n\n\n";
    String pageName="";
    
	public  IphoneLayout(List<CompomentBean> oldBeans) {

		analyseRelativeForVertical(oldBeans);


	
		System.out.println(m);

	}

	

	public void analyseRelativeForVertical(List<CompomentBean> oldBeans) {
		// Collections.sort(oldBeans, comparatorDate);
		m += "//ios界面 \n";
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

		
		
		
		m+="                <viewController title=\""+pageName+"\" id=\""+id()+"\" customClass=\"ViewController\" customModule=\"mobile\" customModuleProvider=\"target\" sceneMemberID=\"viewController\">\n";
		m+="                    <layoutGuides>\n";
		m+="                        <viewControllerLayoutGuide type=\"top\" id=\""+id()+"\"/>\n";
		m+="                        <viewControllerLayoutGuide type=\"bottom\" id=\""+id()+"\"/>\n";
		m+="                    </layoutGuides>\n";
		
		m+="                    <view key=\"view\" contentMode=\"scaleToFill\" id=\""+maxBean.id+"\">\n";
		m+="                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="                        <subviews>\n";
		
		parent(maxBean);
		
		m+="                        </subviews>\n";
		m+="                        <color key=\"backgroundColor\" red=\""+maxBean.getR(maxBean.bgRgb16)+"\" green=\""+maxBean.getG(maxBean.bgRgb16)+"\" blue=\""+maxBean.getB(maxBean.bgRgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

//		m+="                        <constraints>\n";
//		m+="                            <constraint firstItem=\"SyQ-o4-jyF\" firstAttribute=\"leading\" secondItem=\"kh9-bI-dsS\" secondAttribute=\"leadingMargin\" constant=\"20\" id=\"1hU-hb-12c\"/>\n";
//		m+="                            <constraint firstAttribute=\"centerY\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"centerY\" id=\"5gX-fc-Alk\"/>\n";
//		m+="                            <constraint firstItem=\"2fi-mo-0CV\" firstAttribute=\"top\" secondItem=\"SyQ-o4-jyF\" secondAttribute=\"bottom\" constant=\"30\" id=\"pwU-eI-0fQ\"/>\n";
//		m+="                            <constraint firstAttribute=\"trailingMargin\" secondItem=\"SyQ-o4-jyF\" secondAttribute=\"trailing\" constant=\"20\" id=\"q3b-qz-Qw5\"/>\n";
//		m+="                            <constraint firstItem=\"ktc-wb-5bz\" firstAttribute=\"leading\" relation=\"lessThanOrEqual\" secondItem=\"kh9-bI-dsS\" secondAttribute=\"leadingMargin\" constant=\"20\" id=\"tqf-Ym-2sb\"/>\n";
//		m+="                            <constraint firstAttribute=\"trailingMargin\" relation=\"lessThanOrEqual\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"trailing\" constant=\"20\" id=\"vle-4u-4Vv\"/>\n";
//		m+="                        </constraints>\n";
//		m+="                        <variation key=\"default\">\n";
//		m+="                            <mask key=\"subviews\">\n";
//		m+="                                <exclude reference=\"ktc-wb-5bz\"/>\n";
//		m+="                                <exclude reference=\"SyQ-o4-jyF\"/>\n";
//		m+="                            </mask>\n";
//		m+="                            <mask key=\"constraints\">\n";
//		m+="                                <exclude reference=\"pwU-eI-0fQ\"/>\n";
//		m+="                                <exclude reference=\"1hU-hb-12c\"/>\n";
//		m+="                                <exclude reference=\"q3b-qz-Qw5\"/>\n";
//		m+="                                <exclude reference=\"5gX-fc-Alk\"/>\n";
//		m+="                                <exclude reference=\"tqf-Ym-2sb\"/>\n";
//		m+="                                <exclude reference=\"vle-4u-4Vv\"/>\n";
//		m+="                            </mask>\n";
//		m+="                        </variation>\n";
//		m+="                        <variation key=\"widthClass=compact\">\n";
//		m+="                            <mask key=\"subviews\">\n";
//		m+="                                <include reference=\"ktc-wb-5bz\"/>\n";
//		m+="                                <include reference=\"SyQ-o4-jyF\"/>\n";
//		m+="                            </mask>\n";
//		m+="                            <mask key=\"constraints\">\n";
//		m+="                                <include reference=\"pwU-eI-0fQ\"/>\n";
//		m+="                                <include reference=\"1hU-hb-12c\"/>\n";
//		m+="                                <include reference=\"q3b-qz-Qw5\"/>\n";
//		m+="                                <include reference=\"5gX-fc-Alk\"/>\n";
//		m+="                                <include reference=\"tqf-Ym-2sb\"/>\n";
//		m+="                                <include reference=\"vle-4u-4Vv\"/>\n";
//		m+="                            </mask>\n";
//		m+="                        </variation>\n";

		m+="                    </view>\n";
		m+="                    <connections>\n";
		m+="                        <outlet property=\"login\" destination=\"SyQ-o4-jyF\" id=\"bfG-j0-QU0\"/>\n";
		m+="                    </connections>\n";
		m+="                </viewController>\n";

	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					m+="                            <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+chirld.id+"\">\n";
					m+="                                <subviews>\n";
					parent(chirld);
					m+="                                </subviews>\n";
					m+="                                <color key=\"backgroundColor\" red=\""+bean.getR(bean.bgRgb16)+"\" green=\""+bean.getG(bean.bgRgb16)+"\" blue=\""+bean.getB(bean.bgRgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
//					m+="                                <constraints>\n";
//					m+="                                    <constraint firstItem=\"COT-hb-yaP\" firstAttribute=\"centerY\" secondItem=\"itq-au-h9W\" secondAttribute=\"centerY\" constant=\"-0.75\" id=\"4dZ-Um-rQ1\"/>\n";
//					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"leading\" secondItem=\"IYI-bQ-iz6\" secondAttribute=\"trailing\" constant=\"10\" id=\"Fc6-1K-XdQ\"/>\n";
//					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"centerY\" secondItem=\"IYI-bQ-iz6\" secondAttribute=\"centerY\" constant=\"1.75\" id=\"O6k-Fi-NBo\"/>\n";
//					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"top\" secondItem=\"itq-au-h9W\" secondAttribute=\"bottom\" constant=\"19\" id=\"QJt-r8-NgS\"/>\n";
//					m+="                                    <constraint firstItem=\"itq-au-h9W\" firstAttribute=\"top\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"top\" constant=\"20\" id=\"RCq-lH-IOb\"/>\n";
//					m+="                                    <constraint firstItem=\"COT-hb-yaP\" firstAttribute=\"leading\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"leading\" constant=\"20\" id=\"Xnx-Hi-9Bk\"/>\n";
//					m+="                                    <constraint firstItem=\"IYI-bQ-iz6\" firstAttribute=\"leading\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"leading\" constant=\"20\" id=\"Y5f-kA-Y9q\"/>\n";
//					m+="                                    <constraint firstAttribute=\"trailingMargin\" secondItem=\"itq-au-h9W\" secondAttribute=\"trailing\" constant=\"10\" id=\"ctI-ZM-EAO\"/>\n";
//					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"centerY\" secondItem=\"IYI-bQ-iz6\" secondAttribute=\"centerY\" constant=\"1.75\" id=\"j41-yy-0bI\"/>\n";
//					m+="                                    <constraint firstItem=\"itq-au-h9W\" firstAttribute=\"leading\" secondItem=\"COT-hb-yaP\" secondAttribute=\"trailing\" constant=\"10\" id=\"lmb-pJ-JUc\"/>\n";
//					m+="                                    <constraint firstAttribute=\"bottomMargin\" secondItem=\"0iP-qh-p3z\" secondAttribute=\"bottom\" constant=\"20\" id=\"tPu-9L-QiK\"/>\n";
//					m+="                                    <constraint firstAttribute=\"trailingMargin\" secondItem=\"0iP-qh-p3z\" secondAttribute=\"trailing\" constant=\"10\" id=\"wIu-1n-N1A\"/>\n";
//					m+="                                </constraints>\n";
					
					if(bean.type.contains("Layout") && bean.rgb16!=null)
					{//边框颜色
					m+="                                <userDefinedRuntimeAttributes>\n";
					m+="                                    <userDefinedRuntimeAttribute type=\"number\" keyPath=\"layer.borderWidth\">\n";
					m+="                                        <integer key=\"value\" value=\"1\"/>\n";
					m+="                                    </userDefinedRuntimeAttribute>\n";
					m+="                                    <userDefinedRuntimeAttribute type=\"color\" keyPath=\"layer.borderColor\">\n";
					m+="                                        <color key=\"value\" red=\""+bean.getR(bean.rgb16)+"\" green=\""+bean.getG(bean.rgb16)+"\" blue=\""+bean.getB(bean.rgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
					m+="                                    </userDefinedRuntimeAttribute>\n";
					m+="                                    <userDefinedRuntimeAttribute type=\"number\" keyPath=\"layer.cornerRadius\">\n";
					m+="                                        <integer key=\"value\" value=\"10\"/>\n";
					m+="                                    </userDefinedRuntimeAttribute>\n";
					m+="                                </userDefinedRuntimeAttributes>\n";
					}
					
					m+="                                <variation key=\"default\">\n";
//					m+="                                    <mask key=\"subviews\">\n";
//					m+="                                        <exclude reference=\"IYI-bQ-iz6\"/>\n";
//					m+="                                        <exclude reference=\"0iP-qh-p3z\"/>\n";
//					m+="                                        <exclude reference=\"itq-au-h9W\"/>\n";
//					m+="                                        <exclude reference=\"COT-hb-yaP\"/>\n";
//					m+="                                    </mask>\n";
//					m+="                                    <mask key=\"constraints\">\n";
//					m+="                                        <exclude reference=\"Fc6-1K-XdQ\"/>\n";
//					m+="                                        <exclude reference=\"O6k-Fi-NBo\"/>\n";
//					m+="                                        <exclude reference=\"QJt-r8-NgS\"/>\n";
//					m+="                                        <exclude reference=\"j41-yy-0bI\"/>\n";
//					m+="                                        <exclude reference=\"tPu-9L-QiK\"/>\n";
//					m+="                                        <exclude reference=\"wIu-1n-N1A\"/>\n";
//					m+="                                        <exclude reference=\"4dZ-Um-rQ1\"/>\n";
//					m+="                                        <exclude reference=\"Xnx-Hi-9Bk\"/>\n";
//					m+="                                        <exclude reference=\"Y5f-kA-Y9q\"/>\n";
//					m+="                                        <exclude reference=\"RCq-lH-IOb\"/>\n";
//					m+="                                        <exclude reference=\"ctI-ZM-EAO\"/>\n";
//					m+="                                        <exclude reference=\"lmb-pJ-JUc\"/>\n";
//					m+="                                    </mask>\n";
					m+="                                </variation>\n";
					
					m+="                                <variation key=\"widthClass=compact\">\n";
					m+="                                    <mask key=\"subviews\">\n";
					for(int i=0;i<bean.chirlds.size();i++)
					{
					m+="                                        <include reference=\""+bean.chirlds.get(i).id+"\"/>\n";
					}
					m+="                                    </mask>\n";
					
//					m+="                                    <mask key=\"constraints\">\n";
//					m+="                                        <include reference=\"Fc6-1K-XdQ\"/>\n";
//					m+="                                        <include reference=\"O6k-Fi-NBo\"/>\n";
//					m+="                                        <include reference=\"QJt-r8-NgS\"/>\n";
//					m+="                                        <include reference=\"j41-yy-0bI\"/>\n";
//					m+="                                        <include reference=\"tPu-9L-QiK\"/>\n";
//					m+="                                        <include reference=\"wIu-1n-N1A\"/>\n";
//					m+="                                        <include reference=\"4dZ-Um-rQ1\"/>\n";
//					m+="                                        <include reference=\"Xnx-Hi-9Bk\"/>\n";
//					m+="                                        <include reference=\"Y5f-kA-Y9q\"/>\n";
//					m+="                                        <include reference=\"RCq-lH-IOb\"/>\n";
//					m+="                                        <include reference=\"ctI-ZM-EAO\"/>\n";
//					m+="                                        <include reference=\"lmb-pJ-JUc\"/>\n";
//					m+="                                    </mask>\n";
					m+="                                </variation>\n";
					m+="                            </view>\n";

					
				} else {
					chirld(chirld, bean);
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			m+="                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\""+chirld.cnname+"\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+chirld.id+"\">\n";
			m+="                                        <rect key=\"frame\" x=\""+(chirld.x-parent.x)+"\" y=\""+(chirld.y-parent.y)+"\" width=\""+chirld.w+"\" height=\""+chirld.h+"\"/>\n";
			m+="                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"17\"/>\n";
			m+="                                        <color key=\"textColor\" red=\""+chirld.getR(chirld.rgb16)+"\" green=\""+chirld.getG(chirld.rgb16)+"\" blue=\""+chirld.getB(chirld.rgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			m+="                                        <nil key=\"highlightedColor\"/>\n";
			m+="                                    </label>\n";
		}

		if (chirld.type.equals("Button")) {
			m+="                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+chirld.id+"\">\n";
			m+="                                <rect key=\"frame\" x=\""+(chirld.x-parent.x)+"\" y=\""+(chirld.y-parent.y)+"\" width=\""+chirld.w+"\" height=\""+chirld.h+"\"/>\n";
			m+="                                <color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16)+"\" green=\""+chirld.getG(chirld.bgRgb16)+"\" blue=\""+chirld.getB(chirld.bgRgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			m+="                                <color key=\"tintColor\" red=\""+chirld.getR(chirld.rgb16)+"\" green=\""+chirld.getG(chirld.rgb16)+"\" blue=\""+chirld.getB(chirld.rgb16)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			m+="                                <state key=\"normal\" title=\""+chirld.cnname+"\">\n";
			m+="                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			m+="                                </state>\n";
			m+="                            </button>\n";
		}

		if (chirld.type.equals("EditText")) {
			m+="                                    <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+chirld.id+"\">\n";
			m+="                                <rect key=\"frame\" x=\""+(chirld.x-parent.x)+"\" y=\""+(chirld.y-parent.y)+"\" width=\""+chirld.w+"\" height=\""+chirld.h+"\"/>\n";
			m+="                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\""+chirld.textSize+"\"/>\n";
			m+="                                        <textInputTraits key=\"textInputTraits\"/>\n";
			m+="                                    </textField>\n";
			
		}

		if (chirld.type.equals("CheckBox")) {
//			m += "/**" + chirld.cnname + "*/\n";
//			m += " " + chirld.enname + " = new JCheckBox(\""
//					+ chirld.cnname + "\");\n";
//			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
//			m += "/**" + chirld.cnname + "*/\n";
//			m += " " + chirld.enname + " = new JList();\n";
//			m+=" JScrollPane "+chirld.enname +"ScrollPane = new JScrollPane("+chirld.enname +"); \n";
//				
//			m += " ArrayList listDate = new ArrayList();\n";
//			m += " listDate.add(\"RelativeLayout\");\n";
//			m += " listDate.add(\"LinearLayout\");\n";
//			m += chirld.enname
//					+ ".setSelectionMode(ListSelectionModel.SINGLE_SELECTION);\n";
//			m += chirld.enname + ".setListData(listDate.toArray());\n";
//			m += chirld.enname
//					+ ".addListSelectionListener(new ListSelectionListener() {\n";
//
//			m += "	@Override\n";
//			m += "	public void valueChanged(ListSelectionEvent even) {\n";
//
//			m += "	String value = " + chirld.enname
//					+ ".getSelectedValue().toString();\n";
//			m += "	}\n";
//
//			m += "});\n";
//
//			m += parent.enname + ".addComponent(" + chirld.enname + "ScrollPane);\n\n";
		}

		if (chirld.type.equals("JPanel")) {
//			m += "/**" + chirld.cnname + "*/\n";
//			m += " " + chirld.enname + " = new JPanel();\n";
//			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
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

    public static String  genID(int length) //参数为返回随机数的长度
    {
     StringBuffer sb = new StringBuffer();
     Random random = new Random();
     for (int i = 0; i < length; i++)
     {
      sb.append(allChar.charAt(random.nextInt(allChar.length())));
     }
    return sb.toString();
    }
    
    public static String id()
    {
    	
    	return genID(3)+"-"+genID(2)+"-"+genID(3);
    }
}
