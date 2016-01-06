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
		
		
	
	 
	     
		for(InterfaceBean interfaceBean:interfaceBeans)
		{
			//数据表
			TableBean tableBean=new TableBean();
			
			tableBean.tableCnName=interfaceBean.title;//表中文名
			tableBean.tableEnName=interfaceBean.enName;//表英文名
			tableBean.id=interfaceBean.id;//表编号
			tableBean.columns=new ArrayList();//列数组
			
			
			if(tables!=null && tables.size()>0)
			{//已经有首个表（非首表）
				
				y=tables.get(tables.size()-1).y1+bottomspace;
				x=tables.get(tables.size()-1).x;
			}else
			{//首表
				y=0;
				x=0;
			}
			tableBean.y=y;
			tableBean.x=x;
			
			List<Group> groups = interfaceBean.requestGroups;
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
				    int rowCount=0;
					for (Row row : group.rows) {
						//列
						
						if(tableBean.columns!=null && tableBean.columns.size()>0)
						{//已经有首列（非首列）
							TableColumnBean firstColumnBean=tableBean.columns.get(0);
							y=firstColumnBean.y;
							x=tableBean.columns.get(tableBean.columns.size()-1).x1;
						}else
						{//首列
							y=y;
							x=x;
						}
						
						TableColumnBean tableColumnBean=new TableColumnBean();
						
						    tableColumnBean.y=y;
						    tableColumnBean.x=x;
						    
							tableColumnBean.setColumnCnName(row.cnName);
							tableColumnBean.columnCnNameX=x+rightspace;
							tableColumnBean.columnCnNameY=y+tableColumnBean.columnCnNameHeight+bottomspace;
							
							
							tableColumnBean.setColumnEnName(row.enName);
							tableColumnBean.columnEnNameX=x+rightspace;
							y=tableColumnBean.columnCnNameY+tableColumnBean.columnCnNameHeight+bottomspace;
							tableColumnBean.columnEnNameY=y;
							
							tableColumnBean.setKey(row.remarks);
							tableColumnBean.keyX=x+rightspace;
							y=tableColumnBean.columnEnNameY+tableColumnBean.columnEnNameHeight+bottomspace;
							tableColumnBean.keyY=y;
							
							
							tableColumnBean.setType(row.type);
							tableColumnBean.typeX=x+rightspace;
							y=tableColumnBean.keyY+tableColumnBean.keyHeight+bottomspace;
							tableColumnBean.typeY=y;
							
							List<Integer> widths=new ArrayList();
							widths.add(Integer.valueOf(tableColumnBean.columnCnNameWidth));
							widths.add(Integer.valueOf(tableColumnBean.columnEnNameWidth));
							widths.add(Integer.valueOf(tableColumnBean.keyWidth));
							widths.add(Integer.valueOf(tableColumnBean.typeWidth));
							
							
							tableColumnBean.x1=x+maxWidth(widths)+rightspace;
							tableColumnBean.y1=tableColumnBean.typeY+tableColumnBean.typeHeight+bottomspace;
							
						
							tableBean.columns.add(tableColumnBean);
							

							rowCount++;
					}
				}
			}
			
			if(tableBean.columns!=null && tableBean.columns.size()>0)
			{//（最后一列）
				TableColumnBean lastColumnBean=tableBean.columns.get(tableBean.columns.size()-1);
				
				
				tableBean.x1=lastColumnBean.x1;
				tableBean.y1=lastColumnBean.y1;
			}else
			{
				tableBean.x1=0;
				tableBean.y1=0;
			}
			
			
			
			tables.add(tableBean);
		}
		
		this.repaint();
	}
	
	
	public int maxWidth(List<Integer> widths)
	{
		int maxone=0;
		for(Integer temp:widths)
		{
			if(maxone<(int)temp)
			{
				maxone=temp;
			}
		}
		return maxone;
	}
	
	
}
