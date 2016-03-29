package com.compoment.addfunction.web;

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

public class Action {


	 String className;
	
	public Action(String pageName,List<InterfaceBean> interfaceBeans) {
		
		className = firstCharToUpperAndJavaName(pageName);
		
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			action(interfaceBean, "Respond");
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
	
	public void action(InterfaceBean interfaceBean ,String type)
	{
		
		String m="";
		m+="import java.util.HashMap;\n";
		m+="import java.util.Iterator;\n";
		m+="import java.util.LinkedHashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";
		m+="import java.util.Set;\n";

		m+="import javax.annotation.Resource;\n";
		m+="import javax.servlet.http.HttpServletRequest;\n";
		m+="import javax.servlet.http.HttpSession;\n";

		m+="import org.apache.commons.lang.StringUtils;\n";
		m+="import org.apache.struts2.convention.annotation.Action;\n";
		m+="import org.apache.struts2.convention.annotation.Namespace;\n";
		m+="import org.apache.struts2.convention.annotation.ParentPackage;\n";
		m+="import org.apache.struts2.convention.annotation.Result;\n";
		m+="import com.google.gson.Gson;\n";
		m+="import com.opensymphony.xwork2.ActionSupport;\n";
	
		
		m+="@SuppressWarnings(\"unchecked\")\n";
		m+="@ParentPackage(value = \"default\")\n";
		m+="@Namespace(value = \"/companyname/projectname/modulename\")//url里用的路径头(Action路径)\n";
		m+="@Action(value = \""+className+"Action\" ,results = { \n";
		m+="		@Result(name = \"index\", location = \"/companyname/projectname/modulename/"+className+".jsp\"),\n";
		m+="})\n";
		
		

		m+="public class "+className+"Action  {\n";
	

		m+="/**"+interfaceBean.title+"*/\n";
		m+="	public String  "+interfaceBean.enName+"()throws Exception{\n";
		
		m+="		  HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		  HttpSession session = request.getSession();\n";
		m+="	      CstmMsg cstmMsg = session.getAttribute(JiYouSessionKey.CSTMMSG.toString()) == null ? new CstmMsg()\n";
		m+="			: (CstmMsg) session.getAttribute(JiYouSessionKey.CSTMMSG.toString()); \n";
		
		
		
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RequestParam" + interfaceBean.id ;	
		
	
		
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m+="int n"+interfaceBean.id+"="+interfaceBean.id +";\n";
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "public void request"+interfaceBean.id+"(){\n";
		
		 m+=className+" bean"+"=new "+className+"();\n";
		 
		List<Group> groups = interfaceBean.requestGroups;
		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
						    m+="bean."+row.enName+"=\"\";\n";
							
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m+="List<String> "+row.enName+"List=new ArrayList();\n";
							m += "bean."+row.enName+"="+row.enName+ "List.toArray();\n";
						
						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m +=  "bean."+row.enName+"=\"\";\n";

				}
			}

		}
		
		
		m+="Gson gson = new Gson();\n";
		m+= "String s  = gson.toJson(bean);\n";
		
		
		m+="Intent intent = new Intent();\n";
		m+="intent.setClass(.this,WaitActivity.class);\n";
		m+="Bundle bundle = new Bundle();\n";
		m+="bundle.putString(\"url\",WaitActivity.urlbase+\"/Serverlet"+interfaceBean.id+"?parameter=s\");\n";
		m+="intent.putExtras(bundle);\n";
		m+="startActivityForResult(intent,n"+interfaceBean.id+");\n";
		m += "}\n\n";
		
	
		
		
		
		
		
		
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RespondParam" + interfaceBean.id ;	
		String classNameForCache="CacheRespondParam" + interfaceBean.id ;
		m+="List<"+className+"> listData=new ArrayList();\n";
		
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "if (requestCode == n"+interfaceBean.id +"){\n";

		m+="Gson gson = new Gson();\n";
	
		m+= classNameForCache+" bean = gson.fromJson(body, "+classNameForCache+".class);\n";
		
		List<Group> groups = interfaceBean.respondGroups;
		

		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
						  
							m+="for(int i=0;i<bean."+row.enName+";i++)\n{\n";
							  m+=className+" item"+groupCount+"=new "+className+"();\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "item"+groupCount+"."+row.enName+"=bean." + row.enName
									+ "[i];\n";
						
						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				 m+=className+" commonItem"+"=new "+className+"();\n";
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m +=  "commonItem." + row.enName + "=bean."+row.enName+";\n";

				}
			}
