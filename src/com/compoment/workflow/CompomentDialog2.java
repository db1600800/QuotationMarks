package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class CompomentDialog2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField cnNameEdit;
	private JTextField enNameEdit;
	private JTextField colorEdit;
	private JTextField bgColorEdit;
	private JTextField picNameEdit;
	private JTextField textSizeEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CompomentDialog2 dialog = new CompomentDialog2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CompomentDialog2() {
		setBounds(100, 100, 800	, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel baseTitleTextView = new JLabel("基本组件");
		
		JList baseListListView = new JList();
		
		JScrollPane basePicScrollPane = new JScrollPane();
		
		JLabel label = new JLabel("接口");
		
		JList list = new JList();
		
		JLabel label_1 = new JLabel("字段");
		
		JList list_2 = new JList();
		
		JLabel label_2 = new JLabel("事件");
		
		JList list_3 = new JList();
		
		JList list_4 = new JList();
		
		JList list_1 = new JList();
		
		cnNameEdit = new JTextField();
		cnNameEdit.setText("中文名");
		cnNameEdit.setColumns(10);
		
		enNameEdit = new JTextField();
		enNameEdit.setText("英文名");
		enNameEdit.setColumns(10);
		
		colorEdit = new JTextField();
		colorEdit.setText("边框色");
		colorEdit.setColumns(10);
		
		bgColorEdit = new JTextField();
		bgColorEdit.setText("背景色");
		bgColorEdit.setColumns(10);
		
		JButton gaveBgColor = new JButton("->");
		
		JCheckBox circularCheckBox = new JCheckBox("是否圆角");
		
		picNameEdit = new JTextField();
		picNameEdit.setText("图片名");
		picNameEdit.setColumns(10);
		
		textSizeEdit = new JTextField();
		textSizeEdit.setText("字体大小");
		textSizeEdit.setColumns(10);
		
		JCheckBox imgCacheCheckBox = new JCheckBox("是否二级缓存");
		
		JCheckBox setPublicCheckBox = new JCheckBox("是否公共组件");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(146)
							.addComponent(circularCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(imgCacheCheckBox))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(baseListListView, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(baseTitleTextView))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(basePicScrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(cnNameEdit, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(enNameEdit, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(colorEdit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(gaveBgColor, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(bgColorEdit, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(picNameEdit, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(36)
									.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
									.addGap(224)
									.addComponent(list_4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(13)
									.addComponent(textSizeEdit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(30)
									.addComponent(label_2))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
							.addGap(96))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(28)
							.addComponent(setPublicCheckBox)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(baseTitleTextView)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(list_4))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(list_2, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
								.addComponent(baseListListView, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
								.addComponent(basePicScrollPane, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))))
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cnNameEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(enNameEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(colorEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gaveBgColor)
						.addComponent(bgColorEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(picNameEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSizeEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(circularCheckBox)
						.addComponent(imgCacheCheckBox)
						.addComponent(setPublicCheckBox))
					.addGap(112))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ok = new JButton("OK");
				ok.setActionCommand("OK");
				buttonPane.add(ok);
				getRootPane().setDefaultButton(ok);
			}
			{
				JButton cancel = new JButton("Cancel");
				cancel.setActionCommand("Cancel");
				buttonPane.add(cancel);
			}
		}
	}
}
