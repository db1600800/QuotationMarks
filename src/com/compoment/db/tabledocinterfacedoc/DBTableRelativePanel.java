package com.compoment.db.tabledocinterfacedoc;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class DBTableRelativePanel extends JPanel{

	Graphics2D g2;
	List<TableBean> tables;
	
	public DBTableRelativePanel(){
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
			
		//Color oldColor = g2.getColor();
		//g2.setColor(GRABBER_COLOR);
		//g2.draw(new Line2D.Double(aMouseDownPoint, aLastMousePoint));
		//g2.setColor(oldColor);
		
			
		    
	}
	
	
	
	
	

}
