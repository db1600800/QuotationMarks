package com.compoment.ui.web;

public class WebServlet {

	public void create() {

		String m = "";

		m += "import java.io.IOException;\n";
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

		m += "import net.sf.json.JSONArray;\n";

		m += "public class mServlet extends HttpServlet {\n";

		m += "    private static final long serialVersionUID = 1L;\n";

		m += "    public void doGet(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws ServletException, IOException {\n";
		m += "        doPost(request, response);\n";
		m += "    }\n";

		m += "    public void doPost(HttpServletRequest request, HttpServletResponse response)\n";
		m += "            throws ServletException, IOException {\n";
		m += "        //获取对应的请求参数\n";
		m += "        String method = request.getParameter(\"method\");\n";
		m += "        //根据请求参数去调用对应的方法。\n";
		m += "        if (\"init\".equals(method)) {\n";
		m += "        	method1(request, response);\n";
		m += "        } \n";
		m += "        if (\"method1\".equals(method)) {\n";
		m += "        	method1(request, response);\n";
		m += "        } \n";
		m += "    }\n";
		m += "    \n";
		m += "    private void init(HttpServletRequest request, HttpServletResponse response) {\n";
		m += "    	\n";
		m += "    	\n";
		m += "    }\n";

		m += "    \n";
		m += "   \n";
		m += "    private void method1(HttpServletRequest request, HttpServletResponse response) {\n";
		m += "        \n";
		m += "        //取值  ajax提交的数据  \n";
		m += "        String method = request.getParameter(\"method\"); //参数用html里name=\"\"的值\n";
		m += "        \n";
		m += "        \n";
		m += "      //取值  form表单提交的数据  multipart/form-data\n";
		m += "        if(ServletFileUpload.isMultipartContent(request)) \n";
		m += "        {\n";
		m += "            FileItemFactory factory = new DiskFileItemFactory();\n";
		m += "            ServletFileUpload upload = new ServletFileUpload(factory);\n";
		m += "            List<FileItem> items = upload.parseRequest(request);\n";
		m += "            for(FileItem i: items)\n";
		m += "            {\n";
		m += "                i.getFieldName();  　　//参数名\n";
		m += "                //i.getString();   　　//参数值（返回字符串），如果是上传文件，则为文件内容\n";
		m += "        　　　　 //i.get();         　　//参数值（返回字节数组），如果是上传文件，则为文件内容\n";
		m += "        　　　　 //i.getSize();　　　　　//参数值的字节大小\n";
		m += "        　　　　 //i.getName();   　 　 //上传文件的文件名\n";
		m += "        　　　　 //i.getContentType();  //上传文件的内容类型\n";
		m += "        　　　　 if(!i.isFormField()&&i.getSize()>0)　　 //简单参数返回true，文件返回false \n";
		m += "        　　　　　　Files.write(Paths.get(\"/upload/\"+Paths.get(i.getName()).getFileName()), i.get());      \n";
		m += "        　　}\n";
		m += "        }\n";
		m += "        \n";
		m += "        \n";
		m += "        \n";
		m += "        \n";
		m += "        //跳到新页面\n";
		m += "        // 1.\n";
		m += "        try {\n";
		m += "			response.sendRedirect(\"/a.jsp\");//servlet?name=tom 通过get方法传递数据到下个页面(本域名下页面或跨域页面) 跳转后浏览器地址栏变化。\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		m += "        \n";
		m += "         //2.\n";
		m += "         request.setAttribute(\"strRequest\", \"\"); \n";
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/a.jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		m += "        \n";
		m += "        \n";
		m += "        \n";
		m += "        \n";
		m += "        //跳回到本页面(带参数)\n";
		m += "        response.setCharacterEncoding(\"utf-8\");\n";
		m += "		response.setContentType(\"application/json\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		\n";
		m += "		JSONArray jsonArray = JSONArray.fromObject(rows);\n";
		m += "		\n";
		m += "		out.write(\"{\"status\":\"true\",\"info\":\"导入成功。\",\"rows\":\" + jsonArray.toString()+ \"}\");\n";
		m += "        out.flush();\n";
		m += "        out.close();\n";
		m += "        \n";
		m += "        \n";
		m += "        \n";
		m += "    }\n";

		m += " \n";
		m += "}\n";

	}

}