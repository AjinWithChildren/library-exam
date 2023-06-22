package bit.edu.exam.util;

import bit.edu.exam.Main;

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

//    private static final Properties properties = new Properties();

    public static Connection getConnection() {

        Connection con = null;
//
//        System.out.println(Main.class.getClass().getResource("db.properties"));
//
//        System.out.println(System.getProperty("user.dir"));

//        try {
//            properties.load(Main.class.getClass().getResourceAsStream("db.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try (FileReader fileReader = new FileReader("src/main/resources/db.properties")) {
//            properties.load(fileReader);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }






        String jdbcURL = "jdbc:mysql://localhost:3306/bitexam?useUnicode=true&serverTimezone=Asia/Seoul";
        String driver = "com.mysql.cj.jdbc.Driver";
        String id = "root";
        String password = "1234";


        try {
            Class.forName(driver);
            con = DriverManager.getConnection(jdbcURL,id,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
