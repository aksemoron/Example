package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDAO<User> {
    Connection connection;

    public UserJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(User user) {
        if (user.getName().equals("")) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users_preproject " +
                    "(name,password,role) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteById(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users_preproject " +
                    "WHERE id = " + user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //    @Override
    public boolean modifyUserById(User user, Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users_preproject SET name = '" +
                    user.getName() + "'" + ", password = '" + user.getPassword() + "'" + ", role = '" + user.getRole() +
                    "'" +
                    " WHERE id ='" + id + "'");
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        try {
            List<User> listUsers = new ArrayList<User>();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users_preproject");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("name"));
                user.setRole(resultSet.getString("role"));
                user.setPassword(resultSet.getString("password"));
                listUsers.add(user);
            }
            stmt.close();
            return listUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public User getById(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users_preproject " +
                    "WHERE id ='" + user.getId() + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User userReturn = new User(resultSet.getLong("id"), resultSet.getString("name"));
            userReturn.setRole(resultSet.getString("role"));
            userReturn.setPassword(resultSet.getString("password"));
            return userReturn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User logIN(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users_preproject " +
                    "WHERE name ='" + user.getName() + "'"+" AND password = '"+user.getPassword()+"'");
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            String role = resultSet.getString("role");
            User user1 = new User(id, user.getName(), user.getPassword(), role);
            System.out.println(user1.getId());
            return new User(id, user.getName(), user.getPassword(), role);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void createTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("create table if not exists users_preproject (id bigint auto_increment," +
                    " name varchar(256), primary key (id))");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
