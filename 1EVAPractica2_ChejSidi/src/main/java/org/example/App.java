package org.example;

import org.example.utils.DatabaseConnection;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        System.out.println("CONECTADO: " + databaseConnection.getConnection().isValid(2));
    }
}