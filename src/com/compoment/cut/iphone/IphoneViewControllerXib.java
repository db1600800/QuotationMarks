package com.compoment.cut.iphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import com.compoment.util.DeepCopy;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class IphoneViewControllerXib {

	String bodym = "\n\n\n";
	String connection = "";
	String pageName = "";
	String className = "";

    int rootViewWidth=320;
    int rootViewHeight=568;
    
    
    List<CompomentBean> deepCopyCompomentBeans;
	public IphoneViewControllerXib(int cellWidth,int cellHeight) {
		rootViewWidth=cellWidth;
		rootViewHeight=cellHeight;
	}
	
	public IphoneViewControllerXib(String pageName, List<CompomentBean> oldBeans) {
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
		m += "</objects>\n";
		m += "</document>\n";
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
		
		 Collections.sort(oldBeans, comparatorDate);

		 
		 try {
			deepCopyCompomentBeans=DeepCopy.deepCopy(oldBeans);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		 
		 
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();

		// 找出容器及maxbean
		for (CompomentBean bean : deepCopyCompomentBeans) {
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
		if(maxBean.h>500 &&maxBean.h<569)
		{
			rootViewHeight= 568;
		}
		
		else if(maxBean.h>35 && maxBean.h<41)
		{
			rootViewHeight= 40;
		}else
		{
			rootViewHeight=maxBean.h;
		}
		
		
		
		//修正定位
		for (CompomentBean bean : deepCopyCompomentBeans) {
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
		
		

		bodym += "                    <view key=\"view\" translatesAutoresizingMaskIntoConstraints=\"NO\" contentMode=\"scaleToFill\" id=\""
				+ maxBean.id + "\">\n";
		bodym += "<rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\""+rootViewWidth+"\" height=\""+rootViewHeight+"\"/>\n";
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

		//bodym += constraint(maxBean);
		bodym += "                    </view>\n";
		

		return bodym;
	}
	
	
	public String  getConnection()
	{
		return connection;
	}


//	int parentTopSpace=20;
//	int parentHeight=40;
//	int chirldleftspace=30;
//	int chirldleftspaceConstaraint=30;
//	
//	int textViewHeight=20;
//	int buttonWidth=60;
//	int buttonHeght=30;
//
//	int editTextWidht=100;
//	int editTextHeight=30;
//	
//	int imageWidth=30;
//	int imageHeight=30;
//	int imageWidthConstraint=30;
//	int imageHeightConstraint=30;
	
	public void parent(CompomentBean bean) {


	        Collections.sort(bean.chirlds, comparatorDate);
	    
		//有	儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			
			
			if (bean.type.equals("ScrollViewLayout")) {
					
				String newId=bean.newId();
				bodym += " <scrollView contentMode=\"scaleToFill\"  clipsSubviews=\"YES\" multipleTouchEnabled=\"YES\"   translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+ newId + "\">\n";

				bodym += "<rect key=\"frame\" x=\"" + ( bean.x)
					+ "\" y=\"" + (bean.y) + "\" width=\""
					+ bean.w + "\" height=\"" + bean.h + "\"/>\n";


			bodym += "<autoresizingMask key=\"autoresizingMask\" widthSizable=\"YES\" heightSizable=\"YES\"/>\n";
		
			bodym += "  </scrollView>\n";
			
			connection += "      <outlet property=\""
					+ bean.enname + "\" destination=\"" + newId
					+ "\" id=\"" + id() + "\"/>\n";
               
			}else
			{
			
//		
			for (CompomentBean chirld : bean.chirlds) {
				
				//这个儿子是容器 layout
				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					if(chirld.layoutNoUseForIos!=true)
					{
					bodym += " <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""							+ chirld.id + "\">\n";

						bodym += "<rect key=\"frame\" x=\"" + (chirld.x - bean.x)
							+ "\" y=\"" + (chirld.y - bean.y) + "\" width=\""
							+ chirld.w + "\" height=\"" + chirld.h + "\"/>\n";

		
					bodym += "                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
					bodym += "                                <subviews>\n";
					}
					
					parent(chirld);
					
					if(chirld.layoutNoUseForIos!=true)
					{
					bodym += "                                </subviews>\n";
					bodym += "                                <color key=\"backgroundColor\" red=\""
							+ bean.getR(chirld.bgRgb16ios)
							+ "\" green=\""
							+ bean.getG(chirld.bgRgb16ios)
							+ "\" blue=\""
							+ bean.getB(chirld.bgRgb16ios)
							+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
				

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

					bodym += "                            </view>\n";
					}

				} else {//这个儿子是非容器 
					
					chirld(chirld, bean);
				}
			}

		}
		}

	}

	
	
	public void chirld(CompomentBean chirld, CompomentBean parent) {//这个儿子是非容器

	   
	   
		 
	
		 
		if (chirld.type.equals("TextView")) {
			bodym += "                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\""
					+ chirld.cnname
					+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			if(parent.layoutNoUseForIos==true)
			{
				bodym += "                                        <rect key=\"frame\" x=\""
						+ (chirld.x )
						+ "\" y=\""
	                 	+ (chirld.y )
						+ "\" width=\""
						+ "80"
						+ "\" height=\""
						+ "15"
						+ "\"/>\n";
			}else
			{
			bodym += "                                        <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x)
					+ "\" y=\""
                 	+ (chirld.y - parent.y)
					+ "\" width=\""
					+ "80"
					+ "\" height=\""
					+ "15"
					+ "\"/>\n";
			}

			// bodym+="<color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16ios)+"\" green=\""+chirld.getG(chirld.bgRgb16ios)+"\" blue=\""+chirld.getB(chirld.bgRgb16ios)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
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
		
		if (chirld.type.equals("Line")) {
			bodym += "                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\""
					
					+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			
			if(parent.layoutNoUseForIos==true)
			{
				bodym += "                                        <rect key=\"frame\" x=\""
						+ (chirld.x )
						+ "\" y=\""
	                 	+ (chirld.y)
						+ "\" width=\""
						+ "320"
						+ "\" height=\""
						+ "1"
						+ "\"/>\n";
			}else
			{
			bodym += "                                        <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x)
					+ "\" y=\""
                 	+ (chirld.y - parent.y)
					+ "\" width=\""
					+ "320"
					+ "\" height=\""
					+ "1"
					+ "\"/>\n";
			}

			// bodym+="<color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16ios)+"\" green=\""+chirld.getG(chirld.bgRgb16ios)+"\" blue=\""+chirld.getB(chirld.bgRgb16ios)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
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
			bodym += "                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			
			if(parent.layoutNoUseForIos==true)
			{
				bodym += "                                <rect key=\"frame\" x=\""
						+ (chirld.x ) + "\" y=\"" + (chirld.y )
						+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
						+ "\"/>\n";
			}else
			{
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
					+ "\"/>\n";
			}
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
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
		}

		if (chirld.type.equals("CheckBox")) {
			
			//图片部分
			
			bodym += "                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			
			if(parent.layoutNoUseForIos==true)
			{
				bodym += "                                <rect key=\"frame\" x=\""
						+ (chirld.x ) + "\" y=\"" + (chirld.y )
						+ "\" width=\"20\" height=\"20\"/>\n";
			}else
			{
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"" + chirld.w + "\" height=\"" + chirld.h
					+ "\"/>\n";
			}
			bodym += "                                <color key=\"backgroundColor\" red=\""
					+ chirld.getR(chirld.bgRgb16ios)
					+ "\" green=\""
					+ chirld.getG(chirld.bgRgb16ios)
					+ "\" blue=\""
					+ chirld.getB(chirld.bgRgb16ios)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			
			

			bodym += "   <color key=\"tintColor\" white=\"0.0\" alpha=\"0.0\" colorSpace=\"calibratedWhite\"/>\n";
			
		    if(!chirld.picName.equals("图片名"))
		    {
			
			bodym += "                                <state key=\"normal\" title=\"\"  backgroundImage=\""+chirld.picName+".png\">\n";
		    }else
		    {
//		    	bodym += "                                <state key=\"normal\" title=\""
//						+ chirld.cnname + "\">\n";
		    	
		    	
		    	bodym += "                                <state key=\"normal\" title=\"\"  backgroundImage=\"check.png\">\n";
		    }
			bodym += "                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			bodym += "                                </state>\n";
	
			bodym += "                            </button>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";
			
			
			
			//文字部分
			
			
			bodym += "                                    <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\""
					+ chirld.cnname
					+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.newId() + "\">\n";
			if(parent.layoutNoUseForIos==true)
			{
				bodym += "                                        <rect key=\"frame\" x=\""
						+ (chirld.x+22 )
						+ "\" y=\""
	                 	+ (chirld.y )
						+ "\" width=\""
						+ ""+(chirld.w-20) +""
						+ "\" height=\""
						+ ""+chirld.h+""
						+ "\"/>\n";
			}else
			{
			bodym += "                                        <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x+22)
					+ "\" y=\""
                 	+ (chirld.y - parent.y)
					+ "\" width=\""
					+ ""+(chirld.w-20)+""
					+ "\" height=\""
					+ ""+chirld.h+""
					+ "\"/>\n";
			}

			// bodym+="<color key=\"backgroundColor\" red=\""+chirld.getR(chirld.bgRgb16ios)+"\" green=\""+chirld.getG(chirld.bgRgb16ios)+"\" blue=\""+chirld.getB(chirld.bgRgb16ios)+"\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";

			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
			bodym += "                                        <color key=\"textColor\" red=\""
					+ chirld.getR(chirld.rgb16)
					+ "\" green=\""
					+ chirld.getG(chirld.rgb16)
					+ "\" blue=\""
					+ chirld.getB(chirld.rgb16)
					+ "\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
			bodym += "                                        <nil key=\"highlightedColor\"/>\n";
			bodym += "                                    </label>\n";
		

		}
		
		
		if (chirld.type.equals("EditText")) {
			bodym += "                         <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ chirld.id + "\">\n";
			bodym += "                                <rect key=\"frame\" x=\""
					+ (chirld.x - parent.x) + "\" y=\"" + (chirld.y - parent.y)
					+ "\" width=\"100\" height=\"30\"/>\n";



			bodym += "                                        <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\""
					+ chirld.textSize + "\"/>\n";
			bodym += "                                        <textInputTraits key=\"textInputTraits\"/>\n";
			bodym += "                                    </textField>\n";
			connection += "                        <outlet property=\""
					+ chirld.enname + "\" destination=\"" + chirld.id
					+ "\" id=\"" + id() + "\"/>\n";

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
