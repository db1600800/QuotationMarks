package com.compoment.cut.swing.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
			if (secondPath == null) {
				xmlFileName = filePath + "/" + fileName + "." + fileType;
			} else {
				xmlFileName = filePath + "/" + secondPath + "/" + fileName
						+ "." + fileType;
			}

			File tofile = new File(xmlFileName);
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

	/** 递归方法 找文件 */
	public static File findFile(File startDirectory, String wantFindFileName) {

		File[] childs = startDirectory.listFiles(); // listFiles()返回目录下的所有文件.
		for (int i = 0; i < childs.length; i++) {

			if (childs[i].isDirectory()) { // isDirectory()判断是否为目录
				findFile(childs[i], wantFindFileName);
			} else {
				String fileName = childs[i].getName();
				if (fileName.equals(wantFindFileName)) {
					return childs[i];
				}
			}
		}
		return null;
	}
	
	
	 // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

}
