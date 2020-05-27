package util;

import dao.UserDaoFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {



    public static Properties getProperties(String propertiesFileName) throws IOException {
        Properties properties = new Properties();
        properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream(propertiesFileName+".properties"));
        return properties;
    }
}
