package com.compoment.jsonToJava.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.remote.CheckProblemInterface;
import com.compoment.remote.WordtableToJavaObjectInterface;
import com.google.gson.Gson;

//http://wenku.baidu.com/link?url=ll3rEIIMCAr5m_T-F3rcvzawiI-pd5E5W2uxHBXTzHoQkSBMgQXdtnhBaU9VITz4neKofs_J66_OCR_QPpYz94QMVw6xBBkVqhDnMxkIgk_
/**
 * 名称 数据元素 类型 长度 备注 （0） （1） （2） （3） （4） 列用index标记
 * 
 * */

public class WordtableToJavaObject extends UnicastRemoteObject implements WordtableToJavaObjectInterface{

	public WordtableToJavaObject() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	Map point = new HashMap();
	
	


	public static void main(String[] args) throws Exception {

	

		Map point = new HashMap();
		point.put("中文名", 0);// 中文注解在表格的哪一列
		point.put("变量名", 1);
		point.put("类型", 2);// 类型在表格的哪一列
		point.put("备注", 4);

	
		String classDir = "";
		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String docpath = null;

	   String	sourceFile = classDir + "/src/com/compoment/jsonToJava/creater/"
				+ "wordTableToJaveObject.doc"; //JaveObjectTowordTable2.doc  wordTableToJaveObject.doc
	

		WordtableToJavaObject wordtable = new WordtableToJavaObject();
		Gson gson=new Gson();
		List interfaceBeans=wordtable.wordAnalyse(sourceFile ,point);
	
		RequestRespondParamBean requestRespondParamBean=new RequestRespondParamBean();
		requestRespondParamBean.requestRespondParamBean(interfaceBeans);
		
		RequestRespond requestRespond=new RequestRespond();
		requestRespond.requestRespond(interfaceBeans);
	}

	





	public List wordAnalyse(String file,Map point)  throws RemoteException {
		Gson gson=new Gson();
		
		List<InterfaceBean> interfaceBeans = new ArrayList();
		 HWPFDocument hwpf;
		 Range range = null;
		 this.point=point;
		 
		 
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);

			hwpf = new HWPFDocument(in);
			range = hwpf.getRange();// 得到文档的读取范围
			int numP = range.numParagraphs(); // 看看此文档有多少个段落

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int numP = range.numParagraphs(); // 得到range范围的Paragraph的个数

		InterfaceBean interfaceBean = null;

