package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.compoment.cut.CompomentDialog;
import com.compoment.util.KeyValue;

public class ProjectPrototypePicPanel {
	ProjectFrame projectFrame;
	 ArrayList listDate = new ArrayList();
	  JList picListListView ;
	  String path;
	public ProjectPrototypePicPanel (ProjectFrame projectFrame)
	{
		this.projectFrame=projectFrame;
	}
	public JPanel create() {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/** picListListView bg1420791991078LinearLayout */
		GroupLayout.SequentialGroup bg1420791979328LinearLayout = layout
				.createSequentialGroup();
		/** 原型图片列表 */
		
		 picListListView = new JList();
		 JScrollPane listScrollPane = new JScrollPane(picListListView); 
		 picListListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 picListListView.setListData(listDate.toArray());
		 
		 picListListView.addMouseListener(new MouseAdapter()
		 {
		 public void mouseReleased(MouseEvent me)
		 {
		 if(checkClickTime())
		 {
			 String fileName=   picListListView.getSelectedValue().toString();
         new PageFrame(path+"/"+fileName);
		 }
		 }});
				

		/** picTitleTextView */
		GroupLayout.ParallelGroup bg1420791991078LinearLayout = layout
				.createParallelGroup();
		/** 原型图片 */
		JLabel picTitleTextView = new JLabel("原型图片");
		bg1420791991078LinearLayout.addComponent(picTitleTextView);

		bg1420791979328LinearLayout.addGroup(bg1420791991078LinearLayout);
		bg1420791979328LinearLayout.addComponent(listScrollPane);
		layout.setVerticalGroup(bg1420791979328LinearLayout);

		// 水平
		/** picTitleTextView picListListView */
		GroupLayout.ParallelGroup bg1420792068718 = layout
				.createParallelGroup();
		/** 原型图片 */
		bg1420792068718.addComponent(picTitleTextView);

		/** 原型图片列表 */
		bg1420792068718.addComponent(listScrollPane);

		layout.setHorizontalGroup(bg1420792068718);
		searchPics(KeyValue.readCache("picPath"));
		return panel;

	}


	
	
	public ArrayList searchPics(String root)
	
	{
		
		path=root;
		
		listDate.clear();
		picListListView.setListData(listDate.toArray());
		
		File file = new File(root);
		

		File[] files = file.listFiles(new FileFilter(){

			public boolean accept(File fl) {
				return !fl.isDirectory();
			}
			
		});
		if(files==null)
		{
		
			
			JOptionPane.showMessageDialog(null,
					"请建立pic文件夹并放入原型图", "", JOptionPane.INFORMATION_MESSAGE);
			
			return null;
		}
		
		for(File f:files)
		{
		 listDate.add(f.getName());
		 
		}
		picListListView.setListData(listDate.toArray());
		return listDate;
	}
	
	 long clickTime=0;
	public boolean checkClickTime()
	 {
	 long nowTime = (new Date()).getTime();
	 if((nowTime-clickTime)<300)
	 {
	 clickTime = nowTime;
	 return true;
	 }
	 clickTime = nowTime;
	 return false;
	 }
}
