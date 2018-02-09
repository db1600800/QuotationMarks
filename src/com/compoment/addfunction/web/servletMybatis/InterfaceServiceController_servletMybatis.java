package com.compoment.addfunction.web.servletMybatis;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.compoment.addfunction.web.springmvcSpringMybatis.TestInterface_springmvcSpringMybatis;
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

	
	String interfaceName;
	String interfaceCnName;

	public void createInterfaceService(String interfaceName,
			String interfaceCnName, List<TableBean> tables) {
		this.interfaceName = interfaceName;
		this.interfaceCnName = interfaceCnName;

		MapperXmlForSingleTable  mapperXmlForSingleTable=new MapperXmlForSingleTable();
		mapperXmlForSingleTable.mapperXmlForSingleTable(tables);
		
		
		MapperXml mapperXml=new MapperXml();
		mapperXml.mapperXml(tables,interfaceName);
		
		MapperJava mapperJava=new MapperJava();
		mapperJava.mapperJava(tables,interfaceName,interfaceCnName);
		
		Entity entity=new Entity();
		entity.entity(tables,interfaceName,interfaceCnName);
		
		
		mysql(tables);
		oracle(tables);
		
		ServiceInterface serviceInterface=new ServiceInterface();
		serviceInterface.serviceInterface(tables,interfaceName,interfaceCnName);
		
		Controller controller=new Controller();
		controller.controller(tables,interfaceName,interfaceCnName);
		
		mybatisUtil();

		TestInterface_springmvcSpringMybatis testInterface = new TestInterface_springmvcSpringMybatis();
		testInterface.testJsp(interfaceName, interfaceCnName, tables);
		
	

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

	

	public String typeCheck(String type) {
		if (!type.toLowerCase().contains("int")) {
			type = "String";
		} else {
			type = "int";
		}

		return type;
	}
}
