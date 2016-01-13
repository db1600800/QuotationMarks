package com.compoment.db.tabledocinterfacedoc;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

public class Sql {
	
	
	public void createSql(	List<TableBean> tables)
	{
		
		for (TableBean table : tables) {
			
			
			for (TableColumnBean column : table.columns) {

				// 画连线
				if (column.relateColumnBeans != null
						&& column.relateColumnBeans.size() > 0) {
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

				
					}
				}
			}
		}
	}

}
