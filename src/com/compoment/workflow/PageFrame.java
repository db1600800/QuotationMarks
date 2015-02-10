package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.compoment.cut.CompomentBean;
import com.compoment.cut.CompomentDialog;
import com.compoment.cut.CompomentDialog.CompomentDialogCallBack;
import com.compoment.cut.CutCompomentsTypeImg.CutCompomentsTypeImgCallBack;
import com.compoment.cut.CutImg;
import com.compoment.cut.CutImg.CutImgCallBack;

public class PageFrame extends JFrame implements CutImgCallBack,CompomentDialogCallBack,CutCompomentsTypeImgCallBack{

	private CompomentDialogCallBack dialog;
	public JPanel panel;
	
	/**
	 * 全部控件对象
	 * */
	public List<CompomentBean> beans = new ArrayList<CompomentBean>();
	public List<CompomentBean> beansForSwing = null;
	public String pageType;
	public String pagePath;
	public String pageName;
	
	
	
	ArrayList components = new ArrayList();

	
	
	public PageFrame(String pagePath) {
		
		super("");
		this.pagePath=pagePath;
		pageName=getPageName(pagePath);
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String laf = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException exc) {
		} catch (Exception exc) {
		}

		Container c = getContentPane();
		GroupLayout layout = new GroupLayout(c);
		c.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/** pageTypeView pagePicView pageCreateView */
		GroupLayout.ParallelGroup bg1420943922947LinearLayout = layout
				.createParallelGroup();
		/** 页面类型 */
		JPanel pageTypeView = new PageTypePanel(this).create();
		bg1420943922947LinearLayout.addComponent(pageTypeView);

		/** 页面图片 */
	
		JPanel pagePicView =  PicPanel(pagePath);
		//pagePicView.setPreferredSize(new Dimension(600,600));
		JScrollPane scrollPane = new JScrollPane(pagePicView); 
		
		bg1420943922947LinearLayout.addComponent(scrollPane);

		/** 页面生成 */
		JPanel pageCreateView = new PageCreatePanel(this).create();
		bg1420943922947LinearLayout.addComponent(pageCreateView);

		layout.setVerticalGroup(bg1420943922947LinearLayout);

		// 水平
		/** bg1420944003995 bg1420944007370 bg1420944010763 */
		GroupLayout.SequentialGroup bg1420944014881 = layout
				.createSequentialGroup();
		
		/** 页面类型 */
		bg1420944014881.addComponent(pageTypeView);

	
		/** 页面图片 */
		bg1420944014881.addComponent(scrollPane);

	
		/** 页面生成 */
		bg1420944014881.addComponent(pageCreateView);

		layout.setHorizontalGroup(bg1420944014881);

		setLocation(50, 200);
		setSize(1200, 700);
	    this.setTitle("页面设置");
		setVisible(true);
	}

	
	public JPanel PicPanel(String path)
	{

		JPanel panel = new CutImg(this, new File(path)); 
		return panel;
	}
	
	
	public static void main(String[] args) {
		new PageFrame("");
	}


	/**
	 * 获得截取图片的相关属性（x,y,w,h）
	 * */
	public void cutCompomentsTypeImgCallBack( final int x, final int y,
			final int w, final int h) {
		
		CompomentBean bean = new CompomentBean();
		long id=System.currentTimeMillis();
		bean.cnname = "bg"+id;
		bean.enname = "bg"+id;
		bean.rgb16 = "";
		bean.bgRgb16 = "";
		bean.picName ="";
		bean.textSize = "";
		bean.x = x;
		bean.y = y;
		bean.w = w;
		bean.h = h;
		bean.type = "Layout";
		bean.time=System.currentTimeMillis();
		
		beansForSwing.add(bean);
	}

	/**
	 * 根据截取图片生成控件对象
	 * */
	public void compomentDialogCallBack(CompomentBean bean) {
		beans.add(bean);
	}

	
	/**
	 * CutImgCallBack   获得截取图片的相关属性（x,y,w,h,Image）
	 * */
	
	@Override
	public void cutImgCallBack(Image image, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
	
		if(pageType==null)
		{
			JOptionPane.showMessageDialog(this,
					"请选择页面类型", "", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(pageType.contains("Android"))
		{
			components.clear();
			components.add("RelativeLayout");
			components.add("LinearLayout");
			components.add("TableRow");
			components.add("ScrollView");
			components.add("View");
			
			components.add("TextView");
			components.add("Button");
			components.add("CheckBox");
			components.add("EditText");
		
			components.add("ImageView");
			components.add("ListView");
			components.add("ExpandableListView");
			components.add("Spinner");
		}else if(pageType.contains("JFrame-Swing"))
		{
			components.clear();
			
			components.add("LinearLayout");
			components.add("JPanel");
			
		}else if(pageType.contains("JPanel-Swing"))
		{
			components.clear();
		
			components.add("LinearLayout");
			components.add("TextView");
			components.add("Button");
			components.add("CheckBox");
			components.add("EditText");
			components.add("ImageView");
			components.add("ListView");
			
		}
		
		
		CompomentDialog dialog;
		dialog = new CompomentDialog(this,this, image, x, y, w, h,components);
		dialog.setLocation(300, 300);
		dialog.setVisible(true);//
	}
	
	

	
	public String  getPageName(String pagePath)
	{

	
		  
		  
			int p=pagePath.lastIndexOf("/");
			if(p==-1)
			{
				p=pagePath.lastIndexOf("\\");
			}
			int p2=pagePath.lastIndexOf(".");
			String pageName=pagePath.substring(p+1,p2);
			String path=pagePath.substring(0,p);
			
		
			
			File xmlfile=new File(path+"/xml/");
			 if(! xmlfile.exists()) {  
		         makeDir(xmlfile);  
		     } 
			 
				File drawablefile=new File(path+"/drawable/");
				 if(! drawablefile.exists()) {  
			         makeDir(drawablefile);  
			     } 
				 
					File javafile=new File(path+"/java/");
					 if(! javafile.exists()) {  
				         makeDir(javafile);  
				     } 
	
		        return pageName;
		    
	}

public  void makeDir(File dir) {  
    if(! dir.getParentFile().exists()) {  
        makeDir(dir.getParentFile());  
    }  
    dir.mkdir();  
}  

}