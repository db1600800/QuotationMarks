package com.compoment.cut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

/**
 * 填写截取图片的更多属性（中文名，英文名，色值，控件类型，是否有圆角），生成控件对象
 * */
public class CompomentDialog {

	/** 基本组件 */
	public JLabel baseTitleTextView;
	/** 基本组件列表 */
	public JList baseListListView;
	/** 基本组件图片 */
	public JPanel basePicPanel;
	/** 公共组件 */
	public JLabel publicTitleTextView;
	/** 公共组件列表 */
	public JList publicListListView;
	/** 公共组件图片 */
	public JLabel publicPicTextView;
	/** buttonPanel */
	public JPanel buttonPanel;

	JTextField colorEdit;
	JTextField bgColorEdit;
	JTextField textSizeEdit;
	JTextField cnNameEdit;
	JTextField enNameEdit;
	JTextField picNameEdit;
	JCheckBox circularCheckBox;
	JCheckBox imgCacheCheckBox;
	JCheckBox setPublicCheckBox;
	String compomentType;
	ArrayList listDate = null;

	JFrame frame;
	JDialog jdialog;

	/**
	 * 填写截取图片的更多属性（中文名，英文名，色值，控件类型，是否有圆角，是否图片二级缓存），生成控件对象
	 * */
	public CompomentDialog(
			final CompomentDialogCallBack implementInterfaceFrame,
			JFrame frame, final Image image, final int x, final int y,
			final int w, final int h, ArrayList listDate) {
		this.frame = frame;
		jdialog = new JDialog(frame, "组件设置", true);
		jdialog.setSize(1000, 600);
		JPanel panel = create(implementInterfaceFrame, frame, image, x, y, w,
				h, listDate);
		jdialog.add(panel);
		jdialog.setLocationRelativeTo(null);
		jdialog.setVisible(true);
	}

