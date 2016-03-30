package com.compoment.ui.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.compoment.jsonToJava.creater.WordtableToJavaObject.Group;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.InterfaceBean;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.Row;
import com.compoment.util.KeyValue;

public class WebAction {

	String className;

	String m = "";

	public WebAction(String pageName, List<InterfaceBean> interfaceBeans) {

		className = firstCharToUpperAndJavaName(pageName);

		if (interfaceBeans == null)
			return;

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

		for (InterfaceBean interfaceBean : interfaceBeans) {

			action(interfaceBean, "Respond");
		}

		net();
		m += "	}\n\n\n";
	}

	public void action(InterfaceBean interfaceBean, String type) {

		m += "/**" + interfaceBean.title + "*/\n";
		m += "	public String  " + interfaceBean.enName + "()throws Exception{\n";

		m += "		  HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m += "		  HttpSession session = request.getSession();\n";
		m += "	      CstmMsg cstmMsg = session.getAttribute(JiYouSessionKey.CSTMMSG.toString()) == null ? new CstmMsg()\n";
		m += "			: (CstmMsg) session.getAttribute(JiYouSessionKey.CSTMMSG.toString()); \n";

		List<String> mChirldClass = new ArrayList();
		String className = "RequestParam" + interfaceBean.id;

		// Request
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "int n" + interfaceBean.id + "=" + interfaceBean.id + ";\n";
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += className + " bean" + "=new " + className + "();\n";

		List<Group> groups = interfaceBean.requestGroups;
		int groupCount = 0;
		for (Group group : groups) {

			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {

					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {// 自定义对象

							isCustomerClass = true;
						} else {// 非自定义对象
							m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
							m += "bean." + row.enName + "=\"\";\n";

							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
							m += "List<String> " + row.enName + "List=new ArrayList();\n";
							m += "bean." + row.enName + "=" + row.enName + "List.toArray();\n";

						}
					}
					i++;
				}

				m += "}\n\n";

			} else {

				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m += "bean." + row.enName + "=\"\";\n";

				}
			}

		}

		m += "Gson gson = new Gson();\n";
		m += "String s  = gson.toJson(bean);\n";
		m += "String body=http(Urlbase+\"/Serverlet" + interfaceBean.id + "?parameter=s\");\n";

		
		
		// Respond
		String className2 = "RespondParam" + interfaceBean.id;
		String classNameForCache = "CacheRespondParam" + interfaceBean.id;
		m += "List<" + className2 + "> listData=new ArrayList();\n";

		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "if (requestCode == n" + interfaceBean.id + "){\n";

		m += "Gson gson = new Gson();\n";

		m += classNameForCache + " bean = gson.fromJson(body, " + classNameForCache + ".class);\n";

		List<Group> groups2 = interfaceBean.respondGroups;

		int groupCount2 = 0;
		for (Group group : groups2) {

			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {

					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {// 自定义对象

							isCustomerClass = true;
						} else {// 非自定义对象
							m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";

							m += "for(int i=0;i<bean." + row.enName + ";i++)\n{\n";
							m += className2 + " item" + groupCount2 + "=new " + className2 + "();\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
							m += "item" + groupCount2 + "." + row.enName + "=bean." + row.enName + "[i];\n";

						}
					}
					i++;
				}

				m += "}\n\n";

			} else {
				m += className2 + " commonItem" + "=new " + className2 + "();\n";
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m += "commonItem." + row.enName + "=bean." + row.enName + ";\n";

				}
			}
			groupCount2++;
		}
		m += "}\n\n";

	}

	public void net() {

		String m = "";
		m += "// 发起请求\n";
		m += "public String http(String url){\n";
		m += "				HttpClientManager httpClientManager = new HttpClientManager();\n";
		m += "				final HttpClientManager.NetErrBean netErrBean = httpClientManager\n";
		m += "						.httpGet(url);\n";
		m += "				if (netErrBean != null && netErrBean.errorCode.equals(HttpClientManager.connect000000)) {// 有数据返回 数据对不对看下面\n";

		m += "					String jsonStr = netErrBean.returnData;\n";
		m += "					\n";
		m += "					if(jsonStr==null||jsonStr.equals(\"\"))\n";
		m += "					{\n";

		m += "					}\n";

		m += "					\n";
		m += "					try {\n";
		m += "    					JSONObject jsonObject = new JSONObject(jsonStr);\n";

		m += "						String returnCode  = jsonObject.optString(\"returnCode\");\n";
		m += "						String dataJson = jsonObject.getString(\"returnData\");\n";
		m += "						if (returnCode.equals(\"000000\")) {// 调用成功\n";

		m += "							JSONObject returnData = new JSONObject(dataJson);\n";

		m += "							if (returnData != null && returnData.has(\"head\")) {\n";

		m += "								JSONObject HEAD = returnData\n";
		m += "										.optJSONObject(\"head\");\n";
		m += "								String body=returnData.getString(\"body\");\n";
		m += "								final String HOST_RET_ERR = HEAD\n";
		m += "										.optString(\"ret_errcode\");\n";
		m += "								final String HOST_RET_MSG = HEAD\n";
		m += "										.optString(\"ret_msg\");\n";
		m += "								if (!HOST_RET_ERR.equals(\"000000\")) {\n";

		m += "									if (HOST_RET_ERR.contains(\"2001\")) {\n";
		m += "										// token失效不做提示处理\n";
		m += "									} \n";

		m += "								} else \n";
		m += "								\n";
		m += "								{// 报文头 0000000 表示成功获取内容\n";

		m += "									return  body;\n";

		m += "								}\n";

		m += "							} else {// 没有报文头 head\n";

		m += "							}\n";

		m += "						} else {// returncode＝非 000000 调用失败\n";

		m += "						}\n";
		m += "					} catch (JSONException e) {// json格式出错\n";
		m += "						e.printStackTrace();\n";

		m += "					}\n";

		m += "				} else if (netErrBean != null\n";
		m += "						&& !netErrBean.errorCode.equals(HttpClientManager.connect000000)) {//网络不好 服务器没开  服务器太忙没响应\n";

		m += "				\n";
		m += "				}\n";
		m += "}\n";

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
