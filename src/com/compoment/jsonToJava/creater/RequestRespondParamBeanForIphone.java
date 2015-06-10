package com.compoment.jsonToJava.creater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;


import com.compoment.jsonToJava.creater.WordtableToJavaObject.Group;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.InterfaceBean;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.Row;
import com.compoment.util.KeyValue;
import com.google.gson.Gson;
/**
 * 接口 请求 接收Bean
 * */
public class RequestRespondParamBeanForIphone {

	public void requestRespondParamBean(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {

			beanCreate(interfaceBean, "Request");
			beanCreate(interfaceBean, "CacheRespond");
			beanCreate(interfaceBean, "Respond");
		}
	}

	public void beanCreate(InterfaceBean interfaceBean, String type) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();

	
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "@interface " + type + "Param" + interfaceBean.id + ":NSObject{\n";

		List<Group> groups = null;
		if (type.equals("Request")) {
			groups = interfaceBean.requestGroups;
		} else {
			groups = interfaceBean.respondGroups;
		}

		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					String typename="";
					if(row.getType().equals("String"))
					{
						typename="NSString";
						m+="@property (strong, nonatomic) "+typename+" *"+row.enName+";\n";
					}else if(row.getType().equals("List"))
					{
						typename="NSMutableArray";
						m+="@property (strong, nonatomic) "+typename+" *"+row.enName+";\n";
					}else if(row.getType().equals("Map"))
					{
						typename="NSMutableDictionary";
						m+="@property (strong, nonatomic) "+typename+" *"+row.enName+";\n";
					}else 
					{
						typename=row.getType();
						m+="@property (strong, nonatomic) "+typename+" "+row.enName+";\n";
					}
				

				}
			} else {
				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {// 循环域开始
						if (row.getType() != null && !isCommonType(row.getType())) {//自定义类型
							m += "\n\n/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public List<" + row.getType() + "> " + row.getType() + ";\n";
							mChirldClass.add(chirldClass(group));
							isCustomerClass = true;
						} else {//非自定义类型
							m += "\n\n/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public " + row.getType() + " " + row.enName
									+ ";\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							if(type.equals("Request"))
							{
								m += "/** " + row.cnName + " 备注:" + row.remarks
										+ "*/\n";
								m += "public " + row.getType() + " " + row.enName
										+ "[];\n";
							}
							if(type.equals("Respond"))
							{
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public " + row.getType() + " " + row.enName
									+ ";\n";
							}
							if(type.equals("CacheRespond"))
							{
							m += "/** " + row.cnName + " (数组)备注:" + row.remarks
									+ "*/\n";
							m += "public " + row.getType() + " " + row.enName
									+ "[];\n";
							}
						}
					}
					i++;
				}
			}

		}
		m += "}\n\n";

		for (String cirldClassString : mChirldClass) {
			m += cirldClassString + "\n\n";
		}
		makeFile( type + "Param" + interfaceBean.id,m);
		System.out.println(m);
	}

	public String chirldClass(Group group) {
		String n = "";
		int i = 0;
		for (Row row : group.rows) {
			if (i == 0) {
				n += "/** 子类" + row.getType() + " " + row.cnName + " 备注:"
						+ row.remarks + "*/\n";
				n += "public class " + row.getType() + "{\n";
			} else {
				n += "public " + row.getType() + " " + row.enName + ";\n";
			}
			i++;
		}
		n += "}\n\n\n";
		return n;
	}

	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long")||type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

//    NSMutableString *businessParam= [map objectForKey:@"businessParam"];
//    
//    NSDictionary *paramdic=[self jsonString2Dic:[businessParam dataUsingEncoding:NSUTF8StringEncoding] ];
//    
//       bool success= [paramdic objectForKey:@"success"];
//     NSMutableString *data= [paramdic objectForKey:@"data"];
//       NSDictionary *datadic=[self jsonString2Dic:[data dataUsingEncoding:NSUTF8StringEncoding] ];
//    
//     NSDictionary *kk= [datadic objectForKey:@"kk"];
//    
//     NSDictionary *returnData= [kk objectForKey:@"returnData"];
//     NSArray *D4496_MAIL_STATUS= [returnData objectForKey:@"D4496_MAIL_STATUS"];
//     NSString *t2t=[D4496_MAIL_STATUS objectAtIndex:0];
//        NSArray *D44_70_TRAN_TIME= [returnData objectForKey:@"D44_70_TRAN_TIME"];
//    
//    NSString *tt=[D44_70_TRAN_TIME objectAtIndex:0];
//    
//     NSString *D44_70_RECORDNUM1= [returnData objectForKey:@"D44_70_RECORDNUM1"];
    
    
	
	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	public void makeFile(String fileName,String s)
	{
		try {
		String doc=KeyValue.readCache("docPath");
		int p=doc.lastIndexOf("/");
		if(p==-1)
		{
			 p=doc.lastIndexOf("\\");
		}
		
		
		      
		    
		
		File tofile=new File(doc.substring(0, p)+"/java/"+fileName+".java");
		  if(! tofile.exists()) {  
	            makeDir(tofile.getParentFile());  
	        }  
	      
		  tofile.createNewFile(); 
		
		FileWriter fw;
		
			fw = new FileWriter(tofile);
			BufferedWriter buffw=new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(buffw);
		
		

	
		pw.println(s);

		pw.close();
		buffw.close();
		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  public  void makeDir(File dir) {  
	        if(! dir.getParentFile().exists()) {  
	            makeDir(dir.getParentFile());  
	        }  
	        dir.mkdir();  
	    }  
	
	
}
