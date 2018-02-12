package com.compoment.addfunction.webmanage.jspStruct2Mybatis;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class Struct2ActionForWebManage {

	List<TableBean> tables;
	public Struct2ActionForWebManage(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		tables=changeToTableBeans(interfaceBeans);
		
		Mybatis mybatis=new Mybatis("","", tables);
		
		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			action(interfaceBean, "Respond","");
		}
	}
	public void action(InterfaceBean interfaceBean ,String type,String interfaceName)
	{
		
		
		
		
	     //list()

		String urlKeyString2="";
		String listInKeyString="";
		String appendString="";
		String nextPageKeyString="";
		
		
		String keyValue2="";
		
		//toUpdate()
		String toUpdateInKeyString="";
	
		String mainkey="";
		
		//doUpdate
		String doUpdateKeyStirng="";
		
		//file
		String filebean="";
		int fileCount=0;
		String fileAdd="";
		String fileUpdate="";
		String doAddMainKeyAutoCreateWhere="";
		String doAddMainKeyAutoCreateValue="";
		
		
		//select
		String selectList="";
		
		
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
						
						//list()
						listInKeyString+="	String "+row.enName.toLowerCase()+" = StrutsParamUtils.getPraramValue(\""+row.enName.toLowerCase()+"\", \"\");\n";
					
						listInKeyString+="		if(StringUtils.isBlank("+row.enName.toLowerCase()+")||\"0\".equals("+row.enName.toLowerCase()+")){\n";
						listInKeyString+="			//return;\n";
						listInKeyString+="		}else{\n";
						listInKeyString+="where+=\""+row.enName.toLowerCase()+"=? And \";\n"; 
						listInKeyString+="where2+=\""+row.enName.toLowerCase()+"=:"+row.enName.toLowerCase()+" And \";\n";
						if(row.type.toLowerCase().contains("int"))
						{
							listInKeyString+="argslist.add(Integer.valueOf("+row.enName.toLowerCase()+"));\n";
							listInKeyString+="argsMap.put(\""+row.enName.toLowerCase()+"\",Integer.valueOf( "+row.enName.toLowerCase()+"));\n";
						}else
						{
						listInKeyString+="argslist.add("+row.enName.toLowerCase()+");\n";
						listInKeyString+="argsMap.put(\""+row.enName.toLowerCase()+"\", "+row.enName.toLowerCase()+");\n";
						}
						listInKeyString+="}\n";
						
						
						
						urlKeyString2+=""+row.enName.toLowerCase()+"=:"+row.enName.toLowerCase()+" And ";
						doAddMainKeyAutoCreateWhere+=row.enName.toLowerCase()+"=? And ";
						
						doAddMainKeyAutoCreateValue+="argslist.add(entity.get"+this.firstCharUpperCase(row.enName.toLowerCase())+");\n";
						
						keyValue2+="argsMap.put(\""+row.enName.toLowerCase()+"\","+row.enName.toLowerCase()+");\n";
					
						
						
						appendString+="entity.set"+firstCharUpperCase(row.enName.toLowerCase())+"("+row.enName.toLowerCase()+");\n";
						
						nextPageKeyString+=row.enName.toLowerCase()+"=\"+"+row.enName.toLowerCase()+"+\"%26";
						
						
						
						//toUpdate()
						if(row.type.toLowerCase().contains("int"))
						{
							
							toUpdateInKeyString+="int "+row.enName.toLowerCase()+" =Integer.valueOf( StrutsParamUtils.getPraramValue(\""+row.enName.toLowerCase()+"\", \"0\"));\n";
							
						}else
						{
						toUpdateInKeyString+="String "+row.enName.toLowerCase()+" = StrutsParamUtils.getPraramValue(\""+row.enName.toLowerCase()+"\", \"\");\n";
						}
						toUpdateInKeyString+="request.setAttribute(\""+row.enName.toLowerCase()+"\", "+row.enName.toLowerCase()+");\n";
						
						
						if(row.remarks.toLowerCase().equals("mainkey"))
						{
							mainkey=row.enName.toLowerCase();
							
							String temp=row.enName.toLowerCase()+"=? And ";
							doAddMainKeyAutoCreateWhere=doAddMainKeyAutoCreateWhere.replace(temp, "");
					
							String temp2="argslist.add(entity.get"+this.firstCharUpperCase(row.enName.toLowerCase())+");\n";
							doAddMainKeyAutoCreateValue=doAddMainKeyAutoCreateValue.replace(temp2, "");
						}
						
						
						//doUpdate()
						//doUpdateKeyStirng+=" request.setAttribute(\""+row.enName.toLowerCase()+"\", entity.get"+firstCharUpperCase(row.enName.toLowerCase())+"());\n";
					
						
						//doAdd()
						
					
					}
					
					
					
					if(row.type.toLowerCase().contains("file"))
					{
						fileCount++;
						filebean+="    private File file"+fileCount+";//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的\n";
						filebean+="    private String file"+fileCount+"FileName;//   上传输入域FileName  文件名\n";
						filebean+="    private String file"+fileCount+"ContentType;// 上传文件的MIME类型\n";
						
						
						filebean+="	public File getFile"+fileCount+"() {\n";
						filebean+="		return file"+fileCount+";\n";
						filebean+="	}\n";
						filebean+="	public void setFile"+fileCount+"(File file"+fileCount+") {\n";
						filebean+="		this.file"+fileCount+" = file"+fileCount+";\n";
						filebean+="	}\n";
						filebean+="	public String getFile"+fileCount+"FileName() {\n";
						filebean+="		return file"+fileCount+"FileName;\n";
						filebean+="	}\n";
						filebean+="	public void setFile"+fileCount+"FileName(String file"+fileCount+"FileName) {\n";
						filebean+="		this.file"+fileCount+"FileName = file"+fileCount+"FileName;\n";
						filebean+="	}\n";
						filebean+="	public String getFile"+fileCount+"ContentType() {\n";
						filebean+="		return file"+fileCount+"ContentType;\n";
						filebean+="	}\n";
						filebean+="	public void setFile"+fileCount+"ContentType(String file"+fileCount+"ContentType) {\n";
						filebean+="		this.file"+fileCount+"ContentType = file"+fileCount+"ContentType;\n";
						filebean+="	}\n";
						
						
						
						fileUpdate+="		  if(file"+fileCount+" != null){\n";
						fileUpdate+="		 ServletContext sc = ServletActionContext.getServletContext();\n";
						fileUpdate+="		       	 String activityBannerPath=CommonFunction.readDefVal(\"activityBannerPath\");\n";
						fileUpdate+="		            String storePath = sc.getRealPath(activityBannerPath+entity.getActivity_code());\n";
						fileUpdate+="		            FileUtils.copyFile(file"+fileCount+", new File(storePath,\""+row.enName.toLowerCase().replaceAll("", "")+".jpg\"));\n";
						fileUpdate+="		            entity.set"+this.firstCharUpperCase(row.enName.toLowerCase())+"(activityBannerPath+entity.getActivity_code()+\"/"+row.enName.toLowerCase().replaceAll("", "")+".jpg\");\n";
						fileUpdate+="		       }\n";
						
					}
					
					if(row.type.toLowerCase().contains("select"))
					{
					
						
						selectList+="\n//"+row.cnName+"\n";
						selectList+="List "+row.enName.toLowerCase()+"list=new ArrayList();\n";
						selectList+="//for(int i=0;i<list.size();i++)\n";
						selectList+="//{\n";
										
						
						String re1=".*?";	// Non-greedy match on filler
					    String re2="(\\d+)";	// Integer Number 1
					    String re3=".*?";	// Non-greedy match on filler
					    String re4="(:|：)";	// Any Single Character 1
					    String re5=".*?";	// Non-greedy match on filler
					    String re6="([\u4e00-\u9fa5]+)";	// Any Single Word Character (Not Whitespace) 1

					    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
					    Matcher m = p.matcher(row.cnName);
					    while (m.find())
					    {
					        String int1=m.group(1);
					        String c1=m.group(2);
					        String w1=m.group(3);
					      
					        selectList+="	"+row.enName.toLowerCase()+"list.add(\""+int1.toString()+"\"+\"-\"+\""+w1.toString()+"\");\n";
					    }
						
					  
						selectList+="//}\n";
						selectList+="request.setAttribute(\""+row.enName.toLowerCase()+"SelectList\","+row.enName.toLowerCase()+"list);\n";
					}
				}
			}
			}
	
		if(urlKeyString2.lastIndexOf("And")!=-1)
		urlKeyString2=urlKeyString2.substring(0, urlKeyString2.lastIndexOf("And"));
		if(doAddMainKeyAutoCreateWhere.lastIndexOf("And")!=-1)
		doAddMainKeyAutoCreateWhere=doAddMainKeyAutoCreateWhere.substring(0, doAddMainKeyAutoCreateWhere.lastIndexOf("And"));
		
		
		
		
		
		
		String m="";
	
		

		m+="import java.io.File;\n";
		m+="import java.io.IOException;\n";
		m+="import java.util.HashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";
		m+="import java.util.ArrayList;\n";
		m+="import javax.annotation.Resource;\n";
		m+="import javax.servlet.ServletContext;\n";
		m+="import javax.servlet.http.HttpServletRequest;\n";

		m+="import net.sf.json.JSONObject;\n";

		m+="import org.apache.commons.io.FileUtils;\n";
		m+="import org.apache.commons.lang.StringUtils;\n";
		m+="import org.apache.struts2.ServletActionContext;\n";
		m+="import org.apache.struts2.convention.annotation.Action;\n";
		m+="import org.apache.struts2.convention.annotation.Namespace;\n";
		m+="import org.apache.struts2.convention.annotation.ParentPackage;\n";
		m+="import org.apache.struts2.convention.annotation.Result;\n";
	

		m+="import com.tools.CommonFunction;\n";
		
		
		
		m+="import com.tools.PaginationUtil;\n";
		m+="import com.tools.StrutsParamUtils;\n";
		m+="import com.tools.hibernate.ObjectDao;\n";
		m+="//"+interfaceBean.title+"\n";
		m+="//@SuppressWarnings(\"unchecked\")\n";
		m+="//@Namespace(value = \"/"+interfaceBean.projectName.toLowerCase()+"\")\n";
		m+="@Action(value = \""+interfaceBean.enName+"Action\" ,results = { \n";
		m+="		@Result(name = \""+interfaceBean.enName.toLowerCase()+"\", location = \"/WEB-INF/"+interfaceBean.projectName.toLowerCase()+"/"+interfaceBean.enName.toLowerCase()+".jsp\"),\n";
		m+="		@Result(name = \""+interfaceBean.enName.toLowerCase()+"Setting\", location = \"/WEB-INF/"+interfaceBean.projectName.toLowerCase()+"/"+interfaceBean.enName.toLowerCase()+"Setting.jsp\"),\n";
		m+="		@Result(name = \""+interfaceBean.enName.toLowerCase()+"Add\", location = \"/WEB-INF/"+interfaceBean.projectName.toLowerCase()+"/"+interfaceBean.enName.toLowerCase()+"Add.jsp\"),\n";
		m+="		\n";
		m+="	})\n";
		m+="public class "+interfaceBean.enName+"Action {\n";
		m+="	\n";
	
		
		
		m+="    private "+interfaceBean.enName+"Bean entity;\n";
	
	
		
		m+=filebean;
		
		m+="	public "+interfaceBean.enName+"Bean getEntity() {\n";
		m+="		return entity;\n";
		m+="	}\n";
		m+="	public void setEntity("+interfaceBean.enName+"Bean  "+interfaceBean.enName.toLowerCase()+") {\n";
		m+="		this.entity = "+interfaceBean.enName.toLowerCase()+";\n";
		m+="	}\n";
		
		
		m+="	\n";
		
		m+="	public String index(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=toUpdateInKeyString;
		m+="		return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";

		
		String servicename = "";
		String serviceCnName = "";
		String resultType = "";
		String mainTableName = "";
		String queryCondition2="";
		for (TableBean table : tables) {
			servicename += table.tableEnName + "_";
			serviceCnName += table.tableCnName + "_";
			if ( tables.size() > 1) {
			
				resultType = interfaceName + "Bean";
				mainTableName = interfaceName;
				queryCondition2="paraMap";
			}else
			{
				
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				queryCondition2="paraMap";
			
			}
		}
		
		
		m+="	//"+interfaceBean.title+"列表\n";
		m+="	public void list(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		
	    m+="		String pageNo = request.getParameter(\"pageNo\");\n";
		m+="		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m+="			pageNo = \"1\";\n";
		m+="			request.setAttribute(\"pageNo\", pageNo);\n";
		m+="		}\n";
		
		m+="		String pageSize = request.getParameter(\"pageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"pageSize\", pageSize);\n";
		m+="		}\n";
		
		m+="Map paraMap=new HashMap();\n";
		m+="paraMap.put(\"currIndex\", (pageNo-1)*pageSize);\n";
		m+="paraMap.put(\"pageSize\", pageSize);\n";
		
	
		m+=listInKeyString;
		
		m += "List<"+mainTableName + "Bean> " + mainTableName.toLowerCase()
				+ "Beans=null;\n";
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.get("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		

		m += "int count=0;\n";
m += "try {\n";
m += "count="
		+ StringUtil.firstCharToLower(interfaceName) + "Service.getCount("
		+ queryCondition2 + ");\n";
m += "} catch (Exception e) {\n";
m += "	e.printStackTrace();\n";
m += "}\n";
		
		

		
	
		
		m+="		String pageString = PaginationUtil.getPaginationHtml(\n";
		m+="				Integer.valueOf(count), Integer.valueOf(pageSize),\n";
		m+="				Integer.valueOf(pageNo), Integer.valueOf(2),\n";
		m+="				Integer.valueOf(5),\n";
		m+="				\"javascript:getAll('"+interfaceBean.enName+"Action!list?"+nextPageKeyString+"pageNo=\",true);\n";
		m+="		pageString = pageString.replace(\".html\", \"\");\n";
		m+="		JSONObject jsonObject = new JSONObject();\n";
		m+="		jsonObject.put(\"list\", list);\n";
		m+="		jsonObject.put(\"pageString\", pageString);\n";
		m+="		jsonObject.put(\"count\", count);\n";
		m+="		try{\n";
		m+="			StrutsParamUtils.getResponse().setCharacterEncoding(\"UTF-8\");\n";
		m+="			StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());\n";
		m+="		}catch(Exception e){\n";
		m+="			e.printStackTrace();\n";
		m+="		}\n";
		m+="	}\n";
		m+="	\n";
		m+="	\n";
		
		
		m+="//跳到修改页\n";
		m+="	public String toUpdate() {\n";
		
		
		
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		
		m+="//选择器数据\n";
		m+=selectList;
		
		m+="//页面数据\n";
		m+=toUpdateInKeyString;
		
		

	
		
		m += "List<"+mainTableName + "Bean> " + mainTableName.toLowerCase()
				+ "Beans=null;\n";
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.get("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		
		m+="		if (list!=null && list.size()==1) {\n";
		
	   
	    m+="				request.setAttribute(\"entity\", ("+interfaceBean.enName+"Entity) list.get(0));\n";
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Setting\";\n";
		m+="		}else{\n";
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Add\";\n";
		m+="		}\n";
		m+="		\n";
		m+="	}\n";
		m+="	public String doUpdate() throws IOException {\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=fileUpdate;
		
		List<TableColumnBean> queryConditionColumns=getQueryConditionColumns(tables);
		for (TableColumnBean column : queryConditionColumns) {

			if (column.type.toLowerCase().contains("int")) {
				m += "		int " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
						+ column.columnEnName + "\").toString());\n";
				
			}else
			{
				m += "		String " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? null : request.getParameter(\""
						+ column.columnEnName + "\").toString();\n";
			}
			
			
			
		
				m +="paraMap.put(\""+ column.columnEnName + "\","+column.columnEnName+");\n";
		
		}
	
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.update("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
	
		//m+=doUpdateKeyStirng;
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";
		
		
		
		m+="//跳到新增页\n";
		m+="	public String toAdd() {\n";
		
		
		
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="//选择器数据\n";
		m+=selectList;
		
		m+="//页面数据\n";
		
		m+=toUpdateInKeyString;
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Add\";\n";
	
		m+="		\n";
		m+="	}\n";
		
		m+="	\n";
		m+="	public String doAdd()  throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=fileUpdate;
		


		
		for (TableColumnBean column : queryConditionColumns) {

			if (column.type.toLowerCase().contains("int")) {
				m += "		int " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
						+ column.columnEnName + "\").toString());\n";
				
			}else
			{
				m += "		String " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? null : request.getParameter(\""
						+ column.columnEnName + "\").toString();\n";
			}
			
			
			
		
				m +="paraMap.put(\""+ column.columnEnName + "\","+column.columnEnName+");\n";
		
		}
	
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.insert("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		m+="List argslist = new ArrayList();\n";
		m+=doAddMainKeyAutoCreateValue;



		
		//m+=doUpdateKeyStirng;
		m+="		return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";
		
		
		m+="	\n";
		m+="	public void doDelete() throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=toUpdateInKeyString;
		
		for (TableColumnBean column : queryConditionColumns) {

			if (column.type.toLowerCase().contains("int")) {
				m += "		int " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
						+ column.columnEnName + "\").toString());\n";
				
			}else
			{
				m += "		String " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? null : request.getParameter(\""
						+ column.columnEnName + "\").toString();\n";
			}
			
			
			
		
				m +="paraMap.put(\""+ column.columnEnName + "\","+column.columnEnName+");\n";
		
		}
	
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.delete("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		
		
		m+="		StrutsParamUtils.getResponse().getWriter().write(\"success\");\n";
		m+="		return ;\n";
		m+="	}\n";
		m+="	\n";
		m+="}\n";

	

	
	
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager",interfaceBean.enName+"Action", "java", m);
		
		System.out.println(m);
	}
	

	  
	  /** 首字母大写 */
		public static String firstCharUpperCase(String s) {
			if (s.length() > 0) {
				String firstchar = String.valueOf(s.charAt(0)).toUpperCase();
				s = s.substring(1);
				s = firstchar + s;
				return s;
			} else {
				return null;
			}

		}
		
		
		
		
		public List changeToTableBeans(List<InterfaceBean> interfaceBeans) {
			List tables = new ArrayList();

	
			for (InterfaceBean interfaceBean : interfaceBeans) {
				// 数据表
				TableBean tableBean = new TableBean();

				tableBean.tableCnName = interfaceBean.title;// 表中文名
				tableBean.tableEnName = interfaceBean.enName;// 表英文名
				tableBean.id = interfaceBean.id;// 表编号
				tableBean.columns = new ArrayList();// 列数组

				List<Group> groups = interfaceBean.respondGroups;
				for (Group group : groups) {
					String groupname = group.name;
					if (groupname.equals("CommonGroup")) {
						int rowCount = 0;

						Collections.sort(group.rows, rowDate);
						for (Row row : group.rows) {
							
							TableColumnBean tableColumnBean = new TableColumnBean();

							tableColumnBean.setColumnCnName(row.cnName);
						
							tableColumnBean.setColumnEnName(row.enName);

							tableColumnBean.setKey(row.remarks);
		
							tableColumnBean.setType(row.type);
						

							List<Integer> widths = new ArrayList();

							tableBean.columns.add(tableColumnBean);
							Collections
									.sort(tableBean.columns, tableColumnBeanDate);

							rowCount++;
						}
					}
				}

				if (tableBean.columns != null && tableBean.columns.size() > 0) {// （最后一列）
					TableColumnBean lastColumnBean = tableBean.columns
							.get(tableBean.columns.size() - 1);
					tableBean.x1 = lastColumnBean.x1;
					tableBean.y1 = lastColumnBean.y1;
				} else {
					tableBean.x1 = 0;
					tableBean.y1 = 0;
				}

				tables.add(tableBean);
				Collections.sort(tables, tableBeanDate);
			}

		

			return tables;
		}
		
		
		Comparator<Row> rowDate = new Comparator<Row>() {
			public int compare(Row s1, Row s2) {
				// 按日期排
				if (s1.time != s2.time) {
					return (int) (s1.time - s2.time);
				}
				return 0;
			}
		};

		Comparator<TableBean> tableBeanDate = new Comparator<TableBean>() {
			public int compare(TableBean s1, TableBean s2) {
				// 按日期排
				if (s1.time != s2.time) {
					return (int) (s1.time - s2.time);
				}
				return 0;
			}
		};

		Comparator<TableColumnBean> tableColumnBeanDate = new Comparator<TableColumnBean>() {
			public int compare(TableColumnBean s1, TableColumnBean s2) {
				// 按日期排
				if (s1.time != s2.time) {
					return (int) (s1.time - s2.time);
				}
				return 0;
			}
		};
		
		
		public List<TableColumnBean> getQueryConditionColumns(List<TableBean> tables)
		{
		
			List<TableColumnBean> queryConditionColumns=new ArrayList();
		
			for (TableBean table : tables) {

				for (TableColumnBean column : table.columns) {

					if ("right".equals(column.rightClickSelected)) {
						String tablename=StringUtil
								.tableName(column.belongWhichTable.tableEnName);
						String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

						queryConditionColumns.add(column);
					}

				}
			}
			return queryConditionColumns;
		}
		
		public List<TableColumnBean> getResultColumns(List<TableBean> tables)
		{
		
			
			List<TableColumnBean> resultColumns=new ArrayList();
			for (TableBean table : tables) {

				for (TableColumnBean column : table.columns) {

					if ("left".equals(column.leftClickSelected)) {
						String tablename=StringUtil
								.tableName(column.belongWhichTable.tableEnName);
						String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);
						
						resultColumns.add(column);

					}

					
				}
			}
			return resultColumns;
		}
}





