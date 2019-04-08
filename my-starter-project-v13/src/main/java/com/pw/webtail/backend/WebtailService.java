package com.pw.webtail.backend;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

/**
 * Simple backend service to store and retrieve {@link Category} instances.
 */
public class WebtailService {

	/**
	 * Helper class to initialize the singleton Service in a thread-safe way and to
	 * keep the initialization ordering clear between the two services. See also:
	 * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
	 */
	private static class SingletonHolder {
		static final WebtailService INSTANCE = new WebtailService();

		private SingletonHolder() {
		}

	}

	/**
	 * Declared private to ensure uniqueness of this Singleton.
	 */
	private WebtailService() {
	}

	/**
	 * Gets the unique instance of this Singleton.
	 *
	 * @return the unique instance of this Singleton
	 */
	public static WebtailService getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public List<String> getFileContent(String filePath) {
		try {
			File file = FileUtils.getFile(filePath);
			List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());

			return lines;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
