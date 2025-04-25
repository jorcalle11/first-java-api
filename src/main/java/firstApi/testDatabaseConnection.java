package firstApi;

import java.sql.*;

public class testDatabaseConnection {
  
  // Test the connection to the database
  public static void main(String[] args) {
    try (Connection conn = MySQLConnection.getConnection()) {
      System.out.println("✅ Connected to MySQL!");
      
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users");
      
      while (rs.next()) {
        System.out.println("👤 " + rs.getString("name") + " - " + rs.getString("email"));
      }
      
    } catch (SQLException e) {
      System.out.println("❌ Error connecting: " + e.getMessage());
    }
  }
}