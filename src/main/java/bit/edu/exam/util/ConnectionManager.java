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

        try (FileReader fileReader = new FileReader("src/main/resources/db.properties")) {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = (String) properties.get("url");
        String driver = (String) properties.get("driver");
        String username = (String) properties.get("username");
        String password = (String) properties.get("password");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
