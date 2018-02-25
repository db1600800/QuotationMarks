package com.compoment.addfunction.webmanage.jspServletMybatis;

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
import com.compoment.addfunction.webmanage.jspStruct2Mybatis.Mybatis;
import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;


public class LocalApplicationTest {

	List<TableBean> tables;

	public LocalApplicationTest(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		tables = changeToTableBeans(interfaceBeans);

		Mybatis mybatis = new Mybatis("", "", tables);

		for (InterfaceBean interfaceBean : interfaceBeans) {

			action(interfaceBean, "Respond", interfaceBean.enName);
		}
	}

	public void action(InterfaceBean interfaceBean, String type, String interfaceName) {
	
		// file
		String filebean = "";
		int fileCount = 0;

		String fileUpdate = "";

		// select
		String selectList = "";

		List<Group> groups = interfaceBean.respondGroups;
		

		String m = "";

		m += "import java.io.File;\n";
		m += "import java.io.IOException;\n";
		m += "import java.util.HashMap;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		m += "import java.util.ArrayList;\n";
		m += "import javax.annotation.Resource;\n";
		m += "import javax.servlet.ServletContext;\n";
		m += "import javax.servlet.http.HttpServletRequest;\n";

		m += "import net.sf.json.JSONObject;\n";

		m += "import org.apache.commons.io.FileUtils;\n";
		m += "import org.apache.commons.lang.StringUtils;\n";
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

		m += "import com.tools.CommonFunction;\n";

		m += "import com.tools.PaginationUtil;\n";

		m += "//" + interfaceBean.title + "\n";
		
		
		m += "public class " + interfaceBean.enName + "LocalApplicationTest   {\n";
		m += "	\n";
		

		m+="public static void main(String[] args) {\n";
		m+=interfaceBean.enName + "LocalApplicationTest test=new "+interfaceBean.enName + "LocalApplicationTest();\n";
		m+="test.doPost(null,null);\n";
		m+="}\n";
		
		

	

		m += "    public void doPost(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws  IOException {\n";
	
		

		m += "        	doAdd(request, response);\n";
		m += "        	list(request, response);\n";
		
		
		
		m += "        	doUpdate(request, response);\n";
		m += "        	list(request, response);\n";
		
		
		

		m += "        	doDelete(request, response);\n";
		m += "        	list(request, response);\n";
		
		m += "    }\n";
		

		
		m += "	//" + interfaceBean.title + "列表\n";
		m += "	public void list(HttpServletRequest request, HttpServletResponse response){\n";
	


		m += "Map paraMap=new HashMap();\n";
		m += "paraMap.put(\"currIndex\", Integer.valueOf(\"1\") );\n";
		m += "paraMap.put(\"pageSize\", Integer.valueOf(\"10\"));\n";

		String listInKeyString = "";
		String nextPageKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {
					}

					// list()
					

					if (row.type.toLowerCase().contains("int")) {

						listInKeyString += "paraMap.put(\"" + row.enName.toLowerCase() + "\",Integer.valueOf( "
								+ row.enName.toLowerCase() + "));\n";
					} else {

						listInKeyString += "paraMap.put(\"" + row.enName.toLowerCase() + "\", "
								+ row.enName.toLowerCase() + ");\n";
					}
				

					
				}
			}
		}

		m += listInKeyString;

		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "List<" + interfaceName + "Bean> " + interfaceName.toLowerCase() + "Beans=null;\n";

		m += "try {\n";
		m += interfaceName.toLowerCase() + "Beans=" + StringUtil.firstCharToLower(interfaceName)
				+ "Service.get(paraMap);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n\n";

		m += "int count=0;\n";
		m += "try {\n";
		m += "count=" + StringUtil.firstCharToLower(interfaceName) + "Service.getCount(paraMap);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n\n";

		
		m += "	}\n";
		m += "	\n";
		m += "	\n";
		
		
		
		
		
		
		

	
		
		m += "	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {\n";

		m += fileUpdate;

		m += interfaceName + "Bean " + StringUtil.firstCharToLower(interfaceName) + "Bean=new " + interfaceName
				+ "Bean();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
						// list()
					

						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"(Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"("+ row.enName.toLowerCase() + ");\n";
						}
						
					

				}
			}
		}

		m += listInKeyString;
		
		
		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "try {\n";
		m += StringUtil.firstCharToLower(interfaceName) + "Service.update("+StringUtil.firstCharToLower(interfaceName) + "Bean);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		
	
		m += "	}\n";

		
		
		
		
		
		
		
		
		m += "	\n";
		m += "	public void doAdd(HttpServletRequest request, HttpServletResponse response)  throws IOException{\n";
	
		m += fileUpdate;


		m += interfaceName + "Bean " + StringUtil.firstCharToLower(interfaceName) + "Bean=new " + interfaceName
				+ "Bean();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
				
						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"(Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"("+ row.enName.toLowerCase() + ");\n";
						}
						
					

				}
			}
		}

		m += listInKeyString;

		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "try {\n";
		m += StringUtil.firstCharToLower(interfaceName) + "Service.insert("+StringUtil.firstCharToLower(interfaceName) + "Bean);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";

		

		m += "	}\n";

		
		
		
		
		
		
		
		
		m += "	\n";
		m += "	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{\n";
		

		m += interfaceName + "Bean " + StringUtil.firstCharToLower(interfaceName) + "Bean=new " + interfaceName
				+ "Bean();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {

						// list()
				
						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"(Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+ StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"("+ row.enName.toLowerCase() + ");\n";
						}
						
					}

				}
			}
		}

		m += listInKeyString;
		
		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "try {\n";
		m += StringUtil.firstCharToLower(interfaceName) + "Service.delete("+StringUtil.firstCharToLower(interfaceName) + "Bean);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";


		

		m += "	}\n";
		m += "	\n";
		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", interfaceBean.enName + "LocalApplicationTest", "java",
				m);

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
						Collections.sort(tableBean.columns, tableColumnBeanDate);

						rowCount++;
					}
				}
			}

			if (tableBean.columns != null && tableBean.columns.size() > 0) {// （最后一列）
				TableColumnBean lastColumnBean = tableBean.columns.get(tableBean.columns.size() - 1);
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

	public List<TableColumnBean> getQueryConditionColumns(List<TableBean> tables) {

		List<TableColumnBean> queryConditionColumns = new ArrayList();

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("right".equals(column.rightClickSelected)) {
					String tablename = StringUtil.tableName(column.belongWhichTable.tableEnName);
					String shortTableName = tablename.substring(tablename.lastIndexOf("_") + 1);

					queryConditionColumns.add(column);
				}

			}
		}
		return queryConditionColumns;
	}

	public List<TableColumnBean> getResultColumns(List<TableBean> tables) {

		List<TableColumnBean> resultColumns = new ArrayList();
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					String tablename = StringUtil.tableName(column.belongWhichTable.tableEnName);
					String shortTableName = tablename.substring(tablename.lastIndexOf("_") + 1);

					resultColumns.add(column);

				}

			}
		}
		return resultColumns;
	}
}
