package com.compoment.addfunction.webmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.KeyValue;

public class ActionStruct2 {

	public ActionStruct2(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			action(interfaceBean, "Respond");
		}
	}
	public void action(InterfaceBean interfaceBean ,String type)
	{
		
		
		
	     //list()

		String urlKeyString2="";
		String listInKeyString="";
		String appendString="";
		String nextPageKeyString="";
		
		
		String keyValue2="";
		
		//toUpdate()
		String toUpdateInKeyString="";
	
		String mainkey="";
		
		//doUpdate
		String doUpdateKeyStirng="";
		
		//file
		String filebean="";
		int fileCount=0;
		String fileAdd="";
		String fileUpdate="";
		
		
		//select
		String selectList="";
		
		
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
				
					
						
						
						//list()
						listInKeyString+="	String "+row.enName.toLowerCase()+" = StrutsParamUtils.getPraramValue(\""+row.enName.toLowerCase()+"\", \"\");\n";
					
						listInKeyString+="		if(StringUtils.isBlank("+row.enName.toLowerCase()+")){\n";
						listInKeyString+="			//return;\n";
						listInKeyString+="		}else{\n";
						listInKeyString+="where+=\""+row.enName.toLowerCase()+"=? And \";\n"; 
						listInKeyString+="where2+=\""+row.enName.toLowerCase()+"=:"+row.enName.toLowerCase()+" And \";\n";
						listInKeyString+="argslist.add("+row.enName.toLowerCase()+");\n";
						listInKeyString+="argsMap.put(\""+row.enName.toLowerCase()+"\", "+row.enName.toLowerCase()+");\n";
						listInKeyString+="}\n";
						
						
						
						urlKeyString2+=""+row.enName.toLowerCase()+"=:"+row.enName.toLowerCase()+" And ";
						
						
						keyValue2+="argsMap.put(\""+row.enName.toLowerCase()+"\","+row.enName.toLowerCase()+");\n";
					
						
						
						appendString+="entity.set"+firstCharUpperCase(row.enName.toLowerCase())+"("+row.enName.toLowerCase()+");\n";
						
						nextPageKeyString+=row.enName.toLowerCase()+"=\"+"+row.enName.toLowerCase()+"+\"%26";
						
						//toUpdate()
						
						toUpdateInKeyString+="String "+row.enName.toLowerCase()+" = StrutsParamUtils.getPraramValue(\""+row.enName.toLowerCase()+"\", \"\");\n";
						toUpdateInKeyString+="request.setAttribute(\""+row.enName.toLowerCase()+"\", "+row.enName.toLowerCase()+");\n";
						
						
						if(row.remarks.toLowerCase().equals("mainkey"))
						{
							mainkey=row.enName.toLowerCase();
					
						}
						
						
						//doUpdate()
						doUpdateKeyStirng+=" request.setAttribute(\""+row.enName.toLowerCase()+"\", entity.get"+firstCharUpperCase(row.enName.toLowerCase())+"());\n";
					}
					
					
					
					if(row.type.toLowerCase().contains("file"))
					{
						fileCount++;
						filebean+="    private File file"+fileCount+";//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的\n";
						filebean+="    private String file"+fileCount+"FileName;//   上传输入域FileName  文件名\n";
						filebean+="    private String file"+fileCount+"ContentType;// 上传文件的MIME类型\n";
						
						
						filebean+="	public File getFile"+fileCount+"() {\n";
						filebean+="		return file"+fileCount+";\n";
						filebean+="	}\n";
						filebean+="	public void setFile"+fileCount+"(File file"+fileCount+") {\n";
						filebean+="		this.file"+fileCount+" = file"+fileCount+";\n";
						filebean+="	}\n";
						filebean+="	public String getFile"+fileCount+"FileName() {\n";
						filebean+="		return file"+fileCount+"FileName;\n";
						filebean+="	}\n";
						filebean+="	public void setFile"+fileCount+"FileName(String file"+fileCount+"FileName) {\n";
						filebean+="		this.file"+fileCount+"FileName = file"+fileCount+"FileName;\n";
						filebean+="	}\n";
						filebean+="	public String getFile"+fileCount+"ContentType() {\n";
						filebean+="		return file"+fileCount+"ContentType;\n";
						filebean+="	}\n";
						filebean+="	public void setFile"+fileCount+"ContentType(String file"+fileCount+"ContentType) {\n";
						filebean+="		this.file"+fileCount+"ContentType = file"+fileCount+"ContentType;\n";
						filebean+="	}\n";
						
						
						
						fileUpdate+="		  if(file"+fileCount+" != null){\n";
						fileUpdate+="		 ServletContext sc = ServletActionContext.getServletContext();\n";
						fileUpdate+="		       	 String activityBannerPath=CommonFunction.readDefVal(\"activityBannerPath\");\n";
						fileUpdate+="		            String storePath = sc.getRealPath(activityBannerPath+entity.getActivity_code());\n";
						fileUpdate+="		            FileUtils.copyFile(file"+fileCount+", new File(storePath,\""+row.enName.toLowerCase().replaceAll("", "")+".jpg\"));\n";
						fileUpdate+="		            entity.set"+this.firstCharUpperCase(row.enName.toLowerCase())+"(activityBannerPath+entity.getActivity_code()+\"/"+row.enName.toLowerCase().replaceAll("", "")+".jpg\");\n";
						fileUpdate+="		       }\n";
						
					}
					
					if(row.type.toLowerCase().contains("select"))
					{
					
						
						selectList+="request.setAttribute(\""+row.enName.toLowerCase()+"SelectList\","+row.enName.toLowerCase()+"list);\n";
					}
				}
			}
			}
	
	
		urlKeyString2=urlKeyString2.substring(0, urlKeyString2.lastIndexOf("And"));
		
		
		
		
		String m="";
	
		

		m+="import java.io.File;\n";
		m+="import java.io.IOException;\n";
		m+="import java.util.HashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";

		m+="import javax.annotation.Resource;\n";
		m+="import javax.servlet.ServletContext;\n";
		m+="import javax.servlet.http.HttpServletRequest;\n";

		m+="import net.sf.json.JSONObject;\n";

		m+="import org.apache.commons.io.FileUtils;\n";
		m+="import org.apache.commons.lang.StringUtils;\n";
		m+="import org.apache.struts2.ServletActionContext;\n";
		m+="import org.apache.struts2.convention.annotation.Action;\n";
		m+="import org.apache.struts2.convention.annotation.Namespace;\n";
		m+="import org.apache.struts2.convention.annotation.ParentPackage;\n";
		m+="import org.apache.struts2.convention.annotation.Result;\n";
		m+="import org.slf4j.Logger;\n";
		m+="import org.slf4j.LoggerFactory;\n";

		m+="import com.chinapost.tools.CommonFunction;\n";
		m+="import com.chinapost.weixin.model."+interfaceBean.enName+"Entity;\n";
		
		m+="import com.chinapost.weixin.service.ActivityService;\n";
		m+="import com.chinapost.weixin.util.page.PaginationUtil;\n";
		m+="import com.forgon.tools.StrutsParamUtils;\n";
		m+="import com.forgon.tools.hibernate.ObjectDao;\n";

		m+="@SuppressWarnings(\"unchecked\")\n";
		m+="@ParentPackage(value = \"default\")\n";
		m+="@Namespace(value = \"/chinapost\")\n";
		m+="@Action(value = \""+interfaceBean.enName+"Action\" ,results = { \n";
		m+="		@Result(name = \""+interfaceBean.enName.toLowerCase()+"\", location = \"/chinapost/weixin/"+interfaceBean.enName.toLowerCase()+"/"+interfaceBean.enName.toLowerCase()+".jsp\"),\n";
		m+="		@Result(name = \""+interfaceBean.enName.toLowerCase()+"Setting\", location = \"/chinapost/weixin/"+interfaceBean.enName.toLowerCase()+"/"+interfaceBean.enName.toLowerCase()+"Setting.jsp\"),\n";
		m+="		@Result(name = \""+interfaceBean.enName.toLowerCase()+"Add\", location = \"/chinapost/weixin/"+interfaceBean.enName.toLowerCase()+"/"+interfaceBean.enName.toLowerCase()+"Add.jsp\"),\n";
		m+="		\n";
		m+="	})\n";
		m+="public class "+interfaceBean.enName+"Action {\n";
		m+="	\n";
		m+="	private static Logger logger = LoggerFactory.getLogger(BargainAction.class);\n";
		m+="	@Resource\n";
		m+="	private ObjectDao objectDao;\n";
		m+="	\n";
		m+="    private "+interfaceBean.enName+"Entity entity;\n";
	
		m+="    private ActivityService activityService;\n";
		
		m+=filebean;
	
		
		m+="	public ObjectDao getObjectDao() {\n";
		m+="		return objectDao;\n";
		m+="	}\n";
		m+="	public void setObjectDao(ObjectDao objectDao) {\n";
		m+="		this.objectDao = objectDao;\n";
		m+="	}\n";
		m+="	\n";
		

		m+="	public ActivityService getActivityService() {\n";
		m+="		return activityService;\n";
		m+="	}\n";
		m+="	\n";
		m+="	public void setActivityService(ActivityService activityService) {\n";
		m+="		this.activityService = activityService;\n";
		m+="	}\n";
		
		
		m+="	public "+interfaceBean.enName+"Entity getEntity() {\n";
		m+="		return entity;\n";
		m+="	}\n";
		m+="	public void setEntity("+interfaceBean.enName+"Entity  "+interfaceBean.enName.toLowerCase()+") {\n";
		m+="		this.entity = "+interfaceBean.enName.toLowerCase()+";\n";
		m+="	}\n";
		
		
		m+="	\n";
		
		m+="	public String index(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=toUpdateInKeyString;
		m+="		return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";

		m+="	//"+interfaceBean.title+"列表\n";
		m+="	public void list(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		
		m+="		String pageNo = request.getParameter(\"pageNo\");\n";
		
		m+="		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m+="			pageNo = \"1\";\n";
		m+="			request.setAttribute(\"pageNo\", pageNo);\n";
		m+="		}\n";
		
		m+="		String pageSize = request.getParameter(\"pageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"pageSize\", pageSize);\n";
		m+="		}\n";
		
		
		m+="String where=\"\";\n";
		m+="String where2=\"\";\n";
		m+="List argslist=new ArrayList();\n";
		m+="Map<String, Object> argsMap = new HashMap<String, Object>();\n";
		
		m+=listInKeyString;
		
		m+="		StringBuffer sql = new StringBuffer(\"select count(*) from "+interfaceBean.enName+"Entity where \"+where.substring(0,where.lastIndexOf(\"And\")));\n";
		m+="		StringBuffer sb = new StringBuffer(\" select a from "+interfaceBean.enName+"Entity a  where \"+where2.substring(0,where2.lastIndexOf(\"And\")));\n";
		m+="	\n";
	
		
		m+="  int size=argslist.size();\n";
	   m+=" Object[] args = (Object[])argslist.toArray(new String[size]);\n"; 

	       
		
		m+="	//	sb.append(\" order by activity_code desc\");\n";
		
		
		m+="		int count = objectDao.countObjectByHql(sql.toString(),args);\n";
		m+="		List<"+interfaceBean.enName+"Entity> list = (List<"+interfaceBean.enName+"Entity>) objectDao.findByHqlPage(\n";
		m+="				sb.toString(),argsMap,\n";
		m+="				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),\n";
		m+="				Integer.parseInt(pageSize));\n";
		
