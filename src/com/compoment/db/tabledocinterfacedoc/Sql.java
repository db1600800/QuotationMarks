package com.compoment.db.tabledocinterfacedoc;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

public class Sql {
	
	public String createTableSql(List<TableBean> tables)
	{
		
		String show = "";
		String condition="";
		String relate="";
		
		
		for (TableBean table : tables) {
			
		
			for (TableColumnBean column : table.columns) {

				if("left".equals(column.leftOrRightClickSelected))
				{
					show+=" "+column.belongWhichTable.tableEnName+"."+column.columnEnName+",";
					
				}else if("right".equals(column.leftOrRightClickSelected))
				{
					condition+=" "+column.belongWhichTable.tableEnName+"."+column.columnEnName+"= ,and";
				}else
				{
					
				}
				

			}
		}
		
		String sql="select "+show.substring(0, show.lastIndexOf(","))+" from "+relate+" where "+condition.substring(0, condition.lastIndexOf(",and"));
		
		System.out.println("建表:"+sql);
		
		return sql;
		//select * from student ,course where student.ID=course.ID
		
		//select s.Name,C.Cname from student_course as sc left join student as s on s.Sno=sc.Sno left join course as c on c.Cno=sc.Cno
	}
	
	
	
	
	
	public String createQuerySql(	List<TableBean> tables)
	{
		
		String show = "";
		String condition="";
		String relate="";
		
		
		for (TableBean table : tables) {
			
		
			for (TableColumnBean column : table.columns) {

				if("left".equals(column.leftOrRightClickSelected))
				{
					show+=" "+column.belongWhichTable.tableEnName+"."+column.columnEnName+",";
					
				}else if("right".equals(column.leftOrRightClickSelected))
				{
					condition+=" "+column.belongWhichTable.tableEnName+"."+column.columnEnName+"= ,and";
				}else
				{
					
				}
				
				
				
			
				if (column.relateColumnBeans != null
						&& column.relateColumnBeans.size() > 0) {
					
					relate+=column.belongWhichTable.tableEnName;
					//关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

						if(column.relateColumnBeans.size()==1)
						{//两表
							
							relate+=" inner join "+relateColumn.belongWhichTable.tableEnName+" on "+column.belongWhichTable.tableEnName+"."+column.columnEnName+"="+relateColumn.belongWhichTable.tableEnName+"."+relateColumn.columnEnName;
							
						}else if(column.relateColumnBeans.size()>1)
						{//三表或以上
							relate+=" left join "+relateColumn.belongWhichTable.tableEnName+" on "+column.belongWhichTable.tableEnName+"."+column.columnEnName+"="+relateColumn.belongWhichTable.tableEnName+"."+relateColumn.columnEnName;
							
						}
				
						
					}
				}
			}
		}
		
		String sql="select "+show.substring(0, show.lastIndexOf(","))+" from "+relate+" where "+condition.substring(0, condition.lastIndexOf(",and"));
		
		System.out.println("查询:"+sql);
		
		return sql;
		//select * from student ,course where student.ID=course.ID
		
		//select s.Name,C.Cname from student_course as sc left join student as s on s.Sno=sc.Sno left join course as c on c.Cno=sc.Cno
	}

}
