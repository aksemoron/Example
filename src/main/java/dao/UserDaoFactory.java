package dao;

import util.DBHelper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UserDaoFactory {

    private static String getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties"));
        return properties.getProperty("daotype");
    }

    public static UserDAO getUserDao() {
        try {
            String daoType = getProperties();
            switch (daoType) {
                case "jdbc":
                    return new UserJdbcDao(DBHelper.getDBHelper().getConnection());
                case "hibernate":
                    return new UserHibernateDAO(DBHelper.getDBHelper().getConfiguration().buildSessionFactory());
                default:
                    throw new IllegalArgumentException("Wrong doughnut type:" + daoType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
