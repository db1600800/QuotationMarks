public class BuyerPoductSeachBean {
	public Integer productID;/**商品代号*/
	public String productName;/**商品名称*/
	public Integer productCategoryId;/**商品所属分类代号*/
	public String productType;/**商品类型0：单个商品1：套餐*/
	public String productStatus;/**商品状态0:上架1：下架*/
	public String tradingUnit;/**交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶*/
	public String productColor;/**颜色*/
	public String isFresh;/**是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）*/
	public String sourceArea;/**原产地名称*/
	public String isCanRefund;/**是否支持退货0：否 1：是*/
	public String isNeedSpot;/**是否需要当场处理0：否1：是（是否支持当场处理？）*/
	public String productDetail;/**详情URL地址*/
	public Integer supportDay;/**配菜支持天数*/
	public String channelNo;/**商品发布渠道 多个渠道以“,”好隔开01：微信02：安卓03：IOS（应该没有渠道）*/
	public String remakr1;/**备注1*/
	public String remakr2;/**备注2*/
List<CpzProductLevelBean> cpzProductLevels;/**商品分类表*/
List<CpzPlatProductPicBean> cpzPlatProductPics;/**平台商品图片表*/
List<CpzPlatProductNormsBean> cpzPlatProductNormss;/**平台商品规格表*/
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getTradingUnit() {
		return tradingUnit;
	}
	public void setTradingUnit(String tradingUnit) {
		this.tradingUnit = tradingUnit;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public String getIsFresh() {
		return isFresh;
	}
	public void setIsFresh(String isFresh) {
		this.isFresh = isFresh;
	}
	public String getSourceArea() {
		return sourceArea;
	}
	public void setSourceArea(String sourceArea) {
		this.sourceArea = sourceArea;
	}
	public String getIsCanRefund() {
		return isCanRefund;
	}
	public void setIsCanRefund(String isCanRefund) {
		this.isCanRefund = isCanRefund;
	}
	public String getIsNeedSpot() {
		return isNeedSpot;
	}
	public void setIsNeedSpot(String isNeedSpot) {
		this.isNeedSpot = isNeedSpot;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public Integer getSupportDay() {
		return supportDay;
	}
	public void setSupportDay(Integer supportDay) {
		this.supportDay = supportDay;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getRemakr1() {
		return remakr1;
	}
	public void setRemakr1(String remakr1) {
		this.remakr1 = remakr1;
	}
	public String getRemakr2() {
		return remakr2;
	}
	public void setRemakr2(String remakr2) {
		this.remakr2 = remakr2;
	}
	public List getCpzProductLevels() {
		return cpzProductLevels;
	}
	public void setCpzProductLevels(List cpzProductLevels) {
		this.cpzProductLevels = cpzProductLevels;
	}
	public List getCpzPlatProductPics() {
		return cpzPlatProductPics;
	}
	public void setCpzPlatProductPics(List cpzPlatProductPics) {
		this.cpzPlatProductPics = cpzPlatProductPics;
	}
	public List getCpzPlatProductNormss() {
		return cpzPlatProductNormss;
	}
	public void setCpzPlatProductNormss(List cpzPlatProductNormss) {
		this.cpzPlatProductNormss = cpzPlatProductNormss;
	}
}

