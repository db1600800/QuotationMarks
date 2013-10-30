package com.compoment.jsonToJava.creater;

import java.util.List;

public class Bean {
	public String result;
	public String msg;
	public List<DataBean> data;

	public class DataBean {
		public String id;
		public String imageUrl;
		public String operType;
		public String beginDate;
		public String endDate;
		public String status;
		public String createDate;
	}
}
