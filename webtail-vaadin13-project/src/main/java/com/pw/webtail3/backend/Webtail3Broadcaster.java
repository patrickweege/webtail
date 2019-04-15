package com.pw.webtail3.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.vaadin.flow.shared.Registration;

public class Webtail3Broadcaster {
	static Executor executor = Executors.newSingleThreadExecutor();

	static Map<String, Consumer<String>> listeners = new HashMap<>();

	public static synchronized Registration register(String id, Consumer<String> listener) {
		listeners.put(id, listener);

		return () -> {
			synchronized (Webtail3Broadcaster.class) {
				listeners.remove(id);
			}
		};
	}

	public static synchronized void broadcast(String id, String message) {
		Consumer<String> consumer = listeners.get(id);
		executor.execute(() -> consumer.accept(message));
	}
}
