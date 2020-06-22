package jm.task.core.jdbc.dao;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private Connection connection = util.getConnection();
    private Statement st;
    private ResultSet rs;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            st = connection.createStatement();
            st.executeUpdate("CREATE TABLE User (Id INT,Name VARCHAR(10)," +
                    "LastName VARCHAR(10),Age INT)");
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try {
            st = connection.createStatement();
            st.executeUpdate("DROP TABLE User ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String SAVE_USERS = "INSERT User(Name,LastName,Age) VALUES (" + "'" + name + "'" + "," + "'" + lastName + "'" + "," +
                age + ")";
        try {
            st = connection.createStatement();
            st.executeUpdate(SAVE_USERS);
            System.out.println("User с именем " + name + " добавлен базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            st = connection.createStatement();
            st.executeUpdate("DELETE FROM User WHERE Id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT*FROM User");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte("Age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            st = connection.createStatement();
            st.executeUpdate("DELETE FROM User");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
