package com.compoment.ui.ios.creater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;
import com.compoment.gbkToUtf8.creater.GbkToUtf_FileOrDir;

public class UIAnalyze {
	List<ImgBean> controls=new ArrayList();
	List<String> lines=new ArrayList();
	FileReader fr=null;
	
	public static void main(String[] args) throws IOException {

		UIAnalyze mark = new UIAnalyze();
		mark.init();
		mark.analyze();

	}
	
	public UIAnalyze(){
		
	}

	public void analyze() {
	
			
			for(String myreadline:lines)
			{
			
				if(myreadline.contains("addSubview:"))
				{
					
					ImgBean parentBean=new ImgBean();
					ImgBean chirldBean=new ImgBean();
					int start = myreadline.indexOf("[")+1;
					int end = myreadline.indexOf("addSubview");
					String parent = myreadline.substring(start, end).trim();
					if(parent.indexOf("_")==0)
					{
						parent=parent.substring(1);
					}
					
					
					ImgBean tempParentBean=searchXY(parent);
					if(controls.indexOf(tempParentBean)==-1)
					controls.add(searchXY(parent));
					int start2 = myreadline.indexOf(":")+1;
					int end2 = myreadline.indexOf("]");
					
					String chirld = myreadline.substring(start2, end2).trim();
					
					if(chirld.indexOf("_")==0)
					{
						chirld=chirld.substring(1);
					}
					ImgBean tempChirldBean=searchXY(chirld);
					if(controls.indexOf(tempChirldBean)==-1)
					controls.add(searchXY(chirld));
					
					//parentBean.enName=parent;
					//chirldBean.enName=chirld;
					
					
					
//					controls.indexOf(arg0)
//					controls.add(imgBean);
				}
			}
			
			
			
		System.out.println(controls);

	
	}
	
	
	public ImgBean  searchXY(String controlName)
	{
		
		ImgBean imgBean=new ImgBean();
		for(String myreadline:lines)
		{
			if(myreadline.contains(controlName+" =") || myreadline.contains(controlName+" = ["))
			{
				int p=myreadline.indexOf("[");
				int pend=myreadline.substring(p).indexOf(" ")+p;
				imgBean.enName=controlName;
				imgBean.controlType=myreadline.substring(p+1, pend);
				if(imgBean.controlType.indexOf("[")!=-1)
				{
					imgBean.controlType=imgBean.controlType.substring(1);
				}
				
			}
			
			if(myreadline.contains("CGRectMake("))
			{
				int p=myreadline.indexOf("CGRectMake(")+11;
				int pend=myreadline.substring(p).indexOf(")]")+p;
				if(pend<p)
				{
					pend=myreadline.substring(p).indexOf(")")+p;
				}
				String xywh=myreadline.substring(p, pend).trim();
				String xywhs[]=xywh.split(",");
				
				int i=0;
				for(String x:xywhs)
				{
					if(x.contains("."))
					{x=x.trim();
						for(ImgBean bean:controls)
						{
							if(bean!=null && bean.enName!=null && x.contains(bean.enName))
							{
								if(x.contains("height")||x.contains("Height"))
								{
									
									
									int p4= x.indexOf("-");
									int p5= x.indexOf("+");
									Integer left = 0;
									Integer  right = 0;
									Integer now=0;
									if(p4!=-1)
									{
										left=Integer.valueOf(bean.h);
										 right=Integer.valueOf(x.split("[-]")[1].trim());
										 now=left-right;
									}
									
									else if(p5!=-1)
									{
										
										left=Integer.valueOf(bean.h);
										if(x.split("[+]")[1].trim().contains(bean.enName))
										{
											if(x.split("[+]")[1].trim().contains("y"))
											{
												right=bean.y;
											}
										}else
										{
										 right=Integer.valueOf(x.split("[+]")[1].trim());
										}
										 now=left+right;
									}
									else
									{
										now=Integer.valueOf(bean.h);
									}
									
									
									if(i==0)
									{
										imgBean.x=now;
									}else if(i==1)
									{
										imgBean.y=now;
									}else if(i==2)
									{
										imgBean.w=now;
									}else if(i==3)
									{
										imgBean.h=now;
									}
								}else if(x.contains("width") || x.contains("Width"))
								{
									
									int p4= x.indexOf("-");
									int p5= x.indexOf("+");
									int p6= x.indexOf("*");
									Integer left = 0;
									Integer  right = 0;
									Integer now=0;
									if(p4!=-1)
									{
										left=Integer.valueOf(bean.w);
										 right=Integer.valueOf(x.split("[-]")[1].trim());
										 now=left-right;
									}
									
									else if(p5!=-1)
									{
										left=Integer.valueOf(bean.w);
										if(x.split("[+]")[1].trim().contains(".f"))
										{
											int end=x.split("[+]")[1].trim().indexOf(".f");
											
											right=Integer.valueOf(x.split("[+]")[1].trim().substring(0, end));
										}else
										{
										 right=Integer.valueOf(x.split("[+]")[1].trim());
										}
										 now=left+right;
									}
									
									
									else if(p6!=-1)
									{
										left=Integer.valueOf(x.split("[*]")[0].trim());
										 right=Integer.valueOf(x.split("[*]")[1].trim());
										 now=left*right;
									}
									else
									{
										now=Integer.valueOf(bean.w);
									}
									
									
									if(i==0)
									{
										imgBean.x=now;
									}else if(i==1)
									{
										imgBean.y=now;
									}else if(i==2)
									{
										imgBean.w=now;
									}else if(i==3)
									{
										imgBean.h=now;
									}
								}
							}
						}
					}else
					{
						
						int p4= x.indexOf("-");
						int p5= x.indexOf("+");
						int p6= x.indexOf("*");
						Integer left = 0;
						Integer  right = 0;
						Integer now=0;
						if(p4!=-1)
						{
							
							if(x.split("-")[0].trim().equals(""))
							{
								left=0;
							}else
							{
							left=Integer.valueOf(x.split("-")[0].trim());
							}
							 right=Integer.valueOf(x.split("-")[1].trim());
							 now=left-right;
						}
						
						else if(p5!=-1)
						{
							left=Integer.valueOf(x.split("+")[0].trim());
							 right=Integer.valueOf(x.split("+")[1].trim());
							 now=left+right;
						}
						else if(p6!=-1)
						{
							left=Integer.valueOf(x.split("[*]")[0].trim());
							 right=Integer.valueOf(x.split("[*]")[1].trim());
							 now=left*right;
						}
						
						
						else
						{
							now=Integer.valueOf(x.trim());
						}
						
						if(i==0)
						{
							imgBean.x=now;
						}else if(i==1)
						{
							imgBean.y=now;
						}else if(i==2)
						{
							imgBean.w=now;
						}else if(i==3)
						{
							imgBean.h=now;
						}
					}
					i++;
				}
				
			}
		}
		return imgBean;
	}
	
	
	
	
	public void init()
	{
		
		String classDir = this.getClass().getResource("/").getPath();
		try {
			 fr = new FileReader(classDir
					+ "com/compoment/ui/ios/creater/MarkBefor.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(fr);
			String myreadline;
            String line="";
			while (br.ready()) {
				ImgBean imgBean=new ImgBean();
				myreadline = br.readLine();
				
					if(myreadline.contains(";"))
					{
						line+=myreadline;
						if(line.indexOf("_width")!=-1 )
						{
							line=line.replace("_width", "120");
						}
						if(line.indexOf("self.width")!=-1 )
						{
							line=line.replace("self.width", "120");
						}
						
						if(line.indexOf("BXDTodoEditItemCellHeight")!=-1)
						{
							line=line.replace("BXDTodoEditItemCellHeight", "50");
						}
						
						if(line.indexOf("makeTodoItemView")!=-1)
						{
							int p1=line.indexOf("w:");
							int p1end=line.substring(p1).indexOf(" ")+p1;
							
							int p2=line.indexOf("y:");
							int p2end=line.substring(p2).indexOf(" ")+p2;
							
							int p3=line.indexOf("width:");
							int p3end=20+p3;
							
			
							String x=line.substring(p1+2,p1end);
							String y=line.substring(p2+2, p2end);
							String w=line.substring(p3+6, p3end);
							int p4= w.indexOf("-");
							Integer left = 0;
							Integer  right = 0;
							if(p4!=-1)
							{
								 left=Integer.valueOf(w.split("-")[0].trim());
								 right=Integer.valueOf(w.split("-")[1].trim());

									int w2=left-right;
									w=String.valueOf(w2);
							}else
							{
								
							}
							
							String h="50";
							
							line=line.replace("[self makeTodoItemView:"," [[UIView alloc] initWithFrame:CGRectMake("+x+","+y+","+w+","+h+")");
							
						}
					
						lines.add(line);
						line="";
					}else
					{
						line+=myreadline;
					}
				
			}
			
			br.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

	public static String firstCharToUpper(String string) {
		// buy_typelist
		string = string.substring(0, 1).toUpperCase() + string.substring(1);
		return string;
	}

	public class PropertyBean {

		public String type;// 类型
		public String name;// 属性名
	}

	
}
