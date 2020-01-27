package com.mafia.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created by girija-4135 on 24/01/20
 */
public class Configuration {
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

    private static final String FOLDER_CONF = "conf";		// No I18N
    private static final String FILE_EXT_PROPERTIES = "properties";		// No I18N
    private static final Properties PROPERTIES = new Properties();

    static
    {

        try {

            URI uri = Configuration.class.getClassLoader().getResource(FOLDER_CONF).toURI();
            File folder = new File(uri);
            File[] listOfFiles = folder.listFiles();
            for (int i =0 ; i < listOfFiles.length ; i++)
            {
                File file = listOfFiles[i];

                if(getFileExtension(file.getName()).equals(FILE_EXT_PROPERTIES)) {
                    PROPERTIES.load(new FileInputStream(file));
                }
            }
        }catch (Exception e)
        {
            LOGGER.log(Level.SEVERE,String.format("Problem in loading all property files inside conf folder.Exception message : %s",e.getMessage()), e);
        }
    }

    public static Boolean getBoolean(String key)
    {
        String value = getValue(key);
        if (value != null)
        {
            return Boolean.parseBoolean(value);
        }
        else
        {
            return null;
        }
    }

    public static Boolean getBoolean(String key, boolean defaultValue)
    {
        String value = getValue(key, String.valueOf(defaultValue));
        return Boolean.parseBoolean(value);
    }

    public static String getValue(String key, String defaultValue)
    {
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public static String getValue(String key)
    {
        return PROPERTIES.getProperty(key);
    }

    private static String getFileExtension(String fileName){
        return fileName.split("\\.")[1];
    }
}
