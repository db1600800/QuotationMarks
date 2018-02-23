package com.compoment.addfunction.webmanage.jspServletMybatis;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.addfunction.webmanage.jspStruct2Mybatis.Mybatis;
import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class ServletActionForWebManage {

	List<TableBean> tables;

	public ServletActionForWebManage(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		tables = changeToTableBeans(interfaceBeans);

		Mybatis mybatis = new Mybatis("", "", tables);

		for (InterfaceBean interfaceBean : interfaceBeans) {

			action(interfaceBean, "Respond", interfaceBean.enName);
		}
	}

	public void action(InterfaceBean interfaceBean, String type, String interfaceName) {
	
		// file
		String filebean = "";
		int fileCount = 0;

		String fileUpdate = "";

		// select
		String selectList = "";

		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {


					}

					if (row.type.toLowerCase().contains("file")) {
						fileCount++;
						filebean += "    private File file" + fileCount
								+ ";//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的\n";
						filebean += "    private String file" + fileCount + "FileName;//   上传输入域FileName  文件名\n";
						filebean += "    private String file" + fileCount + "ContentType;// 上传文件的MIME类型\n";

						filebean += "	public File getFile" + fileCount + "() {\n";
						filebean += "		return file" + fileCount + ";\n";
						filebean += "	}\n";
						filebean += "	public void setFile" + fileCount + "(File file" + fileCount + ") {\n";
						filebean += "		this.file" + fileCount + " = file" + fileCount + ";\n";
						filebean += "	}\n";
						filebean += "	public String getFile" + fileCount + "FileName() {\n";
						filebean += "		return file" + fileCount + "FileName;\n";
						filebean += "	}\n";
						filebean += "	public void setFile" + fileCount + "FileName(String file" + fileCount
								+ "FileName) {\n";
						filebean += "		this.file" + fileCount + "FileName = file" + fileCount + "FileName;\n";
						filebean += "	}\n";
						filebean += "	public String getFile" + fileCount + "ContentType() {\n";
						filebean += "		return file" + fileCount + "ContentType;\n";
						filebean += "	}\n";
						filebean += "	public void setFile" + fileCount + "ContentType(String file" + fileCount
								+ "ContentType) {\n";
						filebean += "		this.file" + fileCount + "ContentType = file" + fileCount
								+ "ContentType;\n";
						filebean += "	}\n";

						fileUpdate += "		  if(file" + fileCount + " != null){\n";
						fileUpdate += "		 ServletContext sc = ServletActionContext.getServletContext();\n";
						fileUpdate += "		       	 String activityBannerPath=CommonFunction.readDefVal(\"activityBannerPath\");\n";
						fileUpdate += "		            String storePath = sc.getRealPath(activityBannerPath+entity.getActivity_code());\n";
						fileUpdate += "		            FileUtils.copyFile(file" + fileCount + ", new File(storePath,\""
								+ row.enName.toLowerCase().replaceAll("", "") + ".jpg\"));\n";
						fileUpdate += "		            entity.set" + this.firstCharUpperCase(row.enName.toLowerCase())
								+ "(activityBannerPath+entity.getActivity_code()+\"/"
								+ row.enName.toLowerCase().replaceAll("", "") + ".jpg\");\n";
						fileUpdate += "		       }\n";

					}

					if (row.type.toLowerCase().contains("select")) {

						selectList += "\n//" + row.cnName + "\n";
						selectList += "List " + row.enName.toLowerCase() + "list=new ArrayList();\n";
						selectList += "//for(int i=0;i<list.size();i++)\n";
						selectList += "//{\n";

						String re1 = ".*?"; // Non-greedy match on filler
						String re2 = "(\\d+)"; // Integer Number 1
						String re3 = ".*?"; // Non-greedy match on filler
						String re4 = "(:|：)"; // Any Single Character 1
						String re5 = ".*?"; // Non-greedy match on filler
						String re6 = "([\u4e00-\u9fa5]+)"; // Any Single Word Character (Not Whitespace) 1

						Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6,
								Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
						Matcher m = p.matcher(row.cnName);
						while (m.find()) {
							String int1 = m.group(1);
							String c1 = m.group(2);
							String w1 = m.group(3);

							selectList += "	" + row.enName.toLowerCase() + "list.add(\"" + int1.toString()
									+ "\"+\"-\"+\"" + w1.toString() + "\");\n";
						}

						selectList += "//}\n";
						selectList += "request.setAttribute(\"" + row.enName.toLowerCase() + "SelectList\","
								+ row.enName.toLowerCase() + "list);\n";
					}
				}
			}
		}

		String m = "";

		m += "import java.io.File;\n";
		m += "import java.io.IOException;\n";
		m += "import java.util.HashMap;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		m += "import java.util.ArrayList;\n";
		m += "import javax.annotation.Resource;\n";
		m += "import javax.servlet.ServletContext;\n";
		m += "import javax.servlet.http.HttpServletRequest;\n";

		m += "import net.sf.json.JSONObject;\n";

		m += "import org.apache.commons.io.FileUtils;\n";
		m += "import org.apache.commons.lang.StringUtils;\n";
		m += "import java.io.PrintWriter;\n";

		m += "import javax.servlet.RequestDispatcher;\n";
		m += "import javax.servlet.ServletException;\n";
		m += "import javax.servlet.http.HttpServlet;\n";
		m += "import javax.servlet.http.HttpServletRequest;\n";
		m += "import javax.servlet.http.HttpServletResponse;\n";
		
		m += "import org.apache.commons.fileupload.FileItem;\n";
		m += "import org.apache.commons.fileupload.FileItemFactory;\n";
		m += "import org.apache.commons.fileupload.disk.DiskFileItemFactory;\n";
		m += "import org.apache.commons.fileupload.servlet.ServletFileUpload;\n";

		m += "import com.tools.CommonFunction;\n";

		m += "import com.tools.PaginationUtil;\n";

		m += "//" + interfaceBean.title + "\n";
		
		
		m += "public class " + interfaceBean.enName + "Servlet extends HttpServlet  {\n";
		m += "	\n";
		
		m += "    private " + interfaceBean.enName + "Bean entity;\n";

		m += filebean;

		m += "	public " + interfaceBean.enName + "Bean getEntity() {\n";
		m += "		return entity;\n";
		m += "	}\n";
		m += "	public void setEntity(" + interfaceBean.enName + "Bean  " + interfaceBean.enName.toLowerCase()
				+ ") {\n";
		m += "		this.entity = " + interfaceBean.enName.toLowerCase() + ";\n";
		m += "	}\n";
		m += "	\n";
		
		

		m += "    public void doGet(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws ServletException, IOException {\n";
		m += "        doPost(request, response);\n";
		m += "    }\n";

		m += "    public void doPost(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws ServletException, IOException {\n";
		m += "        //获取对应的请求参数\n";
		m += "        String method = request.getParameter(\"method\");\n";
		m += "        //根据请求参数去调用对应的方法。\n";
	
		m += "        if (\"index\".equals(method)) {\n";
		m += "        	index(request, response);\n";
		m += "        } \n";
		m += "        if (\"list\".equals(method)) {\n";
		m += "        	list(request, response);\n";
		m += "        } \n";
		m += "        if (\"toUpdate\".equals(method)) {\n";
		m += "        	toUpdate(request, response);\n";
		m += "        } \n";
		m += "        if (\"doUpdate\".equals(method)) {\n";
		m += "        	doUpdate(request, response);\n";
		m += "        } \n";
		m += "        if (\"toAdd\".equals(method)) {\n";
		m += "        	toAdd(request, response);\n";
		m += "        } \n";
		m += "        if (\"doAdd\".equals(method)) {\n";
		m += "        	doAdd(request, response);\n";
		m += "        } \n";
		m += "        if (\"doDelete\".equals(method)) {\n";
		m += "        	delete(request, response);\n";
		m += "        } \n";
		m += "    }\n";
		

