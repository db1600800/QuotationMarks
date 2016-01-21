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
import javax.swing.JTextField;

public class Main extends JFrame {

	private JPanel contentPane;
	DBTablesPanel dbTablesPanel;
	DBTableRelativePanel dbTableRelativePanel;
	private JTextField sqlResultEditText;
	
	JButton addToRelateTablesView;
	JButton removeFromRelateTablesView;
	JButton queryRelateButton  ;
	JButton deleteRelateButton ;
	JButton updateRelateButton ;
	JButton addRelateButton ;
	
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
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(577)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.addComponent(label, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
								.addComponent(getDBTableBtn)
								.addGap(81))
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))))
					.addGap(27))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		
		 queryRelateButton = new JButton("查询");
	
		
		 deleteRelateButton = new JButton("删除");
		
		
		 updateRelateButton = new JButton("更新");
		
		
		 addRelateButton = new JButton("新增");
		
		sqlResultEditText = new JTextField();
		sqlResultEditText.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(queryRelateButton)
							.addGap(18)
							.addComponent(deleteRelateButton)
							.addGap(18)
							.addComponent(updateRelateButton)
							.addGap(18)
							.addComponent(addRelateButton))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(sqlResultEditText, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(queryRelateButton)
						.addComponent(deleteRelateButton)
						.addComponent(updateRelateButton)
						.addComponent(addRelateButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sqlResultEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
	    dbTableRelativePanel = new DBTableRelativePanel();
	    dbTableRelativePanel.setBackground(Color.LIGHT_GRAY);
		dbTableRelativePanel.setPreferredSize(new Dimension(1000,1000));
		scrollPane_1.setViewportView(dbTableRelativePanel);
		
		//选择到表关系页
		addToRelateTablesView = new JButton("");
		addToRelateTablesView.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		
		
		//从表关系页移除
		 removeFromRelateTablesView = new JButton("");
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
		
		init();
	}
	
	
	
	
	public void init(){
		
	//选择到表关系
	addToRelateTablesView.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dbTableRelativePanel.setDBTables(dbTablesPanel.tables);
			
			Sql sql=new Sql();
			sql.createTableSql(dbTablesPanel.tables);
			
			dbTablesPanel.cleanSelectTables();
		}
	});
	
	
	//从表关系页移除
			
			removeFromRelateTablesView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dbTableRelativePanel.cleanDBTables();
				}
			});
	
	//表关系查询
	queryRelateButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Sql sql=new Sql();
			String query=sql.createQuerySql(dbTableRelativePanel.tables);
			sqlResultEditText.setText(query);
			
		}
	});
	
	//删除
	 deleteRelateButton = new JButton("删除");
	deleteRelateButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	
	
	 updateRelateButton = new JButton("更新");
	updateRelateButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	
	
	
	 addRelateButton = new JButton("新增");
	addRelateButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	
	
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
