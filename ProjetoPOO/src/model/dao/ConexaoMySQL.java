package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoMySQL {

    public static Connection getConexao() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "admin");
        properties.setProperty("useSSL", "false");
        properties.setProperty("useTimezone", "true");
        properties.setProperty("serverTimezone", "UTC");
        properties.setProperty("allowPublicKeyRetrieval", "true");
        String con = "jdbc:mysql://localhost/projetopoo";
        return DriverManager.getConnection(con, properties);
    }
}
