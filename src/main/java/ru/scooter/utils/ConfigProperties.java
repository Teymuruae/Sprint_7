package ru.scooter.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {


    private static Properties properties = new Properties();


    static {
        try (FileInputStream fileInputStreem = new FileInputStream("src/main/resources/Config.properties")) {
            properties.load(fileInputStreem);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {

        return properties.getProperty(key);
    }


}