package com.compoment.jsonToJava.creater;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.io.FileUtils;



import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
//http://www.cnblogs.com/hoojo/archive/2011/04/21/2023805.html
public class Pojo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pojo pojo = new Pojo();
		String jsonString= pojo.jsonFromFile();
		//String jsonString=pojo.jsonFromUrl();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		System.out.println("public class Bean{");
		pojo.getPojo(jsonObject);

		pojo.addJsonValueToBean(jsonObject,jsonString);
	}

	/**从url得到json数据*/
	public String jsonFromUrl() {
		try {
			String urlPath = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=210af0ac7c5dad997a19f7667e5779d3&tags=Singapore&per_page=200&format=json&nojsoncallback=1";
			URL url = new URL(urlPath);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			// ��Ӧ���ַ����ת��
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			reader.close();
			connection.disconnect();
		return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**从文件得到json数据*/
	public String jsonFromFile() {
		String classDir = this.getClass().getResource("/").getPath();
		String jsonString = null;
		try {
			jsonString = FileUtils.readFileToString(new File(classDir
					+ "com/compoment/jsonToJava/creater/sample.json"));
			return jsonString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;

	}


  	int i=0;
	 /**生成Bean  json->bean*/
	public  void getPojo(JSONObject jsonObject) {

		try {
			Set set = jsonObject.keySet();
			Iterator it = set.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				Object obj = (Object) jsonObject.get(key);

				if (obj instanceof JSONArray) {
					// List<PhotoBean> photoBeanList;
					System.out.println("public List<" + firstCharUpperCase(key)
							+ "Bean> " + key + ";");
					// public class PhotoBean {
					System.out.println("public class "
							+ firstCharUpperCase(key) + "Bean{");
					JSONArray jSONArray = (JSONArray) obj;
					for (int i = 0; i < 1; i++) {
						JSONObject temp = jSONArray.getJSONObject(i); // ���JSON�����е�ÿһ��JSONObject����
						getPojo(temp);
					}
				} else if (obj instanceof JSONObject) {

						System.out.println("public "
								+ firstCharUpperCase(key) + "Bean "+key+";");

					System.out.println("public class "
							+ firstCharUpperCase(key) + "Bean{");

					JSONObject temp = (JSONObject) obj;
					getPojo(temp);

				} else {
					System.out.println("public String " + key + ";");
				}

			}
			System.out.println("}");

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**首字母大写*/
	public static String firstCharUpperCase(String s) {
		if (s.length() > 0) {
			String firstchar = String.valueOf(s.charAt(0)).toUpperCase();
			s = s.substring(1);
			s = firstchar + s;
			return s;
		} else {
			return null;
		}

	}




/**如何使用生成的Bean  即json->java*/
	public void addJsonValueToBean(JSONObject jsonObject,String jsonString) {
//		public class Bean{
//			public List<PeopleBean> people;
//			public class PeopleBean{
//			public String firstName;
//			public String lastName;
//			public String email;
//			}
//			}
		Gson gson = new Gson();
		Bean bean = gson.fromJson(jsonString, Bean.class);
		if(bean==null)
		{
        System.out.println(bean.toString());
		}
	}


}
