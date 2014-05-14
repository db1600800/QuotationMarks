package com.compoment.ui.ios.creater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;
import com.compoment.gbkToUtf8.creater.GbkToUtf_FileOrDir;

public class CoredataDbDao {
	List<ImgBean> controls=new ArrayList();

	public static void main(String[] args) throws IOException {

		CoredataDbDao mark = new CoredataDbDao();
		mark.init(mark);


	}

	public void init(CoredataDbDao mark) {

		try {
			String classDir = mark.getClass().getResource("/").getPath();
			FileReader fr = new FileReader(classDir
					+ "com/compoment/db/ios/MarkBefor.txt");
			BufferedReader br = new BufferedReader(fr);

			String myreadline;
           
			while (br.ready()) {
				ImgBean imgBean=new ImgBean();
				myreadline = br.readLine();

				if()
					int start = myreadline.indexOf("*");
					int end = myreadline.indexOf(";");
					imgBean.enName = myreadline.substring(start, end).trim();
					imgBean.controlType="UIView";
					
				
					controls.add(imgBean);
				

			}

			br.close();
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String firstCharToUpper(String string) {
		// buy_typelist
		string = string.substring(0, 1).toUpperCase() + string.substring(1);
		return string;
	}

	public class PropertyBean {

		public String type;// 类型
		public String name;// 属性名
	}

	
}
