package com.compoment.db.tabledocinterfacedoc;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.compoment.jsonToJava.creater.WordtableToJavaObject.Group;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.InterfaceBean;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.Row;


public class DBTablesPanel extends JPanel{

	Graphics2D g2;
	List<TableBean> tables;
	
	public DBTablesPanel(){
		
	}
	

	
	
	@Override
	public void paintComponent(Graphics pGraphics)
	{
		super.paintComponent(pGraphics);
		Graphics2D g2 = (Graphics2D) pGraphics;
		//g2.scale(aZoom, aZoom);
		Rectangle2D bounds = getBounds();
		
				for(TableBean table:tables)
				{
					
					for(TableColumnBean column:table.columns)
					{
						//top
						g2.draw(new Line2D.Double(new Point2D.Double(column.x,column.y), new Point2D.Double(column.x1,column.y)));
						//bottom
						g2.draw(new Line2D.Double(new Point2D.Double(column.x,column.y1), new Point2D.Double(column.x1,column.y1)));
						//left
						g2.draw(new Line2D.Double(new Point2D.Double(column.x,column.y), new Point2D.Double(column.x,column.y1)));
						//right
						g2.draw(new Line2D.Double(new Point2D.Double(column.x1,column.y), new Point2D.Double(column.x1,column.y1)));
					
						
						g2.setFont(new Font("宋体",Font.BOLD,10));    //改变字体大小
						g2.drawString(column.columnCnName, column.columnCnNameX, column.columnCnNameY);
						g2.drawString(column.columnEnName, column.columnEnNameX, column.columnEnNameY);
						g2.drawString(column.type, column.typeX, column.typeY);
						g2.drawString(column.key, column.keyX, column.keyY);
						
					}
					
					//top
					g2.draw(new Line2D.Double(new Point2D.Double(table.x,table.y), new Point2D.Double(table.x1,table.y)));
					//bottom
					g2.draw(new Line2D.Double(new Point2D.Double(table.x,table.y1), new Point2D.Double(table.x1,table.y1)));
					//left
					g2.draw(new Line2D.Double(new Point2D.Double(table.x,table.y), new Point2D.Double(table.x,table.y1)));
					//right
					g2.draw(new Line2D.Double(new Point2D.Double(table.x1,table.y), new Point2D.Double(table.x1,table.y1)));

				}
	}
	
	
	
	public void setDBTables(List<InterfaceBean> interfaceBeans)
	{
		tables=new ArrayList();
		for(InterfaceBean interfaceBean:interfaceBeans)
		{
			TableBean tableBean=new TableBean();
			
			tableBean.tableCnName=interfaceBean.title;
			tableBean.tableEnName=interfaceBean.enName;
			tableBean.id=interfaceBean.id;
			
			tableBean.columns=new ArrayList();
			
			String parm="";
			List<Group> groups = interfaceBean.requestGroups;
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
				
					for (Row row : group.rows) {
						
						TableColumnBean tableColumnBean=new TableColumnBean();
						
							tableColumnBean.columnCnName=row.cnName;
							tableColumnBean.columnEnName=row.enName;
							tableColumnBean.key=row.remarks;
							tableColumnBean.type=row.type;
							tableBean.columns.add(tableColumnBean);
						
					}
				}

			}
			
			
			
		}
		
		
		
	}
	
}
