package Week9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/";
        String databaseName = "Course";
        String username = "root";
        String password = "";	

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            
            // Create the database
            String createDbQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            try (PreparedStatement createDbStatement = con.prepareStatement(createDbQuery)) {
                createDbStatement.executeUpdate();
            }

            // Connect to the specific database
            String connectUrl = url + databaseName;
            try (Connection dbConnection = DriverManager.getConnection(connectUrl, username, password)) {
                Statement st = dbConnection.createStatement();

                // Create the table
                String createTableQuery = "CREATE TABLE IF NOT EXISTS student (username varchar(255), email varchar(255), password varchar(255), confirm_password varchar(255), PRIMARY KEY (username))";
                st.execute(createTableQuery);

                // Insert data into the table
                String usernameValue = "";
                String emailValue = "";
                String passwordValue = "";
                String confirmPasswordValue = "";

                String insertQuery = "INSERT INTO student (username, email, password, confirm_password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStatement = dbConnection.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, usernameValue);
                    insertStatement.setString(2, emailValue);
                    insertStatement.setString(3, passwordValue);
                    insertStatement.setString(4, confirmPasswordValue);

                    insertStatement.executeUpdate();
                }

                System.out.println("Connection success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
