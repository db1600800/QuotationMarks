package com.compoment.cut;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.compoment.util.KeyValue;



/**
 * 填写截取图片的更多属性（中文名，英文名，色值，控件类型，是否有圆角），生成控件对象
 * */
public class CompomentDialog extends JDialog {
	JTextField colorEdit;
	JTextField bgColorEdit;
	JTextField textSizeEdit;
	JTextField cnNameEdit;
	JTextField enNameEdit;
	JTextField picNameEdit;
	JCheckBox circularCheckBox;
	String compomentType;
	ArrayList listDate =null;
	/**
	 * 填写截取图片的更多属性（中文名，英文名，色值，控件类型，是否有圆角），生成控件对象
	 * */
	public CompomentDialog(final CompomentDialogCallBack implementInterfaceFrame,JFrame frame, final Image image, final int x, final int y,
			final int w, final int h,ArrayList listDate) {
		super(frame, "组件设置", true);
        this.listDate=listDate;
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (compomentType == null) {
					JOptionPane.showMessageDialog(CompomentDialog.this,
							"请选择类型", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (cnNameEdit.getText().equals("中文名")) {
					JOptionPane.showMessageDialog(CompomentDialog.this,
							"请输入中文名", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (enNameEdit.getText().equals("英文名")) {
					JOptionPane.showMessageDialog(CompomentDialog.this,
							"请输入英文名", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (colorEdit.getText().contains("颜色")) {
					JOptionPane.showMessageDialog(CompomentDialog.this,
							"请选择颜色", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (compomentType.equals("ImageView") && picNameEdit.getText().contains("图片名")) {
					JOptionPane.showMessageDialog(CompomentDialog.this,
							"请输入图片名", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				CompomentBean bean = new CompomentBean();
				bean.cnname = cnNameEdit.getText().trim().replace(" ", "");
				bean.enname = (enNameEdit.getText().trim() + compomentType).replace(" ", "");
				bean.rgb16 = "#"+colorEdit.getText().trim();
				bean.bgRgb16 = "#"+bgColorEdit.getText().trim();
				bean.picName = picNameEdit.getText().trim().toLowerCase().replace(" ", "");
				bean.textSize = textSizeEdit.getText().trim();
				bean.x = x;
				bean.y = y;
				bean.w = w;
				bean.h = h;
				bean.type = compomentType;
				bean.time=System.currentTimeMillis();
                if(bean.type.equals("ImageView"))
                {
                	savePic(image,bean.picName);
                }
                circula(bean);
                implementInterfaceFrame.compomentDialogCallBack(bean);
				setVisible(false);
				// SureChangeTestFrame.panel.setBackground(Color.YELLOW);//
				// 这里panel.setBackground(Color.YELLOW);改成SureChangeTestFrame.panel.setBackground(Color.YELLOW);
			}
		});

		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
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
		picNameEdit.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(picNameEdit.getText().equals("图片名"))
				{
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
				
			}});
		cnNameEdit = new JTextField();
		cnNameEdit.setText("中文名");
		
		cnNameEdit.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(cnNameEdit.getText().equals("中文名"))
				{
					cnNameEdit.setText("");
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
				
			}});
		enNameEdit = new JTextField();
		enNameEdit.setText("英文名");
		enNameEdit.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(enNameEdit.getText().equals("英文名"))
				{
					enNameEdit.setText("");
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
				
			}});
		textSizeEdit = new JTextField();
		textSizeEdit.setText("16");
		circularCheckBox=new JCheckBox("是否圆角", false);


		JPanel buttonpanel = new JPanel();
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
		
		add(buttonpanel, BorderLayout.SOUTH);

		ColorPanel colorPane = new ColorPanel(frame, image, this);
		add(colorPane, BorderLayout.CENTER);



		final JList list = new JList();

		list.setListData(this.listDate.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub
				String value = list.getSelectedValue().toString();
				compomentType = value;
				
				if(compomentType.contains("Layout"))
				{     
					
					long id=System.currentTimeMillis();
					cnNameEdit.setText("bg"+id);
					enNameEdit.setText("bg"+id);
					
					bgColorEdit.setText(colorEdit.getText());
				}

			}

		});

		JScrollPane listScrollPane = new JScrollPane(list);

		add(listScrollPane, BorderLayout.WEST);

		setSize(1000, 500);
	}

	public void setRgb(String rgb16) {
		colorEdit.setText(rgb16);
	}

	public void savePic(Image iamge,String picName) {
		int w = iamge.getWidth(this);
		int h = iamge.getHeight(this);

		// 首先创建一个BufferedImage变量，因为ImageIO写图片用到了BufferedImage变量。
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

		// 再创建一个Graphics变量，用来画出来要保持的图片，及上面传递过来的Image变量
		Graphics g = bi.getGraphics();
		try {
			g.drawImage(iamge, 0, 0, null);

			// 将BufferedImage变量写入文件中。
			ImageIO.write(bi, "png", new File(KeyValue.readCache("picPath")+"/drawable/"+picName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成圆角drawable文件
	 * */
	public void circula(CompomentBean bean){
		String m="";
		if(circularCheckBox.isSelected())
		{
			if(bean.bgRgb16.equals(bean.rgb16))
			{
				//填充
				m+="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"; 
				m+="<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";    
				m+=" <solid android:color=\""+bean.bgRgb16+"\" /> \n";   
				m+=" <corners android:radius=\"10dp\" />\n";   
				m+="</shape>\n";    
			}else
			{   //描边
				m+="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"; 
				m+="<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";   
				m+=" <solid android:color=\""+bean.bgRgb16+"\" /> \n";   
				m+="<stroke android:width=\"0.5dp\" android:color=\""+bean.rgb16+"\"/>\n";
				m+="<corners android:radius=\"10dp\" />\n";   
				m+="</shape>\n";   
				
				
			}
			stringToFile(m,KeyValue.readCache("picPath")+"/drawable/"+"corner_"+bean.enname.toLowerCase()+".xml");
			bean.bgRgb16="@drawable/corner_"+bean.enname.toLowerCase();
			
		}
		
		

		    
		
	}
	
	
	public static File stringToFile(String name,String path) { 
	    byte[] b=name.getBytes(); 
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

	
	public interface CompomentDialogCallBack
	{
		public void compomentDialogCallBack(CompomentBean bean);
	}
}