package com.compoment.db.tabledocinterfacedoc;
import java.util.List;


public class TableBean {
	public String tableEnName;
	public String tableCnName;
	public int tableCnNameX;
	public int tableCnNameY;
	public String id;
	public List<TableColumnBean> columns;
	public int x;
	public int y;
	public int x1;
	public int y1;
	public int width;
	public int height;
	
	public long time;
	
	public boolean selected;
	
	public TableBean()
	{
		time=System.currentTimeMillis();
	}
}