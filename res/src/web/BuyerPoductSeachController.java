import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;
import com.framework.controller.BaseController;
import com.framework.utils.SpringBeanManger;

/**菜品搜索查询*/
@Controller
@Scope("prototype")
@RequestMapping("/buyerpoductseach")
public class BuyerPoductSeachController extends BaseController {
@Autowired
private BuyerPoductSeachService buyerPoductSeachService;
	@RequestMapping("/query.do")
	public @ResponseBody Map<String, Object> query(@RequestParam Map reqMap) {
		//if (null == reqMap || reqMap.isEmpty())
			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);
		String productName = reqMap.get("productName") == null ? null : reqMap.get("productName").toString();
		String productType = reqMap.get("productType") == null ? null : reqMap.get("productType").toString();
List<BuyerPoductSeachBean> buyerpoductseachBeans=null;
try {
buyerpoductseachBeans=buyerPoductSeachService.get(productName,productType);
} catch (Exception e) {
	e.printStackTrace();
}
Map r=new HashMap();
r.put("returnData",buyerpoductseachBeans);
	  return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE,"",r);
	}
}

