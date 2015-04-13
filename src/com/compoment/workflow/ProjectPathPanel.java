package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.compoment.util.KeyValue;

public class ProjectPathPanel {
	ProjectFrame projectFrame;

	public ProjectPathPanel(ProjectFrame projectFrame) {
		this.projectFrame = projectFrame;
	}

	public JPanel create() {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/**
		 * bg1420787698343LinearLayout bg1420787749375LinearLayout
		 * bg1420787826093LinearLayout bg1420787891968LinearLayout
		 * bg1420788010812LinearLayout
		 */
		GroupLayout.SequentialGroup bg1420787683750LinearLayout = layout
				.createSequentialGroup();
		
		
		/** pathTitleTextView */
		GroupLayout.ParallelGroup bg1420787698343LinearLayout = layout
				.createParallelGroup();
		/** 项目路径 */
		JLabel pathTitleTextView = new JLabel("项目路径");
		bg1420787698343LinearLayout.addComponent(pathTitleTextView);

		bg1420787683750LinearLayout.addGroup(bg1420787698343LinearLayout);
		
		
		
		/** androidPathTitleTextView androidPathValueEditText checkBox*/
		GroupLayout.SequentialGroup bg1420787749375LinearLayout = layout
				.createSequentialGroup();
		
		
		/** Android */
		JLabel androidPathTitleTextView = new JLabel("Android");
		JCheckBox androidCheckBox=new JCheckBox();
		GroupLayout.ParallelGroup bgrow1v = layout
				.createParallelGroup();
		
		
		bgrow1v.addComponent(androidPathTitleTextView);
		bgrow1v.addComponent(androidCheckBox);
		
		
		bg1420787749375LinearLayout.addGroup(bgrow1v);
	

		/** c://dd */
		final JTextField androidPathValueEditText = new JTextField("");
		String projectpath=KeyValue.readCache("projectPath");
		if(projectpath!=null && !projectpath.equals(""))
		androidPathValueEditText.setText(projectpath);
		Document doc = androidPathValueEditText.getDocument();

		// 添加DocumentListener监听器
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = androidPathValueEditText.getText();
				KeyValue.writeCache("projectPath",s);
				
				KeyValue.writeCache("picPath", s+"/pic");
				projectFrame.searchPrototypePic();
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	
		bg1420787749375LinearLayout.addComponent(androidPathValueEditText);

		bg1420787683750LinearLayout.addGroup(bg1420787749375LinearLayout);
		
		
		
		
		
		/** iphonePathTitleTextView iphonePathValueEditText */
		GroupLayout.SequentialGroup bg1420787826093LinearLayout = layout
				.createSequentialGroup();
		/** Iphone */
		JLabel iphonePathTitleTextView = new JLabel("Iphone");
		bg1420787826093LinearLayout.addComponent(iphonePathTitleTextView);

		/** c:// */
		final JTextField iphonePathValueEditText = new JTextField("");
		Document docIphone = iphonePathValueEditText.getDocument();

		// 添加DocumentListener监听器
		docIphone.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = iphonePathValueEditText.getText();
				
				KeyValue.writeCache("projectPath", s);
				KeyValue.writeCache("picPath", s+"/pic");
			
				projectFrame.searchPrototypePic();
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		bg1420787826093LinearLayout.addComponent(iphonePathValueEditText);

		bg1420787683750LinearLayout.addGroup(bg1420787826093LinearLayout);
		
		
		
		/** swingPathTitleTextView swingPathValueEditText */
		GroupLayout.SequentialGroup bg1420787891968LinearLayout = layout
				.createSequentialGroup();
		/** Swing */
		JLabel swingPathTitleTextView = new JLabel("Swing");
		bg1420787891968LinearLayout.addComponent(swingPathTitleTextView);

		/** c:// */
		final JTextField swingPathValueEditText = new JTextField("");
		Document docSwing = swingPathValueEditText.getDocument();

		// 添加DocumentListener监听器
		docSwing.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = swingPathValueEditText.getText();
				write(s);
				projectFrame.searchPrototypePic();
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		bg1420787891968LinearLayout.addComponent(swingPathValueEditText);

		bg1420787683750LinearLayout.addGroup(bg1420787891968LinearLayout);
		
		
		
		
	
		
		
		/** webPathTileTextView webPathValueEditText */
		GroupLayout.SequentialGroup bg1420788010812LinearLayout = layout
				.createSequentialGroup();
		/** Web */
		JLabel webPathTileTextView = new JLabel("Web");
		bg1420788010812LinearLayout.addComponent(webPathTileTextView);

		/** c:// */
		JTextField webPathValueEditText = new JTextField("c://");
		bg1420788010812LinearLayout.addComponent(webPathValueEditText);

		bg1420787683750LinearLayout.addGroup(bg1420788010812LinearLayout);
		layout.setVerticalGroup(bg1420787683750LinearLayout);

		// 水平
		/**
		 * webPathValueEditText webPathTileTextView swingPathValueEditText
		 * swingPathTitleTextView iphonePathValueEditText
		 * iphonePathTitleTextView androidPathValueEditText
		 * androidPathTitleTextView pathTitleTextView
		 */
		GroupLayout.ParallelGroup bg1420788132812 = layout
				.createParallelGroup();
		/** c:// */
		bg1420788132812.addComponent(webPathValueEditText);

		/** Web */
		bg1420788132812.addComponent(webPathTileTextView);

		/** c:// */
		bg1420788132812.addComponent(swingPathValueEditText);

		/** Swing */
		bg1420788132812.addComponent(swingPathTitleTextView);

		/** c:// */
		bg1420788132812.addComponent(iphonePathValueEditText);

		/** Iphone */
		bg1420788132812.addComponent(iphonePathTitleTextView);

		/** c://dd */
		bg1420788132812.addComponent(androidPathValueEditText);
		
		
		GroupLayout.SequentialGroup bgrow1 = layout
				.createSequentialGroup();
		bgrow1.addComponent(androidPathTitleTextView);
		bgrow1.addComponent(androidCheckBox);
		
		
		
	
		/** Android */
		bg1420788132812.addGroup(bgrow1);

		/** 项目路径 */
		bg1420788132812.addComponent(pathTitleTextView);

		layout.setHorizontalGroup(bg1420788132812);

		// panel.setSize(width, height)
		// pack();
		// setVisible(true);

		return panel;
	}



	
	public void write(String s)
	{
		
		
	}
}
