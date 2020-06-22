package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/baida?useUnicode=true&serverTimezone=UTC";
    StringBuilder Change_URL = new StringBuilder(URL);
    private final String USER = "root";
    private final String PASSWORD = "ROOT";
    private Connection connection;

    public Connection getConnection(){
        try{
            connection = DriverManager.getConnection(Change_URL.toString(), USER, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
