package com.pw.fsexplorer.backend;

public class FSExplorerService {

	private static class SingletonHolder {
		static final FSExplorerService INSTANCE = new FSExplorerService();

		private SingletonHolder() {
		}

	}

	public static FSExplorerService getInstance() {
		return SingletonHolder.INSTANCE;
	}

	
}
