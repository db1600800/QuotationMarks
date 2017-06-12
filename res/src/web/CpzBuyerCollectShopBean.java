public class CpzBuyerCollectShopBean {
	private Integer userId;/**用户id*/
	private Integer shopId;/**店铺代号*/
	private Integer marketId;/**店铺所在市场代号*/
	private Integer shopBusineeRangeId;/**店铺经营范围表id*/
	private String isDefaultShop;/**是否默认店铺0：否1：是*/
	private String colletcTime;/**收藏时间*/
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getMarketId() {
		return marketId;
	}
	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	public Integer getShopBusineeRangeId() {
		return shopBusineeRangeId;
	}
	public void setShopBusineeRangeId(Integer shopBusineeRangeId) {
		this.shopBusineeRangeId = shopBusineeRangeId;
	}
	public String getIsDefaultShop() {
		return isDefaultShop;
	}
	public void setIsDefaultShop(String isDefaultShop) {
		this.isDefaultShop = isDefaultShop;
	}
	public String getColletcTime() {
		return colletcTime;
	}
	public void setColletcTime(String colletcTime) {
		this.colletcTime = colletcTime;
	}
}

