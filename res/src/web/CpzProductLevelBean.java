public class CpzProductLevelBean {
	public Integer productCategoryId;/**级别代号顺序号，从1开始*/
	public String productCategoryName;/**级别名称*/
	public Integer parentId;/**父级别代号*/
	public String parentName;/**父级别名称*/
	public String productCategoryGrade;/**级别等级0：根级别1：第1级2：第2级*/
	public String path;/**树路径*/
	public Integer getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getProductCategoryGrade() {
		return productCategoryGrade;
	}
	public void setProductCategoryGrade(String productCategoryGrade) {
		this.productCategoryGrade = productCategoryGrade;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}

