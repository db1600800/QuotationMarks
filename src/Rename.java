import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rename {

	String sourceFile;

	public static void main(String[] args) {

		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sourceFile = classDir + "/src/com";

		Rename rename = new Rename();

		rename.reName(sourceFile);
	}

	public Rename() {

		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sourceFile = classDir + "/src/com";

	}

	File currentFile;
	String currentName;
	String currentFileParent;

	public void reName(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reName(f.getAbsolutePath());
					} else {
						currentFile = f;

						if (currentFile.getName().contains(".java")) {

							currentName = "A" + Math.round(Math.random() * 8999 + 1000);
							String parentPath = f.getParentFile().getAbsolutePath();
							currentFileParent = parentPath.substring(parentPath.lastIndexOf("\\")+1);
							reContent(sourceFile);

							f.renameTo(new File(parentPath + "/" + currentName + ".java"));// 记得将路径也输入
						}
					}
				}
			}
		} else {
			// rootFile.renameTo(new File(path + Math.round(Math.random() * 8999
			// + 1000) + ".jpg"));// 记得将路径也输入
		}
	}

	public void reContent(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reContent(f.getAbsolutePath());
					} else {

						// if(!currentFile.getAbsolutePath().equals(f.getAbsolutePath()));
						List<String> lines = FileUtil.fileContentToArrayList(f.getAbsolutePath());

						boolean canGoToModify = false;
						for (int ii = 0; ii < lines.size(); ii++) {
							String line = lines.get(ii);

							if (line.contains("import ") && line.contains("." + currentFileParent)) {
								canGoToModify = true;
							}
							
							
							if (f.getParent().equals(currentFile.getParent())) {
								canGoToModify = true;
							}
							
							
						}

						if (canGoToModify == true ||f.getAbsolutePath().equals(currentFile.getAbsolutePath())) {
							String m = "";
							for (int ii = 0; ii < lines.size(); ii++) {
								String line = lines.get(ii);
								int point = currentFile.getName().indexOf(".");
								if (point != -1) {
									String filename = currentFile.getName().substring(0, point);

									
									if (line.contains(filename) && !line.contains("m+=\"") && !line.contains("m +=\"")
											&& !line.contains("m += \"") && !line.contains("m+= \"")) {

										System.out.println("before:"+line);
										
										String regex = "(\\(|!|=|,|\\.|<| |	|\\{|\\))" + filename
												+ "(;|,|\\.|>| |\\{|\\(|\\))";
										Pattern pattern = Pattern.compile(regex);

										Matcher matcher = pattern.matcher(line);

										StringBuffer sb = new StringBuffer();
										while (matcher.find()) {

											String matchStr = matcher.group();
											matchStr = matchStr.replaceAll(filename, currentName);

											matcher.appendReplacement(sb, matchStr);
										}
										matcher.appendTail(sb);
										line = sb.toString();
										
										System.out.println("after:"+line);
									}

								}

								m += line + "\n";

							}

							FileUtil.makeFile(f.getAbsolutePath(), m);
						}
					}
				}
			}
		} else {
			// rootFile.renameTo(new File(path + Math.round(Math.random() * 8999
			// + 1000) + ".jpg"));// 记得将路径也输入
		}
	}

	public String rightIsWhat(String line, String key) {

		int p = line.indexOf(key);
		if (p != -1) {

			if (p + key.length() + 1 <= line.length()) {
				String what = line.substring(p + key.length(), p + key.length() + 1);

				if (what.equals(";") || what.equals(",") || what.equals(".") || what.equals(">") || what.equals(" ")
						|| what.equals("{") || what.equals("(") || what.equals(")")) {

					return what;
				}
			} else if (p + key.length() == line.length()) {

				return "";
			}

		}

		return null;
	}

	public String leftIsWhat(String line, String key) {

		int p = line.indexOf(key);
		if (p != -1) {

			if (p - 1 >= 0) {
				String what = line.substring(p - 1, p);

				if (what.equals("(") || what.equals("!") || what.equals("=") || what.equals(",") || what.equals(".")
						|| what.equals("<") || what.equals(" ") || what.equals("	") || what.equals("{")
						|| what.equals("("))
					return what;
			} else if (p - 1 == -1) {
				return "";
			}

		}
		return null;
	}
}
