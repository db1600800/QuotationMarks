package com.compoment.cut.android.function;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.TextView;

import com.compoment.cut.swing.ui.FileUtil;
import com.compoment.cut.swing.ui.KeyValue;
import com.gdpost.cps.activitys.order.Drawback.OnDateClickListener;

public class DateSelect {
	
	List<FileBean> fileBeans=new ArrayList();
	
	
	String sourceAddress="C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress=KeyValue.readCache("projectPath");
	String waitByModifyFileName;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            new DateSelect("");
	}
	
	
	public  DateSelect(String waitByModifyFileName)
	{
		copyFile();
		modify();
		this.waitByModifyFileName=waitByModifyFileName;
	}
	
	
	public void copyFile()
	{

		String sourcePackage="/com/compoment/dateselect";
		String destinationPackage="/com/compoment/dateselect";
		
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"ArrayWheelAdapter","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"JudgeDate","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"NumericWheelAdapter","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"OnWheelChangedListener","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"OnWheelScrollListener","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"ScreenInfo","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"WheelAdapter","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"WheelMain","java"));
		fileBeans.add(new FileBean("/src"+sourcePackage,"/src"+destinationPackage,"WheelView","java"));
		
		
		fileBeans.add(new FileBean("/res/layout",null,"ipo_dialog_date_picker","xml"));
		
		
		
		fileBeans.add(new FileBean("/res/drawable-hdpi",null,"wheel_val","xml"));
		fileBeans.add(new FileBean("/res/drawable-hdpi",null,"wheel_bg","xml"));
	
		for(FileBean bean:fileBeans)
		{
			
		    File wantFile=FileUtil.findFile(new File(sourceAddress+bean.sourcePath), bean.name+"."+bean.type);
		    
             if(wantFile==null )
            	 System.out.println("找不到此文件"+bean.name+"."+bean.type);
             
             //图片文件
		    if(bean.type.equals("png")||bean.type.equals("jpg"))
		    {
		    	try {
					FileUtil.copyFile(wantFile, new File(destinationAddress+bean.destinationPath+bean.name+"."+bean.type));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }else
		    {//其它文件
		    	
		    	List lines=FileUtil.fileContentToArrayList(wantFile.getAbsolutePath());
		    	
		    	String content="";
		    	for(int i=0;i<lines.size();i++)
		    	{String line="";
		    		if(lines.get(i)==null)
		    		{
		    			line="";
		    		}else
		    		{
		    			 line=lines.get(i).toString();
		    		}
		    		
		    		
		    		if(line.contains("package"))
		    		{
		    			line="package "+destinationPackage.substring(1).replaceAll("/", ".")+";";
		    		}else 	if(line.contains("import") && line.contains(".R"))
		    		{
		    			line="";
		    		}
		    		content+=line+"\n";
		    	}
		    	
		    String filename=FileUtil.makeFile(destinationAddress+bean.destinationPath, null, bean.name, bean.type, content);
		    System.out.println(filename);  
		    }
		}
		
		
		
		
		
	
		
		
	
	}
	
	
	public void modify()
	{
		
		
		
    	List lines=FileUtil.fileContentToArrayList(waitByModifyFileName);
    	
    	String content="";
    	for(int i=0;i<lines.size();i++)
    	{String line="";
    		if(lines.get(i)==null)
    		{
    			line="";
    		}else
    		{
    			 line=lines.get(i).toString();
    		}
    		
    		
    		if(line.contains("startDate"))
    		{
    			line="package "+destinationPackage.substring(1).replaceAll("/", ".")+";";
    		}else 	if(line.contains("import") && line.contains(".R"))
    		{
    			line="";
    		}
    		content+=line+"\n";
    	}
    	
    String filename=FileUtil.makeFile(destinationAddress+bean.destinationPath, null, bean.name, bean.type, content);
		
		

		
	
		
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy年MM月dd日");
		calendar.add(Calendar.DATE, 0);// 结束日期是今天
		endDateTextView.setText(ymd.format(calendar.getTime()));
		calendar.add(Calendar.DATE, -3);// 开始日期是今天的前三天
		startDateTextView.setText(ymd.format(calendar.getTime()));
		OnDateClickListener clickListener = new OnDateClickListener();
		startDateTextView.setOnClickListener(clickListener);
		endDateTextView.setOnClickListener(clickListener);
	}

	

	
	
	
	public class FileBean{
		String name;
		String type;
		String sourcePath;
		String destinationPath;
		
		public FileBean(String sourcePath,String destinationPath,String name,String type){
			this.sourcePath=sourcePath;
			if(destinationPath==null)
			{
				this.destinationPath=sourcePath;
			}else
			{
			this.destinationPath=destinationPath;
			}
			this.name=name;
			this.type=type;
		}
	}
}
