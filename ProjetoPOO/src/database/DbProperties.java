package database;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author DEVENG
 */
public class DbProperties {

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
            Properties props = new Properties();
            props.setProperty("user", "root");
            props.setProperty("password", "admin");
            props.setProperty("useSSL", "false");
            props.setProperty("useTimezone", "true");
            props.setProperty("serverTimezone", "UTC");
            props.setProperty("allowPublicKeyRetrieval", "true");
            String url = "jdbc:mysql://localhost/projetopoo";
            conn = DriverManager.getConnection(url, props);
            return conn;
    }
}
