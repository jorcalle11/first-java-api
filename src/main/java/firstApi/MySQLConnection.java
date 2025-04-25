package firstApi;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * MySQLConnection class to manage the connection to a MySQL database.
 * It loads the database configuration from a properties file and provides methods to connect to the database.
 */


public class MySQLConnection {
  private static final String url;
  private static final String user;
  private static final String password;
  
  // Load database properties from db.properties file at runtime
  static {
    try (InputStream input = MySQLConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
      Properties prop = new Properties();
      if (input == null) {
        throw new RuntimeException("⚠️ db.properties file not found");
      }
      prop.load(input);
      url = prop.getProperty("db.url");
      user = prop.getProperty("db.user");
      password = prop.getProperty("db.password");
    } catch (Exception ex) {
      throw new RuntimeException("❌ Failed to load database config: " + ex.getMessage(), ex);
    }
  }
  
  // Method to get a connection to the database
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }
}