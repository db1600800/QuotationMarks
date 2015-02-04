package com.compoment.cut.swing.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

	/**
	 * 字符串写出到文件
	 * */
	public static String makeFile(String filePath, String secondPath,
			String fileName, String fileType, String s) {
		String xmlFileName = "";
		try {
			// String doc=KeyValue.readCache("picPath");

			xmlFileName = filePath + "/" + secondPath + "/" + fileName + "."
					+ fileType;

			File tofile = new File(filePath + "/" + secondPath + "/" + fileName
					+ "." + fileType);
			if (!tofile.exists()) {
				makeDir(tofile.getParentFile());
			}

			tofile.createNewFile();

			FileWriter fw;

			fw = new FileWriter(tofile);
			BufferedWriter buffw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffw);

			pw.println(s);

			pw.close();
			buffw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xmlFileName;
	}

	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	
	/**
	 * 取文件内容到字符串
	 * */
	public static String fileContent(String filePath) {
		String myreadline = "";
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				myreadline += br.readLine() + "\n";

			}

			br.close();

			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return myreadline;

	}

}
