package dao;

import util.DBHelper;
import util.PropertyReader;

import java.io.*;
import java.util.Properties;

//в отдельный пакет =!
public class UserDaoFactory {


    public static UserDAO getUserDao() {
        try {
            Properties properties = PropertyReader.getProperties("realisationConectionTobase");
            String daoType = properties.getProperty("daotype");
            switch (daoType) {
                case "jdbc":
                    return new UserJdbcDao(DBHelper.getDBHelper().getConnection());
                case "hibernate":
                    return new UserHibernateDAO(DBHelper.getDBHelper().getConfiguration().buildSessionFactory());
                default:
                    return new UserHibernateDAO(DBHelper.getDBHelper().getConfiguration().buildSessionFactory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
