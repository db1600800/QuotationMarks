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

public class IphoneTableViewHeadViewXib {

	String bodym= "\n\n\n";
	String connection = "";
	String pageName = "";
    String className="";
	public IphoneTableViewHeadViewXib(String pageName,List<CompomentBean> oldBeans) {
		this.pageName=pageName;
		className=firstCharToUpperAndJavaName(pageName);
		
		IphoneViewControllerXibForHorizontallayout iphoneViewControllerXibForHorizontallayout=new IphoneViewControllerXibForHorizontallayout(320,100);
		
		String body=iphoneViewControllerXibForHorizontallayout.analyse(oldBeans);
		String connection=iphoneViewControllerXibForHorizontallayout.getConnection();

		String m="";
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m+="<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.XIB\" version=\"3.0\" toolsVersion=\"6751\" systemVersion=\"14D136\" targetRuntime=\"iOS.CocoaTouch\" propertyAccessControl=\"none\" useAutolayout=\"YES\" useTraitCollections=\"YES\">\n";
		m+="    <dependencies>\n";
		m+="        <deployment identifier=\"iOS\"/>\n";
		m+="        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"6736\"/>\n";
		m+="    </dependencies>\n";
		m+="    <objects>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\" customClass=\""+className+"ViewController\">\n";
		m+="            <connections>\n";
		m+=connection;
		m+="                <outlet property=\"view\" destination=\""+iphoneViewControllerXibForHorizontallayout.maxBean.id +"\" id=\""+id()+"\"/>\n";
		m+="            </connections>\n";
		m+="        </placeholder>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";

		m+=body;
		m += "</objects>\n";
		m += "</document>\n";
		System.out.println(m);
		
		FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className+"HeadView",
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