//     	m += "    private void demo(HttpServletRequest request, HttpServletResponse response) {\n";
//		
//		m += "     \n //第一步 取值  \n";
//		m += "        //取值  ajax提交的数据  \n";
//		m+="          request.setCharacterEncoding(\"UTF-8\");\n";
//		m += "        String method = request.getParameter(\"method\"); //参数用html里name=\"\"的值\n";
//		m += "        \n";
//		m += "        \n";
//		m += "      //取值  form表单提交的数据  method=\"post\" enctype=\"multipart/form-data\"\n";
//		m += "        if(ServletFileUpload.isMultipartContent(request)) \n";
//		m += "        {\n";
//		m += "            FileItemFactory factory = new DiskFileItemFactory();\n";
//		m += "            ServletFileUpload upload = new ServletFileUpload(factory);\n";
//		m += "            List<FileItem> items = upload.parseRequest(request);\n";
//		m += "            for(FileItem i: items)\n";
//		m += "            {\n";
//		m += "                i.getFieldName();  　　//参数名\n";
//		m += "                //i.getString();   　　//参数值（返回字符串），如果是上传文件，则为文件内容\n";
//		m += "        　　　　 //i.get();         　　//参数值（返回字节数组），如果是上传文件，则为文件内容\n";
//		m+="//i.getInputStream();//上传文件内容\n";
//		m += "        　　　　 //i.getSize();　　　　　//参数值的字节大小\n";
//		m += "        　　　　 //i.getName();   　 　 //上传文件的文件名\n";
//		m += "        　　　　 //i.getContentType();  //上传文件的内容类型\n";
//		m += "        　　　　 if(!i.isFormField()&&i.getSize()>0)　　 //简单参数返回true，文件返回false \n";
//		m += "  {\n";
//		m+="ServletContext servletContext = request.getSession().getServletContext();\n";
//		m+="//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录	\n";
//		m+="String realPath = servletContext.getRealPath(\"/WEB-INF/file\");\n";
//		m+="//3.如果这个真实的目录不存在，需要创建\n";
//		m+="File file = new File(realPath );\n";
//		m+="if(!file.exists()){\n";
//		m+="file.mkdirs();\n";
//		m+="}\n";
//		m+="myfile.renameTo(new File(file,myfileFileName));\n";
//		m += "        　　}\n";
//		
//		m += "        　　}\n";
//		m += "        }\n";
//		m += "        \n";
//		
//		
//		m += "      \n //第二步  发网络请求或发数据库请求 \n";
//		m += "        \n";
//		
//		
//		m += "       \n //第三步  正确跳转到哪  错误跳转到哪  一般用forward\n";
//		m += "        //A跳到新页面\n";
//		m += "        // 1.\n";
//		m += "        try {\n";
//		m += "			response.sendRedirect(\"/a.jsp\");//servlet?name=tom 通过get方法传递数据到下个页面(本域名下页面或跨域页面) 跳转后浏览器地址栏变化。\n";
//		m += "		} catch (IOException e) {\n";
//		m += "			// TODO Auto-generated catch block\n";
//		m += "			e.printStackTrace();\n";
//		m += "		}\n";
//		m += "        \n";
//		m += "         //2.\n";
//		m += "         request.setAttribute(\"strRequest\", \"\"); \n";
//		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/a.jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
//		m += "        try {\n";
//		m += "			dispatcher .forward(request, response);\n";
//		m += "		} catch (ServletException e) {\n";
//		m += "			// TODO Auto-generated catch block\n";
//		m += "			e.printStackTrace();\n";
//		m += "		} catch (IOException e) {\n";
//		m += "			// TODO Auto-generated catch block\n";
//		m += "			e.printStackTrace();\n";
//		m += "		}\n";
//		m += "        \n";
//		m += "        \n";
//		m += "        //B跳回到本页面(带参数)\n";
//		m += "      response.setCharacterEncoding(\"utf-8\");\n";
//		m += "		response.setContentType(\"application/json\");\n";
//		m += "		PrintWriter out = response.getWriter();\n";
//		m += "		\n";
//		m += "		JSONArray jsonArray = JSONArray.fromObject(rows);\n";
//		m += "		\n";
//		m += "		out.write(\"{\"returnCode\":\"00\",\"info\":\"成功。\",\"returnData\":\" + jsonArray.toString()+ \"}\");\n";
//		m += "        out.flush();\n";
//		m += "        out.close();\n";
//		m += "        \n";
//		m += "        \n";
//		m += "        \n";
//		m += "    }\n";
//
//		m += " \n";
//		m += "}\n";
//		
		

		m += "	public void index(HttpServletRequest request, HttpServletResponse response){\n";

		String toUpdateInKeyString = "";

		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {

						// toUpdate()
						if (row.type.toLowerCase().contains("int")) {

							toUpdateInKeyString += "int " + row.enName.toLowerCase()
									+ " =Integer.valueOf( request.getParameter(\"" + row.enName.toLowerCase()
									+ "\", \"0\"));\n";

						} else {
							toUpdateInKeyString += "String " + row.enName.toLowerCase()
									+ " = request.getParameter(\"" + row.enName.toLowerCase()
									+ "\", \"\");\n";
						}
						toUpdateInKeyString += "request.setAttribute(\"" + row.enName.toLowerCase() + "\", "
								+ row.enName.toLowerCase() + ");\n";

					}
				}
			}
		}
		m += toUpdateInKeyString;
		
	
		
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/"+interfaceBean.enName.toLowerCase()+".jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		
		
	
		m += "	}\n";

		
		
		
		
		
		
		
		
		m += "	//" + interfaceBean.title + "列表\n";
		m += "	public void list(HttpServletRequest request, HttpServletResponse response){\n";
	

		m += "		String pageNo = request.getParameter(\"pageNo\");\n";
		m += "		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m += "			pageNo = \"1\";\n";
		m += "			request.setAttribute(\"pageNo\", pageNo);\n";
		m += "		}\n";

		m += "		String pageSize = request.getParameter(\"pageSize\");\n";
		m += "		if (StringUtils.isBlank(pageSize)) {\n";
		m += "			pageSize = \"10\";\n";
		m += "			request.setAttribute(\"pageSize\", pageSize);\n";
		m += "		}\n";

		m += "Map paraMap=new HashMap();\n";
		m += "paraMap.put(\"currIndex\", (Integer.valueOf(pageNo) - 1) * Integer.valueOf(pageSize));\n";
		m += "paraMap.put(\"pageSize\", pageSize);\n";

		String listInKeyString = "";
		String nextPageKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {
					}

					// list()
					listInKeyString += "	String " + row.enName.toLowerCase()
							+ " = request.getParameter(\"" + row.enName.toLowerCase() + "\", \"\");\n";

					listInKeyString += "		if(StringUtils.isBlank(" + row.enName.toLowerCase() + ")){\n";
					listInKeyString += "			//return;\n";
					listInKeyString += "		}else{\n";

					if (row.type.toLowerCase().contains("int")) {

						listInKeyString += "paraMap.put(\"" + row.enName.toLowerCase() + "\",Integer.valueOf( "
								+ row.enName.toLowerCase() + "));\n";
					} else {

						listInKeyString += "paraMap.put(\"" + row.enName.toLowerCase() + "\", "
								+ row.enName.toLowerCase() + ");\n";
					}
					listInKeyString += "}\n";

					nextPageKeyString += row.enName.toLowerCase() + "=\"+" + row.enName.toLowerCase() + "+\"%26";
				}
			}
		}

		m += listInKeyString;

		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "List<" + interfaceName + "Bean> " + interfaceName.toLowerCase() + "Beans=null;\n";

		m += "try {\n";
		m += interfaceName.toLowerCase() + "Beans=" + StringUtil.firstCharToLower(interfaceName)
				+ "Service.get(paraMap);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n\n";

		m += "int count=0;\n";
		m += "try {\n";
		m += "count=" + StringUtil.firstCharToLower(interfaceName) + "Service.getCount(paraMap);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n\n";

		m += "		String pageString = PaginationUtil.getPaginationHtml(\n";
		m += "				Integer.valueOf(count), Integer.valueOf(pageSize),\n";
		m += "				Integer.valueOf(pageNo), Integer.valueOf(2),\n";
		m += "				Integer.valueOf(5),\n";
		m += "				\"javascript:getAll('" + interfaceBean.enName + "Action!list?" + nextPageKeyString
				+ "pageNo=\"+pageNo+\"\",true);\n";
		m += "		pageString = pageString.replace(\".html\", \"\");\n";
		m += "		JSONObject jsonObject = new JSONObject();\n";
		m += "		jsonObject.put(\"list\", " + interfaceName.toLowerCase() + "Beans);\n";
		m += "		jsonObject.put(\"pageString\", pageString);\n";
		m += "		jsonObject.put(\"count\", count);\n";
		m += "		try{\n";
		m += "		response.setCharacterEncoding(\"UTF-8\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		out.write(jsonObject.toString());\n";
		m += "      out.flush();\n";
		m += "      out.close();\n";
		
		m += "		}catch(Exception e){\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		m += "	}\n";
		m += "	\n";
		m += "	\n";
		
		
		
		
		
		
		

		m += "//跳到修改页\n";
		m += "	public void toUpdate(HttpServletRequest request, HttpServletResponse response) {\n";

	

		m += "//选择器数据\n";
		m += selectList;

		m += "//页面数据\n";

		m += "Map paraMap=new HashMap();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {

						// list()
						listInKeyString += "	String " + row.enName.toLowerCase()
								+ " = request.getParameter(\"" + row.enName.toLowerCase() + "\", \"\");\n";

						listInKeyString += "		if(StringUtils.isBlank(" + row.enName.toLowerCase()
								+ ")){\n";
						listInKeyString += "			//return;\n";
						listInKeyString += "		}else{\n";

						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += "paraMap.put(\"" + row.enName.toLowerCase() + "\",Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += "paraMap.put(\"" + row.enName.toLowerCase() + "\", "
									+ row.enName.toLowerCase() + ");\n";
						}
						listInKeyString += "}\n";

					}
				}
			}
		}

		m += listInKeyString;

		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "List<" + interfaceName + "Bean> " + interfaceName.toLowerCase() + "Beans=null;\n";
		m += "try {\n";
		m += interfaceName.toLowerCase() + "Beans=" + StringUtil.firstCharToLower(interfaceName)
				+ "Service.get(paraMap);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";

		m += "		if (" + interfaceName.toLowerCase() + "Beans!=null && " + interfaceName.toLowerCase()
				+ "Beans.size()==1) {\n";

		m += "				request.setAttribute(\"entity\", (" + interfaceBean.enName + "Bean) " + interfaceName.toLowerCase() + "Beans.get(0));\n";

		
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/"+interfaceBean.enName.toLowerCase()+"Setting.jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		
	
		m += "		}else{\n";
		
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/"+interfaceBean.enName.toLowerCase()+"Add.jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
	
		
		m += "		}\n";
		m += "		\n";
		m += "	}\n";

		
		
		
		
		
		
		m += "	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {\n";

		m += fileUpdate;

		m += interfaceName + "Bean " + StringUtil.firstCharToLower(interfaceName) + "Bean=new " + interfaceName
				+ "Bean();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
						// list()
						listInKeyString += "	String " + row.enName.toLowerCase()
								+ " = request.getParameter(\"" + row.enName.toLowerCase() + "\", \"\");\n";

						listInKeyString += "		if(StringUtils.isBlank(" + row.enName.toLowerCase()
								+ ")){\n";
						listInKeyString += "			//return;\n";
						listInKeyString += "		}else{\n";

						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"(Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"("+ row.enName.toLowerCase() + ");\n";
						}
						listInKeyString += "}\n";
					

				}
			}
		}

		m += listInKeyString;
		
		
		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "try {\n";
		m += StringUtil.firstCharToLower(interfaceName) + "Service.update("+StringUtil.firstCharToLower(interfaceName) + "Bean);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		
		
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/"+interfaceBean.enName.toLowerCase()+".jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		
	
		m += "	}\n";

		
		
		
		
		
		
		
		m += "//跳到新增页\n";
		m += "	public void toAdd(HttpServletRequest request, HttpServletResponse response) {\n";
	
		m += "//选择器数据\n";
		m += selectList;

		m += "//页面数据\n";

		m += "Map paraMap=new HashMap();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {

						// list()
						listInKeyString += "	String " + row.enName.toLowerCase()
								+ " = request.getParameter(\"" + row.enName.toLowerCase() + "\", \"\");\n";

						listInKeyString += "		if(StringUtils.isBlank(" + row.enName.toLowerCase()
								+ ")){\n";
						listInKeyString += "			//return;\n";
						listInKeyString += "		}else{\n";

						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += "request.setAttribute(\"" + row.enName.toLowerCase()
									+ "\",Integer.valueOf( " + row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += "request.setAttribute(\"" + row.enName.toLowerCase() + "\", "
									+ row.enName.toLowerCase() + ");\n";
						}
						listInKeyString += "}\n";
					}

				}
			}
		}

		m += listInKeyString;

		
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/"+interfaceBean.enName.toLowerCase()+"Add.jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";


		m += "		\n";
		m += "	}\n";

		
		
		
		
		
		
		m += "	\n";
		m += "	public void doAdd(HttpServletRequest request, HttpServletResponse response)  throws IOException{\n";
	
		m += fileUpdate;


		m += interfaceName + "Bean " + StringUtil.firstCharToLower(interfaceName) + "Bean=new " + interfaceName
				+ "Bean();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
						// list()
						listInKeyString += "	String " + row.enName.toLowerCase()
								+ " = request.getParameter(\"" + row.enName.toLowerCase() + "\", \"\");\n";

						listInKeyString += "		if(StringUtils.isBlank(" + row.enName.toLowerCase()
								+ ")){\n";
						listInKeyString += "			//return;\n";
						listInKeyString += "		}else{\n";

						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"(Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"("+ row.enName.toLowerCase() + ");\n";
						}
						listInKeyString += "}\n";
					

				}
			}
		}

		m += listInKeyString;

		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "try {\n";
		m += StringUtil.firstCharToLower(interfaceName) + "Service.insert("+StringUtil.firstCharToLower(interfaceName) + "Bean);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";

		
		
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/"+interfaceBean.enName.toLowerCase()+".jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
	
		m += "	}\n";

		
		
		
		
		
		
		
		
		m += "	\n";
		m += "	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{\n";
		

		m += interfaceName + "Bean " + StringUtil.firstCharToLower(interfaceName) + "Bean=new " + interfaceName
				+ "Bean();\n";
		listInKeyString = "";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (row.remarks.toLowerCase().contains("key")) {

						// list()
						listInKeyString += "	String " + row.enName.toLowerCase()
								+ " = request.getParameter(\"" + row.enName.toLowerCase() + "\", \"\");\n";

						listInKeyString += "		if(StringUtils.isBlank(" + row.enName.toLowerCase()
								+ ")){\n";
						listInKeyString += "			//return;\n";
						listInKeyString += "		}else{\n";

						if (row.type.toLowerCase().contains("int")) {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"(Integer.valueOf( "
									+ row.enName.toLowerCase() + "));\n";
						} else {

							listInKeyString += StringUtil.firstCharToLower(interfaceName) + "Bean.set"+ StringUtil
									.firstCharToUpperAndJavaName(row.enName)+"("+ row.enName.toLowerCase() + ");\n";
						}
						listInKeyString += "}\n";
					}

				}
			}
		}

		m += listInKeyString;
		
		m += interfaceName + "Service " + StringUtil.firstCharToLower(interfaceName) + "Service=new " + interfaceName
				+ "ServiceImpl();\n";
		m += "try {\n";
		m += StringUtil.firstCharToLower(interfaceName) + "Service.delete("+StringUtil.firstCharToLower(interfaceName) + "Bean);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";

		
		m += "		response.setCharacterEncoding(\"UTF-8\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		out.write(\"success\");\n";
		m += "      out.flush();\n";
		m += "      out.close();\n";
		

		m += "	}\n";
		m += "	\n";
		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", interfaceBean.enName + "Action", "java",
				m);

		System.out.println(m);
	}

	/** 首字母大写 */
	public static String firstCharUpperCase(String s) {
		if (s.length() > 0) {
			String firstchar = String.valueOf(s.charAt(0)).toUpperCase();
			s = s.substring(1);
			s = firstchar + s;
			return s;
		} else {
			return null;
		}

	}

	public List changeToTableBeans(List<InterfaceBean> interfaceBeans) {
		List tables = new ArrayList();

		for (InterfaceBean interfaceBean : interfaceBeans) {
			// 数据表
			TableBean tableBean = new TableBean();

			tableBean.tableCnName = interfaceBean.title;// 表中文名
			tableBean.tableEnName = interfaceBean.enName;// 表英文名
			tableBean.id = interfaceBean.id;// 表编号
			tableBean.columns = new ArrayList();// 列数组

			List<Group> groups = interfaceBean.respondGroups;
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int rowCount = 0;

					Collections.sort(group.rows, rowDate);
					for (Row row : group.rows) {

						TableColumnBean tableColumnBean = new TableColumnBean();

						tableColumnBean.setColumnCnName(row.cnName);

						tableColumnBean.setColumnEnName(row.enName);

						tableColumnBean.setKey(row.remarks);

						tableColumnBean.setType(row.type);

						List<Integer> widths = new ArrayList();

						tableBean.columns.add(tableColumnBean);
						Collections.sort(tableBean.columns, tableColumnBeanDate);

						rowCount++;
					}
				}
			}

			if (tableBean.columns != null && tableBean.columns.size() > 0) {// （最后一列）
				TableColumnBean lastColumnBean = tableBean.columns.get(tableBean.columns.size() - 1);
				tableBean.x1 = lastColumnBean.x1;
				tableBean.y1 = lastColumnBean.y1;
			} else {
				tableBean.x1 = 0;
				tableBean.y1 = 0;
			}

			tables.add(tableBean);
			Collections.sort(tables, tableBeanDate);
		}

		return tables;
	}

	Comparator<Row> rowDate = new Comparator<Row>() {
		public int compare(Row s1, Row s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	Comparator<TableBean> tableBeanDate = new Comparator<TableBean>() {
		public int compare(TableBean s1, TableBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	Comparator<TableColumnBean> tableColumnBeanDate = new Comparator<TableColumnBean>() {
		public int compare(TableColumnBean s1, TableColumnBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	public List<TableColumnBean> getQueryConditionColumns(List<TableBean> tables) {

		List<TableColumnBean> queryConditionColumns = new ArrayList();

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("right".equals(column.rightClickSelected)) {
					String tablename = StringUtil.tableName(column.belongWhichTable.tableEnName);
					String shortTableName = tablename.substring(tablename.lastIndexOf("_") + 1);

					queryConditionColumns.add(column);
				}

			}
		}
		return queryConditionColumns;
	}

	public List<TableColumnBean> getResultColumns(List<TableBean> tables) {

		List<TableColumnBean> resultColumns = new ArrayList();
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					String tablename = StringUtil.tableName(column.belongWhichTable.tableEnName);
					String shortTableName = tablename.substring(tablename.lastIndexOf("_") + 1);

					resultColumns.add(column);

				}

			}
		}
		return resultColumns;
	}
}
