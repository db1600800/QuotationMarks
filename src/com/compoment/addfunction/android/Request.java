package com.compoment.addfunction.android;

//http://www.bejson.com/go.html?u=http://www.bejson.com/webInterface.html
//http://52android.blog.51cto.com/2554429/496621
//http://www.docin.com/p-465454174.html
//http://wenku.baidu.com/view/4de2df6f1eb91a37f1115ca1.html
//http://www.cnblogs.com/hanyonglu/archive/2012/02/19/2357842.html

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;
import com.compoment.util.RegexUtil.ControllerBean;
import com.compoment.workflow.PageInterfaceDocPanel;

/***
 * 网络
 * */
public class Request {

	String sourceAddress = KeyValue.readCache("compomentProjectAddress");// "C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress = KeyValue.readCache("projectPath");
	String waitByModifyFileName;
	PageInterfaceDocPanel pageInterfaceDocPanel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Paging("");
	}

	public Request(String waitByModifyFileName,PageInterfaceDocPanel pageInterfaceDocPanel) {
		this.waitByModifyFileName = waitByModifyFileName;
		this.pageInterfaceDocPanel=pageInterfaceDocPanel;
		copyFile();
		add();
	}

	public void copyFile() {

		List<FileBean> fileBeans = new ArrayList();
		String sourcePackage = "/com/compoment/network";
		String destinationPackage = "/com/compoment/network";
		

		fileBeans.add(new FileBean("/src/com/compoment/network"  , "/src/com/compoment/network"
				, "WaitActivity", "java"));
		
		fileBeans.add(new FileBean("/src" + sourcePackage, "/src"
				+ destinationPackage, "HttpClientManager", "java"));
		
		fileBeans.add(new FileBean("/res/layout", null, "progressbar", "xml"));
		fileBeans.add(new FileBean("/res/drawable", null, "progressbar_img_xml",
				"xml"));
		fileBeans.add(new FileBean("/res/drawable", null, "progressbar_bg",
				"xml"));
		fileBeans.add(new FileBean("/res/drawable-hdpi", null, "progressbar_img",
				"png"));
		
	
		for (FileBean bean : fileBeans) {

			File wantFile = FileUtil.findFile(new File(sourceAddress
					+ bean.sourcePath), bean.name + "." + bean.type);

			if (wantFile == null)
				System.out.println("找不到此文件" + bean.name + "." + bean.type);

			// 图片文件
			if (bean.type.equals("png") || bean.type.equals("jpg")) {
				try {
					FileUtil.copyFile(wantFile, new File(destinationAddress
							+ bean.destinationPath +"/"+ bean.name + "."
							+ bean.type));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {// 其它文件

				List lines = FileUtil.fileContentToArrayList(wantFile
						.getAbsolutePath());

				String content = "";
				for (int i = 0; i < lines.size(); i++) {
					String line = "";
					if (lines.get(i) == null) {
						line = "";
					} else {
						line = lines.get(i).toString();
					}

					content += line + "\n";
				}

				String filename = FileUtil.makeFile(destinationAddress
						+ bean.destinationPath, null, bean.name, bean.type,
						content);
				System.out.println(filename);
			}
		}

	}

	public void add() {

		List addedLines = new ArrayList();

		RegexUtil regex = new RegexUtil();

		List<String> lines = FileUtil.fileContentToArrayList(waitByModifyFileName);

		String className = "";

		/**类结尾位置*/
		int lastIndexOfEndClass=-1;
		// 类结尾位置
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line!=null && regex.rightBraceRegex(line)) {
				lastIndexOfEndClass=i;
			}
		}
		
		
		// 是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line!=null && line.contains("//注入网络请求,响应,等待提示")) {
				return;
			}
		}

		boolean findViewByIdFirst = true;
        
		
		//
		String content = "";
		content+="//注入网络请求,响应,等待提示\n";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if (lastIndexOfEndClass!=-1 && lastIndexOfEndClass==i) {
				// 类结尾位置
				String m="";
				for(Object select:pageInterfaceDocPanel.selects)
				{
					String id=select.toString().split(":")[0];
					String cn=select.toString().split(":")[1];
					
				m+=pageInterfaceDocPanel.requestFunction(id);
				pageInterfaceDocPanel.serverlet(id);
				}
				content += m;
			}else if(line.contains("//End注入RequestRespond"))
			{
				String m="";
				for(Object select:pageInterfaceDocPanel.selects)
				{
					String id=select.toString().split(":")[0];
					String cn=select.toString().split(":")[1];
					
				m+=pageInterfaceDocPanel.respondFunction(id);
			
				}
				content += m;
			}

			content += line + "\n";

		

		}

		String filename = FileUtil.makeFile(waitByModifyFileName, content);
	}
	
	
	
	


}
