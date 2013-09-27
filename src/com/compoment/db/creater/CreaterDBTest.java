package com.compoment.db.creater;

import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class CreaterDBTest {

	List<XmlDBTableBean> tables = null;
	String className = "";

	public static void main(String[] args) {
		CreaterDBTest createrDBTest = new CreaterDBTest();

		String m = "";
		// m+=createrDBTest.insertTest(m);//插入
		m += createrDBTest.queryTest(m);// 查询
		// m+=createrDBTest.deleteAllTest(m);//删除
		System.out.println(m);
	}

	/** 插入--查出 */
	public String insertTest(String m) {
		m += "\n";
		for (XmlDBTableBean table : tables) {
			m += "/**" + table.tableChineseName + "*/\n";
			m += "public void insert" + table.tableName
					+ "Test(Context context){\n";
			// AccountBean accountBean =new AccountBean ();
			m += "        " + table.tableName + "Bean "
					+ table.firstCharLowercase(table.tableName) + "Bean =new "
					+ table.tableName + "Bean ();\n";
			// m+="        accountBean.setAccountId(\"1\");\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "        " + table.firstCharLowercase(table.tableName)
						+ "Bean.set" + column.columnName + "(\""
						+ column.columnName + "\");//"
						+ column.columnChineseName + "\n";
			}

			// Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBContentResolver
			// dBContentResolver = new
			// Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBContentResolver(this);
			m += "        " + className
					+ "DBContentResolver dBContentResolver  = new " + className
					+ "DBContentResolver(context);\n";
			// dBContentResolver.insert(accountBean);
			m += "		dBContentResolver.insert("
					+ table.firstCharLowercase(table.tableName) + "Bean);\n";

			m += "}\n";
		}
		return m;

	}

	public String queryTest(String m) {
		m += "\n";
		m += is20Length(m);
		for (XmlDBTableBean table : tables) {
			m += "/**" + table.tableChineseName + "*/\n";
			m += "public void query" + table.tableName
					+ "Test(Context context){\n";
			m += "	Log.i(\"bbq\", \"**********************************"
					+ table.tableChineseName + "\");\n";

			// Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBContentResolver
			// dBContentResolver = new
			// Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBContentResolver(this);
			m += "        " + className
					+ "DBContentResolver dBContentResolver  = new " + className
					+ "DBContentResolver(context);\n";

			// m +=
			// "		List<AccountBean> accountBeans = dao.queryAllAccount();\n";
			m += "		List<" + table.tableName + "Bean> "
					+ table.firstCharLowercase(table.tableName)
					+ "Beans = dBContentResolver.queryAll" + table.tableName
					+ "();\n";
			m += "     	int i = 0;\n";
			// m+="	for (AccountBean bean : accountBeans) {\n";
			m += "for (" + table.tableName + "Bean bean : "
					+ table.firstCharLowercase(table.tableName) + "Beans) {\n";
			m += "			String logStringTitle = \"\";\n";
			m += "			String logString = \"\";\n";

			m += "              if(i==0)\n";
			m += "              {\n";

			for (XmlDBColumnBean column : table.columnsName) {
				m += "				logString += is20Length(bean.get" + column.columnName
						+ "());\n";

				m += "				logStringTitle+= is20Length(bean."
						+ table.firstCharLowercase(column.columnName)
						+ "_ChineseName);\n";
			}
			m += "				Log.i(\"bbq\", logStringTitle);\n";
			m += "				Log.i(\"bbq\", logString);\n";

			m += "              }else\n";
			m += "              {\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "				logString += is20Length(bean.get" + column.columnName
						+ "());\n";
			}
			m += "				Log.i(\"bbq\", logString);\n";
			m += "              }\n";
			m += "              i++;\n";

			m += "		}\n";

			m += "		}\n";
		}
		return m;

	}

	/** 更新--查出 */
	public void updateTest() {

	}

	/** 删除--查出 */
	public String deleteAllTest(String m) {
		m += "\n";
		for (XmlDBTableBean table : tables) {
			m += "public void deleteAll" + table.tableName
					+ "(Context context){\n";
			m += className + "DBContentResolver dBContentResolver = new "
					+ className + "DBContentResolver(context);\n";
			m += "dBContentResolver.clear" + table.tableName + "();\n";
			m += "}\n";
		}
		return m;
	}

	public String is20Length(String m) {
		m += "public String is20Length(String value) {\n";
		m += "		int chineseCount = getChineseCount(value);\n";
		m += "		if (chineseCount>0) {\n";

		m += "			if(chineseCount*2>=26)\n";
		m += "			{\n";
		m += "				chineseCount=26/2;\n";
		m += "				value=(String) value.subSequence(0, 13);\n";
		m += "			}else\n";
		m += "			{\n";
		m += "				int aBCCount=value.length()-chineseCount;\n";

		m += "				for (int j = 0; j < 26 - chineseCount*2-aBCCount; j++) {\n";
		m += "					value += \" \";\n";
		m += "				}\n";
		m += "			}\n";

		m += "		} else {\n";
		m += "			int length = value.length();\n";
		m += "			if (length < 26) {\n";
		m += "				for (int j = 0; j < 26 - length; j++) {\n";
		m += "					value += \" \";\n";
		m += "				}\n";
		m += "			} else {\n";
		m += "				value = value.substring(0, 26);\n";
		m += "			}\n";
		m += "		}\n";
		m += "		return value;\n";
		m += "	    }\n";

		m += "	public static int getChineseCount(String content) {\n";

		m += "		int count=0;\n";
		m += "		char []cs=content.toCharArray();\n";
		m += "		for (int i = 0; i < cs.length; i++) {\n";
		m += "			char c = cs[i];\n";
		m += "			if(isChinese(c))\n";
		m += "			{\n";
		m += "				count++;\n";
		m += "			}\n";
		m += "		}\n";
		m += "		return count;\n";
		m += "	    }\n";

		m += "	  public static boolean isChinese(char c) {\n";

		m += "	        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);\n";

		m += "	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS\n";

		m += "	                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS\n";

		m += "	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A\n";

		m += "	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION\n";

		m += "	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION\n";

		m += "	                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ) {\n";

		m += "	            return true;\n";

		m += "	        }\n";

		m += "	        return false;\n";

		m += "	    }\n";
		return m;
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

		for (XmlDBTableBean table : tables) {
			className += table.tableName + "_";
		}

	}
}
