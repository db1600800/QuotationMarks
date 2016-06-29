import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("unchecked")
@ParentPackage(value = "default")
@Namespace(value = "/companyname/projectname/modulename")//url里用的路径头(Action路径)
@Action(value = "PackageDetailAction" ,results = { 
		@Result(name = "index", location = "/companyname/projectname/modulename/PackageDetail.jsp"),
})
public class PackageDetailAction  {
//注入网络请求,响应,等待提示
	}



