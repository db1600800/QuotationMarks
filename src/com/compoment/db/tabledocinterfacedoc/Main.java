package com.compoment.db.tabledocinterfacedoc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.compoment.addfunction.webmanage.StructAction;
import com.compoment.addfunction.webmanage.StructActionForm;
import com.compoment.addfunction.webmanage.AddJsp;
import com.compoment.addfunction.webmanage.QueryJsp;
import com.compoment.workflow.InterfaceDocDialog;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	DBTablesPanel dbTablesPanel;
	DBTableRelativePanel dbTableRelativePanel;
	private JTextField sqlResultEditText;

	JButton addToRelateTablesView;
	JButton removeFromRelateTablesView;
	JButton queryRelateButton;
	JButton deleteRelateButton;
	JButton updateRelateButton;
	JButton addRelateButton;

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
		setBounds(100, 100, 1200, 800);
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
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JLabel lblNewLabel = new JLabel("数据表");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label = new JLabel("表关联(左键红色表示查询结果，右键绿色表示查询条件)");
		label.setFont(new Font("宋体", Font.PLAIN, 14));

		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(577)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 586,
										Short.MAX_VALUE)
								.addComponent(label, Alignment.LEADING).addGroup(Alignment.LEADING,
										gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblNewLabel)
														.addPreferredGap(ComponentPlacement.RELATED, 368,
																Short.MAX_VALUE)
														.addComponent(getDBTableBtn).addGap(81))
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586,
														Short.MAX_VALUE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(scrollPane_1, Alignment.LEADING, 0, 0,
																Short.MAX_VALUE)
														.addComponent(panel, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))))
						.addGap(27)));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(getDBTableBtn).addComponent(lblNewLabel))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 265,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(label)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 235,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 77,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap(90, Short.MAX_VALUE)));

		queryRelateButton = new JButton("查询");

		deleteRelateButton = new JButton("删除");

		updateRelateButton = new JButton("更新");

		addRelateButton = new JButton("新增");

		sqlResultEditText = new JTextField();
		sqlResultEditText.setColumns(10);

		JButton button = new JButton("连线回退");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = 0;

				if (dbTableRelativePanel.latestRelateColumnBean != null
						&& dbTableRelativePanel.latestRelateColumnBean.size() != 0) {
					int count = dbTableRelativePanel.latestRelateColumnBean.size();

					if (count < 1) {
						return;
					} else {
						index = count - 1;
					}
				} else {
					return;
				}

				TableColumnBean latestColumnBean = dbTableRelativePanel.latestRelateColumnBean.get(index);

				if (dbTableRelativePanel.tables != null && dbTableRelativePanel.tables.size() > 0) {
					for (TableBean table : dbTableRelativePanel.tables) {

						for (TableColumnBean column : table.columns) {

							if (latestColumnBean.columnCnName.equals(column.columnCnName)) {
								column.relateColumnBeans.clear();
								column.relateColumnBeans = new ArrayList();
								dbTableRelativePanel.latestRelateColumnBean.remove(index);

								dbTableRelativePanel.repaint();
								return;
							}

						}

					}
				}

			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(
						gl_panel_1
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(
										gl_panel_1.createSequentialGroup()
												.addGroup(
														gl_panel_1
																.createParallelGroup(
																		Alignment.LEADING)
																.addGroup(gl_panel_1.createSequentialGroup()
																		.addComponent(queryRelateButton).addGap(18)
																		.addComponent(deleteRelateButton).addGap(18)
																		.addComponent(updateRelateButton).addGap(18)
																		.addComponent(addRelateButton)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(button))
																.addGroup(gl_panel_1.createSequentialGroup().addGap(6)
																		.addComponent(sqlResultEditText,
																				GroupLayout.DEFAULT_SIZE, 566,
																				Short.MAX_VALUE)))
												.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(queryRelateButton).addComponent(deleteRelateButton)
								.addComponent(updateRelateButton).addComponent(addRelateButton).addComponent(button))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(sqlResultEditText,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		dbTableRelativePanel = new DBTableRelativePanel();
		dbTableRelativePanel.setBackground(Color.LIGHT_GRAY);
		dbTableRelativePanel.setPreferredSize(new Dimension(1000, 1000));
		scrollPane_1.setViewportView(dbTableRelativePanel);

		// 选择到表关系页
		addToRelateTablesView = new JButton("");
		addToRelateTablesView
				.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));

		// 从表关系页移除
		removeFromRelateTablesView = new JButton("");
		removeFromRelateTablesView
				.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(116).addComponent(addToRelateTablesView)
						.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
						.addComponent(removeFromRelateTablesView).addGap(136)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel
						.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(addToRelateTablesView).addComponent(removeFromRelateTablesView))
						.addContainerGap(11, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		dbTablesPanel = new DBTablesPanel();
		dbTablesPanel.setPreferredSize(new Dimension(1000, 1000));
		dbTablesPanel.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(dbTablesPanel);
		contentPane.setLayout(gl_contentPane);

		init();
	}

	public void init() {

		// 选择到表关系
		addToRelateTablesView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbTableRelativePanel.setDBTables(dbTablesPanel.tables);

				Sql sql = new Sql();
				sql.createTableSql(dbTablesPanel.tables);

				dbTablesPanel.cleanSelectTables();
			}
		});

		// 从表关系页移除

		removeFromRelateTablesView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbTableRelativePanel.cleanDBTables();
			}
		});

		// 表关系查询
		queryRelateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sql sql = new Sql();
				String query = sql.createQuerySql(dbTableRelativePanel.tables);
				sqlResultEditText.setText(query);

			}
		});

		// 删除
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

	public void getDBTables() {
		InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(false, true);
		projectDocPanel.setModal(true);
		projectDocPanel.setVisible(true);

		if (projectDocPanel.interfaceBeans != null) {
			// 接口列表
			dbTablesPanel.setDBTables(projectDocPanel.interfaceBeans);

		}
	}
}
