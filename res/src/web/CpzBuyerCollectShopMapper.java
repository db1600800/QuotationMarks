public interface CpzBuyerCollectShopMapper {
List<CpzBuyerCollectShopBean> CpzBuyerCollectShopSelect(@Param("shopBusineeRangeId")int shopBusineeRangeId);
void CpzBuyerCollectShopInsert(CpzBuyerCollectShopBean bean);
void CpzBuyerCollectShopUpdate(CpzBuyerCollectShopBean bean);
void CpzBuyerCollectShopDelete(CpzBuyerCollectShopBean bean);
}

