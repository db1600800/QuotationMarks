package com.compoment.jspcss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cssToJsp {

	public cssToJsp() {

	}



	public static void main(String[] args) {

		cssToJsp cssToJsp = new cssToJsp();
		cssToJsp.cssToObject();
		cssToJsp.jsp();
	}



	public static boolean isinteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// 第二种方法

	List<CssNode> csss = new ArrayList();

	/**
	 * .swiper { positionk };
	 */
	public String cssToObject() {

		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String pathCss = courseFile + "/src/com/compoment/jspcss/css.txt";

		FileReader frCss = null;
		BufferedReader brCss = null;
		try {

			frCss = new FileReader(pathCss);

			brCss = new BufferedReader(frCss);

			while (brCss.ready()) {
				String txt = brCss.readLine();// ".swiper { positionk }";

				// 1.没有点 . (纯标签) a{}
				if (txt.indexOf(".") == -1) {
					String re1 = "(.*?)"; // Non-greedy match on filler
					String re2 = "(\\{)"; // Any Single Character 1
					String re3 = "(.*)"; // Variable Name 1
					String re4 = "(\\})"; // Any Single Character 2

					Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
					Matcher m = p.matcher(txt);
					if (m.find()) {
						String c1 = m.group(1);
						String var1 = m.group(2);
						String c2 = m.group(3);
						String c3 = m.group(4);

						String cleanTag[] = c1.split(",");

						for (int i = 0; i < cleanTag.length; i++) {

							CssNode cssNode = new CssNode();
							cssNode.name = cleanTag[i].trim();
							String names[] = cssNode.name.split(" ");
							String newname = "";
							if (names.length == 1) {
								newname += names[0];
							} else {
								for (int j = 0; j < names.length; j++) {

									newname += names[j] + "/";
								}
							}
							cssNode.name = newname;
							cssNode.cssString = c2;
							csss.add(cssNode);

						}
					}

				} else {

					// 2.只有点

					List keys = new ArrayList();

					// 情况1 ".showmenu a .menuwrap { ddb }";

					String re1 = "(\\.)"; // Any Single Character 1
					String re2 = "(.*)"; // Variable Name 1
					String re3 = "(\\.)"; // White Space 1

					Pattern p = Pattern.compile(re1 + re2 + re3, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
					Matcher m = p.matcher(txt.substring(0, txt.indexOf("{")));
					while (m.find()) {
						String c1 = m.group(2);

						String names[] = c1.trim().split(" ");
						String newname = "";
						if (names.length == 1) {
							newname += names[0];
						} else {
							for (int j = 0; j < names.length; j++) {

								newname += names[j] + "/";
							}
						}

						keys.add("." + newname);
					}

					// 情况2 ".showmenu a .menuwrap-df df{ a }";

					String re11 = "(\\.)"; // Any Single Character 1
					String re21 = "([\\w\\-]+\\s?\\w*\\s?\\w*\\s?)"; // Variable
																		// Name
																		// 1
					String re31 = "(\\{)"; // White Space 1
					String re41 = "(.*)"; // Variable Name 1
					String re51 = "(\\})"; // Any Single Character 2

					Pattern p1 = Pattern.compile(re11 + re21 + re31 + re41 + re51,
							Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
					Matcher m1 = p1.matcher(txt);
					String cssvalue = "";
					if (m1.find()) {
						String c1 = m1.group(2);

						cssvalue = m1.group(4);

						String names[] = c1.trim().split(" ");
						String newname = "";
						if (names.length == 1) {
							newname += names[0];
						} else {
							for (int j = 0; j < names.length; j++) {

								newname += names[j] + "/";
							}
						}

						keys.add("." + newname);

					}

					for (int i = 0; i < keys.size(); i++) {

						CssNode cssNode = new CssNode();
						cssNode.name = (String) keys.get(i);
						cssNode.cssString = cssvalue;
						csss.add(cssNode);
					}

				}

			}

			brCss.close();
			frCss.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public void jsp() {

		try {

			String courseFile = null;
			File directory = new File("");// 参数为空
			courseFile = directory.getCanonicalPath();

			String pathJsp = courseFile + "/src/com/compoment/jspcss/jsp.txt";

			FileReader frJsp = new FileReader(pathJsp);
			BufferedReader brJsp = new BufferedReader(frJsp);

			String jspline;

			String newJsp = "";

			while (brJsp.ready()) {
				jspline = brJsp.readLine();

				//System.out.println(jspline);
				// img a ol li body table input h1 h2 h3 h4 textarea select
				// strong span th td header dd dt

				// 没有点 （标签样式）
				for (CssNode cssNode : csss) {
					
					
					if (jspline.contains("<" + cssNode.name )) {// 没有点
																		// （标签样式）
						if (!jspline.contains("style=\"")) {

							jspline = jspline.replace("<" + cssNode.name ,
									"<" + cssNode.name + " " + "style=\"" + cssNode.cssString + "\"") + "\n";

						} else {
							jspline = jspline.replace("style=\"", "style=\"" + cssNode.cssString + ";") + "\n";
						}
					}

				}

				// 有点
				for (CssNode cssNode : csss) {
					if (jspline.length() >= 1 && jspline.contains("class=\"")) {

						String classStringStart = jspline.substring(jspline.indexOf("class=\"") + 7);

						String classString = classStringStart.substring(0, classStringStart.indexOf("\""));

						for (String classStringpart1 : classString.split(" ")) {
							if (classStringpart1.equals(cssNode.name.substring(1))) {

								if (!jspline.contains("style=\"")) {

									jspline = jspline.replace("<" + cssNode.name ,
											"<" + cssNode.name + " " + "style=\"" + cssNode.cssString + "\" ") + "\n";

								} else {
									jspline = jspline.replace("style=\"", "style=\"" + cssNode.cssString + ";") + "\n";
								}
							}
						}

						jspline = jspline.replace("class=\"" + classString + "\"", "");

					}
				}

				
				

				newJsp += jspline + "\n";

			}

			brJsp.close();
			frJsp.close();

			System.out.println(newJsp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class CssNode {
		String name;
		boolean hasChirld;
		String cssString;
		List<CssNode> nodes;
	}

}