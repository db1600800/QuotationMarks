import java.io.File;
import java.io.IOException;
import java.util.List;

import com.compoment.util.FileUtil;

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

		String sourceFile = classDir + "/src";


	
		
		Rename rename=new Rename();
		
		rename.reName(sourceFile);
	}
	
	public Rename()
	{
		
		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sourceFile = classDir + "/src";

	}

	File currentFile;
	String currentName;
	public  void reName(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reName(f.getAbsolutePath());
					} else {
						currentFile=f;
						currentName= "A"+Math.round(Math.random() * 8999 + 1000) ;
						reContent(sourceFile);
						f.renameTo(new File(currentName+".java"));// 记得将路径也输入
					}
				}
			}
		} else {
			//rootFile.renameTo(new File(path + Math.round(Math.random() * 8999 + 1000) + ".jpg"));// 记得将路径也输入
		}
	}
	
	
	
	
	public  void reContent(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reContent(f.getAbsolutePath());
					} else {
						
						//if(!currentFile.getAbsolutePath().equals(f.getAbsolutePath()));
						List<String> lines = FileUtil.fileContentToArrayList(f.getAbsolutePath());
						
						for(int ii=0;ii<lines.size();ii++)
						{
							String line=lines.get(ii);
							if(line.contains(currentFile.getName()))
							{
								
							}
							
						}
					}
				}
			}
		} else {
			//rootFile.renameTo(new File(path + Math.round(Math.random() * 8999 + 1000) + ".jpg"));// 记得将路径也输入
		}
	}
	
	
}
