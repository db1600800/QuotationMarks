package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ProjectFrame2 extends JFrame {

	private JPanel contentPane;
	private JTextField androidPathValueEditText;
	private JTextField iphonePathValueEditText;
	private JTextField swingPathValueEditText;
	private JTextField webPathValueEditText;
	JList picListListView;
	JCheckBox androidCheckBox;
	
	 ArrayList listDate = new ArrayList();
	
	  String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectFrame2 frame = new ProjectFrame2();
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
	public ProjectFrame2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("项目路径");
		
		 androidCheckBox = new JCheckBox("Android");
	
		
		
		androidPathValueEditText = new JTextField();
		androidPathValueEditText.setColumns(10);
		
		iphonePathValueEditText = new JTextField();
		iphonePathValueEditText.setColumns(10);
		
		JCheckBox iphoneCheckBox = new JCheckBox("Iphone");
		
		JCheckBox swingCheckBox = new JCheckBox("Swing");
		
		JCheckBox webCheckBox = new JCheckBox("Web");
		
		swingPathValueEditText = new JTextField();
		swingPathValueEditText.setColumns(10);
		
		webPathValueEditText = new JTextField();
		webPathValueEditText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("原型图片");
		
		 picListListView = new JList();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel)
								.addGap(149))
							.addComponent(swingPathValueEditText, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(swingCheckBox))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(webPathValueEditText, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(iphoneCheckBox)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(webCheckBox)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(androidPathValueEditText, Alignment.LEADING)
									.addComponent(iphonePathValueEditText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(androidCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(108)
							.addComponent(lblNewLabel_5))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(97)
							.addComponent(picListListView, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(androidCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(androidPathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(iphoneCheckBox)
							.addGap(5)
							.addComponent(iphonePathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(swingCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(swingPathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(webCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(webPathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(picListListView, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
					.addGap(65))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
		
		init();
	
	}
	
	
	public void init()
	{
		
		
		 androidCheckBox.addItemListener(new ItemListener() {
			 	public void itemStateChanged(ItemEvent e) {
			 	
			
				if (KeyValue.readCache("compomentProjectAddress") == null
						|| KeyValue.readCache("compomentProjectAddress").equals("")) {
					String inputValue = JOptionPane.showInputDialog("请输入(mobile-android)Project路径");
					KeyValue.writeCache("compomentProjectAddress", inputValue);
				}else
				{
					
					String projectPath=KeyValue.readCache("compomentProjectAddress");
					if(FileUtil.isDirectory(projectPath+"/src/com/compoment"))
					{
						
					}else
					{
						String inputValue = JOptionPane.showInputDialog("请输入(mobile-android)Project路径");
						KeyValue.writeCache("compomentProjectAddress", inputValue);
					}
				}
			}
		});
		
		//android
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
				searchPics(KeyValue.readCache("picPath"));
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		
		
		//Iphone
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
			
				searchPics(KeyValue.readCache("picPath"));
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		
		
		//Swing
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
			
				searchPics(KeyValue.readCache("picPath"));
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		
		
		
		
		//piclist
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