groupCount++;
		}
		m += "}\n\n";

		
		
		m+="	}\n\n\n";

		
		
		
		m+="/**删除*/\n";
		m+="	public ActionForward del(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		String errmsg = null;\n";
		m+="		"+interfaceBean.enName+"ActionForm myform = ("+interfaceBean.enName+"ActionForm) form;\n";

		m+="		try {\n";
	
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						m+="		ArrayList "+row.enName+"list = new ArrayList();//"+row.cnName+"\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
	
		m+="		\n";
		m+="		for (int i = 0; i < myform.checkbox_row_id.length; i++) {\n";
		m+="			String st = myform.checkbox_row_id[i].trim()==null?\"\":myform.checkbox_row_id[i].trim();\n";
		m+="			String[] rs = Const.split(st);\n";
	
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
						m+=" "+row.enName+"list.add(rs["+columnCount+"].trim());//"+row.cnName+"\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		

		
		m+="		}	\n";
		m+="		\n";
		m+="		Map packet = new HashMap();\n";
		m+="		packet.put(\"SNDMSG_HEAD\", PackUtil.setPacketHead(request));\n";
	
		
		m+="		packet.put(\"D44_70_RECORDNUM\",myform.checkbox_row_id.length);\n";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
				
						m+="		packet.put(\""+row.enName+"\","+row.enName+"list); \n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
	
	
		m+="		\n";
		m+="		log.info(\"主交易\" + \"\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(packet, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			Map recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
	
		m+="		} else {\n";
	
		m+="		}\n";
		
		
		
		m+="			request.setAttribute(\"optret\", receivemsg);\n";
	
		m+="			return this.queryJsp(mapping, form, request, response);\n";
		m+="		} catch (Exception e) {\n";
		m+="			log.info(e.getMessage());\n";
		m+="			String msgg = e.getMessage();\n";
		m+="			if (msgg == null || \"\".equals(msgg)) {\n";
		m+="				errmsg = \"删除失败！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			// e.printStackTrace();\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"msgb\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n\n";

		
		
		
		
		m+="/**新增*/\n";
		m+="	public ActionForward add(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		"+interfaceBean.enName+"ActionForm sform = ("+interfaceBean.enName+"ActionForm) form;\n";
		
		m+="		\n";
		m+="		String st = request.getParameter(\"parm\") == null ? \" \" : request\n";
		m+="				.getParameter(\"parm\").trim();\n";
		m+="		\n";
	
		m+="		System.out.println(st);\n";
		m+="		String[] rs = Const.split(st);\n";
	
		m+="		Map map = new HashMap();\n";
		m+="		String errmsg = null;\n";
		m+="		map.put(\"headmsg\", PackUtil.setPacketHead(request));\n";
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="		map.put(\""+row.enName+"\", 1);\n";
					} else {
						m+="String "+row.enName+" = rs["+columnCount+"].trim();//"+row.cnName+"\n";
						m+="		ArrayList "+row.enName+"list = new ArrayList();\n";
						m+="		"+row.enName+"list.add("+row.enName+" == null ? \" \" : "+row.enName+");\n";
						m+="		map.put(\""+row.enName+"\", "+row.enName+"list);\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
	
		m+="	\n";
		m+="		try {\n";
		
		
		m+="log.info(\"主交易\" + \"4453329\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(map, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			Map recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
		
		m+="		} else {\n";
	
		m+="		}\n";
		
		m+="			String[] rs1 = Const.split(receivemsg);\n";
		m+="			String err = rs1[0];\n";
		m+="			if (\"000000\".equals(err)) {\n";
		m+="				errmsg = \"新增成功！\";\n";
		m+="				request.setAttribute(\"optret\", errmsg);\n";
		m+="				return this.queryJsp(mapping, form, request, response);\n";
	
		m+="			} else {\n";
		m+="				errmsg = rs1[1];\n";
		m+="				request.setAttribute(\"optret\", errmsg);\n";
	
		m+="				return this.queryJsp(mapping, form, request, response);\n";
		m+="			}\n";
		m+="		} catch (Exception e) {\n";
		m+="			log.info(e.getMessage());\n";
		m+="			String msgg = e.getMessage();\n";
		m+="			if (msgg == null || \"\".equals(msgg)) {\n";
		m+="				errmsg = \"新增失败！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			e.printStackTrace();\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"msgb\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n";

		
		

		
		
		m+="/**跳转到新增addJsp页*/\n";
		m+="	public ActionForward addJsp(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		\n";
		m+="		String errmsg = null;\n";
		m+="		ActionForward af = null;\n";
		m+="		Map map = new HashMap();\n";
		m+="		try {\n";
		m+="			HttpSession session = request.getSession();\n";
		m+="			String brchNO = session.getAttribute(\"brch_no\").toString();\n";
		m+="			if (brchNO != null) {\n";
		m+="					af = mapping.findForward(\"addJsp\");\n";
		m+="					return af;\n";
		m+="			} else {\n";
		m+="				String msg = \"没有获取用户信息，请先登陆！！！\";\n";
		m+="				String nextUrl = \"/login.jsp\";\n";
		m+="				return this.errorPage(msg, nextUrl, mapping, request);\n";
		m+="			}\n";
		m+="		} catch (Exception e) {\n";
		m+="			e.printStackTrace();\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n";



		

		m+="	public ActionForward errorPage(String msg, String nextUrl,\n";
		m+="			ActionMapping mapping, HttpServletRequest request) {\n";
		m+="		HttpSession session = request.getSession();\n";
		m+="		session.setAttribute(\"successMsg\", msg);\n";
		m+="		System.out.println(\"msg \" + msg);\n";
		m+="		session.setAttribute(\"nextPage\", nextUrl);\n";
		m+="		return mapping.findForward(\"error\");\n";
		m+="	}\n";

		
		
		
		
		
		m+="/**跳转到QueryJsp页*/\n";
		m+="	public ActionForward queryJsp(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		String errmsg = null;\n";
		m+="		ActionForward af = null;\n";
		m+="		try {\n";
		m+="			HttpSession session = request.getSession();\n";
		m+="			String brchNO = session.getAttribute(\"brch_no\").toString();\n";
	
		m+="			if (brchNO != null) {\n";
		m+="					af = mapping.findForward(\"queryJsp\");\n";
		m+="					return af;\n";
		m+="			} else {\n";
		m+="				String msg = \"没有获取用户信息，请先登陆！！！\";\n";
		m+="				String nextUrl = \"/login.jsp\";\n";
		m+="				return this.errorPage(msg, nextUrl, mapping, request);\n";
		m+="			}\n";
		m+="		} catch (Exception e) {\n";
		m+="			e.printStackTrace();\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n";
		
		
		
		m+="/**查询*/\n";
		m+="	public ActionForward query(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		
		m+="		"+interfaceBean.enName+"ActionForm sform = ("+interfaceBean.enName+"ActionForm) form;\n";
		
		m+="		Map map = new HashMap();\n";
		m+="		map.put(\"headmsg\", PackUtil.setPacketHead(request));\n";
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
				
					} else {
						m+="		String "+row.enName+" = sform."+row.enName.toLowerCase()+".trim() == null ? \"\"\n";
						m+="				: sform."+row.enName.toLowerCase()+".trim();//"+row.cnName+"\n";
						
						m+="		map.put(\""+row.enName+"\", "+row.enName+");\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
		
	
		
	
	
	
		m+="		String errmsg = null;\n";
		m+="		try {\n";
		m+="log.info(\"主交易\" + \"4453329\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(map, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="Map recmap=null ;\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			 recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
		
		m+="		} else {\n";
	
		m+="		}\n";
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="	int recodeNum=	Integer.valueOf(recmap.get(\""+row.enName+"\").toString());\n";
						m+="List list=new ArrayList();\n";
						m+="for(int i=0;i<recodeNum;i++){\n";
						m+=""+interfaceBean.enName+"ActionForm actionForm=new "+interfaceBean.enName+"ActionForm();\n";
					} else {
					
						
						m+="	actionForm."+row.enName.toLowerCase()+"=	((String[])recmap.get(\""+row.enName+"\"))["+columnCount+"].toString();//"+row.cnName+"\n";
						
						columnCount++;
					}
					i++;
				}
			}
		}
		
		m+="list.add(actionForm);\n";
	
		m+="}\n";
		
		
		m+="			sform.setRedo(list);\n";
	
		m+="		} catch (Exception e) {\n";

		m+="			log.info(e.getMessage());\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"未知错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";

		m+="		return this.queryJsp(mapping, form, request, response);\n";
		m+="	}\n";
		
		
		
		m+="}\n";
		makeFile(  interfaceBean.enName+"Action",m);
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
}
