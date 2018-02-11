package com.compoment.addfunction.webmanage.jspStruct2Mybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.addfunction.web.servletMybatis.Entity;
import com.compoment.addfunction.web.servletMybatis.MapperJava;
import com.compoment.addfunction.web.servletMybatis.MapperXml;
import com.compoment.addfunction.web.servletMybatis.MapperXmlForSingleTable;
import com.compoment.addfunction.web.servletMybatis.MybatisUtil;
import com.compoment.addfunction.web.servletMybatis.ServiceInterface;
import com.compoment.addfunction.web.servletMybatis.SqlScript;
import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;

public class Mybatis {
	
	public Mybatis(String interfaceName,
			String interfaceCnName, List<TableBean> temptables)
	{
		for (TableBean table : temptables) {
		for (TableColumnBean column : table.columns) {
			column.belongWhichTable=table;
		}
		
		List<TableBean>  tables =new ArrayList();
		tables.add(table);
		
		MapperXmlForSingleTable  mapperXmlForSingleTable=new MapperXmlForSingleTable();
		mapperXmlForSingleTable.mapperXmlForSingleTable(tables);
		
		
		MapperXml mapperXml=new MapperXml();
		mapperXml.mapperXml(tables,interfaceName);
		
		MapperJava mapperJava=new MapperJava();
		mapperJava.mapperJava(tables,interfaceName,interfaceCnName);
		
		Entity entity=new Entity();
		entity.entity(tables,interfaceName,interfaceCnName);
		
		SqlScript  sqlScript=new SqlScript();
		sqlScript.mysql(tables);
		sqlScript.oracle(tables);
		
		ServiceInterface serviceInterface=new ServiceInterface();
		serviceInterface.serviceInterface(tables,interfaceName,interfaceCnName);
		
		
		
		}
		
		
		
		MybatisUtil mybatisUtil=new MybatisUtil();
		mybatisUtil.mybatisUtil();
	}

}
