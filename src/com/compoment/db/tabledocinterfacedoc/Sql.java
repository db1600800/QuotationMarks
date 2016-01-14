package com.compoment.db.tabledocinterfacedoc;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

public class Sql {
	
	
	public void createQuerySql(	List<TableBean> tables)
	{
		
		String show = null;
		String condition=null;
		String relate=null;
		
		
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
					//关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

						if(column.relateColumnBeans.size()==1)
						{//两表
							
							
						}else if(column.relateColumnBeans.size()>1)
						{//三表或以上
							
						}
				
						
					}
				}
			}
		}
		
		
		//select * from student ,course where student.ID=course.ID
		
		//select s.Name,C.Cname from student_course as sc left join student as s on s.Sno=sc.Sno left join course as c on c.Cno=sc.Cno
	}

}
