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
	
	
	String compomentType;
	ArrayList listDate = null;
	JFrame frame;
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
		setBounds(100, 100, 1000	, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel baseTitleTextView = new JLabel("基本组件");
		
		 baseListListView = new JList();
		
		 basePicScrollPane = new JScrollPane();
		
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
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(146)
									.addComponent(circularCheckBox)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(imgCacheCheckBox))
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
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
													.addComponent(bgColorEdit, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(picNameEdit, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
													.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(textSizeEdit, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
														.addComponent(list_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
												.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
													.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(interfaceBtn)
														.addComponent(list, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
													.addGap(23)))
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
													.addGap(224)
													.addComponent(list_4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(label_1)
														.addGroup(gl_contentPanel.createSequentialGroup()
															.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))))
											.addGap(137))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(449)
											.addComponent(label_2)
											.addGap(213))))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(28)
									.addComponent(setPublicCheckBox))))
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
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(interfaceBtn))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGap(12)
					.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(basePicScrollPane, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addComponent(list_4)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
							.addComponent(list_2, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
						.addComponent(baseListListView, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
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

		
		 interfaceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InterfaceDoc projectDocPanel=new InterfaceDoc();
				projectDocPanel.setModal(true);
				projectDocPanel.setVisible(true);
				
				if(projectDocPanel.selects==null)
				{
					JOptionPane.showMessageDialog(null, "没有选择接口,请重试", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
			}
		});
	     
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
				bean.x = x+(int)xyzMap.get("xoffset");
				bean.y = y+(int)xyzMap.get("yoffset");
				bean.w = w-(int)xyzMap.get("woffset");
				bean.h = h-(int)xyzMap.get("hoffset");
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
		bgColorEdit.setText("背景颜色             ");

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
		//circularCheckBox = new JCheckBox("是否圆角", false);
		//imgCacheCheckBox = new JCheckBox("是否图片二级缓存", false);
		//setPublicCheckBox = new JCheckBox("设为公共组件", false);

//		JPanel buttonpanel = buttonPanel;
//		buttonpanel.add(cnNameEdit);
//		buttonpanel.add(enNameEdit);
//		buttonpanel.add(ok);
//		buttonpanel.add(cancel);
//		buttonpanel.add(colorEdit);
//		buttonpanel.add(gaveBgColor);
//		buttonpanel.add(bgColorEdit);
//		buttonpanel.add(picNameEdit);
//		buttonpanel.add(textSizeEdit);
//		buttonpanel.add(circularCheckBox);
//		buttonpanel.add(imgCacheCheckBox);
//		buttonpanel.add(setPublicCheckBox);
		
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
				} else if (compomentType.contains("ListView")) {
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
				}
				
				
				else if (compomentType.contains("CheckBox")
						|| compomentType.contains("TextView")) {
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
				} else if (compomentType.equals("View")) {
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
			
			System.out.println("j:"+j+" i:"+i);
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
        	System.out.println(rgbString16(entry.getKey())+"   "+entry.getKey());
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
}
