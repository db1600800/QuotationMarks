package com.compoment.jsonToJava.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.google.gson.Gson;

/**
 * 请求 接收 函数
 * */
public class RequestRespondForIphone {

//	public void requestRespond(List<InterfaceBean> interfaceBeans) {
//		if (interfaceBeans == null)
//			return;
//
//		for (InterfaceBean interfaceBean : interfaceBeans) {
//
//			request(interfaceBean);
//			respond(interfaceBean);
//		}
//	}

	public String request(InterfaceBean interfaceBean) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RequestParam" + interfaceBean.id ;	
		
	
		
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		m+="NSString  *n"+interfaceBean.id+"=@\""+interfaceBean.id +"\";\n";
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "-(void) request"+interfaceBean.id+"{\n";
		
		m+="NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];\n";
		
		
		 
		List<Group> groups = interfaceBean.requestGroups;
		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m+="[businessparam setValue:@\"\" forKey:@\""+row.enName+"\"];\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m+="NSMutableArray *"+row.enName+"List=[[NSMutableArray alloc]init];\n";
							m+="// ["+row.enName+"List addObject:@\"\"];\n";
							m+="[businessparam setValue:"+row.enName+"List forKey:@\""+row.enName+"\"];\n";

						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				
				for (Row row : group.rows) {
					m += "/* " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m+="[businessparam setValue:@\"\" forKey:@\""+row.enName+"\"];\n";
				}
			}
		}

		
		m+=" ServiceInvoker *serviceInvoker=[ServiceInvoker sharedInstance ];\n";
		m+=" [serviceInvoker callWebservice:businessparam otherParam:nil  delegate:self  formName:n"+interfaceBean.id+" ];\n";
		
		

		m += "}\n\n";

		System.out.println(m);
		return m;
	}
	
	
	public String  respond(String baseJson,InterfaceBean interfaceBean) {
		String m = "\n\n\n";
	
		String className="RespondParam" + interfaceBean.id ;	
		
		
		m+="NSMutableArray *listData"+interfaceBean.id+"=[[NSMutableArray alloc]init];\n";
		
	
		
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "if ([msgReturn.formName isEqualToString:n"+interfaceBean.id +"]){\n";

		JsonToIosBeanForSimple jsonToIosBeanForSimple=new JsonToIosBeanForSimple(baseJson);
		m+=jsonToIosBeanForSimple.getJaveBeanClass();
		
		List<Group> groups = interfaceBean.respondGroups;
		

		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
						  
							m+="int "+row.enName+"= [[returnDataBody objectForKey:@\""+row.enName+"\"]intValue];\n";
							m+="for(int i=0;i<"+row.enName+";i++)\n{\n";
							  m+=className+" *item"+groupCount+"=[["+className+" alloc]init];\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							if(row.type.toLowerCase().equals("int"))
							{
								m += "item"+groupCount+"."+row.enName+"=[[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i]intValue];\n";
								
							}else if(row.type.toLowerCase().equals("float"))
							{
								m += "item"+groupCount+"."+row.enName+"=[[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i]floatValue];\n";
								
							}else
							{
							m += "item"+groupCount+"."+row.enName+"=[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i];\n";
							}
						}
					}
					i++;
				}
				m+="[listData"+interfaceBean.id+" addObject:item"+groupCount+"];\n";
				m+="}\n\n";
				
			} else {
				 m+=className+" *commonItem"+"=[["+className+" alloc]init];\n";
				for (Row row : group.rows) {
					m += "/* " + row.cnName + " 备注:" + row.remarks + "*/\n";
					if(row.type.toLowerCase().equals("int"))
					{
						m +=  "commonItem." + row.enName + "=[[returnDataBody objectForKey:@\""+row.enName+"\"]intValue];\n";
						
					}else if(row.type.toLowerCase().equals("float"))
					{
						m +=  "commonItem." + row.enName + "=[[returnDataBody objectForKey:@\""+row.enName+"\"]floatValue];\n";
					}else
					{
					
					m +=  "commonItem." + row.enName + "=[returnDataBody objectForKey:@\""+row.enName+"\"];\n";
					}
					
				}
			}
          groupCount++;
		}
		m += "}\n\n";

	

		System.out.println(m);
		return m;
	}

	
	
	

	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long")||type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	
	
	public void serviceInvoker()
	{
		
		
		
	}
}
