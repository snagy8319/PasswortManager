package com.pass.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.pass.helper.ui.PrintedColor;

public class DatabaseHandler {
    private Connection connection;

    public DatabaseHandler() {
        try {
            connectToDatabase();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        createUsersTableIfNotExists();
        createPasswordsTableIfNotExists();

    }


    private void connectToDatabase() throws ClassNotFoundException {
        try {
            // Check if the database file exists
            File dbFile = new File("easypass3.db");
            if (!dbFile.exists()) {
                // Clear the console
                System.out.print("\033[H\033[2J");
                System.out.flush();
                // Connect to the database
                this.connection = DriverManager.getConnection("jdbc:sqlite:easypass3.db");

                DatabaseMetaData meta = this.connection.getMetaData();


                System.out.println(
                        PrintedColor.successMessage + "===================================");
                System.out.println("Connection to SQLite has been established.");
                System.out.println("|  A new database" + dbFile + "has been created. |");
                System.out.println(" The driver name is " + meta.getDriverName());
                System.out.println("===================================" + PrintedColor.resetColor);
            } else {
                // Clear the console
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(
                        PrintedColor.successMessage + "===================================");
                System.out.println("Connection to SQLite has been established.");
                System.out.println("===================================" + PrintedColor.resetColor);
                // Connect to the database without printing the messages
                this.connection = DriverManager.getConnection("jdbc:sqlite:easypass3.db");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Integer validateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            // If the query returns a result, the user is valid
            if (resultSet.next()) {
                return resultSet.getInt("userId");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while validating the user: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        }
        return null;
    }

    public boolean addUser(String username, String password) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }



    public void updateEasyPassword(String username, String newPassword, int passID)
            throws SQLException {
        String sql = "UPDATE passwords SET password = ? WHERE passID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, passID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public ResultSet getAllEasyPasswords(String username) {
        String sql = "SELECT * FROM passwords WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }


    public boolean addEasyPassword(String website, String username, String password, String notes,
            Integer passID) throws SQLException {
        String sql =
                "INSERT INTO passwords (passID ,website, username, password, notes) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, passID);
            preparedStatement.setString(2, website);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, notes);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public void deleteEasyPassword(int passID) {
        String sql = "DELETE FROM passwords WHERE passID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, passID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printSQLException(SQLException e) {
        System.err.println("An error occurred: " + e.getMessage());
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
    }



    public void createUsersTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" + " userId integer PRIMARY KEY,"
                + " username text NOT NULL," + " password text NOT NULL" + ");";

        try (Statement stmt = connection.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void createPasswordsTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS passwords (" + " passID integer PRIMARY KEY,"
                + " website text NOT NULL," + " username text NOT NULL,"
                + " password text NOT NULL," + " notes text" + ");";

        try (Statement stmt = connection.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