//		m+="for(int i=0;i<list.size();i++){\n";
//		m+=interfaceBean.enName+"Entity entity=list.get(i);\n";
//		m+=appendString;
//		m+="}\n";
		
		
	
		
		m+="		String pageString = PaginationUtil.getPaginationHtml(\n";
		m+="				Integer.valueOf(count), Integer.valueOf(pageSize),\n";
		m+="				Integer.valueOf(pageNo), Integer.valueOf(2),\n";
		m+="				Integer.valueOf(5),\n";
		m+="				\"javascript:getAll('/chinapost/"+interfaceBean.enName+"Action!list.do?"+nextPageKeyString+"pageNo=\",true);\n";
		m+="		pageString = pageString.replace(\".html\", \"\");\n";
		m+="		JSONObject jsonObject = new JSONObject();\n";
		m+="		jsonObject.put(\"list\", list);\n";
		m+="		jsonObject.put(\"pageString\", pageString);\n";
		m+="		jsonObject.put(\"count\", count);\n";
		m+="		try{\n";
		m+="			StrutsParamUtils.getResponse().setCharacterEncoding(\"UTF-8\");\n";
		m+="			StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());\n";
		m+="		}catch(Exception e){\n";
		m+="			e.printStackTrace();\n";
		m+="		}\n";
		m+="	}\n";
		m+="	\n";
		m+="	\n";
		
		
		m+="//跳到修改页\n";
		m+="	public String toUpdate() {\n";
		
		
		
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		
		m+="//选择器数据\n";
		m+=selectList;
		
		m+="//页面数据\n";
		m+=toUpdateInKeyString;
		
		m+="		StringBuffer sb = new StringBuffer(\" select a from "+interfaceBean.enName+"Entity a  where "+urlKeyString2+" \");\n";
		m+="\nMap<String, Object> argsMap=new HashMap<String, Object>();\n";
		m+=keyValue2;
		m+="		List<"+interfaceBean.enName+"Entity> list = (List<"+interfaceBean.enName+"Entity>) objectDao.findByHqlPage(\n";
		m+="				sb.toString(),argsMap,\n";
		m+="				0,\n";
		m+="				10);\n";
		
		m+="		if (list!=null && list.size()==1) {\n";
		
	   
	    m+="				request.setAttribute(\"entity\", ("+interfaceBean.enName+"Entity) list.get(0));\n";
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Setting\";\n";
		m+="		}else{\n";
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Add\";\n";
		m+="		}\n";
		m+="		\n";
		m+="	}\n";
		m+="	public String doUpdate() throws IOException {\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=fileUpdate;
		
		m+="	  //批量删除    //  List  objectList = objectDao.findByProperty(\"MerchMsg\", \"belong_activity\", activityInfo.getActivity_code() );\n";
		m+="	       // objectDao.deleteAll(objectList);\n";
		
		m+="			objectDao.saveOrUpdate(entity);//form表单提交过来的对象\n";
	
		m+=doUpdateKeyStirng;
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";
		
		
		
		m+="//跳到新增页\n";
		m+="	public String toAdd() {\n";
		
		
		
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="//选择器数据\n";
		m+=selectList;
		
		m+="//页面数据\n";
		
		m+=toUpdateInKeyString;
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Add\";\n";
	
		m+="		\n";
		m+="	}\n";
		
		m+="	\n";
		m+="	public String doAdd()  throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=fileUpdate;
		
		m+="		objectDao.save(entity);//form表单提交过来的对象\n";
		m+=doUpdateKeyStirng;
		m+="		return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";
		
		
		m+="	\n";
		m+="	public void doDelete() throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+=toUpdateInKeyString;
		
		m+=""+interfaceBean.enName+"Entity entity = new "+interfaceBean.enName+"Entity();\n";
	
		m+=appendString;
		m+="		objectDao.delete(entity);\n";
		
		m+="		StrutsParamUtils.getResponse().getWriter().write(\"success\");\n";
		m+="		return ;\n";
		m+="	}\n";
		m+="	\n";
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
}





