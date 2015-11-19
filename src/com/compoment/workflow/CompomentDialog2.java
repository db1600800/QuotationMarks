package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.compoment.cut.ColorPanel;
import com.compoment.cut.CompomentBean;
import com.compoment.cut.CompomentDialog.CompomentDialogCallBack;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.*;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.InterfaceBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.Group;
import javax.swing.JComboBox;

public class CompomentDialog2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField cnNameEdit;
	private JTextField enNameEdit;
	private JTextField colorEdit;
	private JTextField bgColorEdit;
	private JTextField picNameEdit;
	private JTextField textSizeEdit;
	JButton ok;
	JCheckBox imgCacheCheckBox;
	JCheckBox setPublicCheckBox;
	JCheckBox circularCheckBox;
	JButton cancel;
	JList baseListListView;
	JButton gaveBgColor;
	JScrollPane basePicScrollPane;
	JButton interfaceBtn;
	JList interfaceList;
	JList interfaceColumnList;
	JList actionList;
	JComboBox jumpToViewComboBox;
	JCheckBox isRunTimeHeight;
	String compomentType;
	ArrayList listDate = null;
	JFrame frame;
	
	List<InterfaceBean> tempInterfaceBeans;
	String interfaceid;
	String interfaceColumnEnName;
	String actionString;
	String actionDetailString;
	String jumpToWhichPage;
	private JTextField actionDetail;
	
	JCheckBox runTimeAddScrollView;
	
	
	
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
		setBounds(100, 100, 1100, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel baseTitleTextView = new JLabel("基本组件");
		
		 baseListListView = new JList();
		
		 basePicScrollPane = new JScrollPane();
		
		 interfaceList = new JList();
		
		JLabel label_1 = new JLabel("字段");
		
		JLabel label_2 = new JLabel("事件");
		
		 actionList = new JList();
		
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
		
		 gaveBgColor = new JButton("->");
		
		 circularCheckBox = new JCheckBox("是否圆角");
		
		picNameEdit = new JTextField();
		picNameEdit.setText("图片名");
		picNameEdit.setColumns(10);
		
		textSizeEdit = new JTextField();
		textSizeEdit.setText("字体大小");
		textSizeEdit.setColumns(10);
		
		 imgCacheCheckBox = new JCheckBox("是否二级缓存");
		
		 setPublicCheckBox = new JCheckBox("是否公共组件");
		
		 ok = new JButton("ok");
		
		 cancel = new JButton("cancel");
		
		 interfaceBtn = new JButton("接口");
		 interfaceColumnList = new JList();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(interfaceColumnList);
		
		actionDetail = new JTextField();
		actionDetail.setColumns(10);
		
		 jumpToViewComboBox = new JComboBox();
		
		JLabel label = new JLabel("页面");
		
		 runTimeAddScrollView = new JCheckBox("是否动态加入ScrollView");
		 runTimeAddScrollView.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(runTimeAddScrollView.isSelected())
		 		{
		 			cnNameEdit.setText("中文名");
		 			enNameEdit.setText("英文名");	
		 		}
		 	}
		 });
		
		 isRunTimeHeight = new JCheckBox("是否动态高度");
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(baseListListView, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(basePicScrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
										.addComponent(baseTitleTextView))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(45)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(cnNameEdit, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(runTimeAddScrollView))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(enNameEdit, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
											.addGap(29)
											.addComponent(colorEdit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(gaveBgColor, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(circularCheckBox)
											.addGap(46)
											.addComponent(imgCacheCheckBox)))))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(bgColorEdit, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
											.addComponent(picNameEdit, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
											.addComponent(textSizeEdit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(interfaceBtn)
											.addGap(151)
											.addComponent(label_1)
											.addGap(81))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(interfaceList, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(48)
											.addComponent(label_2))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(12)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(actionList, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
												.addComponent(actionDetail)
												.addComponent(jumpToViewComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label))))
									.addGap(166))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(28)
									.addComponent(setPublicCheckBox)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(isRunTimeHeight))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(256)
							.addComponent(ok)
							.addGap(84)
							.addComponent(cancel)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(baseTitleTextView)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(interfaceBtn)
								.addComponent(label_1)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(basePicScrollPane, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(interfaceList, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(actionList, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(actionDetail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(3)
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jumpToViewComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(4)))
						.addComponent(baseListListView, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
					.addGap(18)
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
						.addComponent(imgCacheCheckBox)
						.addComponent(setPublicCheckBox)
						.addComponent(circularCheckBox)
						.addComponent(runTimeAddScrollView)
						.addComponent(isRunTimeHeight))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancel)
						.addComponent(ok))
					.addGap(65))
		);
		
	
		
		
		contentPanel.setLayout(gl_contentPanel);
	}
	
	
	public void init(final CompomentDialogCallBack implementInterfaceFrame,
			JFrame frame, final Image image, final int x, final int y,
			final int w, final int h, ArrayList listDate) {
		this.frame = frame;
		setView(implementInterfaceFrame, frame, image, x, y, w, h, listDate);
		
	}
	
	
	public void setView(final CompomentDialogCallBack implementInterfaceFrame,
			JFrame frame, final Image image, final int x, final int y,
			final int w, final int h, ArrayList listDate) {

		
		List picnames=searchPics(KeyValue.readCache("picPath"));
		
		for(int i=0;i<picnames.size();i++)
		{
		jumpToViewComboBox.addItem(picnames.get(i));
		}
		
		jumpToViewComboBox.addActionListener( new ActionListener(){   
	            public void actionPerformed( ActionEvent e){   
	             
	                jumpToWhichPage=jumpToViewComboBox.getSelectedItem().toString();
	            }   
	        }); 
		
		List actions=new ArrayList();
		actions.add("跳到");
		actions.add("跳回到上个");
		actions.add("跳回到上几个");
		actions.add("单选");
		actions.add("发请求");
		actions.add("弹出");
		
		
		actionList.setListData(actions.toArray());
		actionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		actionList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub
				
				if(actionList.getSelectedValue()==null)
					return;
				
				String value = actionList.getSelectedValue().toString();
			
				actionString=value;
			}});
		
		
		
		//接口按钮
		 interfaceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InterfaceDoc projectDocPanel=new InterfaceDoc();
				projectDocPanel.setModal(true);
				projectDocPanel.setVisible(true);
				
				if(projectDocPanel.interfaceBeans!=null)
				{
					
					tempInterfaceBeans=projectDocPanel.interfaceBeans;
					//接口列表
					interfaceList.setListData(projectDocPanel.listDate.toArray());
					interfaceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					interfaceList.addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent even) {
							// TODO Auto-generated method stub
							
							if(interfaceList.getSelectedValue()==null)
								return;
							
							String value = interfaceList.getSelectedValue().toString();
							String id= value.split(":")[0];

							interfaceid=id;
							
							for(InterfaceBean ibean:tempInterfaceBeans)
							{
								
								
								
								if(ibean.id.equals(id))
								{
									List<Group> tempGroups=null;
									List<String> tempcolumns=new ArrayList();
									//接口字段
									 interfaceColumnList.setListData(tempcolumns.toArray());
									
									 if (compomentType.contains("ImageView")) {
										
										 tempGroups=ibean.respondGroups;
										}  else if (
												 compomentType.contains("EditText")) {
											 tempGroups=ibean.requestGroups;
										}
									
										else if (compomentType.contains("CheckBox"))
										{
											 tempGroups=ibean.requestGroups;
										}
										else if ( compomentType.contains("TextView")) {
											 tempGroups=ibean.respondGroups;
										}
									 
									 
									 for(Group group:tempGroups)
									 {
										 
										 for(Row row:group.rows)
										 {
											String column= row.enName+":"+row.cnName;
											tempcolumns.add(column);
										 }
										 
									 }
									
									 interfaceColumnList.setListData(tempcolumns.toArray());
								}
							}
							
							
							
							
			               CompomentDialog2.this.setVisible(true);

						}

					});
					
					
					return ;
				}
			}
		});
		 
		 
		 
		 interfaceColumnList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		 interfaceColumnList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent even) {
					// TODO Auto-generated method stub
					
					if(interfaceColumnList.getSelectedValue()==null)
						return;
					
					String value = interfaceColumnList.getSelectedValue().toString();
					String enname= value.split(":")[0];
					interfaceColumnEnName=enname;
					
				}});

	     
		ColorPanel basePicPanel = new ColorPanel(frame, image, this);
	
		
		basePicScrollPane.setViewportView(basePicPanel);
	     
	     final Map xyzMap=getNearXYZ(image,x,y,w,h);
		
		
		this.listDate = listDate;
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(compomentType.contains(".xml"))
				{
					List<CompomentBean>  beans=SerializeToFile.deSerializeFromXml(compomentType);//filepath
					for(CompomentBean bean:beans)
					{
					implementInterfaceFrame.compomentDialogCallBack(bean);
					}
				  CompomentDialog2.this.setVisible(false);
					return;
				}
				
				if (compomentType == null) {
					JOptionPane.showMessageDialog(null, "请选择类型", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (cnNameEdit.getText().equals("中文名")) {
					JOptionPane.showMessageDialog(null, "请输入中文名", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (enNameEdit.getText().equals("英文名")) {
					JOptionPane.showMessageDialog(null, "请输入英文名", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (colorEdit.getText().contains("颜色")) {
					JOptionPane.showMessageDialog(null, "请选择颜色", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (compomentType.equals("ImageView")
						&& picNameEdit.getText().contains("图片名")) {
					JOptionPane.showMessageDialog(null, "请输入图片名", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				CompomentBean bean = new CompomentBean();
				bean.actionString=actionString;
				bean.actionDetailString=actionDetail.getText();
				bean.interfaceId=interfaceid;
				bean.interfaceColumnEnName=interfaceColumnEnName;
				bean.jumpToWhichPage=jumpToWhichPage;
				
				bean.cnname = cnNameEdit.getText().trim().replace(" ", "");
				bean.enname = (enNameEdit.getText().trim() + compomentType)
						.replace(" ", "");
				bean.rgb16 = "#" + colorEdit.getText().trim();
				bean.bgRgb16 = "#" + bgColorEdit.getText().trim();
				
				bean.rgb16ios = "#" + colorEdit.getText().trim();
				bean.bgRgb16ios = "#" + bgColorEdit.getText().trim();
				bean.picName = picNameEdit.getText().trim().toLowerCase()
						.replace(" ", "");
				bean.textSize = textSizeEdit.getText().trim();
				
				if(!compomentType.contains("Layout"))
				{
				bean.x = x+(Integer)xyzMap.get("xoffset");
				bean.y = y+(Integer)xyzMap.get("yoffset");
				bean.w = w-(Integer)xyzMap.get("woffset");
				bean.h = h-(Integer)xyzMap.get("hoffset");
				}else
				{
					bean.x = x;
					bean.y = y;
					bean.w = w;
					bean.h = h;	
					
				}
				bean.type = compomentType;
				bean.time = System.currentTimeMillis();
				if (bean.type.equals("ImageView")||bean.type.equals("Button")) {
					if(!bean.picName.equals("图片名"))
					savePic(image, bean.picName);
					if (imgCacheCheckBox.isSelected()) {
						bean.isImgCache = true;
					}

				}
				if(setPublicCheckBox.isSelected())
				{   
					savePic(image, bean.enname);
					bean.isPublicCompoment=true;
				}
				
				if(runTimeAddScrollView.isSelected())
				{
					bean.isRunTimeAddScrollView=true;	
				}
				
				if(isRunTimeHeight.isSelected())
				{
					
					bean.isRunTimeHeightTextview=true;
				}
				
				circula(bean);
				implementInterfaceFrame.compomentDialogCallBack(bean);
				CompomentDialog2.this.setVisible(false);
				// SureChangeTestFrame.panel.setBackground(Color.YELLOW);//
				// 这里panel.setBackground(Color.YELLOW);改成SureChangeTestFrame.panel.setBackground(Color.YELLOW);
			}
		});

		//JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CompomentDialog2.this.setVisible(false);
			}

		});

		//colorEdit = new JTextField();
		
		colorEdit.setText((String)xyzMap.get("textColor"));
		
		gaveBgColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bgColorEdit.setText(colorEdit.getText());
			}

		});

		//bgColorEdit = new JTextField();
		bgColorEdit.setText("背景颜色");

		//picNameEdit = new JTextField();
		picNameEdit.setText("图片名");
		picNameEdit.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (picNameEdit.getText().equals("图片名")) {
					picNameEdit.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		//cnNameEdit = new JTextField();
		cnNameEdit.setText("中文名");
		cnNameEdit.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(final FocusEvent arg0) {
				if (cnNameEdit.getText().equals("中文名")) {
					cnNameEdit.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (cnNameEdit.getText().equals("")) {
					cnNameEdit.setText("中文名");
				}
			}
		});

		//enNameEdit = new JTextField();
		enNameEdit.setText("英文名");
		enNameEdit.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(final FocusEvent arg0) {
				if (enNameEdit.getText().equals("英文名")) {
					enNameEdit.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (enNameEdit.getText().equals("")) {
					enNameEdit.setText("英文名");
				}
			}
		});
		
		gaveBgColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bgColorEdit.setText(colorEdit.getText());
			}

		});

		//textSizeEdit = new JTextField();
		textSizeEdit.setText("16");

		
		final JList list = baseListListView;

		list.setListData(this.listDate.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub
				
				
				String value = list.getSelectedValue().toString();
				compomentType = value;

				if (compomentType.contains("Layout")) {

					long id = System.currentTimeMillis();
					cnNameEdit.setText("bg" + id);
					enNameEdit.setText("bg" + id);

					bgColorEdit.setText(colorEdit.getText());

					colorEdit.setText("边框颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(true);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(true);
					runTimeAddScrollView.setVisible(true);
					isRunTimeHeight.setVisible(false);
					
				} else if (compomentType.contains("ImageView")) {
					colorEdit.setText("颜色");
					colorEdit.setVisible(false);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(true);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(true);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				} else if (compomentType.contains("ListView")||compomentType.contains("ScrollView")) {
					colorEdit.setText("行间距颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				} else if (
						 compomentType.contains("EditText")) {
					colorEdit.setText("文字颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				}
				else if (compomentType.contains("Button")
						) {
					colorEdit.setText("文字颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(true);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				}
				
				
				else if (compomentType.contains("CheckBox")
						) {
					colorEdit.setText("文字颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				} 
				else if ( compomentType.contains("TextView")) {
					colorEdit.setText("文字颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(true);
				}
				
				else if (compomentType.equals("Line")) {
					colorEdit.setText("文字颜色");
					colorEdit.setVisible(false);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				} else {
					colorEdit.setText("颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(true);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
				}

               CompomentDialog2.this.setVisible(true);

			}

		});
		
		
		
		
		
		

	}

	public void setRgb(String rgb16) {
		colorEdit.setText(rgb16);
	}

	public void setBgRgb(String bgrgb16) {
		bgColorEdit.setText(bgrgb16);
	}

	public void savePic(Image iamge, String picName) {
		int w = iamge.getWidth(frame);
		int h = iamge.getHeight(frame);

		// 首先创建一个BufferedImage变量，因为ImageIO写图片用到了BufferedImage变量。
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

		// 再创建一个Graphics变量，用来画出来要保持的图片，及上面传递过来的Image变量
		Graphics g = bi.getGraphics();
		try {
			g.drawImage(iamge, 0, 0, null);

			// 将BufferedImage变量写入文件中。
			if(!setPublicCheckBox.isSelected())
			{
			ImageIO.write(bi, "png", new File(KeyValue.readCache("picPath")
					+ "/drawable/" + picName + ".png"));
			
			//ImageIO.write(bi, "png", new File(KeyValue.readCache("projectPath")
					//+ "/res/drawable-hdpi/" + picName + ".png"));
			}else
			{
				FileUtil.makeDir(new File(KeyValue.readCache("picPath")
						+ "/publiccompoment/"));
				ImageIO.write(bi, "png", new File(KeyValue.readCache("picPath")
						+ "/publiccompoment/" + picName + ".png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成圆角drawable文件
	 * */
	public void circula(CompomentBean bean) {
		String m = "";
		if (circularCheckBox.isSelected()) {
			bean.isFilletedCorner=true;
			if (bean.bgRgb16.equals(bean.rgb16)) {
				// 填充 没描边
				m += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
				m += "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";
				m += " <solid android:color=\"" + bean.bgRgb16 + "\" /> \n";
				m += " <corners android:radius=\"10dp\" />\n";
				m += "</shape>\n";
			} else { // 填充 描边
				m += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
				m += "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";
				m += " <solid android:color=\"" + bean.bgRgb16 + "\" /> \n";
				m += "<stroke android:width=\"0.5dp\" android:color=\""
						+ bean.rgb16 + "\"/>\n";
				m += "<corners android:radius=\"10dp\" />\n";
				m += "</shape>\n";

			}
			stringToFile(m, KeyValue.readCache("picPath") + "/drawable/"
					+ "corner_" + bean.enname.toLowerCase() + ".xml");
			bean.bgRgb16 = "@drawable/corner_" + bean.enname.toLowerCase();

		}

	}

	public static File stringToFile(String name, String path) {
		byte[] b = name.getBytes();
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(path);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}
	
	
	

	public interface CompomentDialogCallBack {
		public void compomentDialogCallBack(CompomentBean bean);
	}
	
	
	
	public Map getNearXYZ(Image image,int x, int y, int w, int h) {
		
		BufferedImage bufImg = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);   
	     Graphics g = bufImg .createGraphics();   
        g.drawImage(image, 0, 0, null);   
        g.dispose(); 
		
		int rgbs[] = new int[w * h];
		rgbs =  bufImg.getRGB(0, 0, w, h, rgbs, 0, w);
		
	
		
		Map map=buildMap(rgbs);
		
		
		
		int max1keyvalue[]=getMax( map);
		
		
		
		int xoffset=0;//偏移量
		int yoffset=0;
		int woffset=0;
		int hoffset=0;
		
		//x
		boolean xok=false;
		for(int j=0;j<w;j++)
		{
			
		for(int i=0;i<h;i++)
		{
			if(xok)
			{
				break;
			}
			if(bufImg.getRGB(j, i)!=max1keyvalue[0])
			{
				xoffset=j;
				xok=true;
				break;
			}
		}
		}
		
		
		//w
		boolean wok=false;
		for(int j=w-1;j>1;j--)
		{
			if(wok)
			{
				break;
			}
		for(int i=0;i<h;i++)
		{
			
			if(bufImg.getRGB(j, i)!=max1keyvalue[0])
			{
				woffset=(w-1)-j;
				wok=true;
				break;
			}
		}
		}
		
		
		//y
		boolean yok=false;
		for(int j=0;j<h-1;j++)
		{
		for(int i=0;i<w-1;i++)
		{
			if(yok)
			{
				break;
			}
			
			
			if(bufImg.getRGB(i, j)!=max1keyvalue[0])
			{
				yoffset=j;
				yok=true;
				break;
			}
		}
		}
		
		
		//h
		boolean hok=false;
		for(int j=h-1;j>1;j--)
		{
			
			if(hok)
			{
				break;
			}
			
		for(int i=0;i<w-1;i++)
		{
			
			if(bufImg.getRGB(i, j)!=max1keyvalue[0])
			{
				hoffset=(h-1)-j;
				hok=true;
				break;
			}
		}
		}
		
		
		HashMap temp=new HashMap();
		temp.put("xoffset", xoffset);
		temp.put("yoffset", yoffset);
		temp.put("woffset", woffset);
		temp.put("hoffset", hoffset);
		
	
		
		
		temp.put("textColor",rgbString16( maxOne(rgbs)));
		
		return temp;
		
	}
	
	public static String rgbString16(int pixel)
	{
		
		int rgb[]=new int[3];
		rgb[0] = (pixel & 0xff0000) >> 16;
		rgb[1] = (pixel & 0xff00) >> 8;
		rgb[2] = (pixel & 0xff);
		
		 String color16=ColorPanel.getColorInHexFromRGB(rgb[0], rgb[1], rgb[2]);
		return color16;
		
	}
	
	  public static int maxOne(int[] strArr) { 
	  
		  int min=0;
		  for(int i :strArr)
		  {
			  
			  if(i<min)
			  {
				  
				  min=i;
			  }
		  }
		  
		  return min;
		  
	  }
	
	
	/*************建立哈希映射***************/  
    public static Map<Integer, Integer> buildMap(int[] strArr) {  
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();  
        for (int str : strArr) {  
            if (map.containsKey(str)) {  
                map.put(str, map.get(str) + 1);  
            } else {  
                map.put(str, 0);  
            }  
        }  
        return map;  
    }  
  
    /*************得到最大出现次数的映射***************/  
    public static int[] getMax(Map<Integer, Integer> map) {  
        int max = 0;  
        Integer result = null;  
        for (Entry<Integer, Integer> entry : map.entrySet()) {  
        	//System.out.println(rgbString16(entry.getKey())+"   "+entry.getKey());
            if (entry.getValue() > max) {  
                result = entry.getKey();  
                if(result !=null) max = entry.getValue();  
            }  
        }  
        int keyvalue[]=new int[2];
        keyvalue[0]=result;
        keyvalue[1]=map.get(result);
       
        return  keyvalue;  
    }  
    
    
    
public ArrayList searchPics(String root)
	
	{
	
	ArrayList temp=new ArrayList();
		File file = new File(root);
		
		File[] files = file.listFiles(new FileFilter(){

			public boolean accept(File fl) {
				return !fl.isDirectory();
			}
			
		});
		
	
		
		for(File f:files)
		{
			String picname=f.getName();
			if(picname.indexOf(".")!=-1)
			{
				int p=picname.indexOf(".");
				
				temp.add(picname.substring(0, p));
			}else
				
			{
				temp.add(f.getName());
			}
			
		 
		}

		return temp;
	}
}