	public JPanel create(final CompomentDialogCallBack implementInterfaceFrame,
			JFrame frame, final Image image, final int x, final int y,
			final int w, final int h, ArrayList listDate) {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// 垂直
		/**
		 * bg1427167246312LinearLayout bg1427167276109LinearLayout
		 * bg1427167356515LinearLayout bg1427167380796LinearLayout
		 * bg1427167436390LinearLayout
		 */
		GroupLayout.SequentialGroup bg1427167232671LinearLayout = layout
				.createSequentialGroup();
		/** baseTitleTextView */
		GroupLayout.ParallelGroup bg1427167246312LinearLayout = layout
				.createParallelGroup();
		/** 基本组件 */
		baseTitleTextView = new JLabel("基本组件");
		bg1427167246312LinearLayout.addComponent(baseTitleTextView);

		bg1427167232671LinearLayout.addGroup(bg1427167246312LinearLayout);

		/** baseListListView basePicTextView */
		GroupLayout.ParallelGroup bg1427167276109LinearLayout = layout
				.createParallelGroup();
		/** 基本组件列表 */
		baseListListView = new JList();
		JScrollPane baseListListViewScrollPane = new JScrollPane(
				baseListListView);

		baseListListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		bg1427167276109LinearLayout.addComponent(baseListListViewScrollPane);

		/** 基本组件图片 */
		basePicPanel = new ColorPanel(frame, image, this);
		JScrollPane basePicScrollPane = new JScrollPane(
				basePicPanel);
		
		
		bg1427167276109LinearLayout.addComponent(basePicScrollPane);

		bg1427167232671LinearLayout.addGroup(bg1427167276109LinearLayout);

		/** publicTitleTextView */
		GroupLayout.ParallelGroup bg1427167356515LinearLayout = layout
				.createParallelGroup();
		/** 公共组件 */
		publicTitleTextView = new JLabel("公共组件");
		bg1427167356515LinearLayout.addComponent(publicTitleTextView);

		bg1427167232671LinearLayout.addGroup(bg1427167356515LinearLayout);

		/** publicListListView publicPicTextView */
		GroupLayout.ParallelGroup bg1427167380796LinearLayout = layout
				.createParallelGroup();
		/** 公共组件列表 */
		publicListListView = new JList();
		JScrollPane publicListListViewScrollPane = new JScrollPane(
				publicListListView);
		ArrayList listDate1 = new ArrayList();
		listDate1.add("RelativeLayout");
		listDate1.add("LinearLayout");
		publicListListView
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		publicListListView.setListData(listDate1.toArray());
		publicListListView
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent even) {
						String value = publicListListView.getSelectedValue()
								.toString();
					}
				});
		bg1427167380796LinearLayout.addComponent(publicListListViewScrollPane);

		/** 公共组件图片 */
		publicPicTextView = new JLabel("公共组件图片");
		bg1427167380796LinearLayout.addComponent(publicPicTextView);

		bg1427167232671LinearLayout.addGroup(bg1427167380796LinearLayout);

		/** okButton */
		GroupLayout.ParallelGroup bg1427167436390LinearLayout = layout
				.createParallelGroup();
		/** ok */
		buttonPanel = new JPanel();

		bg1427167436390LinearLayout.addComponent(buttonPanel);

		bg1427167232671LinearLayout.addGroup(bg1427167436390LinearLayout);

		layout.setVerticalGroup(bg1427167232671LinearLayout);

		// 水平
		/** bg1427180112562 bg1427180118343 */
		GroupLayout.ParallelGroup bg1427180123234 = layout
				.createParallelGroup();
		/** bg1427180105187 bg1427180109078 */
		GroupLayout.SequentialGroup bg1427180112562 = layout
				.createSequentialGroup();
		/**
		 * baseTitleTextView baseListListView publicTitleTextView
		 * publicListListView
		 */
		GroupLayout.ParallelGroup bg1427180105187 = layout
				.createParallelGroup();
		/** 基本组件 */
		bg1427180105187.addComponent(baseTitleTextView);

		/** 基本组件列表 */
		bg1427180105187.addComponent(baseListListViewScrollPane);

		/** 公共组件 */
		bg1427180105187.addComponent(publicTitleTextView);

		/** 公共组件列表 */
		bg1427180105187.addComponent(publicListListViewScrollPane);

		bg1427180112562.addGroup(bg1427180105187);

		/** basePicTextView publicPicTextView */
		GroupLayout.ParallelGroup bg1427180109078 = layout
				.createParallelGroup();
		/** 基本组件图片 */
		bg1427180109078.addComponent(basePicScrollPane);

		/** 公共组件图片 */
		bg1427180109078.addComponent(publicPicTextView);

		bg1427180112562.addGroup(bg1427180109078);

		bg1427180123234.addGroup(bg1427180112562);

		/** okButton */
		GroupLayout.SequentialGroup bg1427180118343 = layout
				.createSequentialGroup();
		/** ok */
		bg1427180118343.addComponent(buttonPanel);

		bg1427180123234.addGroup(bg1427180118343);

		layout.setHorizontalGroup(bg1427180123234);

		setView(implementInterfaceFrame, frame, image, x, y, w, h, listDate);
		
	
		
		return panel;

	}

	public void setView(final CompomentDialogCallBack implementInterfaceFrame,
			JFrame frame, final Image image, final int x, final int y,
			final int w, final int h, ArrayList listDate) {

		this.listDate = listDate;
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

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
				bean.picName = picNameEdit.getText().trim().toLowerCase()
						.replace(" ", "");
				bean.textSize = textSizeEdit.getText().trim();
				bean.x = x;
				bean.y = y;
				bean.w = w;
				bean.h = h;
				bean.type = compomentType;
				bean.time = System.currentTimeMillis();
				if (bean.type.equals("ImageView")) {
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
				jdialog.setVisible(false);
				// SureChangeTestFrame.panel.setBackground(Color.YELLOW);//
				// 这里panel.setBackground(Color.YELLOW);改成SureChangeTestFrame.panel.setBackground(Color.YELLOW);
			}
		});

		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				jdialog.setVisible(false);
			}

		});

		colorEdit = new JTextField();
		colorEdit.setText("颜色             ");
		JButton gaveBgColor = new JButton("->");
		gaveBgColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bgColorEdit.setText(colorEdit.getText());
			}

		});

		bgColorEdit = new JTextField();
		bgColorEdit.setText("背景颜色             ");

		picNameEdit = new JTextField();
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
		cnNameEdit = new JTextField();
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

		enNameEdit = new JTextField();
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

		textSizeEdit = new JTextField();
		textSizeEdit.setText("16");
		circularCheckBox = new JCheckBox("是否圆角", false);
		imgCacheCheckBox = new JCheckBox("是否图片二级缓存", false);
		setPublicCheckBox = new JCheckBox("设为公共组件", false);

		JPanel buttonpanel = buttonPanel;
		buttonpanel.add(cnNameEdit);
		buttonpanel.add(enNameEdit);
		buttonpanel.add(ok);
		buttonpanel.add(cancel);
		buttonpanel.add(colorEdit);
		buttonpanel.add(gaveBgColor);
		buttonpanel.add(bgColorEdit);
		buttonpanel.add(picNameEdit);
		buttonpanel.add(textSizeEdit);
		buttonpanel.add(circularCheckBox);
		buttonpanel.add(imgCacheCheckBox);
		buttonpanel.add(setPublicCheckBox);
		
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
				} else if (compomentType.contains("Button")
						|| compomentType.contains("EditText")) {
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
				} else if (compomentType.contains("CheckBox")
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

				jdialog.setVisible(true);

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
			
			ImageIO.write(bi, "png", new File(KeyValue.readCache("projectPath")
					+ "/res/drawable-hdpi/" + picName + ".png"));
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
}