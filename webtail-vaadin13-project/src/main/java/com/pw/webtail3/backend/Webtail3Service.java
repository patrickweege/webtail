package com.pw.webtail3.backend;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

public class Webtail3Service {

	private final Map<String, Tailer> tailers;

	private static class SingletonHolder {
		static final Webtail3Service INSTANCE = new Webtail3Service();

		private SingletonHolder() {
		}

	}

	private Webtail3Service() {
		this.tailers = new HashMap<String, Tailer>();
	}

	public static Webtail3Service getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public void doStartReadFile(String id, String filePath) {
		try {
			synchronized (this.tailers) {
				InternalTailerListener tl = new InternalTailerListener(id);
				Tailer tailer = Tailer.create(new File(filePath), tl, 1000, false);
				tailers.put(id, tailer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void doStopReadFile(String id) {
		Tailer removedTailer = tailers.remove(id);
		removedTailer.stop();
	}

	private class InternalTailerListener implements TailerListener {

		private final String id;

		public InternalTailerListener(String id) {
			this.id = id;
		}
		
		@Override
		public void init(Tailer tailer) {
			// TODO Auto-generated method stub
		}

		@Override
		public void handle(Exception ex) {
			// TODO Auto-generated method stub
		}

		@Override
		public void handle(String line) {
			Webtail3Broadcaster.broadcast(this.id, line);
		}

		@Override
		public void fileRotated() {
			// TODO Auto-generated method stub

		}

		@Override
		public void fileNotFound() {
			// TODO Auto-generated method stub

		}
	};

}
