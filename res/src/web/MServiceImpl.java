import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.framework.dao.BaseDao;
import com.framework.dao.common.DaoTools;
import com.framework.exception.CommonException;
@Service("MService")
public class MServiceImpl implements MService {
	private final static Logger logger = LoggerFactory.getLogger(MServiceImpl.class);
	
	@Resource
	private CpzBuyerCollectShopMapper mapper;
	
	@Override
	public CpzBuyerCollectShopBean get() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> m = new HashMap<>();
return mapper.CpzBuyerCollectShopSelect();
	}
	@Override
	public CpzBuyerCollectShopBean get() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> m = new HashMap();
return mapper.CpzBuyerCollectShopSelect();
	}

	@Override
public void  insert(CpzBuyerCollectShopBean bean){
 mapper.CpzBuyerCollectShopInsert(bean);
	}
	@Override
public void update(CpzBuyerCollectShopBean bean){
 mapper.CpzBuyerCollectShopUpdate(bean);
	}

	@Override
public void   delete(Integer id){
 mapper.CpzBuyerCollectShopDelete(id);
	}
}

