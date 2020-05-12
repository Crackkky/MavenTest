import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Crunchify.com
 * Simple Hello World MySQL Tutorial on how to make JDBC connection, Add and Retrieve Data by App Shah
 *
 */

public class SqlJdbcConnection {

    static Connection connection = null;
    static PreparedStatement preparedStatement = null;

    public static void main(String[] argv) {
        try {
            makeJDBCConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter id:");
            String idString = br.readLine();
            System.out.println("Enter color:");
            String color = br.readLine();
            int id = Integer.parseInt(idString);

            addDataToDB(id, color);

            log("\n---------- Let's get Data from DB ----------");
            getDataFromDB();

            preparedStatement.close();
            connection.close(); // connection close

        } catch (SQLException | IOException e) {

            e.printStackTrace();
        }
    }

    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            log("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/TestJAVAjdbc", "root", "root");
            if (connection != null) {
                log("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("MySQL Connection Failed!");
            e.printStackTrace();
        }

    }

    private static void addDataToDB(int id, String color) {

        try {
            String insertQueryStatement = "INSERT  INTO  Vehicle  VALUES  (?,?)";

            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, color);

            // execute insert SQL statement
            preparedStatement.executeUpdate();
            log("The " + color + " vehicle, number " + id + " has been added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDataFromDB() {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM vehicle";

            preparedStatement = connection.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String id = rs.getString("id");
                String color = rs.getString("color");

                // Simply Print the results
                System.out.format("%s, %s\n", id, color);
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }
}