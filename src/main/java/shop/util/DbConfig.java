package shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    private static final Connection CONFIG;

    static {
        try {
            CONFIG = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "457894561");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static Connection getConnection() {
        return CONFIG;
    }
}
