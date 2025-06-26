package online_quiz_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Update DB name and credentials if needed
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quiz_system", "root", "Panu@123");
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;
        }
    }
}
