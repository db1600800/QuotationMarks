package com.compoment.ui.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class WebAction {

	String className;

	String m = "";

	public WebAction(String pageName) {

		className = firstCharToUpperAndJavaName(pageName);

		

		m += "import java.util.HashMap;\n";
		m += "import java.util.Iterator;\n";
		m += "import java.util.LinkedHashMap;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		m += "import java.util.Set;\n";

		m += "import javax.annotation.Resource;\n";
		m += "import javax.servlet.http.HttpServletRequest;\n";
		m += "import javax.servlet.http.HttpSession;\n";

		m += "import org.apache.commons.lang.StringUtils;\n";
		m += "import org.apache.struts2.convention.annotation.Action;\n";
		m += "import org.apache.struts2.convention.annotation.Namespace;\n";
		m += "import org.apache.struts2.convention.annotation.ParentPackage;\n";
		m += "import org.apache.struts2.convention.annotation.Result;\n";
		m += "import com.google.gson.Gson;\n";
		m += "import com.opensymphony.xwork2.ActionSupport;\n";

		m += "@SuppressWarnings(\"unchecked\")\n";
		m += "@ParentPackage(value = \"default\")\n";
		m += "@Namespace(value = \"/companyname/projectname/modulename\")//url里用的路径头(Action路径)\n";
		m += "@Action(value = \"" + className + "Action\" ,results = { \n";
		m += "		@Result(name = \"index\", location = \"/companyname/projectname/modulename/" + className
				+ ".jsp\"),\n";
		m += "})\n";

		m += "public class " + className + "Action  {\n";

		m+="//注入网络请求,响应,等待提示\n";
		m += "	}\n\n\n";
		
		
		
		
	
		FileUtil.makeFile(KeyValue.readCache("picPath"), "src/Jsp", className + "Action", "java", m);
	}

	

	

	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long") || type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (!s.equals("item"))
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return temp;
	}

	public void makeFile(String fileName, String s) {
		try {
			String doc = KeyValue.readCache("docPath");
			int p = doc.lastIndexOf("/");
			if (p == -1) {
				p = doc.lastIndexOf("\\");
			}

			File tofile = new File(doc.substring(0, p) + "/java/" + fileName + ".java");
			if (!tofile.exists()) {
				makeDir(tofile.getParentFile());
			}

			tofile.createNewFile();

			FileWriter fw;

			fw = new FileWriter(tofile);
			BufferedWriter buffw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffw);

			pw.println(s);

			pw.close();
			buffw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
}
