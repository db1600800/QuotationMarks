public interface BuyerPoductSeachMapper {
List<BuyerPoductSeachBean> BuyerPoductSeachSelect(@Param("productName")String productName,@Param("productType")String productType);
}

