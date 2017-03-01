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
		
		String m="";
		
		
		
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						
					} else {
						
					
						
						columnCount++;
					}
					i++;
				}
			}

		}
		
		
		
	

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
		m+="    private File file4;//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的\n";
		m+="    private String file4FileName;//   上传输入域FileName  文件名\n";
		m+="    private String file4ContentType;// 上传文件的MIME类型\n";
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
		m+="	public File getFile4() {\n";
		m+="		return file4;\n";
		m+="	}\n";
		m+="	public void setFile4(File file4) {\n";
		m+="		this.file4 = file4;\n";
		m+="	}\n";
		m+="	public String getFile4FileName() {\n";
		m+="		return file4FileName;\n";
		m+="	}\n";
		m+="	public void setFile4FileName(String file4FileName) {\n";
		m+="		this.file4FileName = file4FileName;\n";
		m+="	}\n";
		m+="	public String getFile4ContentType() {\n";
		m+="		return file4ContentType;\n";
		m+="	}\n";
		m+="	public void setFile4ContentType(String file4ContentType) {\n";
		m+="		this.file4ContentType = file4ContentType;\n";
		m+="	}\n";
		m+="	public String index(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		String activity_class = StrutsParamUtils.getPraramValue(\"activity_class\", \"\");\n";
		m+="		request.setAttribute(\"activity_class\", activity_class);\n";
		m+="		return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";

		m+="	//"+interfaceBean.title+"列表\n";
		m+="	public void list(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		String pageNo = request.getParameter(\"pageNo\");\n";
		m+="		String activity_name = StrutsParamUtils.getPraramValue(\"activity_name\", \"\");\n";
		m+="		String activity_class = StrutsParamUtils.getPraramValue(\"activity_class\", \"\");\n";
		m+="		String thd_sys_id=StrutsParamUtils.getPraramValue(\"thd_sys_id\", \"\");\n";
		m+="		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m+="			pageNo = \"1\";\n";
		m+="			request.setAttribute(\"pageNo\", pageNo);\n";
		m+="		}\n";
		m+="		if(StringUtils.isBlank(thd_sys_id) || StringUtils.isBlank(activity_class)){\n";
		m+="			return;\n";
		m+="		}\n";
		m+="		String pageSize = request.getParameter(\"subclassPageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"subclassPageSize\", pageSize);\n";
		m+="		}\n";
		m+="		StringBuffer sql = new StringBuffer(\"select count(*) from "+interfaceBean.enName+"Entity where thd_sys_id=? \");\n";
		m+="		StringBuffer sb = new StringBuffer(\" select a from "+interfaceBean.enName+"Entity a  where thd_sys_id=:thd_sys_id \");\n";
		m+="	\n";
		m+="		int i=1;\n";
		m+="		if(!\"\".equals(activity_name)){\n";
		m+="			i++;\n";
		m+="		}\n";
		m+="		if(!\"\".equals(activity_class)){\n";
		m+="			i++;\n";
		m+="		}\n";
		m+="		Object[] args =new Object[i];\n";
		m+="		Map<String, Object> argsMap=new HashMap<String, Object>();\n";
		m+="		args[0]=thd_sys_id;\n";
		m+="		argsMap.put(\"thd_sys_id\",thd_sys_id);\n";
		m+="		if(!\"\".equals(activity_name)){\n";
		m+="			args[1]=\"%\"+activity_name+\"%\";\n";
		m+="			argsMap.put(\"activity_name\", \"%\"+activity_name+\"%\");\n";
		m+="			sql.append(\" and activity_name like ?\");\n";
		m+="			sb.append(\" and activity_name like :activity_name\");\n";
		m+="		}\n";
		m+="		if(!\"\".equals(activity_class)){\n";
		m+="			args[i-1]=activity_class;\n";
		m+="			argsMap.put(\"activity_class\",activity_class);\n";
		m+="			sql.append(\" and activity_class = ? \");\n";
		m+="			sb.append(\" and activity_class = :activity_class  \");\n";
		m+="		}\n";
		m+="		sb.append(\" order by activity_code desc\");\n";
		m+="		int count = objectDao.countObjectByHql(sql.toString(),args);\n";
		m+="		List<"+interfaceBean.enName+"Entity> list = (List<"+interfaceBean.enName+"Entity>) objectDao.findByHqlPage(\n";
		m+="				sb.toString(),argsMap,\n";
		m+="				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),\n";
		m+="				Integer.parseInt(pageSize));\n";
		m+="		String pageString = PaginationUtil.getPaginationHtml(\n";
		m+="				Integer.valueOf(count), Integer.valueOf(pageSize),\n";
		m+="				Integer.valueOf(pageNo), Integer.valueOf(2),\n";
		m+="				Integer.valueOf(5),\n";
		m+="				\"javascript:getAll('/chinapost/secKillAction!parameters.do?activity_class=\"+activity_class+\"&activity_name=\"+activity_name+\"&pageNo=\",true);\n";
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
		m+="		String activity_code = request.getParameter(\"activity_code\");\n";
		m+="		String activity_class = StrutsParamUtils.getPraramValue(\"activity_class\", \"\");\n";
		m+="		request.setAttribute(\"activity_class\", activity_class);\n";
		m+="		if (StringUtils.isNotBlank(activity_code)) {\n";
		m+="			Object obj = objectDao.getByProperty(\"ActivityInfo\", \"activity_code\", activity_code );\n";
		m+="			if (obj != null) {\n";
		m+="				request.setAttribute(\"activityInfo\", (ActivityInfo) obj);\n";
		m+="			}\n";
		m+="			Object objext = objectDao.getByProperty(\"ActivityInfoExt\", \"activity_code\", activity_code );\n";
		m+="			if (objext != null) {\n";
		m+="				request.setAttribute(\"activityInfoExt\",  (ActivityInfoExt) objext);\n";
		m+="			}\n";
		m+="			merchMsg = (MerchMsg)objectDao.getByProperty(\"MerchMsg\", \"belong_activity\", activity_code );\n";
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Setting\";\n";
		m+="		}else{\n";
		m+="			return \""+interfaceBean.enName.toLowerCase()+"Add\";\n";
		m+="		}\n";
		m+="		\n";
		m+="	}\n";
		m+="	public String doUpdate() throws IOException {\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		 ServletContext sc = ServletActionContext.getServletContext();\n";
		m+="		  if(file4 != null){\n";
		m+="		       	 String activityBannerPath=CommonFunction.readDefVal(\"activityBannerPath\");\n";
		m+="		            String storePath = sc.getRealPath(activityBannerPath+activityInfo.getActivity_code());\n";
		m+="		            FileUtils.copyFile(file4, new File(storePath,\"guangzhu.jpg\"));\n";
		m+="		            activityInfoExt.setQrcode(activityBannerPath+activityInfo.getActivity_code()+\"/guangzhu.jpg\");\n";
		m+="		       }\n";
		m+="	        List  objectList = objectDao.findByProperty(\"MerchMsg\", \"belong_activity\", activityInfo.getActivity_code() );\n";
		m+="	        objectDao.deleteAll(objectList);\n";
		
		m+="			objectDao.saveOrUpdate(entity);\n";
	
		m+="			request.setAttribute(\"activity_class\", activityInfo.getActivity_class());\n";
		
		m+="			return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";
		m+="	\n";
		m+="	public String doAdd()  throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		 ServletContext sc = ServletActionContext.getServletContext();\n";
		m+="        String activity_code=activityService.getActivityCode();\n";
		m+="        if(file4 != null){\n";
		m+="         	 String activityBannerPath=CommonFunction.readDefVal(\"activityBannerPath\");\n";
		m+="              String storePath = sc.getRealPath(activityBannerPath+activity_code);\n";
		m+="              FileUtils.copyFile(file4, new File(storePath,\"guangzhu.jpg\"));\n";
		m+="              activityInfoExt.setQrcode(activityBannerPath+activity_code+\"/guangzhu.jpg\");\n";
		m+="         }\n";
		m+="        entity.setActivity_code(activity_code);\n";
		m+="		objectDao.save(entity);\n";
		m+="		request.setAttribute(\"activity_class\", entity.getActivity_class());\n";
		m+="		return \""+interfaceBean.enName.toLowerCase()+"\";\n";
		m+="	}\n";
		m+="	\n";
		m+="	public void doDelete() throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		String activity_code = request.getParameter(\"activity_code\");\n";
		m+="		"+interfaceBean.enName+"Entity act = new "+interfaceBean.enName+"Entity();\n";
		m+="		act.setActivity_code(activity_code);\n";
		m+="		objectDao.delete(act);\n";
		
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
}





