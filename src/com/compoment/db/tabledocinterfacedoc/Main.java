package com.compoment.db.tabledocinterfacedoc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.compoment.addfunction.webmanage.Action;
import com.compoment.addfunction.webmanage.ActionForm;
import com.compoment.addfunction.webmanage.AddJsp;
import com.compoment.addfunction.webmanage.QueryJsp;
import com.compoment.workflow.InterfaceDoc;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;
	DBTablesPanel dbTablesPanel;
	DBTableRelativePanel dbTableRelativePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton getDBTableBtn = new JButton("DBTable");
		getDBTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDBTables();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("数据表");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label = new JLabel("表关联");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(577)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(getDBTableBtn)
								.addGap(81))
							.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(scrollPane_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)))))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getDBTableBtn)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(173, Short.MAX_VALUE))
		);
		
	    dbTableRelativePanel = new DBTableRelativePanel();
	    dbTableRelativePanel.setBackground(Color.LIGHT_GRAY);
		dbTableRelativePanel.setPreferredSize(new Dimension(1000,1000));
		scrollPane_1.setViewportView(dbTableRelativePanel);
		
		JButton addToRelateTablesView = new JButton("");
		addToRelateTablesView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbTableRelativePanel.setDBTables(dbTablesPanel.tables);
				dbTablesPanel.cleanSelectTables();
			}
		});
		addToRelateTablesView.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		
		JButton removeFromRelateTablesView = new JButton("");
		removeFromRelateTablesView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbTableRelativePanel.cleanDBTables();
			}
		});
		removeFromRelateTablesView.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(116)
					.addComponent(addToRelateTablesView)
					.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
					.addComponent(removeFromRelateTablesView)
					.addGap(136))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(addToRelateTablesView)
						.addComponent(removeFromRelateTablesView))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		 dbTablesPanel = new DBTablesPanel();
		 dbTablesPanel.setPreferredSize(new Dimension(1000,1000));
		 dbTablesPanel.setBackground(Color.LIGHT_GRAY);
		 scrollPane.setViewportView(dbTablesPanel);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public void getDBTables()
	{
		InterfaceDoc projectDocPanel=new InterfaceDoc();
		projectDocPanel.setModal(true);
		projectDocPanel.setVisible(true);
		
		if(projectDocPanel.interfaceBeans!=null)
		{		
			//接口列表
			dbTablesPanel.setDBTables(projectDocPanel.interfaceBeans);
			
		}
	}
}
