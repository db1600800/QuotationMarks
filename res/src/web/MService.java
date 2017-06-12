public interface MService {
	List<CpzBuyerCollectShopBean> get(int shopId,int marketId) throws Exception;
void insert(CpzBuyerCollectShopBean bean);
void update(CpzBuyerCollectShopBean bean);
void delete(Integer id);
}

