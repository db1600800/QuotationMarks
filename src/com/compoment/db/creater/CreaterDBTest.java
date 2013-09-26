package com.compoment.db.creater;

import java.util.List;

import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class CreaterDBTest {

	List<XmlDBTableBean> tables = null;

	public static void main(String[] args) {
		new CreaterDBTest();
	}

	public CreaterDBTest() {

		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


       //插入--查出

//        AccountBean accountBean =new AccountBean ();
//        accountBean.setAccountBalance("10");
//        accountBean.setAccountId("1");
//        accountBean.setIntegralBalance("10");
//        accountBean.setPassWord("10");
//        accountBean.setUserName("10");
//
//        Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBContentResolver dao = new Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBContentResolver(this);
//		dao.insert(accountBean);
//		List<AccountBean> accountBeans=dao.queryAllAccount();
//		for(AccountBean bean:accountBeans)
//		{
//			System.out.println(bean.getUserName());
//		}




	   //更新--查出

	   //删除--查出


	}
}
