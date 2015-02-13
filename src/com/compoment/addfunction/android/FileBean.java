package com.compoment.addfunction.android;

public class FileBean {
	String name;
	String type;
	String sourcePath;
	String destinationPath;

	public FileBean(String sourcePath, String destinationPath, String name,
			String type) {
		this.sourcePath = sourcePath;
		if (destinationPath == null) {
			this.destinationPath = sourcePath;
		} else {
			this.destinationPath = destinationPath;
		}
		this.name = name;
		this.type = type;
	}
}
