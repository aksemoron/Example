package util;


import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBHelper {

    private static DBHelper dbHelper;

    public static DBHelper getDBHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    private DBHelper() {

    }

    public Connection getConnection() throws IOException {

        Properties properties = PropertyReader.getProperties("jdbc");
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            //передать строку через параметры как в конфигурашин  =!
            return DriverManager.getConnection(properties.getProperty("jdbcConfig"));
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Configuration getConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        return configuration.setProperties(PropertyReader.getProperties("hibernate"));



        /*
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "6831");
        configuration.setProperty("hibernate.show_sql", "true");
//       configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;*/
    }

}
