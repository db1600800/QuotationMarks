package com.compoment.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.compoment.remote.IphoneViewControllerXibInterface;
import com.compoment.remote.VersionCheckInterface;

public class VersionCheck  extends UnicastRemoteObject implements VersionCheckInterface {
	public VersionCheck() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	String nowVersion="1.0";
	
	public String hasNewVersion(String currentVersion) throws RemoteException
	{
		if(!currentVersion.equals(nowVersion))
		{
			
			
			File file = FileUtil.findFile(new File(KeyValue.readCache("projectPath")), "DevelopHelper" + "." + "zip");

			try {
				
				
				 
		       	
		          //FileUtil fileUtil=new FileUtil();
		          //return fileUtil.fileToByte(file.getPath());
			
				return "http://120.76.232.114:8080/DeveloperHelperHomePage/app/DevelopHelper.zip";
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else
			
		{
			return null;
		}
		
	}
}

