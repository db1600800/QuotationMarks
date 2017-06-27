package com.compoment.addfunction.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class InterfaceServiceController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	List<TableColumnBean> resultColumns = new ArrayList();
	List<TableColumnBean> queryConditionColumns = new ArrayList();
	String interfaceName;
	String interfaceCnName;

	public void createInterfaceService(String interfaceName,
			String interfaceCnName, List<TableBean> tables) {
		this.interfaceName = interfaceName;
		this.interfaceCnName = interfaceCnName;

		mapperXmlForSingleTable(tables);
		mapperXml(tables);
		mapperJava(tables);
		entity(tables);
		serviceInterface(tables);
		controller(tables);

		TestInterface testInterface = new TestInterface();
		testInterface.testJsp(interfaceName, interfaceCnName, tables);

	}

	public void mapperXmlForSingleTable(List<TableBean> tables) {

		if (tables != null && tables.size() > 1) {
			return;
		}

		String show = "";
		String condition = "";
		String relate = "";
		String mainTableName = "";

		boolean haveRelate = false;

		boolean conditionFirstColumn = true;

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					show += " "
							+ StringUtil
									.tableName(column.belongWhichTable.tableEnName)
							+ "." + column.columnEnName + ",";
					resultColumns.add(column);

				}

				if ("right".equals(column.rightClickSelected)) {

					if (conditionFirstColumn) {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " "
								+ StringUtil
										.tableName(column.belongWhichTable.tableEnName)
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";
						conditionFirstColumn = false;
					} else {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " and "
								+ StringUtil
										.tableName(column.belongWhichTable.tableEnName)
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";

					}

					queryConditionColumns.add(column);
				} else {

				}

				if (column.relateColumnBeans != null
						&& column.relateColumnBeans.size() > 0) {
					haveRelate = true;
					relate += StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					// 关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

						if (column.relateColumnBeans.size() == 1) {// 两表

							relate += " inner join "
									+ StringUtil
											.tableName(relateColumn.belongWhichTable.tableEnName)
									+ " on "
									+ StringUtil
											.tableName(column.belongWhichTable.tableEnName)
									+ "."
									+ column.columnEnName
									+ "="
									+ StringUtil
											.tableName(relateColumn.belongWhichTable.tableEnName)
									+ "." + relateColumn.columnEnName;

						} else if (column.relateColumnBeans.size() > 1) {// 三表或以上
							relate += " inner join "
									+ StringUtil
											.tableName(relateColumn.belongWhichTable.tableEnName)
									+ " on "
									+ StringUtil
											.tableName(column.belongWhichTable.tableEnName)
									+ "."
									+ column.columnEnName
									+ "="
									+ StringUtil
											.tableName(relateColumn.belongWhichTable.tableEnName)
									+ "." + relateColumn.columnEnName;

						}

					}
				}
			}
		}

		for (TableBean table : tables) {
			for (TableColumnBean column : table.columns) {
				if (!haveRelate) {// 单个表
					relate = StringUtil
							.tableName(column.belongWhichTable.tableEnName);
				}

			}
		}

		// 查询
		String sql = "";

		if ("".equals(show) && "".equals(condition)) {
			sql = "select * from " + relate;
		}

		else if ("".equals(show) && !"".equals(condition)) {
			sql = "select * from " + relate + " where " + condition;
		} else if (!"".equals(show) && "".equals(condition)) {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate;
		} else {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate + " where " + condition;
		}

		String m = "";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
		m += "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n";

		for (TableBean table : tables) {

			if (table.isMainTable || tables.size() == 1) {
				mainTableName = table.tableEnName;
				m += "<mapper namespace=\"com.company.dao.impl."
						+ table.tableEnName + "Mapper\">\n";
				m += "	<resultMap id=\"" + table.tableEnName
						+ "ResultMap\" type=\"com.company.pojo."
						+ table.tableEnName + "Bean\">\n";
				for (TableColumnBean column : resultColumns) {
					if (column.belongWhichTable.tableEnName
							.equals(table.tableEnName)) {
						if (column.columnEnName.contains("id")) {
							m += "<id column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						} else {
							m += "<result column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						}
					}
				}
			}
		}

		for (TableBean table : tables) {

			if (!table.isMainTable && tables.size() > 1) {
				m += "	 <collection property=\"" + table.tableEnName
						+ "\" ofType=\"com.company.bean." + table.tableEnName
						+ "Bean\">\n";
				for (TableColumnBean column : resultColumns) {
					if (column.belongWhichTable.tableEnName
							.equals(table.tableEnName)) {
						if (column.columnEnName.contains("id")) {
							m += "<id column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						} else {
							m += "<result column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						}
					}
				}

				m += "</collection>\n";
			}
		}

		m += "	</resultMap>\n";

		for (TableBean table : tables) {

			if (table.isMainTable || tables.size() == 1) {
				m += "	<select id=\"" + table.tableEnName
						+ "Select\" resultMap=\"" + table.tableEnName
						+ "ResultMap\" >\n";

			}
		}
		m += sql;
		m += "	</select>\n";

		// 插入
		for (TableBean table : tables) {
			m += "\n\n	<insert id=\"" + table.tableEnName
					+ "Insert\" parameterType=\"" + table.tableEnName
					+ "Bean\">\n";
			m += "		insert into " + StringUtil.tableName(table.tableEnName)
					+ " \n";
			m += "		<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n";
			for (TableColumnBean column : table.columns) {

				m += "			<if test=\"" + column.columnEnName + " != null\">\n";
				m += "				" + column.columnEnName + ",\n";
				m += "			</if>\n";

			}
		}

		m += "		</trim>\n";

		m += "		<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n";

		for (TableBean table : tables) {
			for (TableColumnBean column : table.columns) {

				if (!column.type.toLowerCase().contains("int")) {
					m += "			<if test=\"" + column.columnEnName
							+ " != null\">\n";
					m += "				#{" + column.columnEnName
							+ ",jdbcType=VARCHAR},\n";
					m += "			</if>\n";
				} else {
					m += "			<if test=\"" + column.columnEnName
							+ " != null\">\n";
					m += "				#{" + column.columnEnName
							+ ",jdbcType=INTEGER},\n";
					m += "			</if>\n";
				}

			}
		}
		m += "		</trim>\n";
		m += "	</insert>\n\n";

		// 删除
		for (TableBean table : tables) {
			m += "	<delete id=\"" + table.tableEnName
					+ "Delete\" parameterType=\"" + table.tableEnName
					+ "Bean\">\n";
			m += "		delete from " + StringUtil.tableName(table.tableEnName)
					+ "";
			m += "		where \n";
			m+="<trim  suffix=\"\" suffixOverrides=\"and\">\n";
			for (TableColumnBean column : table.columns) {

				if (column.key != null
						&& column.key.toLowerCase().contains("key")) {
					if (!column.type.toLowerCase().contains("int")) {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=VARCHAR} and \n";
						m += "			</if>\n";
					} else {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=INTEGER} and \n";
						m += "			</if>\n";
					}
				}
			}
		}
