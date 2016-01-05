package com.compoment.db.tabledocinterfacedoc;

import java.awt.BorderLayout;
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

public class Main extends JFrame {

	private JPanel contentPane;
	DBTablesPanel dbTablesPanel;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		 dbTablesPanel = new DBTablesPanel();
		
		JButton getDBTableBtn = new JButton("DBTable");
		getDBTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDBTables();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(214, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(dbTablesPanel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
							.addGap(22))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(getDBTableBtn)
							.addGap(32))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(getDBTableBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dbTablesPanel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
		);
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
