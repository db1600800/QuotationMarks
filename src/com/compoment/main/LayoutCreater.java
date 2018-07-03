package com.compoment.main;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JFrame;

import com.compoment.addfunction.web.springmvcSpringMybatis.InterfaceServiceController_springmvcSpringMybatis;
import com.compoment.cut.CheckProblem;
import com.compoment.cut.android.AndroidLayoutXml;
import com.compoment.cut.iphone.IphoneViewControllerXib;
import com.compoment.jsonToJava.creater.WordtableToJavaObject;
import com.compoment.util.VersionCheck;
import com.compoment.workflow.ProjectFrame2;



public class LayoutCreater {
	
	
	public static void main(String[] args) {

		
		LayoutCreater layoutCreater=new LayoutCreater();
		layoutCreater.remote();
		
		
		ProjectFrame2 frame = new ProjectFrame2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		frame.setVisible(true);
	
	}
	
	
	public void remote()
	{}
}
