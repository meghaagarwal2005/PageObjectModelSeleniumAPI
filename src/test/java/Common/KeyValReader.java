package Common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class KeyValReader {

	private Properties properties;

	public KeyValReader(String envPath) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(envPath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties file could not be found at " + envPath);
		}
	}

	public String getProperty(String propertyName) {
		
		String systemPropertyValue = System.getProperty(propertyName);
		String propertyValue;
		
	    if (systemPropertyValue != null) {
	    	setProperty(propertyName, systemPropertyValue);
	    	propertyValue = systemPropertyValue;
	    } else {
			propertyValue = properties.getProperty(propertyName);
			if (propertyValue != null) {
				//property is present in the properties file
			} else {
				throw new RuntimeException("Cannot return " + propertyName);
	    	}
	    }
	
		return propertyValue;
	}

	public void setProperty(String key, String value) {
		try {
			properties.setProperty(key, value);
		} catch (Exception e) {
			throw new RuntimeException("Not able to set the property (key:" + key + " and value:" + value);
		}
	}

	public String readFile(String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			try {
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
				}
				return stringBuilder.toString();
			} finally {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
