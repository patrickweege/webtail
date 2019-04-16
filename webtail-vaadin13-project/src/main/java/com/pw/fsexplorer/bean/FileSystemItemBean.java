package com.pw.fsexplorer.bean;

public class FileSystemItemBean {

	private final String name;
	private final int size;
	private final String modified;
	private final String path;

	public FileSystemItemBean(String name, int size, String modified, String path) {
		super();
		this.name = name;
		this.size = size;
		this.modified = modified;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public String getModified() {
		return modified;
	}

	public String getPath() {
		return path;
	}

}
