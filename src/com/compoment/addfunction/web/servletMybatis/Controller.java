package com.compoment.addfunction.web.servletMybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class Controller {

	public void controller(List<TableBean> tables,String interfaceName ,String interfaceCnName) {

		String servicename = "";
		String serviceCnName = "";
		String resultType = "";
		
		String queryCondition2 = "";
		String mainTableName = "";
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
				queryCondition2="reqMap";
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

		if (interfaceName == null || "".equals(interfaceName)) {
			interfaceName = servicename;
		}

	

		String m = "";
		m += "package com.company.controller;\n";
		
		m += "import java.io.IOException;\n";
		m += "import java.io.PrintWriter;\n";

		m += "import javax.servlet.RequestDispatcher;\n";
		m += "import javax.servlet.ServletException;\n";
		m += "import javax.servlet.http.HttpServlet;\n";
		m += "import javax.servlet.http.HttpServletRequest;\n";
		m += "import javax.servlet.http.HttpServletResponse;\n";

		m += "import org.apache.commons.fileupload.FileItem;\n";
		m += "import org.apache.commons.fileupload.FileItemFactory;\n";
		m += "import org.apache.commons.fileupload.disk.DiskFileItemFactory;\n";
		m += "import org.apache.commons.fileupload.servlet.ServletFileUpload;\n";

		m +="import net.sf.json.JSONArray;\n";
		m +="import org.slf4j.Logger;\n";
		m +="import org.slf4j.LoggerFactory;\n";
		

		m += "\n/**" + interfaceCnName + "*/\n";
		m += "public class "+interfaceName+"Servlet extends HttpServlet {\n";
		
		m += "    public void doGet(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws ServletException, IOException {\n";
		m += "        doPost(request, response);\n";
		m += "    }\n";

		m += "    public void doPost(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws ServletException, IOException {\n";
		m += "        //获取对应的请求参数\n";
		m += "        String method = request.getParameter(\"method\");\n";
		m += "        //根据请求参数去调用对应的方法。\n";
	
		m += "        if (\"query\".equals(method)) {\n";
		m += "        	query(request, response);\n";
		m += "        } \n";
		m += "        if (\"insert\".equals(method)) {\n";
		m += "        	insert(request, response);\n";
		m += "        } \n";
		m += "        if (\"update\".equals(method)) {\n";
		m += "        	update(request, response);\n";
		m += "        } \n";
		m += "        if (\"delete\".equals(method)) {\n";
		m += "        	delete(request, response);\n";
		m += "        } \n";
		m += "    }\n";

		
		m += "private " + interfaceName + "Service "
				+ StringUtil.firstCharToLower(interfaceName) + "Service=new "+interfaceName+"Service();\n";

		
	
		m += "	public void query(HttpServletRequest request, HttpServletResponse response) {\n";

	
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
		
		int i = 0;
		String n="";
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
			

			i++;
		}
		
		m += "List<"+mainTableName + "Bean> " + mainTableName.toLowerCase()
				+ "Beans=null;\n";
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.get("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";


	
		m += "      response.setCharacterEncoding(\"utf-8\");\n";
		m += "		response.setContentType(\"application/json\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		\n";
		m += "		JSONArray jsonArray = JSONArray.fromObject("+mainTableName.toLowerCase()+"Beans);\n";
		m += "		\n";
		m += "		  out.write(\"{\"returnCode\":\"00\",\"info\":\"成功。\",\"returnData\":\" + jsonArray.toString()+ \"}\");\n";
		m += "        out.flush();\n";
		m += "        out.close();\n";
		
		
		m += "	}\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

			} else if (tables.size() == 1) {

			
				m += "	public void insert(HttpServletRequest request, HttpServletResponse response) {\n";

			

				 n="";
				for (TableColumnBean column : table.columns) {
					

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
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m+="boolean isOk=true;\n";
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.insert(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m+="isOk=false;\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "      response.setCharacterEncoding(\"utf-8\");\n";
				m += "		response.setContentType(\"application/json\");\n";
				m += "		PrintWriter out = response.getWriter();\n";
				m += "		\n";
				m += "		  out.write(\"{\"returnCode\":\"00\",\"info\":\"新增成功。\",\"returnData\":\"\"}\");\n";
				m += "        out.flush();\n";
				m += "        out.close();\n";
				m += "	}\n";

			
				m += "	public void update(HttpServletRequest request, HttpServletResponse response) {\n";

		

				 n="";
				for (TableColumnBean column : table.columns) {
				
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
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.update(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "      response.setCharacterEncoding(\"utf-8\");\n";
				m += "		response.setContentType(\"application/json\");\n";
				m += "		PrintWriter out = response.getWriter();\n";
				m += "		\n";
				m += "		  out.write(\"{\"returnCode\":\"00\",\"info\":\"更新成功。\",\"returnData\":\"\"}\");\n";
				m += "        out.flush();\n";
				m += "        out.close();\n";
				m += "	}\n";

				
				
				
				
			
				m += "	public void delete(HttpServletRequest request, HttpServletResponse response) {\n";

			
				
				 n="";
				for (TableColumnBean column : table.columns) {
					if (column.key != null
							&& column.key.toLowerCase().contains("key")) {

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
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					}
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.delete(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "      response.setCharacterEncoding(\"utf-8\");\n";
				m += "		response.setContentType(\"application/json\");\n";
				m += "		PrintWriter out = response.getWriter();\n";
				m += "		\n";
				m += "		  out.write(\"{\"returnCode\":\"00\",\"info\":\"删除成功。\",\"returnData\":\"\"}\");\n";
				m += "        out.flush();\n";
				m += "        out.close();\n";
				m += "	}\n";

			}
		}

		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Servlet", "java", m);
	}
	
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
