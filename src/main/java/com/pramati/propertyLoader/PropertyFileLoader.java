package com.pramati.propertyLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Manish
 * 
 *         This class is used to load the property file
 */
public class PropertyFileLoader {

	public Properties loadProperty() {

		Properties prop = new Properties();
		try {

			File jarPath = new File(getClass().getProtectionDomain()
					.getCodeSource().getLocation().getPath());
			String propertiesPath = jarPath.getParentFile().getAbsolutePath();

			prop.load(new FileInputStream(propertiesPath
					+ "/crawler.properties"));
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		return prop;
	}
}
