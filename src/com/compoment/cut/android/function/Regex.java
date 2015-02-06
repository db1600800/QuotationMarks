package com.compoment.cut.android.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//http://www.jb51.net/article/31251.htm

public class Regex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean classRegex(String input) {
		// String
		// input="public class About extends Activity implements OnScrollListener {\n";

		String regex = "(public|private)(\\s+)class(\\s+)(\\w+)(\\s+)extends([/s/S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			// System.out.println(matcher.group());
			return true;
		}
		return false;
	}
	
	
	public boolean functionRegex(String input) {
		// String
		// input="public class About extends Activity implements OnScrollListener {\n";

		String regex = "(public|private)(\\s+)class(\\s+)(\\w+)(\\s+)extends([/s/S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			// System.out.println(matcher.group());
			return true;
		}
		return false;
	}

}
