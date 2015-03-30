package com.compoment.jsonToJava.creater;
import java.io.File;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
class JavaObjectToWordtable {
	既然没有解答，那自己来好了，在其他同事的帮助下，顺利解决问题。
	（1）poi的HWPF是不能新建word文档的，只能先有一个文档，再生成一个新文档。这个我试验过很多次了，但是也可能是错的，仅供大家参考哈~~关键代码如下：
	FileInputStream fileInputStream = new FileInputStream( soureFile);
	POIFSFileSystem pfs = new POIFSFileSystem( fileInputStream );
	HWPFDocument hwpf = new HWPFDocument(pfs);// make a HWPFDocument object

	OutputStream output = new FileOutputStream( targetFile );
	hwpf.write(output);// write to the target file
	output.close();

	（2）再word中插入表格。HWPF的情况：
	Table tcDataTable = range.insertTableBefore( (short)column , row);//column and row列数和行数
	tcDataTable.getRow(i).getCell(j).getParagraph(0).getCharacterRun(0).insertBefore("插入i行j列的内容" );

	XWPF的情况：
	String outputFile = "D:\\test.doc";

	XWPFDocument document = new XWPFDocument();

	XWPFTable tableOne = document.createTable();




	XWPFTableRow tableOneRowOne = tableOne.getRow(0);
	tableOneRowOne.getCell(0).setText("11");
	XWPFTableCell cell12 =   tableOneRowOne.createCell();
	cell12.setText("12");
//		 tableOneRowOne.addNewTableCell().setText("第1行第2列");
//		 tableOneRowOne.addNewTableCell().setText("第1行第3列");
//		 tableOneRowOne.addNewTableCell().setText("第1行第4列");

	XWPFTableRow tableOneRowTwo = tableOne.createRow();
	tableOneRowTwo.getCell(0).setText("21");
	tableOneRowTwo.getCell(1).setText("22");
//		 tableOneRowTwo.getCell(2).setText("第2行第3列");

	XWPFTableRow tableOneRow3 = tableOne.createRow();
	tableOneRow3.addNewTableCell().setText("31");
	tableOneRow3.addNewTableCell().setText("32");

	FileOutputStream fOut;

	try {
	fOut = new FileOutputStream(outputFile);

	document.write(fOut); 
	fOut.flush();
	// 操作结束，关闭文件
	fOut.close();
	} catch (Exception e) {
	e.printStackTrace();
	} 

}

