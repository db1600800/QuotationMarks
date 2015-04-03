package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.compoment.cut.CompomentBean;
import com.compoment.cut.CompomentDialog;
import com.compoment.cut.CutCompomentsTypeImg;
import com.compoment.cut.android.AndroidLayoutXml;
import com.compoment.cut.swing.SwingLayout;
import com.compoment.ui.CreateActivityChirldView;
import com.compoment.ui.CreateActivityView;
import com.compoment.ui.CreaterAdapter;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;

public class PageCreatePanel {
	
	PageFrame frame;
	public PageCreatePanel(PageFrame frame)
	{
		this.frame=frame;
	}

	public JPanel create() {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		//垂直
		/**resetButton createButton */
		GroupLayout.SequentialGroup bg1420980499385LinearLayout = layout.createSequentialGroup();
		/**重置*/
		JButton resetButton = new JButton("用缓存重新生成");
		bg1420980499385LinearLayout.addComponent(resetButton);

		/**生成代码*/
		JButton createButton = new JButton("生成");
		bg1420980499385LinearLayout.addComponent(createButton);
		
		layout.setVerticalGroup(bg1420980499385LinearLayout);

		//水平
		/**resetButton createButton */
		GroupLayout.ParallelGroup bg1420980564924 = layout.createParallelGroup();
		/**重置*/
		bg1420980564924.addComponent(resetButton);

		/**生成代码*/
		bg1420980564924.addComponent(createButton);

		layout.setHorizontalGroup(bg1420980564924);
		
		
		
		
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SerializeToFile serializeToFile = new SerializeToFile();
				serializeToFile.serializeToXml(frame.beans);
				createPageCode();
			}

		});
		
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.beans.clear();
				if(frame.beansForSwing!=null)
				frame.beansForSwing.clear();
				
				SerializeToFile serializeToFile = new SerializeToFile();
				frame.beans=serializeToFile.deSerializeFromXml();
				createPageCode();
			}

		});

		return panel;
	}
	
	
	// 深拷贝2:序列化|反序列化方法
		public List copyBySerialize(List src) throws IOException,
				ClassNotFoundException {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(src);

			ByteArrayInputStream byteIn = new ByteArrayInputStream(
					byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			List dest = (List) in.readObject();
			return dest;
		}
		
		
		// 深拷贝1：递归方法
		public void copy(List src, List dest) {
			for (int i = 0; i < src.size(); i++) {
				Object obj = src.get(i);
				if (obj instanceof List) {
					dest.add(new ArrayList());
					copy((List) obj, (List) ((List) dest).get(i));
				} else {
					dest.add(obj);
				}
			}

		}
		
		
		public void createPageCode()
		{
			//拷贝保留原始位置数据
			try {
				frame.beansForSwing = copyBySerialize(frame.beans);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	

		
			if(frame.pageType==null)
			{
				JOptionPane.showMessageDialog(frame,
						"请选择页面类型", "", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(frame.pageType.contains("Activity-Android"))
			{
				//android页面分析生成
				AndroidLayoutXml androidLayoutXml = new AndroidLayoutXml();
				String xmlFileName=androidLayoutXml.analyseRelative(frame.pageName,frame.beans);
				
				savePublicCompoment();
				
				CreateActivityView createView = new CreateActivityView(frame.pageName);
				createView.create();
			}else if(frame.pageType.contains("Item-Android"))
			{
				
				//android页面分析生成
				AndroidLayoutXml androidLayoutXml = new AndroidLayoutXml();
				String xmlFileName=androidLayoutXml.analyseRelative(frame.pageName+"_item",frame.beans);
				
				savePublicCompoment();
				
				com.compoment.ui.CreaterAdapter createrAdapter=new CreaterAdapter(frame.pageName+"_item",frame.beans);
				createrAdapter.create();
			}else if(frame.pageType.contains("Fragment-Android"))
			{
				
			}else if(frame.pageType.contains("Layout-Android"))
			{
				//android页面分析生成
				AndroidLayoutXml androidLayoutXml = new AndroidLayoutXml();
				String xmlFileName=androidLayoutXml.analyseRelative(frame.pageName,frame.beans);
				
				savePublicCompoment();
				
				com.compoment.ui.CreateActivityChirldView createrAdapter=new CreateActivityChirldView(frame.pageName);
				createrAdapter.create();
			}else if(frame.pageType.contains("JFrame-Swing"))
			{
				//android页面分析生成
				AndroidLayoutXml androidLayoutXml = new AndroidLayoutXml();
				String xmlFileName=androidLayoutXml.analyseRelative(frame.pageName,frame.beans);
				
			
				savePublicCompoment();
				
				//Swing页面水平方向 再次取得分组信息
				JPanel swingPanel = new CutCompomentsTypeImg(frame,
						frame.beansForSwing);
				JDialog jdialog = new JDialog(frame, "swing水平方向截取", true);
				jdialog.setSize(800, 800);
				jdialog.add(swingPanel);
				jdialog.setLocation(10, 10);
				jdialog.setVisible(true);
				
				SwingLayout swingLayout = new SwingLayout();
				swingLayout.createJFrame(frame.beans,frame.beansForSwing);
				
			}else if(frame.pageType.contains("JDialog-Swing"))
			{
				
				//android页面分析生成
				AndroidLayoutXml androidLayoutXml = new AndroidLayoutXml();
				String xmlFileName=androidLayoutXml.analyseRelative(frame.pageName,frame.beans);
				
			
				savePublicCompoment();
				
				//Swing页面水平方向 再次取得分组信息
				JPanel swingPanel = new CutCompomentsTypeImg(frame,
						frame.beansForSwing);
				JDialog jdialog = new JDialog(frame, "swing水平方向截取", true);
				jdialog.setSize(800, 800);
				jdialog.add(swingPanel);
				jdialog.setLocation(10, 10);
				jdialog.setVisible(true);
				
				SwingLayout swingLayout = new SwingLayout();
				swingLayout.createJDialog(frame.beans,frame.beansForSwing);
				
			}else if(frame.pageType.contains("JPanel-Swing"))
			{
				//android页面分析生成
				AndroidLayoutXml androidLayoutXml = new AndroidLayoutXml();
				String xmlFileName=androidLayoutXml.analyseRelative(frame.pageName,frame.beans);
				
			
				savePublicCompoment();
				
				//Swing页面水平方向 再次取得分组信息
				JPanel swingPanel = new CutCompomentsTypeImg(frame,
						frame.beansForSwing);
				JDialog jdialog = new JDialog(frame, "swing水平方向截取", true);
				jdialog.setSize(800, 800);
				jdialog.add(swingPanel);
				jdialog.setLocation(10, 10);
				jdialog.setVisible(true);
				
				SwingLayout swingLayout = new SwingLayout();
				swingLayout.createJPanel(frame.beans,frame.beansForSwing);
			}
			
			frame.beans.clear();
			frame.beansForSwing.clear();
			
			JOptionPane.showMessageDialog(frame,
					"刷新Eclipse目录或到"+KeyValue.readCache("picPath")+"查看生成的文件", "", JOptionPane.INFORMATION_MESSAGE);
		
			new CodeFunctionAdd();
		}
		
		
		
		public void savePublicCompoment()
		{
			SerializeToFile serializeToFile=new SerializeToFile();
			List temps=new ArrayList();
			String fileName="";
			for(CompomentBean bean:frame.beans)
			{
			
				if(bean.isPublicCompoment)
				{
					 fileName=bean.enname;
					 
						for(CompomentBean unmodifybean:frame.beansForSwing)
						{
							if(unmodifybean.enname.equals(fileName))
							{
								  temps.add(unmodifybean);
							}
						}
					 
				    
				 
					if(bean.chirlds!=null&& bean.chirlds.size()>0)
					{
						
						for(CompomentBean chrild:bean.chirlds)
						{
						
							for(CompomentBean unmodifybean:frame.beansForSwing)
							{
								if(unmodifybean.enname.equals(chrild.enname))
								{
									  temps.add(unmodifybean);
								}
							}
								
							
							
						}
					}
				}
			}
			
			String xmlFileName = FileUtil.makeFile(KeyValue.readCache("picPath"),
					"publiccompoment", fileName, "xml", "");
			serializeToFile.serializeToXml(temps,xmlFileName);
		}
		
}
