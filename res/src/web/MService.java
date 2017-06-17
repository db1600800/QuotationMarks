public interface MService {
	List<CpzBuyerCollectShopBean> get(int shopBusineeRangeId) throws Exception;
void insert(CpzBuyerCollectShopBean bean);
void update(CpzBuyerCollectShopBean bean);
void delete(CpzBuyerCollectShopBean bean);
}

