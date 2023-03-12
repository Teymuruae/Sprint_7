package ru.scooter.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import static java.nio.charset.StandardCharsets.*;
public class ConfigProperties {


    private static Properties properties = new Properties();


    static {
        try (FileInputStream fileInputStreem = new FileInputStream("src/main/resources/Config.properties")) {
            properties.load(fileInputStreem);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static String getProperty( String key) {

        return properties.getProperty(new String (key));

    }


}