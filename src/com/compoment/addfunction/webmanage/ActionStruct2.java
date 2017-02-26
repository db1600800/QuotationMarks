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
		m+="import com.chinapost.weixin.model.ActivityInfo;\n";
		m+="import com.chinapost.weixin.model.ActivityInfoExt;\n";
		m+="import com.chinapost.weixin.model.MerchMsg;\n";
		m+="import com.chinapost.weixin.model.RandomNumber;\n";
		m+="import com.chinapost.weixin.service.ActivityService;\n";
		m+="import com.chinapost.weixin.util.page.PaginationUtil;\n";
		m+="import com.forgon.tools.StrutsParamUtils;\n";
		m+="import com.forgon.tools.hibernate.ObjectDao;\n";

		m+="@SuppressWarnings(\"unchecked\")\n";
		m+="@ParentPackage(value = \"default\")\n";
		m+="@Namespace(value = \"/chinapost\")\n";
		m+="@Action(value = \"bargainAction\" ,results = { \n";
		m+="		@Result(name = \"parameters\", location = \"/chinapost/weixin/bargain/parameters.jsp\"),\n";
		m+="		@Result(name = \"parameterssetting\", location = \"/chinapost/weixin/bargain/parameterssetting.jsp\"),\n";
		m+="		@Result(name = \"addparameters\", location = \"/chinapost/weixin/bargain/parametersAdd.jsp\"),\n";
		m+="		@Result(name = \"bargainSubclass\", location = \"/chinapost/weixin/bargain/bargainSubclass.jsp\"),\n";
		m+="		@Result(name = \"bargainSubclassSetting\", location = \"/chinapost/weixin/bargain/bargainSubclassSetting.jsp\"),\n";
		m+="		@Result(name = \"bargainSubclassAdd\", location = \"/chinapost/weixin/bargain/bargainSubclassAdd.jsp\"),\n";
		m+="		@Result(name = \"prize\", location = \"/chinapost/weixin/bargain/prize.jsp\"),\n";
		m+="		@Result(name = \"prizeSetting\", location = \"/chinapost/weixin/bargain/prizeSetting.jsp\"),\n";
		m+="		@Result(name = \"prizeAdd\", location = \"/chinapost/weixin/bargain/prizeAdd.jsp\")\n";
		m+="		\n";
		m+="	})\n";
		m+="public class "+interfaceBean.enName+"Action {\n";
		m+="	\n";
		m+="	private static Logger logger = LoggerFactory.getLogger(BargainAction.class);\n";
		m+="	@Resource\n";
		m+="	private ObjectDao objectDao;\n";
		m+="	\n";
		m+="    private ActivityInfo entity;\n";
		m+="    private ActivityInfoExt activityInfoExt;\n";
		m+="    private MerchMsg merchMsg;\n";
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
		m+="	\n";
		m+="	public ActivityInfoExt getActivityInfoExt() {\n";
		m+="		return activityInfoExt;\n";
		m+="	}\n";
		m+="	public void setActivityInfoExt(ActivityInfoExt activityInfoExt) {\n";
		m+="		this.activityInfoExt = activityInfoExt;\n";
		m+="	}\n";
		m+="	public ActivityService getActivityService() {\n";
		m+="		return activityService;\n";
		m+="	}\n";
		m+="	\n";

		m+="	public ActivityInfo getActivityInfo() {\n";
		m+="		return activityInfo;\n";
		m+="	}\n";
		m+="	public void setActivityInfo(ActivityInfo activityInfo) {\n";
		m+="		this.activityInfo = activityInfo;\n";
		m+="	}\n";
		m+="	public void setActivityService(ActivityService activityService) {\n";
		m+="		this.activityService = activityService;\n";
		m+="	}\n";
		m+="	\n";
		m+="	public MerchMsg getMerchMsg() {\n";
		m+="		return merchMsg;\n";
		m+="	}\n";
		m+="	public void setMerchMsg(MerchMsg merchMsg) {\n";
		m+="		this.merchMsg = merchMsg;\n";
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
		m+="		return \"parameters\";\n";
		m+="	}\n";

		m+="	//砍价子类列表\n";
		m+="	public void bargainSubclass(){\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		String pageNo = request.getParameter(\"subclassPageNo\");\n";
		m+="		String activity_name = StrutsParamUtils.getPraramValue(\"activity_name\", \"\");\n";
		m+="		String activity_class = StrutsParamUtils.getPraramValue(\"activity_class\", \"\");\n";
		m+="		String thd_sys_id=StrutsParamUtils.getPraramValue(\"thd_sys_id\", \"\");\n";
		m+="		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m+="			pageNo = \"1\";\n";
		m+="			request.setAttribute(\"subclassPageNo\", pageNo);\n";
		m+="		}\n";
		m+="		if(StringUtils.isBlank(thd_sys_id) || StringUtils.isBlank(activity_class)){\n";
		m+="			return;\n";
		m+="		}\n";
		m+="		String pageSize = request.getParameter(\"subclassPageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"subclassPageSize\", pageSize);\n";
		m+="		}\n";
		m+="		StringBuffer sql = new StringBuffer(\"select count(*) from ActivityInfo where thd_sys_id=? \");\n";
		m+="		StringBuffer sb = new StringBuffer(\" select a from ActivityInfo a  where thd_sys_id=:thd_sys_id \");\n";
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
		m+="		List<ActivityInfo> list = (List<ActivityInfo>) objectDao.findByHqlPage(\n";
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
		m+="	//奖状列表\n";
		m+="	public void prize(){\n";
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
		m+="		String pageSize = request.getParameter(\"pageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"pageSize\", pageSize);\n";
		m+="		}\n";
		m+="		StringBuffer sql = new StringBuffer(\"select count(*) from ActivityInfo where thd_sys_id=? \");\n";
		m+="		StringBuffer sb = new StringBuffer(\" select a from ActivityInfo a  where thd_sys_id=:thd_sys_id \");\n";
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
		m+="		List<ActivityInfo> list = (List<ActivityInfo>) objectDao.findByHqlPage(\n";
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
		m+="	\n";
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
		m+="			return \"parameterssetting\";\n";
		m+="		}else{\n";
		m+="			return \"addparameters\";\n";
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
		m+="			merchMsg.setThd_sys_id(activityInfo.getThd_sys_id());\n";
		m+="			merchMsg.setMerch_status(\"01\");\n";
		m+="	        objectDao.saveOrUpdate(merchMsg);\n";
		m+="			objectDao.saveOrUpdate(activityInfo);\n";
		m+="			activityInfoExt.setActivity_code(activityInfo.getActivity_code());\n";
		m+="			objectDao.saveOrUpdate(activityInfoExt);\n";
		m+="			request.setAttribute(\"activity_class\", activityInfo.getActivity_class());\n";
		m+="			RandomNumber randomNumber =(RandomNumber)objectDao.getByProperty(\"RandomNumber\", \"activity_type\", activityInfo.getActivity_code());\n";
		m+="			if(randomNumber == null){\n";
		m+="				randomNumber=new RandomNumber();\n";
		m+="			}\n";
		m+="			int start= (int)((merchMsg.getMerch_price()-merchMsg.getMerch_price1())* 100 * 0.5) /Integer.parseInt(activityInfo.getTotal_join_cnt());\n";
		m+="			int end= (int)((merchMsg.getMerch_price()-merchMsg.getMerch_price1())* 100 * 1.5) /Integer.parseInt(activityInfo.getTotal_join_cnt());\n";
		m+="			randomNumber.setStart_value(new Long(start));\n";
		m+="			randomNumber.setEnd_value(new Long (end));\n";
		m+="			randomNumber.setFirst_number(new Long (1));\n";
		m+="			randomNumber.setLast_number(new Long (100));\n";
		m+="			objectDao.saveOrUpdate(randomNumber);\n";
		m+="			return \"parameters\";\n";
		m+="	}\n";
		m+="	\n";
		m+="	public String doSave()  throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		 ServletContext sc = ServletActionContext.getServletContext();\n";
		m+="        String activity_code=activityService.getActivityCode();\n";
		m+="        if(file4 != null){\n";
		m+="         	 String activityBannerPath=CommonFunction.readDefVal(\"activityBannerPath\");\n";
		m+="              String storePath = sc.getRealPath(activityBannerPath+activity_code);\n";
		m+="              FileUtils.copyFile(file4, new File(storePath,\"guangzhu.jpg\"));\n";
		m+="              activityInfoExt.setQrcode(activityBannerPath+activity_code+\"/guangzhu.jpg\");\n";
		m+="         }\n";
		m+="        activityInfo.setActivity_code(activity_code);\n";
		m+="		objectDao.save(activityInfo);\n";
		m+="		activityInfoExt.setActivity_code(activity_code);\n";
		m+="		merchMsg.setThd_sys_id(activityInfo.getThd_sys_id());\n";
		m+="		merchMsg.setBelong_activity(activity_code);\n";
		m+="		merchMsg.setSale_num(new Long(0));\n";
		m+="		merchMsg.setMerch_status(\"01\");\n";
		m+="		objectDao.save(activityInfoExt);\n";
		m+="		objectDao.save(merchMsg);\n";
		m+="		request.setAttribute(\"activity_class\", activityInfo.getActivity_class());\n";
		m+="		RandomNumber randomNumber = new RandomNumber();\n";
		m+="		randomNumber.setActivity_type(activity_code);\n";
		m+="		int start= (int)((merchMsg.getMerch_price()-merchMsg.getMerch_price1())* 100 * 0.5) /Integer.parseInt(activityInfo.getTotal_join_cnt());\n";
		m+="		int end= (int)((merchMsg.getMerch_price()-merchMsg.getMerch_price1())* 100 * 1.5) /Integer.parseInt(activityInfo.getTotal_join_cnt());\n";
		m+="		randomNumber.setStart_value(new Long(start));\n";
		m+="		randomNumber.setEnd_value(new Long (end));\n";
		m+="		randomNumber.setFirst_number(new Long (1));\n";
		m+="		randomNumber.setLast_number(new Long (100));\n";
		m+="		objectDao.save(randomNumber);\n";
		m+="		return \"parameters\";\n";
		m+="	}\n";
		m+="	\n";
		m+="	public void todelete() throws IOException{\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		String activity_code = request.getParameter(\"activity_code\");\n";
		m+="		ActivityInfo act = new ActivityInfo();\n";
		m+="		act.setActivity_code(activity_code);\n";
		m+="		objectDao.delete(act);\n";
		m+="		ActivityInfoExt actext = new ActivityInfoExt();\n";
		m+="		actext.setActivity_code(activity_code);\n";
		m+="		objectDao.delete(actext);\n";
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