		for (int i = 0; i < numP; ++i) {
			// 从每一段落中获取文字,每一段是一个回车
			Paragraph p = range.getParagraph(i);

			String ids[]=getInterfaceId(p.text());
			if (ids != null) {
					
				interfaceBean = new InterfaceBean();
				interfaceBean.title = getInterfaceTitle(p.text());
				interfaceBean.id = ids[0].trim();
				if(ids.length>1)
				{
					interfaceBean.enName=ids[1].trim();
				}else
				{
					interfaceBean.enName="";
				}
			
				interfaceBeans.add(interfaceBean);
				
			}else
			{
				System.out.println("错误 没有Table id"+p.text());
			}

			if (p.text().contains("传入参数") || p.text().contains("输入参数")||p.text().contains("发送报文")||p.text().contains("表字段")) {

				// 判断该Paragraph是否在word的表格中
				Paragraph nextP;
				int j=1;
				do{
					// 判断该Paragraph是否在word的表格中
					 nextP = range.getParagraph(i + j);
					 j++;
				}while(nextP.isInTable() != true);
				if (nextP.isInTable() == true) {
					
					String tableTitle=nextP.text();
					
					Table table = range.getTable(nextP); // 通过第一个在table中的Paragraph来获取整个table

					commonGroup(interfaceBean, table, "request");
					notCommonGroup(interfaceBean, table, "request");
					
				}
			} else if (p.text().contains("传出参数") || p.text().contains("输出参数")||p.text().contains("接收报文")) {

				
				Paragraph nextP;
				int j=1;
				do{
					// 判断该Paragraph是否在word的表格中
					 nextP = range.getParagraph(i + j);
					 j++;
				}while(nextP.isInTable() != true);
				
				
				if (nextP.isInTable() == true) {
					Table table = range.getTable(nextP); // 通过第一个在table中的Paragraph来获取整个table

					commonGroup(interfaceBean, table, "respond");
					notCommonGroup(interfaceBean, table, "respond");
					
				}
			}

		}

	
		//ListToArray.listToArray(interfaceBeans)
		return interfaceBeans;
	
	}

	public String getInterfaceTitle(String txt) {
		int start = txt.indexOf("(");
		int start2 = txt.indexOf("（");
		int end = txt.indexOf(")");
		int end2 = txt.indexOf("）");

		if ((start != -1 || start2 != -1) && (end != -1 || end2 != -1)) {
			if (start != -1) {
				return txt.subSequence(0, start).toString();
			} else {
				return txt.subSequence(0, start2).toString();
			}
		}
		return null;
	}

	public String[] getInterfaceId(String txt) {


		int start = txt.indexOf("(");
		int start2 = txt.indexOf("（");
		int end = txt.indexOf(")");
		int end2 = txt.indexOf("）");

		if ((start != -1 || start2 != -1) && (end != -1 || end2 != -1)) {
			if (start != -1 && end!=-1) {
				String id = txt.subSequence(start + 1, end).toString();
				String ids[]=id.split("|");
				if (id != null) {
					if (isNum(ids[0])) {
						return ids;
					}
				}
			} else if(start2!=-1 && end2!=-1){
				String id = txt.subSequence(start2 + 1, end2).toString();
				String ids[]=id.split(",");
				if (id != null) {
					if (isNum(ids[0])) {
						return ids;
					}
				}
			}else if(start!=-1 && end2!=-1){
				String id = txt.subSequence(start + 1, end2).toString();
				String ids[]=id.split("|");
				if (id != null) {
					if (isNum(ids[0])) {
						return ids;
					}
				}}else if(start2!=-1 && end!=-1){
					String id = txt.subSequence(start2 + 1, end).toString();
					String ids[]=id.split("|");
					if (id != null) {
						if (isNum(ids[0])) {
							return ids;
						}
					}}
			
		}
		return null;
	}

	public void commonGroup(InterfaceBean interfaceBean, Table tb, String type) {
		// 迭代行，默认从0开始

		boolean isCommon = true;

		int rowCount = tb.numRows();

		if (rowCount < 2)
			return;
		Group commonGroup =interfaceBean.new Group();
		for (int i = 1; i < tb.numRows(); i++) {// 1表示第二行开始

			TableRow tr = tb.getRow(i);
			
			int enNameIndex = (Integer) point.get("变量名");
			String sEnName = "";
			if (enNameIndex < tr.numCells()) {
				TableCell tdEnName = tr.getCell(enNameIndex);// 取得
																// 变量名
																// index
																// 再取row值
				// 单元格（变量名）
				Paragraph paraEnName = tdEnName.getParagraph(0);
				sEnName = paraEnName.text().replaceAll("", "");
			}

			int cnNameIndex=(Integer) point.get("中文名");
			String sCnName="";
			if(cnNameIndex<tr.numCells())
			{
			TableCell tdCnName = tr.getCell(cnNameIndex);// 取得
			// 中文名
			// index
			// 再取row值
			//
			Paragraph paralCnName = tdCnName.getParagraph(0);
			 sCnName = paralCnName.text();
			}
			
			int remarksIndex=(Integer) point.get("备注");
			String sRemarks = "";
			if(remarksIndex<tr.numCells())
			{
			TableCell tdRemarks = tr.getCell(remarksIndex);// 取得 备注
																		// index
																		// 再取row值
																		//
		
			for (int b = 0; b < tdRemarks.numParagraphs(); b++) {
				Paragraph paraRemarks = tdRemarks.getParagraph(b);
				sRemarks += paraRemarks.text().replaceAll("", "");
			}}

			int typeIndex=(Integer) point.get("类型");
			String sType="";
			if(typeIndex<tr.numCells())
			{
			TableCell tdType = tr.getCell(typeIndex);// 取得 类型
																		// index
																		// 再取row值

			Paragraph paralType = tdType.getParagraph(0);
			 sType = paralType.text();
			 if(sType==null ||"".equals(sType)||sType.length()==1)
			 {
				 sType = "String";
			 }
			 else if (sType.contains("字符")||sType.contains("string")||sType.contains("char")) {
				sType = "String";
			} else if (sType.contains("整数") || sType.contains("整型")||sType.contains("Integer")||sType.contains("long")||sType.contains("Long")) {
				sType = "int";
			} else if (sType.contains("浮点")||sType.contains("Float")) {
				sType = "float";
			}}

			if (sCnName.contains("开始") && sCnName.contains("循环")) {
				isCommon = false;

			} else if (sCnName.contains("结束") && sCnName.contains("循环")) {
				isCommon = true;

			}

			if (sType.toLowerCase().contains("string")
					|| sType.toLowerCase().contains("int")
					|| sType.toLowerCase().contains("float")
					|| sType.toLowerCase().contains("long")) {

				if (isCommon) {
					Row row = interfaceBean.new Row();
					row.cnName = sCnName;
					row.enName = sEnName;
					row.remarks = sRemarks;
					row.type = sType;
					commonGroup.rows.add(row);
				}

			}

		}
		commonGroup.name="CommonGroup";
		if (type.equals("request")) {
			interfaceBean.requestGroups.add(commonGroup);
		} else {
			interfaceBean.respondGroups.add(commonGroup);
		}

	}

	/*** 循环域开始结束构成一个组 ， 自定义对象开始结束构成一个组 */
	public void notCommonGroup(InterfaceBean interfaceBean, Table tb,
			String type) {
		// 迭代行，默认从0开始
		Group group = null;

		boolean isNotCommon = false;
		for (int i = 1; i < tb.numRows(); i++) {// 1表示第二行开始

			TableRow tr = tb.getRow(i);
			// 迭代列，默认从0开始

			int enNameIndex=(Integer) point.get("变量名");
			String sEnName="";
			if(enNameIndex<tr.numCells())
			{
			TableCell tdEnName = tr.getCell(enNameIndex);// 取得
																		// 变量名
																		// index
																		// 再取row值
			// 单元格（变量名）
			Paragraph paraEnName = tdEnName.getParagraph(0);
			 sEnName = paraEnName.text().replaceAll("", "");
			}
			
			
			int cnNameIndex=(Integer) point.get("中文名");
			String sCnName="";
			if(cnNameIndex<tr.numCells())
			{
			TableCell tdCnName = tr.getCell(cnNameIndex);// 取得
			// 中文名
			// index
			// 再取row值
			//
			Paragraph paralCnName = tdCnName.getParagraph(0);
			 sCnName = paralCnName.text();
			}
			
			
			int remarksIndex=(Integer) point.get("备注");
			String sRemarks = "";
			if(remarksIndex<tr.numCells())
			{
			TableCell tdRemarks = tr.getCell(remarksIndex);// 取得 备注
																		// index
																		// 再取row值
																		//
			
			for (int b = 0; b < tdRemarks.numParagraphs(); b++) {
				Paragraph paraRemarks = tdRemarks.getParagraph(b);
				sRemarks += paraRemarks.text().replaceAll("", "");
			}}

			
			int typeIndex=(Integer) point.get("类型");
			String sType="";
			if(typeIndex<tr.numCells())
			{
			TableCell tdType = tr.getCell(typeIndex);// 取得 类型
																		// index
																		// 再取row值

			Paragraph paralType = tdType.getParagraph(0);
			 sType = paralType.text().replaceAll("", "");
			 if(sType==null || "".equals(sType)||sType.length()==1)
			 {
				 sType = "String";
			 }
			 else if (sType.contains("String")||sType.contains("字符")||sType.contains("string")||sType.contains("char")) {
					sType = "String";
				} else if (sType.contains("int")||sType.contains("整数") || sType.contains("整型")||sType.contains("Integer")||sType.contains("long")||sType.contains("Long")) {
					sType = "int";
				} else if (sType.contains("浮点")||sType.contains("float")||sType.contains("Float")) {
					sType = "float";
				}	
			}

			if (sCnName.contains("开始") && sCnName.contains("循环")) {
				group = interfaceBean.new Group();
				group.name=sEnName+"Group";
				if(!sType.matches("[a-zA-Z]+") || !sType.equals("int") )
				{
					sType="int";
				}
				isNotCommon = true;

			} else if (sCnName.contains("结束") && sCnName.contains("循环")) {
				if (type.equals("request")) {
					interfaceBean.requestGroups.add(group);
				} else {
					interfaceBean.respondGroups.add(group);
				}
				isNotCommon = false;
			}

			if (sType.matches("[a-zA-Z]+")) {

				if (isNotCommon) {
					Row row = interfaceBean.new Row();
					row.cnName = sCnName;
					row.enName = sEnName;
					row.remarks = sRemarks;
					row.type = sType;
					group.rows.add(row);
				}

			}

		}

	}

