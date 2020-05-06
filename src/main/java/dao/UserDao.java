package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public Boolean addUser(String name) {
        if (name.equals("")) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users_preproject (name) VALUES (?)");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteUserById(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users_preproject WHERE id = " + id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean modifyUserById(User user, Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users_preproject SET name = '" + user.getName() + "' WHERE id ='" + id + "'");
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> listUsers = new ArrayList<User>();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users_preproject");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("name"));
                listUsers.add(user);
            }
            stmt.close();
            return listUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public User getUserById(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users_preproject WHERE id ='" + id + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getLong("id"), resultSet.getString("name"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void createTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("create table if not exists users_preproject (id bigint auto_increment, name varchar(256), primary key (id))");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
