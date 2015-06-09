package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PageTypePanel {
	PageFrame pageFrame;
	public PageTypePanel(PageFrame pageFrame){
		this.pageFrame=pageFrame;
	}

	public JPanel create() {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/** pageTypeTitleTextView pageTypeListListView */
		GroupLayout.SequentialGroup bg1420976916712LinearLayout = layout
				.createSequentialGroup();
		/** 页面类型 */
		JLabel pageTypeTitleTextView = new JLabel("页面类型");
		bg1420976916712LinearLayout.addComponent(pageTypeTitleTextView);

		/** 页面类型列表 */
		final JList pageTypeListListView = new JList();
		ArrayList listDate = new ArrayList();
		listDate.add("---Android---");
		listDate.add("Activity-Android");
		listDate.add("Item-Android");
		listDate.add("Fragment-Android");
		listDate.add("Activity-ChirldView-Android");
		listDate.add("---Swing---");
		listDate.add("JFrame-Swing");
		listDate.add("JDialog-Swing");
		listDate.add("JPanel-Swing");
		listDate.add("---IOS---");
		listDate.add("ViewController-IOS");
		listDate.add("TableViewCell-IOS");
		
		pageTypeListListView
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pageTypeListListView.setListData(listDate.toArray());
		pageTypeListListView
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent even) {
						String value = pageTypeListListView.getSelectedValue()
								.toString();
						if(!value.contains("---"))
						{
						pageFrame.pageType=value;
						}else
						{
							pageFrame.pageType=null;
						}
					}
				});

		
		bg1420976916712LinearLayout.addComponent(pageTypeListListView);

		layout.setVerticalGroup(bg1420976916712LinearLayout);

		// 水平
		/** pageTypeTitleTextView pageTypeListListView */
		GroupLayout.ParallelGroup bg1420977009718 = layout
				.createParallelGroup();
		/** 页面类型 */
		bg1420977009718.addComponent(pageTypeTitleTextView);

		/** 页面类型列表 */
		bg1420977009718.addComponent(pageTypeListListView);

		layout.setHorizontalGroup(bg1420977009718);

		return panel;
	}
	
}
