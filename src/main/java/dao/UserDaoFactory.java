package dao;

import util.DBHelper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UserDaoFactory {

    private String getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        return properties.getProperty("daotype");
    }

    public UserDAO getUserDao() {
        try {
            String daoType = getProperties();
            switch (daoType) {
                case "jdbc":
                    return new UserJdbcDao(DBHelper.getDBHelper().getConnection());
                case "hibernate":
                    return new UserHibernateDAO(DBHelper.getDBHelper().getConfiguration().buildSessionFactory().openSession());
                default:
                    throw new IllegalArgumentException("Wrong doughnut type:" + daoType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
