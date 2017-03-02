package com.compoment.addfunction.webmanage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.KeyValue;

public class TableToHibernateEntity {
	
	
	public TableToHibernateEntity(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			tableScriptToHibernateEntity(interfaceBean, "Respond");
		}
	}
	
	public void tableScriptToHibernateEntity(InterfaceBean interfaceBean ,String type)
	{
		


			

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
			m += "public class "+interfaceBean.enName+"Entity {\n";
			
			
			List<Group> groups = interfaceBean.respondGroups;
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int i = 0;
					for (Row row : group.rows) {
					
							
							m += "	private String " + row.enName.toLowerCase() + ";//"+row.cnName+"\n";
						
						i++;
					}
				}

			}

			
			
			
			
			
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int i = 0;
					for (Row row : group.rows) {
						String start = row.enName.substring(0, 1)
								.toUpperCase();
						String end = row.enName.substring(1)
								.toLowerCase();
						if (i == 0) {// 循环域开始
							m += "	@Id\n";
							m += "	public String get" + start + end + "() {\n";
							m += "		return " + row.enName.toLowerCase() + ";\n";
							m += "	}\n";
							m += "	public void set" + start + end + "(String m"
									+ row.enName.toLowerCase() + ") {\n";
							m += "		" + row.enName.toLowerCase() + " = m"
									+ row.enName.toLowerCase() + ";\n";
							m += "	}\n";
						} else {
							
							m += "	public String get" + start + end + "() {\n";
							m += "		return " + row.enName.toLowerCase() + ";\n";
							m += "	}\n";
							m += "	public void set" + start + end + "(String m"
									+ row.enName.toLowerCase() + ") {\n";
							m += "		" + row.enName.toLowerCase() + " = m"
									+ row.enName.toLowerCase() + ";\n";
							m += "	}\n";
						}
						i++;
					}
				}

			}
		

			
			m += "	public String toString() {\n";
			m += "        return ToStringBuilder.reflectionToString(this);\n";
			m += "    }\n";
			m += "}\n";

			makeFile(  interfaceBean.enName+"Entity",m);
			System.out.println(m);
	

		
	}
	
	public void makeFile(String fileName,String s)
	{
		try {
		String doc=KeyValue.readCache("docPath");
		int p=doc.lastIndexOf("/");
		if(p==-1)
		{
			 p=doc.lastIndexOf("\\");
		}
		
		
		      
		    
		
		File tofile=new File(doc.substring(0, p)+"/java/"+fileName+".java");
		  if(! tofile.exists()) {  
	            makeDir(tofile.getParentFile());  
	        }  
	      
		  tofile.createNewFile(); 
		
		FileWriter fw;
		
			fw = new FileWriter(tofile);
			BufferedWriter buffw=new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(buffw);
		
		

	
		pw.println(s);

		pw.close();
		buffw.close();
		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  public  void makeDir(File dir) {  
	        if(! dir.getParentFile().exists()) {  
	            makeDir(dir.getParentFile());  
	        }  
	        dir.mkdir();  
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




