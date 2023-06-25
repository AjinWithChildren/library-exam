package bit.edu.exam.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class ConnectionManager {

    private static final Properties properties = new Properties();

    public static Connection getConnection() {

        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/bitexam";
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "1234";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
