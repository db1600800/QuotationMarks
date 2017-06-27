import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.framework.dao.BaseDao;
import com.framework.dao.common.DaoTools;
import com.framework.exception.CommonException;
@Service("BuyerPoductSeachService")
public class BuyerPoductSeachServiceImpl implements BuyerPoductSeachService {
	private final static Logger logger = LoggerFactory.getLogger(BuyerPoductSeachServiceImpl.class);
	
	@Resource
	private BuyerPoductSeachMapper mapper;
	
	@Override
	public List<BuyerPoductSeachBean> get(String productName,String productType) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> m = new HashMap();
m.put("productName", productName);
m.put("productType", productType);
return mapper.BuyerPoductSeachSelect( productName, productType);
	}
}

