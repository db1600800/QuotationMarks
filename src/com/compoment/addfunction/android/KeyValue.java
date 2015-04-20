package com.compoment.addfunction.android;

public class KeyValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void write()
	{
		String m="";
		m+=" SharedPreferences userInfo = getSharedPreferences(\"filename\", 0);  \n";
		m+=" userInfo.edit().putString(\"name\", user.getText().toString()).commit();  \n";
		m+=" userInfo.edit().putString(\"pass\", password.getText().toString()).commit();  \n";
	}
	
	public void read()
	{
		String m="";
		m+=" SharedPreferences userInfo = getSharedPreferences(\"filename\", 0);  \n";
		m+=" String username = userInfo.getString(\"name\", \"\");  \n";
		m+=" String pass = userInfo.getString(\"pass\", \"\");  \n";

	}

}
