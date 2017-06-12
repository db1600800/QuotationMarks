public interface CpzBuyerCollectShopMapper {
List<CpzBuyerCollectShopBean> CpzBuyerCollectShopSelect(int shopId,int marketId);
void CpzBuyerCollectShopInsert(CpzBuyerCollectShopBean bean);
void CpzBuyerCollectShopUpdate(CpzBuyerCollectShopBean bean);
void CpzBuyerCollectShopDelete(Integer id);
}

