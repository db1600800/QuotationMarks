package com.compoment.workflow;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import com.compoment.util.KeyValue;

public class ProjectFrame extends JFrame {
	ProjectPrototypePicPanel projectPrototypePicPanel;
	ProjectPathPanel projectPathPanel;
	//ProjectDocPanel projectDocPanel;
	
	
	public ProjectFrame() {

		super("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String laf = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException exc) {
		} catch (Exception exc) {
		}

		Container c = getContentPane();
		GroupLayout layout = new GroupLayout(c);
		c.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/** pathView right1View picView right2View docsView */
		GroupLayout.ParallelGroup bg1420785982703LinearLayout = layout
				.createParallelGroup();
		/** 项目路径 */
		 projectPathPanel=new ProjectPathPanel(this);
		JPanel pathView = projectPathPanel.create();
		
		bg1420785982703LinearLayout.addComponent(pathView);

		/** 箭头 */
		JLabel right1View = new JLabel("--->");
		bg1420785982703LinearLayout.addComponent(right1View);

		/** 原型图片 */
		 projectPrototypePicPanel=new ProjectPrototypePicPanel(this);
		JPanel picView = projectPrototypePicPanel.create();
		bg1420785982703LinearLayout.addComponent(picView);

		/** 箭头2 */
		JLabel right2View = new JLabel("--->");
		bg1420785982703LinearLayout.addComponent(right2View);

		/** 文档 */
		//projectDocPanel=new ProjectDocPanel(this);
//		JPanel docsView = projectDocPanel.create();
	//	bg1420785982703LinearLayout.addComponent(docsView);

		layout.setVerticalGroup(bg1420785982703LinearLayout);

		// 水平
		/**
		 * bg1420786383484 bg1420786377000 bg1420786374890 bg1420786368484
		 * bg1420786362812
		 */
		GroupLayout.SequentialGroup bg1420786390765 = layout
				.createSequentialGroup();
		
		/** pathView */
		GroupLayout.SequentialGroup bg1420786362812 = layout
				.createSequentialGroup();
		/** 项目路径 */
		bg1420786362812.addComponent(pathView);

		bg1420786390765.addGroup(bg1420786362812);
		
		
		
		/** right2View */
		GroupLayout.SequentialGroup bg1420786377000 = layout
				.createSequentialGroup();
		/** 箭头2 */
		bg1420786377000.addComponent(right2View);

		bg1420786390765.addGroup(bg1420786377000);
		
		
		
		/** picView */
		GroupLayout.SequentialGroup bg1420786374890 = layout
				.createSequentialGroup();
		/** 原型图片 */
		bg1420786374890.addComponent(picView);

		bg1420786390765.addGroup(bg1420786374890);
		
		
		
		/** right1View */
		GroupLayout.SequentialGroup bg1420786368484 = layout
				.createSequentialGroup();
		/** 箭头 */
		bg1420786368484.addComponent(right1View);

		bg1420786390765.addGroup(bg1420786368484);
		
		
		/** docsView */
		GroupLayout.SequentialGroup bg1420786383484 = layout
				.createSequentialGroup();
		/** 文档 */
		//bg1420786383484.addComponent(docsView);

		bg1420786390765.addGroup(bg1420786383484);
		
		
		
		
		
		layout.setHorizontalGroup(bg1420786390765);

		setLocation(200, 200);
		this.setTitle("工程设置");
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ProjectFrame();
	}
	
	
	public void searchPrototypePic()
	{
		ArrayList result=projectPrototypePicPanel.searchPics(KeyValue.readCache("picPath"));
		 
	}
	
	
	
	

	
}