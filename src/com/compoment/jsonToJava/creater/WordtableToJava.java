package com.compoment.jsonToJava.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

import com.google.gson.Gson;



public class WordtableToJava {
	static int columnY = 0;
	static int columnL = 0;
	static int columnB = 0;
	static HWPFDocument hwpf;
	String sourceFile;
	static Range range;
	static String javaName = "";
    static String path="";
	public static void main(String[] args) throws Exception {
		path="gbss-mobile-order/order/releaseStock";
		javaName =path.substring( path.lastIndexOf("/")+1);
		
		javaName= javaName.substring(0, 1).toUpperCase() + javaName.substring(1);
		WordtableToJava wordtable = new WordtableToJava();

		String classDir = wordtable.getClass().getResource("/").getPath();
		String docpath = null;

		docpath = classDir + "com/compoment/jsonToJava/creater/" + "V2.0.0.doc";
		poiWordTableReplace(docpath);
	}

	public static void poiWordTableReplace(String sourceFile) throws Exception {

		FileInputStream in = new FileInputStream(sourceFile);
		try {
			hwpf = new HWPFDocument(in);
			range = hwpf.getRange();// 得到文档的读取范围
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getColumnIndex();
		getRequestBean();
		getRequest();
		 getResult();

	}

	public static void getColumnIndex() {
		TableIterator it = new TableIterator(range);
		// 迭代文档中的表格
		while (it.hasNext()) {
			Table tb = (Table) it.next();
			// 迭代行，默认从0开始

			TableRow tr = tb.getRow(0);
			// 迭代列，默认从0开始
			for (int j = 0; j < tr.numCells(); j++) {
				TableCell td = tr.getCell(j);// 取得单元格
				// 取得单元格的内容

				Paragraph para = td.getParagraph(0);

				String s = para.text();

				if (s.contains("元素")) {
					columnY = j;
				} else if (s.contains("类型")) {
					columnL = j;
				} else if (s.contains("备注")) {
					columnB = j;
				}

				// end for
			} // end for
			// end for
		} // end while

	}

	public static void getRequestBean() {

		String m = "private class " + javaName
				+ "RequestParam extends NetEntity{\n";

		boolean first = true;
		TableIterator it = new TableIterator(range);
		int counttable = 0;
		// 迭代文档中的表格
		while (it.hasNext()) {
			Table tb;
			if (counttable == 0) {
				tb = (Table) it.next();
				counttable++;
			} else {
				break;
			}

			// 迭代行，默认从0开始
			for (int i = 2; i < tb.numRows(); i++) {
				TableRow tr = tb.getRow(i);
				// 迭代列，默认从0开始

				TableCell td = tr.getCell(columnY);// 取得 元素 单元格
				Paragraph para = td.getParagraph(0);
				String s = para.text().replaceAll("", "");
				
				TableCell tdB = tr.getCell(columnB);// 取得 备注 单元格
				String sb="";
				for(int b=0;b<tdB.numParagraphs();b++)
				{
				Paragraph parab= tdB.getParagraph(b);
				 sb+= parab.text().replaceAll("", "");
				}

				TableCell tdl = tr.getCell(columnL);// 取得 类型 单元格
				Paragraph paral = tdl.getParagraph(0);
				String sl = paral.text();
				if (sl.contains("[]")) {
					if (first) {
						m+="/**"+sb+"*/\n";
						m += "public "+sl.replaceAll("", "") + " " + s + ";\n";
						String className = sl.substring(0, sl.indexOf("["));
						m += "public  class  " + className + "{\n";
					} else {
						m += "}\n";
						m+="/**"+sb+"*/\n";
						m += "public "+sl.replaceAll("", "") + " " + s + ";\n";
						String className = sl.substring(0, sl.indexOf("["));
						m += "public  class  " + className + "{\n";
					}

					first = false;
				} else {
					// String productCode;
					m+="/**"+sb+"*/\n";
					m += "public "+sl.replaceAll("", "") + " " + s + ";\n";
				}

			} // end for

			m += "}\n";
			m += "}\n";
		} // end while

		System.out.println(m);
	}
	
	
	public static void getRequest() {
		
		String objectname="requestParam";
		String m ="";
		m+= "public void " + javaName
				+ "Request() {\n";

		m+="String url = \"https://gbssdev.infinitus.com.cn/gbss-mobile/front/"+path+"?json=\";\n";
		 m+= "" + javaName
				+ "RequestParam "+objectname+"=new "+javaName+"RequestParam();\n";
		 m+=objectname+".commonParam = UECCommonUtil.buildCommonParam(context);\n";
		boolean first = true;
		TableIterator it = new TableIterator(range);
		int counttable = 0;
		// 迭代文档中的表格
		while (it.hasNext()) {
			Table tb;
			if (counttable == 0) {
				tb = (Table) it.next();
				counttable++;
			} else {
				break;
			}

			// 迭代行，默认从0开始
		
			for (int i = 2; i < tb.numRows(); i++) {
				TableRow tr = tb.getRow(i);
				// 迭代列，默认从0开始

				TableCell td = tr.getCell(columnY);// 取得 元素 单元格
				Paragraph para = td.getParagraph(0);
				String s = para.text().replaceAll("", "");
				
				TableCell tdB = tr.getCell(columnB);// 取得 备注 单元格
				String sb="";
				for(int b=0;b<tdB.numParagraphs();b++)
				{
				Paragraph parab= tdB.getParagraph(b);
				 sb+= parab.text().replaceAll("", "");
				}

				TableCell tdl = tr.getCell(columnL);// 取得 类型 单元格
				Paragraph paral = tdl.getParagraph(0);
				String sl = paral.text();
				if (sl.contains("[]")) {
					if (first) {
						
						String className = sl.substring(0, sl.indexOf("["));
						
						 m+= "" + className
									+ " "+className+"Bean=new "+className+"();\n";
						 objectname=className+"Bean";
						m += "{\n";
					} else {
						m += "}\n";
						
						String className = sl.substring(0, sl.indexOf("["));
						 m+= "" + className
									+ " "+className+"Bean=new "+className+"();\n";
						 objectname=className+"Bean";
						m += "{\n";
					}

					first = false;
				} else {
					// String productCode;
					m+="//"+sb+"\n";
					m +=  objectname+"." + s + "=\"\";\n";
				}

			} // end for

			m += "}\n";
			
			m+="String strParam = JsonParser.object2Json(param);\n";
			m+="HttpClientComponent httpClient = new HttpClientComponent(context);\n";
			m+="InvokeResult result = httpClient.invokeNetMethod(url, strParam);\n";

			m+="if (result.status == InvokeResult.RESULT_OK) {\n";
			m+="	Gson gson = new Gson();\n";
			m+=javaName+"ResultBean bean = gson.fromJson(\n";
			m+="			String.valueOf(result.returnObject),\n";
			m+=javaName+"ResultBean.class);\n";
			m+="	netToDb(bean);\n";

			m+="	return bean;\n";
			m+="} else {\n";
			m+="}\n";
			m += "}\n";
		} // end while

		System.out.println(m);
	}

	public static void getResult() {

		String m = "private class " + javaName + "ResultBean {\n";

		boolean first = true;
		TableIterator it = new TableIterator(range);
		int counttable = 0;
		// 迭代文档中的表格
		
		while (it.hasNext()) {
			Table tb;
			if (counttable == 0) {
				tb = (Table) it.next();
				counttable++;
				continue;
			}else if(counttable == 1)
			{
				tb = (Table) it.next();
				counttable++;
			}
			else {
			
				break;
			}

			// 迭代行，默认从0开始
		
			for (int i = 1; i < tb.numRows(); i++) {
				TableRow tr = tb.getRow(i);
				// 迭代列，默认从0开始

				TableCell td = tr.getCell(columnY);// 取得 元素 单元格
				Paragraph para = td.getParagraph(0);
				String s = para.text().replaceAll("", "");

				TableCell tdB = tr.getCell(columnB);// 取得 备注 单元格
				String sb="";
				for(int b=0;b<tdB.numParagraphs();b++)
				{
				Paragraph parab= tdB.getParagraph(b);
				 sb+= parab.text().replaceAll("", "");
				}
				
				TableCell tdl = tr.getCell(columnL);// 取得 类型 单元格
				Paragraph paral = tdl.getParagraph(0);
				String sl = paral.text();
				if (sl.contains("[]")) {
					if (first) {
						m+="/**"+sb+"*/\n";
						m += "public "+sl.replaceAll("", "") + " " + s + ";\n";
						String className = sl.substring(0, sl.indexOf("["));
						m += "public  class  " + className + "{\n";
					} else {
						m += "}\n";
						m+="/**"+sb+"*/\n";
						m += "public "+sl.replaceAll("", "") + " " + s + ";\n";
						String className = sl.substring(0, sl.indexOf("["));
						m += "public  class  " + className + "{\n";
					}

					first = false;
				} else {
					// String productCode;
					m+="/**"+sb+"*/\n";
					m += "public "+sl.replaceAll("", "") + " " + s + ";\n";
				}

			} // end for

			m += "}\n";
			

			m += "}\n";
		} // end while

		System.out.println(m);
	}

}
