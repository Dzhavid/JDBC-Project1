package jm.task.core.jdbc.dao;


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
    private final String CREATE_USERS_TABLE = "CREATE TABLE User (Id INT,Name VARCHAR(10)," +
            "LastName VARCHAR(10),Age INT)";
    private final String DROP_USERS_TABLE = "DROP TABLE User ";
    private final String REMOVE_USERS_BY_ID = "DELETE FROM User WHERE Id = ";
    private final String GET_ALL_USERS = "SELECT*FROM User";
    private final String CLEAN_USERS_TABLE = "DELETE FROM User";
    private Util util = new Util();
    private Statement st;
    private ResultSet rs;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            st = util.getConnection().createStatement();
            st.executeUpdate(CREATE_USERS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            st = util.getConnection().createStatement();
            st.executeUpdate(DROP_USERS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String SAVE_USERS = "INSERT User(Name,LastName,Age) VALUES (" + "'" + name + "'" + "," + "'" + lastName + "'" + "," +
                age + ")";
        try {
            st = util.getConnection().createStatement();
            st.executeUpdate(SAVE_USERS);
            System.out.println("User с именем " + name + " добавлен базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            st = util.getConnection().createStatement();
            st.executeUpdate(REMOVE_USERS_BY_ID + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            st = util.getConnection().createStatement();
            rs = st.executeQuery(GET_ALL_USERS);
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
            st = util.getConnection().createStatement();
            st.executeUpdate(CLEAN_USERS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
