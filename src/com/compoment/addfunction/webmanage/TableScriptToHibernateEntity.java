package com.compoment.addfunction.webmanage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;

public class TableScriptToHibernateEntity {

	public TableScriptToHibernateEntity() {
		try {
			String courseFile = null;
			File directory = new File("");// 参数为空
			courseFile = directory.getCanonicalPath();
			String path = courseFile
					+ "/src/com/compoment/addfunction/webmanage/MarkBefor.txt";

			// C:\Documents and Settings\Administrator\My
			// Documents\下载\QuotationMarks-github\QuotationMarks-github\QuotationMarks-github\src\com\compoment\creater\first\MarkBefor.txt

			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);

			String myreadline;
			List columns = new ArrayList();
			List columnnameAndcomment=new ArrayList();
			
			while (br.ready()) {
				myreadline = br.readLine();

				if(myreadline.contains("COMMENT"))
				{
					//COMMENT ON COLUMN MOBILEGATE.T_GD_WX_PARA.TYPE_CD IS '参数分类';  oracle数据库注释
					
					String[] parts=myreadline.split("COMMENT ON COLUMN");
					
					String part2=parts[1];
					
					String[] tablenameAndcomment=part2.split("IS");
					
					String d=tablenameAndcomment[0];
					String ds[]=d.split("\\.");
					
					columnnameAndcomment.add(""+ds[2].replaceAll(" ", "")+":"+tablenameAndcomment[1].replaceAll(";", ""));
				}else
				{
				
				if (myreadline.length() >= 1) {

					String[] part = myreadline.split(" ");
					for (int i = 0; i < part.length; i++) {
						if (!part[i].equals("")) {
							String t = part[i];
							t=t.replaceAll(" ", "");
							columns.add(t.toLowerCase().trim());
							System.out.println(t.toLowerCase());
							break;
						}
					}
				}
				}
				
				
				
			}

			br.close();
			fr.close();
			
			
           
			
			
			
			

			String m = "\n\n";
			m += "import javax.persistence.Entity;\n";
			m += "import javax.persistence.Id;\n";
			m += "import javax.persistence.Table;\n";

			m += "import org.apache.commons.lang.builder.ToStringBuilder;\n";
			m += "import org.hibernate.annotations.Cache;\n";
			m += "import org.hibernate.annotations.CacheConcurrencyStrategy;\n";

			m += "@Entity\n";
			m += "@Table(name = \"tablename..\")\n";
			m += "@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)\n";
			m += "public class classname.. {\n";

			for (int i = 0; i < columns.size(); i++) {
				String columnname=columns.get(i).toString();
				
				String comment="";
				for(int j=0;j<columnnameAndcomment.size();j++)
				{
					
					String tt=columnnameAndcomment.get(i).toString().toLowerCase();
					String tts[]=tt.split(":");
					System.out.println(tt);
					if(tts.length==2 )
					{
						if(columnname.contains(tts[0]))
						{
						String ds[]=tt.split("\\:");		
						if(ds.length==2)
						{
						comment=ds[1];
						break;
						}
					
						}
					
					}
				}
				m += "	private String " + columns.get(i) + "//"+comment+";\n";
			}

			for (int i = 0; i < columns.size(); i++) {

				String start = columns.get(i).toString().substring(0, 1)
						.toUpperCase();
				String end = columns.get(i).toString().substring(1)
						.toLowerCase();
				if (i == 0) {
					m += "	@Id\n";
					m += "	public String get" + start + end + "() {\n";
					m += "		return " + columns.get(i).toString() + ";\n";
					m += "	}\n";
					m += "	public void set" + start + end + "(String m"
							+ columns.get(i).toString() + ") {\n";
					m += "		" + columns.get(i).toString() + " = m"
							+ columns.get(i).toString() + ";\n";
					m += "	}\n";

				} else {

					m += "	public String get" + start + end + "() {\n";
					m += "		return " + columns.get(i).toString() + ";\n";
					m += "	}\n";
					m += "	public void set" + start + end + "(String m"
							+ columns.get(i).toString() + ") {\n";
					m += "		" + columns.get(i).toString() + " = m"
							+ columns.get(i).toString() + ";\n";
					m += "	}\n";

				}

			}
			m += "	public String toString() {\n";
			m += "        return ToStringBuilder.reflectionToString(this);\n";
			m += "    }\n";
			m += "}\n";

			System.out.println(m);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		TableScriptToHibernateEntity mark = new TableScriptToHibernateEntity();

	}

	public static boolean isinteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}





