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
		m+=" [serviceInvoker callWebservice:businessparam otherParam:array  delegate:self  formName:n"+interfaceBean.id+" ];\n";
		
		

		m += "}\n\n";

		System.out.println(m);
		return m;
	}
	
	
	public String  respond(String baseJson,InterfaceBean interfaceBean) {
		String m = "\n\n\n";
	
		String className="RespondParam" + interfaceBean.id ;	
		
		
		m+="NSMutableArray *listData=[[NSMutableArray alloc]init];\n";
		
//		m+="NSMutableArray *sectionAZDicArray=[[NSMutableArray alloc]init];\n";
//		
//		NSMutableDictionary *sectionParentDic = [NSMutableDictionary dictionary];
//		
//		//chirlds
//		m+="NSMutableArray *sectionChirldsArray=[[NSMutableArray alloc]init];\n";
//		//chirldA
//		m+="NSMutableDictionary *sectionAChirldADic = [NSMutableDictionary dictionary];\n";
//		m+="[sectionAChirldADic setValue:@\"\" forKey:@\"\"];";
//		
//		
//		NSMutableDictionary *sectionADic = [NSMutableDictionary dictionary];
//		[sectionADic setValue:@"SectionParent" forKey:@"SectionParent"];
//		[sectionADic setValue:sectionChirldsArray forKey:@"SectionChirlds"];
//		[sectionAZDicArray addObject:sectionADic];
		
		


		
		
//	    NSMutableString *businessParam= [map objectForKey:@"businessParam"];
	//    
//	    NSDictionary *paramdic=[self jsonString2Dic:[businessParam dataUsingEncoding:NSUTF8StringEncoding] ];
	//    
//	       bool success= [paramdic objectForKey:@"success"];
//	     NSMutableString *data= [paramdic objectForKey:@"data"];
//	       NSDictionary *datadic=[self jsonString2Dic:[data dataUsingEncoding:NSUTF8StringEncoding] ];
	//    
//	     NSDictionary *kk= [datadic objectForKey:@"kk"];
	//    
//	     NSDictionary *returnData= [kk objectForKey:@"returnData"];
//	     NSArray *D4496_MAIL_STATUS= [returnData objectForKey:@"D4496_MAIL_STATUS"];
//	     NSString *t2t=[D4496_MAIL_STATUS objectAtIndex:0];
//	        NSArray *D44_70_TRAN_TIME= [returnData objectForKey:@"D44_70_TRAN_TIME"];
	//    
//	    NSString *tt=[D44_70_TRAN_TIME objectAtIndex:0];
	//    
//	     NSString *D44_70_RECORDNUM1= [returnDataBody objectForKey:@"D44_70_RECORDNUM1"];
	    
	
		
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "if ([requestCode isEqualToString:n"+interfaceBean.id +"]){\n";

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
							m += "item"+groupCount+"."+row.enName+"=[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i];\n";
						
						}
					}
					i++;
				}
				m+="[listData addObject:item"+groupCount+"];\n";
				m+="}\n\n";
				
			} else {
				 m+=className+" *commonItem"+"=[["+className+" alloc]init];\n";
				for (Row row : group.rows) {
					m += "/* " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m +=  "commonItem." + row.enName + "=[returnDataBody objectForKey:@\""+row.enName+"\"];\n";
					
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
