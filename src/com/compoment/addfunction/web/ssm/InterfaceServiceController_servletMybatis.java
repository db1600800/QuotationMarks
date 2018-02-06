package com.compoment.addfunction.web.ssm;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.remote.CheckProblemInterface;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

/**servlet  mybatis*/
public class InterfaceServiceController_servletMybatis  {


	public InterfaceServiceController_servletMybatis() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
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
		mysql(tables);
		oracle(tables);
		serviceInterface(tables);
		controller(tables);
		mybatisUtil();

		TestInterface_springmvcSpringMybatis testInterface = new TestInterface_springmvcSpringMybatis();
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
				
		
			
			}
		}
		
		
		
		//单表没选条件 
		if("".equals(condition))
		{
		
			for (TableBean table : tables) {

				for (TableColumnBean column : table.columns) {

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
			sql = "select * from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
		} else if (!"".equals(show) && "".equals(condition)) {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate;
		} else {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
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
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);
					
					show += " "
							+ shortTableName
							+ "." + column.columnEnName + ",";
					resultColumns.add(column);

				}

				if ("right".equals(column.rightClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

					if (conditionFirstColumn) {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " "
								+ shortTableName
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";
						conditionFirstColumn = false;
					} else {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " and "
								+ shortTableName
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
				
				String mainName=StringUtil
						.tableName(mainTableColumn.belongWhichTable.tableEnName);
				String shortMainTableName=mainName.substring(mainName.lastIndexOf("_")+1);
				
				if(i==0)
				{
					
					relate += mainName+" "+shortMainTableName;
				}
				
				
				String chirldName=StringUtil
						.tableName(chirldTableColumn.belongWhichTable.tableEnName);
				String shortChirldTableName=chirldName.substring(chirldName.lastIndexOf("_")+1);
					relate += " inner join "
							+ chirldName+" "+shortChirldTableName
							+ " on "
							+ shortChirldTableName
							+ "."
							+ chirldTableColumn.columnEnName
							+ "="
							+shortMainTableName
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
			sql = "select * from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
		} else if (!"".equals(show) && "".equals(condition)) {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate;
		} else {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
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

		String selectPara = "";
		
		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {
				mainTableName = interfaceName;
				selectPara+="Map para";
			} else if (tables.size() == 1) {
				mainTableName = table.tableEnName;
				selectPara+="Map para";
			}
		}

		
//		for (int i = 0; i < queryConditionColumns.size(); i++) {
//			TableColumnBean column = queryConditionColumns.get(i);
//			if (i == queryConditionColumns.size() - 1) {
//				selectPara += "@Param(\""+column.columnEnName+"\")"+typeCheck(column.type) + " "
//						+ column.columnEnName + "";
//			} else {
//				selectPara += "@Param(\""+column.columnEnName+"\")"+typeCheck(column.type) + " "
//						+ column.columnEnName + ",";
//			}
//		}
		
		

		String m = "";
		m += "package com.company.dao.impl;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		m += "//"+interfaceCnName+"\n";
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

	
	public void mysql(List<TableBean>tables)
	{
	
		
		for (TableBean table : tables) {
			String m="";
			m+="\n\n drop table "+StringUtil
					.tableName(table.tableEnName)+";\n";
			m+="CREATE TABLE "+StringUtil
					.tableName(table.tableEnName)+"(\n";
			
			String primarykeyString="";
			for (TableColumnBean column : table.columns) {
				if (column.type.toLowerCase().contains("int")) {
				  if(column.key.toLowerCase().contains("key"))
				  {
					  primarykeyString+=column.columnEnName+",";
				  
					  m+=column.columnEnName+"     INT   NOT NULL,\n";
				  }else
				  {
					  m+=column.columnEnName+"     INT,\n";
				  }
				} else {
					int start=column.columnEnName.indexOf("(");
					int end=column.columnEnName.indexOf(")");
					String size="100";
					if(start==-1||end==-1)
					{
						
					}else
					{
					 size=column.columnEnName.substring(start+1, end);
					}
					
					
					 if(column.key.toLowerCase().contains("key"))
					  {
						 primarykeyString+=column.columnEnName+",";
					  
						  m+=column.columnEnName+"     VARCHAR("+size+")   NOT NULL,\n";
					  }else
					  {
						  m+=column.columnEnName+"     VARCHAR("+size+"),\n";
					  }
				}
		}
	m+=");\n";
	
	
	FileUtil.makeFile(
			KeyValue.readCache("projectPath"),
			"src/web",
			StringUtil
			.tableName(table.tableEnName)+"_Mysql"
					+ "", "sql", m );
	
		}
		
	
	}
	
	
	public void oracle(List<TableBean>tables)
	{
		
		
		for (TableBean table : tables) {
			String m="";
			m+="\n\n drop table "+StringUtil
					.tableName(table.tableEnName)+";\n";
			m+="CREATE TABLE "+StringUtil
					.tableName(table.tableEnName)+"(\n";
			
			String primarykeyString="";
			for (TableColumnBean column : table.columns) {
				if (column.type.toLowerCase().contains("int")) {
				  if(column.key.toLowerCase().contains("key"))
				  {
					  primarykeyString+=column.columnEnName+",";
				  
					  m+=column.columnEnName+"     NUMBER   NOT NULL,\n";
				  }else
				  {
					  m+=column.columnEnName+"     NUMBER,\n";
				  }
				} else {
					int start=column.columnEnName.indexOf("(");
					int end=column.columnEnName.indexOf(")");
					String size="100";
					if(start==-1||end==-1)
					{
						
					}else
					{
					 size=column.columnEnName.substring(start+1, end);
					}
					
					
					 if(column.key.toLowerCase().contains("key"))
					  {
						 primarykeyString+=column.columnEnName+",";
					  
						  m+=column.columnEnName+"     VARCHAR2("+size+" BYTE)   NOT NULL,\n";
					  }else
					  {
						  m+=column.columnEnName+"     VARCHAR2("+size+" BYTE),\n";
					  }
				}
		}
	m+=")TABLESPACE TBS_QS_AGENT;\n";
	primarykeyString=primarykeyString.substring(0, primarykeyString.lastIndexOf(","));
	m+="alter table "+StringUtil
			.tableName(table.tableEnName)+" add primary key ("+primarykeyString+") using index TABLESPACE IDX_TBS_QS_AGENT;\n";
	
	FileUtil.makeFile(
			KeyValue.readCache("projectPath"),
			"src/web",
			StringUtil
			.tableName(table.tableEnName)+ "_Oracle", "sql", m );
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
			m="";
			m += "package com.company.pojo;\n";
			m += "import java.util.List;\n";
			m += "import java.util.Map;\n";
			m += "//"+interfaceCnName+"\n";
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
				queryCondition+="Map para";
				queryCondition3+="para";
			} else if (tables.size() == 1) {
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				mappername = table.tableEnName;
				queryCondition+="Map para";
				queryCondition3+="para";
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

		for (TableColumnBean column : queryConditionColumns) {
			//queryCondition += typeCheck(column.type) + " "+ column.columnEnName + ",";
		//	queryCondition3 += " " + column.columnEnName + ",";
			queryCondition2 += "m.put(\"" + column.columnEnName + "\", "
					+ column.columnEnName + ");\n";
		}

//		if (queryCondition.lastIndexOf(",") != -1) {
//			queryCondition = queryCondition.substring(0,
//					queryCondition.lastIndexOf(","));
//		}

//		if (queryCondition3.lastIndexOf(",") != -1) {
//			queryCondition3 = queryCondition3.substring(0,
//					queryCondition3.lastIndexOf(","));
//		}

		m += "package com.company.service.impl;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		
		m += "//"+interfaceCnName+"\n";
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
			m += "package com.company.service.impl;\n";
			m += "import java.util.HashMap;\n";
			m += "import java.util.Map;\n";
			m += "import java.util.List;\n";
			m += "import javax.annotation.Resource;\n";

			m += "import org.slf4j.Logger;\n";
			m += "import org.slf4j.LoggerFactory;\n";
			m += "import org.springframework.stereotype.Service;\n";

			m += "import com.framework.dao.BaseDao;\n";
			m += "import com.framework.dao.common.DaoTools;\n";
			m += "import com.framework.exception.CommonException;\n";
			m += "//"+interfaceCnName+"\n";
		
			m += "public class " + interfaceName + "ServiceImpl implements "
					+ interfaceName + "Service {\n";
			m += "	private final static Logger logger = LoggerFactory.getLogger("
					+ interfaceName + "ServiceImpl.class);\n";
			m += "	\n";

		
			m += "	private " + mappername + "Mapper mapper;\n";
			m += "	\n";
			m += " private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();\n";
			    //创建能执行映射文件中sql的sqlSession
			m += "   SqlSession session = sessionFactory.openSession();\n";

			for (TableBean table : tables) {

				if (table.isMainTable && tables.size() > 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "	/*	Map<String,Object> m = new HashMap();\n";
					
					m += queryCondition2;
					
					m +="mapper=session.getMapper("+mappername+"Mapper.class);\n";
					m+="*/\n";

					m+="try{\n";
					m += "  List list=mapper." + mainTableName + "Select("
							+ queryCondition3 + ");\n";
					
					m+="} finally {\n" ; 
					m+="session.close();\n";
					m+="return list;\n";
					m += "	}\n";
					
					m += "	}\n";

				} else if (tables.size() == 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "	/*	Map<String,Object> m = new HashMap();\n";
					
					m += queryCondition2;
					
					m +="mapper=session.getMapper("+mappername+"Mapper.class);\n";
					m+="*/\n";

					m+="try{\n";
					m += "  List list=mapper." + mainTableName + "Select("
							+ queryCondition3 + ");\n";
					
					m+="} finally {\n" ; 
					m+="session.close();\n";
					m+="return list;\n";
					m += "	}\n";
					m += "	}\n";
					
					

					m += "	@Override\n";
					m += "public void " + " insert(" + table.tableEnName
							+ "Bean bean){\n";
					m +="mapper=session.getMapper("+mappername+"Mapper.class);\n";
					m+="try{\n";
					m += " List list=mapper." + mainTableName + "Insert(bean);\n";
					m+="} finally {\n" ; 
					m+="session.close();\n";
					m+="return list;\n";
					m += "	}\n";
					m += "	}\n";

					m += "	@Override\n";
					m += "public void update(" + table.tableEnName
							+ "Bean bean){\n";
					m +="mapper=session.getMapper("+mappername+"Mapper.class);\n";
					m+="try{\n";
					m += "List list= mapper." + mainTableName + "Update(bean);\n";
					m+="} finally {\n" ; 
					m+="session.close();\n";
					m+="return list;\n";
					m += "	}\n";
					m += "	}\n\n";

					m += "	@Override\n";
					m += "public void   delete(" + table.tableEnName
							+ "Bean bean){\n";
					m +="mapper=session.getMapper("+mappername+"Mapper.class);\n";
					m+="try{\n";
					m += "List list=mapper." + mainTableName + "Delete(bean);\n";
					m+="} finally {\n" ; 
					m+="session.close();\n";
					m+="return list;\n";
					m += "	}\n";
					m += "	}\n";

				}
			}

			m += "}\n";

			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
					interfaceName + "ServiceImpl", "java", m);
		}

	}
	
	
	public void mybatisUtil()
	{
		String m="";
		m+="import org.apache.ibatis.io.Resources;\n";
		m+="import org.apache.ibatis.session.SqlSessionFactory;\n";
		m+="import org.apache.ibatis.session.SqlSessionFactoryBuilder;\n";

		m+="import java.io.IOException;\n";
		m+="import java.io.Reader;\n";


		m+="public class MybatisUtil {\n";
		m+="    private static SqlSessionFactory sessionFactory;\n";
		m+="    private static Reader reader;\n";

		m+="    static {\n";
		m+="        try {\n";
		m+="            //mybatis的配置文件\n";
		m+="            String resource = \"mybatisConfig.xml\";\n";
		m+="            //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）\n";
		m+="            //InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);\n";
		m+="            reader = Resources.getResourceAsReader(resource);\n";

		m+="            //构建sqlSession的工厂\n";
		m+="            sessionFactory = new SqlSessionFactoryBuilder().build(reader);\n";

		m+="        } catch (IOException e) {\n";
		m+="            e.printStackTrace();\n";
		m+="        }\n";
		m+="    }\n";

		m+="    public static SqlSessionFactory getInstance() {\n";
		m+="        return sessionFactory;\n";
		m+="    }\n";
		m+="}\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				  "MybatisUtil", "java", m);
		
		m="";
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		m+="<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n";
		m+="<configuration>\n";
		m+="    <!-- 引用db.properties配置文件 -->\n";
		m+="    <properties resource=\"jdbc.properties\"/>\n";


		m+="    <environments default=\"development\">\n";
		m+="        <environment id=\"development\">\n";
		m+="            <transactionManager type=\"JDBC\"/>\n";
		m+="            <!-- 配置数据库连接信息 -->\n";
		m+="            <dataSource type=\"POOLED\">\n";
		m+="                <property name=\"driver\" value=\"${driver}\"/>\n";
		m+="                <property name=\"url\" value=\"${url}\"/>\n";
		m+="                <property name=\"username\" value=\"${name}\"/>\n";
		m+="                <property name=\"password\" value=\"${password}\"/>\n";
		m+="            </dataSource>\n";
		m+="        </environment>\n";
		m+="    </environments>\n";
		m+="    <mappers>\n";
		m+="        <mapper resource=\"com/yyr/mapping/userMapper.xml\"/>\n";
		m+="    </mappers>\n";
		m+="</configuration>\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				  "mybatisConfig", "xml", m);
		
		m="";
		m+="driver = com.mysql.jdbc.Driver\n";
		m+="url = jdbc:mysql://localhost:3306/yyr_test\n";
		m+="name = root\n";
		m+="password = jialin89086\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				  "jdbc", "properties", m);

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

	
		m+="Map paraMap=new HashMap();\n";
		
		int i = 0;
		String n="";
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
				interfaceName + "Servelet", "java", m);
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
