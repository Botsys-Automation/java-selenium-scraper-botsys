package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import logging.Logging;

public class PropertyReader implements Logging {

	private Properties prop = new Properties();

    public PropertyReader() {
        try (InputStream in = getClass().getResourceAsStream("/" + Constants.PROPERTIES_NAME)){
            try {
            	getLogger().debug("Attempting .properties file load.");
                prop.load(in);

            } catch (FileNotFoundException e) {
            	getLogger().error(Constants.PROPERTIES_NAME + " Property file not found", e.getLocalizedMessage());
            } 
        
        } catch (IOException e) {
        	getLogger().error("Error reading file " + Constants.PROPERTIES_NAME, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * @param propertyName
     * @return
     */
    public String getString(String propertyName) {
        if (prop.containsKey(propertyName)) {
            return prop.getProperty(propertyName);
        } else {
            return System.getenv(propertyName);
        }

    }

    public Integer getInt(String propertyName) {
    	int temp = -1;
    	
    	try {
    		temp = Integer.parseInt(this.getString(propertyName));
    	} catch (NumberFormatException e) {
    	}
        return temp;
    }

    public Double getDouble(String propertyName) {
        double temp = -1.0;

        try {
            temp = Double.parseDouble(this.getString(propertyName));
        } catch (NumberFormatException e) {
            getLogger().error("The property named: " + propertyName + " cannot be parsed to a Double.");
        }
        return temp;
    }
}
