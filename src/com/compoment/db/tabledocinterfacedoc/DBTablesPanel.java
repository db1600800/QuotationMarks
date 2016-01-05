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
		tables=new ArrayList();
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
		
		int x=0;
		int y=0;
		
		int leftspace=3;
		int rightspace=3;
		int topspace=3;
		int bottomspace=3;
		
		x=leftspace;
		
		
		for(InterfaceBean interfaceBean:interfaceBeans)
		{
			//数据表
			TableBean tableBean=new TableBean();
			
			tableBean.tableCnName=interfaceBean.title;//表中文名
			tableBean.tableEnName=interfaceBean.enName;//表英文名
			tableBean.id=interfaceBean.id;//表编号
			tableBean.columns=new ArrayList();//列数组
			
			x=5;
			
			List<Group> groups = interfaceBean.requestGroups;
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
				
					for (Row row : group.rows) {
						//列
						TableColumnBean tableColumnBean=new TableColumnBean();
						
							tableColumnBean.setColumnCnName(row.cnName);
							tableColumnBean.setColumnEnName(row.enName);
							tableColumnBean.setKey(row.remarks);
							tableColumnBean.setType(row.type);
							tableColumnBean.x=x;
							
							x=x+tableColumnBean.columnEnNameWidth+rightspace+leftspace;
							tableBean.columns.add(tableColumnBean);
					}
				}
			}
		}
	}
	
}