//	public static void getRequest() {
//
//		String objectname = "requestParam";
//		String m = "";
//		m += "public void " + javaName + "Request() {\n";
//
//		m += "String url = \"https://gbssdev.infinitus.com.cn/gbss-mobile/front/"
//				+ path + "?json=\";\n";
//		m += "" + javaName + "RequestParam " + objectname + "=new " + javaName
//				+ "RequestParam();\n";
//		m += objectname
//				+ ".commonParam = UECCommonUtil.buildCommonParam(context);\n";
//		boolean first = true;
//		TableIterator it = new TableIterator(range);
//		int counttable = 0;
//		// 迭代文档中的表格
//		while (it.hasNext()) {
//			Table tb;
//			if (counttable == 0) {
//				tb = (Table) it.next();
//				counttable++;
//			} else {
//				break;
//			}
//
//			// 迭代行，默认从0开始
//
//			for (int i = 2; i < tb.numRows(); i++) {
//				TableRow tr = tb.getRow(i);
//				// 迭代列，默认从0开始
//
//				TableCell td = tr.getCell(columnY);// 取得 元素 单元格
//				Paragraph para = td.getParagraph(0);
//				String s = para.text().replaceAll("", "");
//
//				TableCell tdB = tr.getCell(columnB);// 取得 备注 单元格
//				String sb = "";
//				for (int b = 0; b < tdB.numParagraphs(); b++) {
//					Paragraph parab = tdB.getParagraph(b);
//					sb += parab.text().replaceAll("", "");
//				}
//
//				TableCell tdl = tr.getCell(columnL);// 取得 类型 单元格
//				Paragraph paral = tdl.getParagraph(0);
//				String sl = paral.text();
//				if (sl.contains("[]")) {
//					if (first) {
//
//						String className = sl.substring(0, sl.indexOf("["));
//
//						m += "" + className + " " + className + "Bean=new "
//								+ className + "();\n";
//						objectname = className + "Bean";
//						m += "{\n";
//					} else {
//						m += "}\n";
//
//						String className = sl.substring(0, sl.indexOf("["));
//						m += "" + className + " " + className + "Bean=new "
//								+ className + "();\n";
//						objectname = className + "Bean";
//						m += "{\n";
//					}
//
//					first = false;
//				} else {
//					// String productCode;
//					m += "//" + sb + "\n";
//					m += objectname + "." + s + "=\"\";\n";
//				}
//
//			} // end for
//
//			m += "}\n";
//
//			m += "String strParam = JsonParser.object2Json(param);\n";
//			m += "HttpClientComponent httpClient = new HttpClientComponent(context);\n";
//			m += "InvokeResult result = httpClient.invokeNetMethod(url, strParam);\n";
//
//			m += "if (result.status == InvokeResult.RESULT_OK) {\n";
//			m += "	Gson gson = new Gson();\n";
//			m += javaName + "ResultBean bean = gson.fromJson(\n";
//			m += "			String.valueOf(result.returnObject),\n";
//			m += javaName + "ResultBean.class);\n";
//			m += "	netToDb(bean);\n";
//
//			m += "	return bean;\n";
//			m += "} else {\n";
//			m += "}\n";
//			m += "}\n";
//		} // end while
//
//		System.out.println(m);
//	}
//
//	public static void getResultBean(String result) {
//
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream(result);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			hwpf = new HWPFDocument(in);
//			range = hwpf.getRange();// 得到文档的读取范围
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String m = "private class " + javaName + "ResultBean {\n";
//
//		boolean first = true;
//		TableIterator it = new TableIterator(range);
//		int counttable = 0;
//		// 迭代文档中的表格
//
//		while (it.hasNext()) {
//			Table tb;
//			if (counttable == 0) {
//				tb = (Table) it.next();
//				counttable++;
//				continue;
//			} else if (counttable == 1) {
//				tb = (Table) it.next();
//				counttable++;
//			} else {
//
//				break;
//			}
//
//			// 迭代行，默认从0开始
//
//			for (int i = 1; i < tb.numRows(); i++) {
//				TableRow tr = tb.getRow(i);
//				// 迭代列，默认从0开始
//
//				TableCell td = tr.getCell(columnY);// 取得 元素 单元格
//				Paragraph para = td.getParagraph(0);
//				String s = para.text().replaceAll("", "");
//
//				TableCell tdB = tr.getCell(columnB);// 取得 备注 单元格
//				String sb = "";
//				for (int b = 0; b < tdB.numParagraphs(); b++) {
//					Paragraph parab = tdB.getParagraph(b);
//					sb += parab.text().replaceAll("", "");
//				}
//
//				TableCell tdl = tr.getCell(columnL);// 取得 类型 单元格
//				Paragraph paral = tdl.getParagraph(0);
//				String sl = paral.text();
//				if (sl.contains("[]")) {
//					if (first) {
//						m += "/**" + sb + "*/\n";
//						m += "public " + sl.replaceAll("", "") + " " + s
//								+ ";\n";
//						String className = sl.substring(0, sl.indexOf("["));
//						m += "public  class  " + className + "{\n";
//					} else {
//						m += "}\n";
//						m += "/**" + sb + "*/\n";
//						m += "public " + sl.replaceAll("", "") + " " + s
//								+ ";\n";
//						String className = sl.substring(0, sl.indexOf("["));
//						m += "public  class  " + className + "{\n";
//					}
//
//					first = false;
//				} else {
//					// String productCode;
//					m += "/**" + sb + "*/\n";
//					m += "public " + sl.replaceAll("", "") + " " + s + ";\n";
//				}
//
//			} // end for
//
//			m += "}\n";
//
//			m += "}\n";
//		} // end while
//
//		System.out.println(m);
//	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
