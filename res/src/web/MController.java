import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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

/**好*/
@Controller
@Scope("prototype")
@RequestMapping("/m")
public class MController extends BaseController {
@Autowired
private MService mService;
	@RequestMapping("/query.do")
	public @ResponseBody Map<String, Object> query(@RequestParam Map reqMap) {
		//if (null == reqMap || reqMap.isEmpty())
			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);
CpzBuyerCollectShopBean cpzbuyercollectshopBean=null;
try {
cpzbuyercollectshopBean=mService.get();
} catch (Exception e) {
	e.printStackTrace();
}
		return null;//return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);
	}
	@RequestMapping("/insert.do")
	public @ResponseBody Map<String, Object> insert(@RequestParam Map reqMap) {
		//if (null == reqMap || reqMap.isEmpty())
			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);
CpzBuyerCollectShopBean cpzbuyercollectshopBean=null;
try {
mService.insert(cpzbuyercollectshopBean);
} catch (Exception e) {
	e.printStackTrace();
}
		return null;//return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);
	}
	@RequestMapping("/update.do")
	public @ResponseBody Map<String, Object> update(@RequestParam Map reqMap) {
		//if (null == reqMap || reqMap.isEmpty())
			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);
CpzBuyerCollectShopBean cpzbuyercollectshopBean=null;
try {
mService.update(cpzbuyercollectshopBean);
} catch (Exception e) {
	e.printStackTrace();
}
		return null;//return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);
	}
	@RequestMapping("/delete.do")
	public @ResponseBody Map<String, Object> delete(@RequestParam Map reqMap) {
		//if (null == reqMap || reqMap.isEmpty())
			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);
CpzBuyerCollectShopBean cpzbuyercollectshopBean=null;
try {
mService.delete(cpzbuyercollectshopBean.id);
} catch (Exception e) {
	e.printStackTrace();
}
		return null;//return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);
	}
}

