package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private final String propertyFilePath = "src//main//resources//config//config.properties";

    public ConfigReader()
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getBrowser() throws Exception {
        String browser = properties.getProperty("browser");
        if(browser!=null)
        {
            return browser;
        }
        else
        {
            throw new Exception("Browser not specified");
        }
    }

    public String getURL() throws Exception {
        String url = properties.getProperty("url");
        if(url!=null)
        {
            return url;
        }
        else
        {
            throw new Exception("URL not specified");
        }
    }

    public String getTitle() throws Exception {

        String title = properties.getProperty("title");
        if(title!=null)
        {
            return title;
        }
        else
        {
            throw new Exception("title not specified");
        }
    }
}
