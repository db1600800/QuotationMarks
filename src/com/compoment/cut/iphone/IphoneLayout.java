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
	public void createJFrame(List<CompomentBean> oldBeans,
			List<CompomentBean> newBeans) {

		analyseRelativeForVertical(oldBeans);

		analyseRelativeForSwingHorizontal(newBeans);

	
		System.out.println(m);

	}

	

	public void analyseRelativeForVertical(List<CompomentBean> oldBeans) {
		// Collections.sort(oldBeans, comparatorDate);
		m += "//垂直\n";
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

		
		
		
		
		m+="                <viewController title=\""+pageName+"\" id=\""+genID(3)+"-"+genID(2)+"-"+genID(3)+"\" customClass=\"ViewController\" customModule=\"mobile\" customModuleProvider=\"target\" sceneMemberID=\"viewController\">\n";
		m+="                    <layoutGuides>\n";
		m+="                        <viewControllerLayoutGuide type=\"top\" id=\""+genID(3)+"-"+genID(2)+"-"+genID(3)+"\"/>\n";
		m+="                        <viewControllerLayoutGuide type=\"bottom\" id=\""+genID(3)+"-"+genID(2)+"-"+genID(3)+"\"/>\n";
		m+="                    </layoutGuides>\n";
		
		m+="                    <view key=\"view\" contentMode=\"scaleToFill\" id=\""+maxBean.id+"\">\n";
		m+="                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="                        <subviews>\n";
		
		parent(maxBean);
		
		m+="                        </subviews>\n";
		m+="                        <color key=\"backgroundColor\" white=\"0.66666666666666663\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";

		m+="                        <constraints>\n";
		m+="                            <constraint firstItem=\"SyQ-o4-jyF\" firstAttribute=\"leading\" secondItem=\"kh9-bI-dsS\" secondAttribute=\"leadingMargin\" constant=\"20\" id=\"1hU-hb-12c\"/>\n";
		m+="                            <constraint firstAttribute=\"centerY\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"centerY\" id=\"5gX-fc-Alk\"/>\n";
		m+="                            <constraint firstItem=\"2fi-mo-0CV\" firstAttribute=\"top\" secondItem=\"SyQ-o4-jyF\" secondAttribute=\"bottom\" constant=\"30\" id=\"pwU-eI-0fQ\"/>\n";
		m+="                            <constraint firstAttribute=\"trailingMargin\" secondItem=\"SyQ-o4-jyF\" secondAttribute=\"trailing\" constant=\"20\" id=\"q3b-qz-Qw5\"/>\n";
		m+="                            <constraint firstItem=\"ktc-wb-5bz\" firstAttribute=\"leading\" relation=\"lessThanOrEqual\" secondItem=\"kh9-bI-dsS\" secondAttribute=\"leadingMargin\" constant=\"20\" id=\"tqf-Ym-2sb\"/>\n";
		m+="                            <constraint firstAttribute=\"trailingMargin\" relation=\"lessThanOrEqual\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"trailing\" constant=\"20\" id=\"vle-4u-4Vv\"/>\n";
		m+="                        </constraints>\n";
		m+="                        <variation key=\"default\">\n";
		m+="                            <mask key=\"subviews\">\n";
		m+="                                <exclude reference=\"ktc-wb-5bz\"/>\n";
		m+="                                <exclude reference=\"SyQ-o4-jyF\"/>\n";
		m+="                            </mask>\n";
		m+="                            <mask key=\"constraints\">\n";
		m+="                                <exclude reference=\"pwU-eI-0fQ\"/>\n";
		m+="                                <exclude reference=\"1hU-hb-12c\"/>\n";
		m+="                                <exclude reference=\"q3b-qz-Qw5\"/>\n";
		m+="                                <exclude reference=\"5gX-fc-Alk\"/>\n";
		m+="                                <exclude reference=\"tqf-Ym-2sb\"/>\n";
		m+="                                <exclude reference=\"vle-4u-4Vv\"/>\n";
		m+="                            </mask>\n";
		m+="                        </variation>\n";
		m+="                        <variation key=\"widthClass=compact\">\n";
		m+="                            <mask key=\"subviews\">\n";
		m+="                                <include reference=\"ktc-wb-5bz\"/>\n";
		m+="                                <include reference=\"SyQ-o4-jyF\"/>\n";
		m+="                            </mask>\n";
		m+="                            <mask key=\"constraints\">\n";
		m+="                                <include reference=\"pwU-eI-0fQ\"/>\n";
		m+="                                <include reference=\"1hU-hb-12c\"/>\n";
		m+="                                <include reference=\"q3b-qz-Qw5\"/>\n";
		m+="                                <include reference=\"5gX-fc-Alk\"/>\n";
		m+="                                <include reference=\"tqf-Ym-2sb\"/>\n";
		m+="                                <include reference=\"vle-4u-4Vv\"/>\n";
		m+="                            </mask>\n";
		m+="                        </variation>\n";

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
					m+="                                <color key=\"backgroundColor\" red=\"1\" green=\"1\" blue=\"1\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
					m+="                                <constraints>\n";
					m+="                                    <constraint firstItem=\"COT-hb-yaP\" firstAttribute=\"centerY\" secondItem=\"itq-au-h9W\" secondAttribute=\"centerY\" constant=\"-0.75\" id=\"4dZ-Um-rQ1\"/>\n";
					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"leading\" secondItem=\"IYI-bQ-iz6\" secondAttribute=\"trailing\" constant=\"10\" id=\"Fc6-1K-XdQ\"/>\n";
					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"centerY\" secondItem=\"IYI-bQ-iz6\" secondAttribute=\"centerY\" constant=\"1.75\" id=\"O6k-Fi-NBo\"/>\n";
					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"top\" secondItem=\"itq-au-h9W\" secondAttribute=\"bottom\" constant=\"19\" id=\"QJt-r8-NgS\"/>\n";
					m+="                                    <constraint firstItem=\"itq-au-h9W\" firstAttribute=\"top\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"top\" constant=\"20\" id=\"RCq-lH-IOb\"/>\n";
					m+="                                    <constraint firstItem=\"COT-hb-yaP\" firstAttribute=\"leading\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"leading\" constant=\"20\" id=\"Xnx-Hi-9Bk\"/>\n";
					m+="                                    <constraint firstItem=\"IYI-bQ-iz6\" firstAttribute=\"leading\" secondItem=\"ktc-wb-5bz\" secondAttribute=\"leading\" constant=\"20\" id=\"Y5f-kA-Y9q\"/>\n";
					m+="                                    <constraint firstAttribute=\"trailingMargin\" secondItem=\"itq-au-h9W\" secondAttribute=\"trailing\" constant=\"10\" id=\"ctI-ZM-EAO\"/>\n";
					m+="                                    <constraint firstItem=\"0iP-qh-p3z\" firstAttribute=\"centerY\" secondItem=\"IYI-bQ-iz6\" secondAttribute=\"centerY\" constant=\"1.75\" id=\"j41-yy-0bI\"/>\n";
					m+="                                    <constraint firstItem=\"itq-au-h9W\" firstAttribute=\"leading\" secondItem=\"COT-hb-yaP\" secondAttribute=\"trailing\" constant=\"10\" id=\"lmb-pJ-JUc\"/>\n";
					m+="                                    <constraint firstAttribute=\"bottomMargin\" secondItem=\"0iP-qh-p3z\" secondAttribute=\"bottom\" constant=\"20\" id=\"tPu-9L-QiK\"/>\n";
					m+="                                    <constraint firstAttribute=\"trailingMargin\" secondItem=\"0iP-qh-p3z\" secondAttribute=\"trailing\" constant=\"10\" id=\"wIu-1n-N1A\"/>\n";
					m+="                                </constraints>\n";
					m+="                                <userDefinedRuntimeAttributes>\n";
					m+="                                    <userDefinedRuntimeAttribute type=\"number\" keyPath=\"layer.borderWidth\">\n";
					m+="                                        <integer key=\"value\" value=\"1\"/>\n";
					m+="                                    </userDefinedRuntimeAttribute>\n";
					m+="                                    <userDefinedRuntimeAttribute type=\"color\" keyPath=\"layer.borderColor\">\n";
					m+="                                        <color key=\"value\" red=\"0.74128657318433855\" green=\"1\" blue=\"0.73626735733780047\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
					m+="                                    </userDefinedRuntimeAttribute>\n";
					m+="                                    <userDefinedRuntimeAttribute type=\"number\" keyPath=\"layer.cornerRadius\">\n";
					m+="                                        <integer key=\"value\" value=\"10\"/>\n";
					m+="                                    </userDefinedRuntimeAttribute>\n";
					m+="                                </userDefinedRuntimeAttributes>\n";
					m+="                                <variation key=\"default\">\n";
					m+="                                    <mask key=\"subviews\">\n";
					m+="                                        <exclude reference=\"IYI-bQ-iz6\"/>\n";
					m+="                                        <exclude reference=\"0iP-qh-p3z\"/>\n";
					m+="                                        <exclude reference=\"itq-au-h9W\"/>\n";
					m+="                                        <exclude reference=\"COT-hb-yaP\"/>\n";
					m+="                                    </mask>\n";
					m+="                                    <mask key=\"constraints\">\n";
					m+="                                        <exclude reference=\"Fc6-1K-XdQ\"/>\n";
					m+="                                        <exclude reference=\"O6k-Fi-NBo\"/>\n";
					m+="                                        <exclude reference=\"QJt-r8-NgS\"/>\n";
					m+="                                        <exclude reference=\"j41-yy-0bI\"/>\n";
					m+="                                        <exclude reference=\"tPu-9L-QiK\"/>\n";
					m+="                                        <exclude reference=\"wIu-1n-N1A\"/>\n";
					m+="                                        <exclude reference=\"4dZ-Um-rQ1\"/>\n";
					m+="                                        <exclude reference=\"Xnx-Hi-9Bk\"/>\n";
					m+="                                        <exclude reference=\"Y5f-kA-Y9q\"/>\n";
					m+="                                        <exclude reference=\"RCq-lH-IOb\"/>\n";
					m+="                                        <exclude reference=\"ctI-ZM-EAO\"/>\n";
					m+="                                        <exclude reference=\"lmb-pJ-JUc\"/>\n";
					m+="                                    </mask>\n";
					m+="                                </variation>\n";
					m+="                                <variation key=\"widthClass=compact\">\n";
					m+="                                    <mask key=\"subviews\">\n";
					m+="                                        <include reference=\"IYI-bQ-iz6\"/>\n";
					m+="                                        <include reference=\"0iP-qh-p3z\"/>\n";
					m+="                                        <include reference=\"itq-au-h9W\"/>\n";
					m+="                                        <include reference=\"COT-hb-yaP\"/>\n";
					m+="                                    </mask>\n";
					m+="                                    <mask key=\"constraints\">\n";
					m+="                                        <include reference=\"Fc6-1K-XdQ\"/>\n";
					m+="                                        <include reference=\"O6k-Fi-NBo\"/>\n";
					m+="                                        <include reference=\"QJt-r8-NgS\"/>\n";
					m+="                                        <include reference=\"j41-yy-0bI\"/>\n";
					m+="                                        <include reference=\"tPu-9L-QiK\"/>\n";
					m+="                                        <include reference=\"wIu-1n-N1A\"/>\n";
					m+="                                        <include reference=\"4dZ-Um-rQ1\"/>\n";
					m+="                                        <include reference=\"Xnx-Hi-9Bk\"/>\n";
					m+="                                        <include reference=\"Y5f-kA-Y9q\"/>\n";
					m+="                                        <include reference=\"RCq-lH-IOb\"/>\n";
					m+="                                        <include reference=\"ctI-ZM-EAO\"/>\n";
					m+="                                        <include reference=\"lmb-pJ-JUc\"/>\n";
					m+="                                    </mask>\n";
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
			m+="                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\"   密码 \" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"IYI-bQ-iz6\">\n";
			m+="                                        <rect key=\"frame\" x=\"0.0\" y=\"-21\" width=\"42\" height=\"21\"/>\n";
			m+="                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"17\"/>\n";
			m+="                                        <color key=\"textColor\" red=\"0.0\" green=\"0.0\" blue=\"0.0\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			m+="                                        <nil key=\"highlightedColor\"/>\n";
			m+="                                    </label>\n";
		}

		if (chirld.type.equals("Button")) {
			m+="                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"SyQ-o4-jyF\">\n";
			m+="                                <rect key=\"frame\" x=\"-23\" y=\"-15\" width=\"46\" height=\"30\"/>\n";
			m+="                                <color key=\"backgroundColor\" red=\"0.039361924819999998\" green=\"0.52156865600000002\" blue=\"0.11210238779999999\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			m+="                                <color key=\"tintColor\" red=\"1\" green=\"1\" blue=\"1\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			m+="                                <state key=\"normal\" title=\"登陆\">\n";
			m+="                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			m+="                                </state>\n";
			m+="                            </button>\n";
		}

		if (chirld.type.equals("EditText")) {
			m+="                                    <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"itq-au-h9W\">\n";
			m+="                                        <rect key=\"frame\" x=\"0.0\" y=\"-30\" width=\"97\" height=\"30\"/>\n";
			m+="                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
			m+="                                        <textInputTraits key=\"textInputTraits\"/>\n";
			m+="                                    </textField>\n";
			
		}

		if (chirld.type.equals("CheckBox")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JCheckBox(\""
					+ chirld.cnname + "\");\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JList();\n";
			m+=" JScrollPane "+chirld.enname +"ScrollPane = new JScrollPane("+chirld.enname +"); \n";
				
			m += " ArrayList listDate = new ArrayList();\n";
			m += " listDate.add(\"RelativeLayout\");\n";
			m += " listDate.add(\"LinearLayout\");\n";
			m += chirld.enname
					+ ".setSelectionMode(ListSelectionModel.SINGLE_SELECTION);\n";
			m += chirld.enname + ".setListData(listDate.toArray());\n";
			m += chirld.enname
					+ ".addListSelectionListener(new ListSelectionListener() {\n";

			m += "	@Override\n";
			m += "	public void valueChanged(ListSelectionEvent even) {\n";

			m += "	String value = " + chirld.enname
					+ ".getSelectedValue().toString();\n";
			m += "	}\n";

			m += "});\n";

			m += parent.enname + ".addComponent(" + chirld.enname + "ScrollPane);\n\n";
		}

		if (chirld.type.equals("JPanel")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JPanel();\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}

	public void analyseRelativeForSwingHorizontal(List<CompomentBean> newBeans) {

		m += "//水平\n";
		int maxW = 0;
		int maxH = 0;

		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : newBeans) {
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

		if (maxBean == null)
			return;

		// 儿子找父亲 （子控件找容器）
		for (CompomentBean bean : newBeans) {
			if (!bean.type.contains("Layout")) {
				CompomentBean nearLayout = null;
				Integer minDistance = null;
				for (CompomentBean layout : layouts) {
					if (bean.x >= layout.x && bean.x <= layout.w + layout.x
							&& bean.y >= layout.y
							&& bean.y <= layout.h + layout.y) {
						if (minDistance == null) {
							minDistance = bean.x - layout.x;
							nearLayout = layout;
						} else {
							if (minDistance > bean.x - layout.x) {
								minDistance = bean.x - layout.x;
								nearLayout = layout;
							}
						}
					}
				}

				if (nearLayout != null)
					nearLayout.setChirld(bean);
			}

		}

		// 儿子找父亲（子容器找父容器）
		for (CompomentBean bean : layouts) {

			CompomentBean nearLayout = null;
			Integer minDistance = null;
			for (CompomentBean layout : layouts) {

				if (!layout.enname.equals(bean.enname) && bean.x >= layout.x
						&& bean.x <= layout.w + layout.x && bean.y >= layout.y
						&& bean.y <= layout.h + layout.y) {

					if (minDistance == null) {
						minDistance = bean.x - layout.x;
						nearLayout = layout;
					} else {
						if (minDistance > bean.x - layout.x) {
							minDistance = bean.x - layout.x;
							nearLayout = layout;
						}
					}

				}

			}

			if (nearLayout != null)
				nearLayout.setChirld(bean);

		}

		// LinearLayout RelativeLayout 儿子们的位置关系(排版方向 水平或垂直)
		for (CompomentBean bean : layouts) {
			if (bean.type.contains("Layout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {
					CompomentBean firstChirld = null;
					CompomentBean secondChirld = null;

					int count = 0;
					for (CompomentBean chirld : bean.chirlds) {
						if (count == 0) {
							firstChirld = chirld;
						} else if (count == 1) {
							secondChirld = chirld;
						}
						count++;
					}

					if (firstChirld.x > secondChirld.x) {
						if (firstChirld.x >= secondChirld.x + secondChirld.w) {
							bean.orientation = "horizontal";
						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x < secondChirld.x) {
						if (secondChirld.x >= firstChirld.x + firstChirld.w) {
							bean.orientation = "horizontal";
						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x == secondChirld.x) {
						bean.orientation = "vertical";
					}

				} else {
					bean.orientation = "horizontal";
				}
			}

		}

		parentForHorizontal(maxBean);

		m += "layout.setHorizontalGroup(" + maxBean.enname + ");\n\n";

	}

	public void parentForHorizontal(CompomentBean bean) {

		if (bean.type.contains("Layout")) {
			m += "/**";
			if (bean.chirlds != null && bean.chirlds.size() > 0) {
				for (CompomentBean chirld : bean.chirlds) {
					m += chirld.enname + " ";
				}
			}
			m += "*/\n";
			// 水平
			if (bean.orientation.equals("horizontal")) {
				m += "GroupLayout.SequentialGroup " + bean.enname
						+ " = layout.createSequentialGroup();\n";
			} else if (bean.orientation.equals("vertical")) {

				m += "GroupLayout.ParallelGroup " + bean.enname
						+ " = layout.createParallelGroup();\n";

			}
		}

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parentForHorizontal(chirld);
					m += bean.enname + ".addGroup(" + chirld.enname
							+ ");\n\n\n";
				} else {
					chirldForHorizontal(chirld, bean);
				}
			}

		}

	}

	public void chirldForHorizontal(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JLabel " + chirld.enname + " = new JLabel(\"" +
			// chirld.cnname
			// + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("Button")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JButton " + chirld.enname + " = new JButton(\""
			// + chirld.cnname + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("EditText")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JTextField " + chirld.enname + " = new JTextField(\""
			// + chirld.cnname + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("CheckBox")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JCheckBox " + chirld.enname + " = new JCheckBox(\""
			// + chirld.cnname + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JList " + chirld.enname + " = new JList(\"" + chirld.cnname
			// + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + "ScrollPane);\n\n";
		}

		if (chirld.type.equals("JPanel")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JPanel " + chirld.enname + " = new JPanel();\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}
	
	
	
	
	public String typeChange(String type) {

		if (type.equals("TextView")) {
			
			return "JLabel";
		}

		if (type.equals("Button")) {
			
			return  "JButton";
		}

		if (type.equals("EditText")) {
			return "JTextField";
		}

		if (type.equals("CheckBox")) {
			
			return  "JCheckBox" ;
		}

		if (type.equals("ListView")) {
			
			return "JList" ;
		}

		if (type.equals("JPanel")) {
			return "JPanel" ;
		}
		return "";
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
}
