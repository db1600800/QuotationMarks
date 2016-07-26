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

	String nowVersion="2.0";
	
	public byte[] hasNewVersion(String currentVersion) throws RemoteException
	{
		if(!currentVersion.equals(nowVersion))
		{
			
			
			File file = FileUtil.findFile(new File(KeyValue.readCache("projectPath")), "DevelopHelper" + "." + "zip");

			try {
				
				
				  //byte[] content = new byte[(int)file.length()];
				  //BufferedInputStream  input = new BufferedInputStream(new FileInputStream(file));
		          // input.read(content);
		       	//return content;
		       	
		          FileUtil fileUtil=new FileUtil();
		          return fileUtil.fileToByte(file.getPath());
			
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