m+="</trim>\n";
		m += "	</delete>\n\n";

		// 更新

		for (TableBean table : tables) {
			m += "	<update id=\"" + table.tableEnName
					+ "Update\" parameterType=\"" + table.tableEnName
					+ "Bean\">\n";
			m += "		update " + StringUtil.tableName(table.tableEnName) + "\n";
			m += "		<set>\n";

			for (TableColumnBean column : table.columns) {

				if (column.key == null
						|| !column.key.toLowerCase().contains("key")) {
					if (!column.type.toLowerCase().contains("int")) {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=VARCHAR},\n";
						m += "			</if>\n";
					} else {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=INTEGER},\n";
						m += "			</if>\n";
					}
				}
			}

			m += "		</set>\n";

			m += "		where \n";
			m+="<trim  suffix=\"\" suffixOverrides=\"and\">\n";
			for (TableColumnBean column : table.columns) {

				if (column.key != null
						&& column.key.toLowerCase().contains("key")) {
					if (!column.type.toLowerCase().contains("int")) {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=VARCHAR} and \n";
						m += "			</if>\n";
					} else {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=INTEGER} and \n";
						m += "			</if>\n";
					}
				}
			}
		}
m+="</trim>\n";
		m += "	</update>\n";

		m += "</mapper>\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				mainTableName + "Mapper", "xml", m);

	}

	public void mapperXml(List<TableBean> tables) {

		if (tables != null && tables.size() <= 1) {
			return;
		}

		String show = "";
		String condition = "";
		String relate = "";
		String mainTableName = "";

		boolean haveRelate = false;
		
		List<Map> mainTableRelateChirldTableList=new ArrayList();

		boolean conditionFirstColumn = true;

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					show += " "
							+ StringUtil
									.tableName(column.belongWhichTable.tableEnName)
							+ "." + column.columnEnName + ",";
					resultColumns.add(column);

				}

				if ("right".equals(column.rightClickSelected)) {

					if (conditionFirstColumn) {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " "
								+ StringUtil
										.tableName(column.belongWhichTable.tableEnName)
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";
						conditionFirstColumn = false;
					} else {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " and "
								+ StringUtil
										.tableName(column.belongWhichTable.tableEnName)
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";

					}

					queryConditionColumns.add(column);
				}

				if (column.relateColumnBeans != null
						&& column.relateColumnBeans.size() > 0) {
					
					
					haveRelate = true;
					// 关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

					if(column.belongWhichTable.isMainTable && !relateColumn.belongWhichTable.isMainTable)
					{
						Map mainTableRelateChirldTable=new HashMap();
						mainTableRelateChirldTable.put(column, relateColumn);
						mainTableRelateChirldTableList.add(mainTableRelateChirldTable);
					}else if(!column.belongWhichTable.isMainTable && relateColumn.belongWhichTable.isMainTable)
					{
						Map mainTableRelateChirldTable=new HashMap();
						mainTableRelateChirldTable.put(relateColumn, column);
						mainTableRelateChirldTableList.add(mainTableRelateChirldTable);
					}else if(!column.belongWhichTable.isMainTable && !relateColumn.belongWhichTable.isMainTable)
					{
						
					}
						
						

						
					}

				}
			}
		}

		
		
		
		
		
		//多表

			int i=0;
			for(Map mainTableRelateChirldTable:mainTableRelateChirldTableList)
			{
			for(Object key : mainTableRelateChirldTable.keySet())
			{
				
				TableColumnBean mainTableColumn=(TableColumnBean)key;
				TableColumnBean chirldTableColumn=(TableColumnBean) mainTableRelateChirldTable.get(key);
				
				if(i==0)
				{
					relate += StringUtil
							.tableName(mainTableColumn.belongWhichTable.tableEnName);
				}
				
					relate += " inner join "
							+ StringUtil
									.tableName(chirldTableColumn.belongWhichTable.tableEnName)
							+ " on "
							+ StringUtil
									.tableName(chirldTableColumn.belongWhichTable.tableEnName)
							+ "."
							+ chirldTableColumn.columnEnName
							+ "="
							+ StringUtil
									.tableName(mainTableColumn.belongWhichTable.tableEnName)
							+ "." + mainTableColumn.columnEnName;
				
					i++;
			}
			}

		
		
		
		
		for (TableBean table : tables) {
			for (TableColumnBean column : table.columns) {
				if (!haveRelate) {// 单个表
					relate = StringUtil
							.tableName(column.belongWhichTable.tableEnName);
				}

			}
		}

		// 查询
		String sql = "";

		if ("".equals(show) && "".equals(condition)) {
			sql = "select * from " + relate;
		}

		else if ("".equals(show) && !"".equals(condition)) {
			sql = "select * from " + relate + " where " + condition;
		} else if (!"".equals(show) && "".equals(condition)) {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate;
		} else {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate + " where " + condition;
		}

		String m = "";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
		m += "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n";

		for (TableBean table : tables) {

			if (table.isMainTable) {
				mainTableName = table.tableEnName;
				m += "<mapper namespace=\"com.company.dao.impl."
						+ interfaceName + "Mapper\">\n";
				m += "	<resultMap id=\"" + interfaceName
						+ "ResultMap\" type=\"com.company.pojo."
						+ interfaceName + "Bean\">\n";
				for (TableColumnBean column : resultColumns) {
					if (column.belongWhichTable.tableEnName
							.equals(table.tableEnName)) {
						if (column.columnEnName.contains("id")) {
							m += "<id column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						} else {
							m += "<result column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						}
					}
				}
			}
		}

		for (TableBean table : tables) {

			if (!table.isMainTable) {
				m += "	 <collection property=\""
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s\" ofType=\"com.company.pojo." + table.tableEnName
						+ "Bean\">\n";
				for (TableColumnBean column : resultColumns) {
					if (column.belongWhichTable.tableEnName
							.equals(table.tableEnName)) {
						if (column.columnEnName.contains("id")) {
							m += "<id column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						} else {
							m += "<result column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						}
					}
				}

				m += "</collection>\n";
			}
		}

		m += "	</resultMap>\n";

		for (TableBean table : tables) {

			if (table.isMainTable) {
				m += "	<select id=\"" + interfaceName
						+ "Select\" resultMap=\"" + interfaceName
						+ "ResultMap\" >\n";

			}
		}
		m += sql;
		m += "	</select>\n";

		m += "</mapper>\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Mapper", "xml", m);

	}

	public void mapperJava(List<TableBean> tables) {

		String show = "";
		String condition = "";
		String relate = "";
		String mainTableName = "";

		boolean haveRelate = false;

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {
				mainTableName = interfaceName;

			} else if (tables.size() == 1) {
				mainTableName = table.tableEnName;
			}
		}

		String selectPara = "";
		for (int i = 0; i < queryConditionColumns.size(); i++) {
			TableColumnBean column = queryConditionColumns.get(i);
			if (i == queryConditionColumns.size() - 1) {
				selectPara += "@Param(\""+column.columnEnName+"\")"+typeCheck(column.type) + " "
						+ column.columnEnName + "";
			} else {
				selectPara += "@Param(\""+column.columnEnName+"\")"+typeCheck(column.type) + " "
						+ column.columnEnName + ",";
			}
		}

		String m = "";

		m += "public interface " + mainTableName + "Mapper" + " {\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

				m += "List<" + interfaceName + "Bean> " + interfaceName
						+ "Select(" + selectPara + ");\n";

			} else if (tables.size() == 1) {

				m += "List<" + table.tableEnName + "Bean> " + table.tableEnName
						+ "Select(" + selectPara + ");\n";
				m += "void " + table.tableEnName + "Insert("
						+ table.tableEnName + "Bean bean);\n";
				m += "void " + table.tableEnName + "Update("
						+ table.tableEnName + "Bean bean);\n";
				m += "void " + table.tableEnName + "Delete("
						+ table.tableEnName + "Bean bean);\n";

			}
		}

		m += "}\n";

		
		if(tables!=null && tables.size()>1)
		{
			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
					interfaceName + "Mapper", "java", m);
		}else
		{
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				mainTableName + "Mapper", "java", m);
		}

	}

	public void entity(List<TableBean> tables) {
		String m = "";
		String m1 = "";
		String noMainTable = "";
		String noMainTable1 = "";

		for (TableBean table : tables) {

			if(tables.size()>1)
			{
			if (table.isMainTable) {

			} else {
				noMainTable += "List<"
						+ StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
						+ "Bean> "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s;/**" + table.tableCnName + "*/\n";
				noMainTable1 += "";

				noMainTable1 += "	public List get"
						+ StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
						+ "s() {\n";
				noMainTable1 += "		return "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s;\n";
				noMainTable1 += "	}\n";

				noMainTable1 += "	public void set"
						+ StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
						+ "s(List "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s) {\n";
				noMainTable1 += "		this."
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s = "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s;\n";
				noMainTable1 += "	}\n";
			}
			}
		}

		for (TableBean table : tables) {
			m = "";
			m1 = "";
			if (tables.size()>1 && table.isMainTable) {
				m += "public class "
						+ interfaceName
						+ "Bean {\n";
			}else
			{
			m += "public class "
					+ StringUtil.firstCharToUpperAndJavaName(table.tableEnName)
					+ "Bean {\n";
			}
			for (TableColumnBean column : table.columns) {

				if (column.type.toLowerCase().contains("int")) {
					m += "	public Integer " + column.columnEnName + ";/**"
							+ column.columnCnName + "*/\n";
					m1 += "	public Integer get"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "(Integer " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
				} else {
					m += "	public String " + column.columnEnName + ";/**"
							+ column.columnCnName + "*/\n";
					m1 += "	public String get"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "(String " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
				}
			}

			if (tables.size()>1 && table.isMainTable) {
				FileUtil.makeFile(
						KeyValue.readCache("projectPath"),
						"src/web",
						interfaceName
								+ "Bean", "java", m + noMainTable + m1
								+ noMainTable1 + "}\n");
			} else {
				FileUtil.makeFile(
						KeyValue.readCache("projectPath"),
						"src/web",
						StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
								+ "Bean", "java", m + m1 + "}\n");
			}

		}

	}

	public void serviceInterface(List<TableBean> tables) {
		String m = "";

		String servicename = "";
		String resultType = "";
		String queryCondition = "";
		String queryCondition2 = "";
		String queryCondition3 = "";
		String mainTableName = "";
		String mappername = "";

		for (TableBean table : tables) {
			servicename += table.tableEnName + "_";
			if (table.isMainTable && tables.size() > 1) {
				resultType = interfaceName + "Bean";
				mainTableName = interfaceName;
				mappername = interfaceName;
			} else if (tables.size() == 1) {
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				mappername = table.tableEnName;
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

		for (TableColumnBean column : queryConditionColumns) {
			queryCondition += typeCheck(column.type) + " "
					+ column.columnEnName + ",";
			queryCondition3 += " " + column.columnEnName + ",";
			queryCondition2 += "m.put(\"" + column.columnEnName + "\", "
					+ column.columnEnName + ");\n";
		}

		if (queryCondition.lastIndexOf(",") != -1) {
			queryCondition = queryCondition.substring(0,
					queryCondition.lastIndexOf(","));
		}

		if (queryCondition3.lastIndexOf(",") != -1) {
			queryCondition3 = queryCondition3.substring(0,
					queryCondition3.lastIndexOf(","));
		}

		m += "public interface " + interfaceName + "Service {\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

				m += "	List<" + resultType + "> get(" + queryCondition
						+ ") throws Exception;\n";

			} else if (tables.size() == 1) {

				m += "	List<" + resultType + "> get(" + queryCondition
						+ ") throws Exception;\n";

				m += "void " + "insert(" + table.tableEnName + "Bean bean);\n";
				m += "void " + "update(" + table.tableEnName + "Bean bean);\n";
				m += "void " + "delete(" + table.tableEnName + "Bean bean);\n";

			}
		}

		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Service", "java", m);

		// 接口实现
		{

			m = "";
			m += "import java.util.HashMap;\n";
			m += "import java.util.Map;\n";

			m += "import javax.annotation.Resource;\n";

			m += "import org.slf4j.Logger;\n";
			m += "import org.slf4j.LoggerFactory;\n";
			m += "import org.springframework.stereotype.Service;\n";

			m += "import com.framework.dao.BaseDao;\n";
			m += "import com.framework.dao.common.DaoTools;\n";
			m += "import com.framework.exception.CommonException;\n";

			m += "@Service(\"" + interfaceName + "Service\")\n";
			m += "public class " + interfaceName + "ServiceImpl implements "
					+ interfaceName + "Service {\n";
			m += "	private final static Logger logger = LoggerFactory.getLogger("
					+ interfaceName + "ServiceImpl.class);\n";
			m += "	\n";

			m += "	@Resource\n";
			m += "	private " + mappername + "Mapper mapper;\n";
			m += "	\n";

			for (TableBean table : tables) {

				if (table.isMainTable && tables.size() > 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "		Map<String,Object> m = new HashMap();\n";
					m += queryCondition2;

					m += "return mapper." + mainTableName + "Select("
							+ queryCondition3 + ");\n";
					m += "	}\n";

				} else if (tables.size() == 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "		Map<String,Object> m = new HashMap();\n";
					m += queryCondition2;

					m += "return mapper." + mainTableName + "Select("
							+ queryCondition3 + ");\n";
					m += "	}\n\n";

					m += "	@Override\n";
					m += "public void " + " insert(" + table.tableEnName
							+ "Bean bean){\n";

					m += " mapper." + mainTableName + "Insert(bean);\n";
					m += "	}\n";

					m += "	@Override\n";
					m += "public void update(" + table.tableEnName
							+ "Bean bean){\n";
					m += " mapper." + mainTableName + "Update(bean);\n";
					m += "	}\n\n";

					m += "	@Override\n";
					m += "public void   delete(" + table.tableEnName
							+ "Bean bean){\n";

					m += " mapper." + mainTableName + "Delete(bean);\n";
					m += "	}\n";

				}
			}

			m += "}\n";

			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
					interfaceName + "ServiceImpl", "java", m);
		}

	}

	public void controller(List<TableBean> tables) {

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
			}else
			{
				
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
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
		m += "import java.util.Locale;\n";
		m += "import java.util.Map;\n";
		m += "import java.util.Set;\n";

		m += "import javax.validation.ConstraintViolation;\n";
		m += "import javax.validation.Validator;\n";
		m += "import org.springframework.beans.factory.annotation.Autowired;\n";
		m += "import org.springframework.context.annotation.Scope;\n";
		m += "import org.springframework.context.i18n.LocaleContextHolder;\n";
		m += "import org.springframework.stereotype.Controller;\n";
		m += "import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;\n";
		m += "import org.springframework.web.bind.annotation.RequestBody;\n";
		m += "import org.springframework.web.bind.annotation.RequestMapping;\n";
		m += "import org.springframework.web.bind.annotation.RequestParam;\n";
		m += "import org.springframework.web.bind.annotation.ResponseBody;\n";

		m += "import org.springframework.web.servlet.support.RequestContext;\n";

		m += "import com.framework.controller.BaseController;\n";
		m += "import com.framework.utils.SpringBeanManger;\n";

		m += "\n/**" + interfaceCnName + "*/\n";
		m += "@Controller\n";
		m += "@Scope(\"prototype\")\n";
		m += "@RequestMapping(\"/" + interfaceName.toLowerCase() + "\")\n";
		m += "public class " + interfaceName
				+ "Controller extends BaseController {\n";

		m += "@Autowired\n";
		m += "private " + interfaceName + "Service "
				+ StringUtil.firstCharToLower(interfaceName) + "Service;\n";

		m += "	@RequestMapping(\"/query.do\")\n";
		m += "	public @ResponseBody Map<String, Object> query(@RequestParam Map reqMap) {\n";

		m += "		//if (null == reqMap || reqMap.isEmpty())\n";
		m += "			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

		
		int i = 0;
		String n="";
		for (TableColumnBean column : queryConditionColumns) {

			if (column.type.toLowerCase().contains("int")) {
				m += "		int " + column.columnEnName
						+ " = reqMap.get(\"" + column.columnEnName
						+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
						+ column.columnEnName + "\").toString());\n";
				
			}else
			{
				m += "		String " + column.columnEnName
						+ " = reqMap.get(\"" + column.columnEnName
						+ "\") == null ? null : reqMap.get(\""
						+ column.columnEnName + "\").toString();\n";
			}
			
			
			
			if (i == queryConditionColumns.size() - 1) {
				queryCondition2 += column.columnEnName + "";
			} else {
				queryCondition2 += column.columnEnName + ",";
			}

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

		m+="Map r=new HashMap();\n";
		m+="r.put(\"returnData\","+ mainTableName.toLowerCase()+"Beans);\n";
		m += "	  return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE,\"\",r);\n";
		m += "	}\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

			} else if (tables.size() == 1) {

				m += "	@RequestMapping(\"/insert.do\")\n";
				m += "	public @ResponseBody Map<String, Object> insert(@RequestParam Map reqMap) {\n";

				m += "		if (null == reqMap || reqMap.isEmpty())\n";
				m += "			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

				 n="";
				for (TableColumnBean column : table.columns) {
					

					if (column.type.toLowerCase().contains("int")) {
						m += "		int " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
								+ column.columnEnName + "\").toString());\n";
						
					}else
					{
						m += "		String " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? null : reqMap.get(\""
								+ column.columnEnName + "\").toString();\n";
					}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.insert(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
				m += "	}\n";

				m += "	@RequestMapping(\"/update.do\")\n";
				m += "	public @ResponseBody Map<String, Object> update(@RequestParam Map reqMap) {\n";

				m += "		if (null == reqMap || reqMap.isEmpty())\n";
				m += "			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

				 n="";
				for (TableColumnBean column : table.columns) {
				
					if (column.type.toLowerCase().contains("int")) {
						m += "		int " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
								+ column.columnEnName + "\").toString());\n";
						
					}else
					{
						m += "		String " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? null : reqMap.get(\""
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

				m += "		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
				m += "	}\n";

				
				
				
				
				m += "	@RequestMapping(\"/delete.do\")\n";
				m += "	public @ResponseBody Map<String, Object> delete(@RequestParam Map reqMap) {\n";

				m += "		if (null == reqMap || reqMap.isEmpty())\n";
				m += "			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

				
				 n="";
				for (TableColumnBean column : table.columns) {
					if (column.key != null
							&& column.key.toLowerCase().contains("key")) {

						if (column.type.toLowerCase().contains("int")) {
							m += "		int " + column.columnEnName
									+ " = reqMap.get(\"" + column.columnEnName
									+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
									+ column.columnEnName + "\").toString());\n";
							
						}else
						{
							m += "		String " + column.columnEnName
									+ " = reqMap.get(\"" + column.columnEnName
									+ "\") == null ? null : reqMap.get(\""
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

				m += "		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
				m += "	}\n";

			}
		}

		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Controller", "java", m);
	}

	public String typeCheck(String type) {
		if (!type.toLowerCase().contains("int")) {
			type = "String";
		} else {
			type = "int";
		}

		return type;
	}
}
