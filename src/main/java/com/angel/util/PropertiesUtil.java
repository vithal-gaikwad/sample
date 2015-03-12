package com.angel.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	public static Properties properties = null;

	public static String getProperty(String uploadPath) {
		String result = null;
		if (properties == null) {
			loadProperties();
		}
		result = properties.getProperty(uploadPath);
		return result;
	}

	public static void loadProperties() {

		properties = new Properties();
		try {
			properties.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("config.properties"));

		} catch (IOException ex) {
			logger.error("IOException", ex);
		}
	}

}