package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:practica.db";
            this.connection = DriverManager.getConnection(url);
            prepararBaseDeDatos();
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encuentra el driver de SQLite", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }


    private void prepararBaseDeDatos() throws SQLException {
        try (Statement st = connection.createStatement()) {
            // En SQLite las claves foráneas vienen desactivadas: hay que activarlas en cada conexión
            st.execute("PRAGMA foreign_keys = ON");

            st.execute("CREATE TABLE IF NOT EXISTS doctors ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "lastname TEXT NOT NULL, "
                    + "dni TEXT NOT NULL, "
                    + "salary REAL NOT NULL, "
                    + "speciality TEXT NOT NULL)");

            st.execute("CREATE TABLE IF NOT EXISTS patients ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "lastname TEXT NOT NULL, "
                    + "dni TEXT NOT NULL, "
                    + "age INTEGER NOT NULL, "
                    + "phone TEXT, "
                    + "disease TEXT, "
                    + "doctor_id INTEGER REFERENCES doctors(id) ON DELETE SET NULL)");

            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM doctors");
            if (rs.next() && rs.getInt(1) == 0) {
                st.execute("INSERT INTO doctors (name, lastname, dni, salary, speciality) "
                        + "VALUES ('Ana', 'García López', '12345678A', 3500.00, 'Cardiología')");
                st.execute("INSERT INTO patients (name, lastname, dni, age, phone, disease, doctor_id) VALUES "
                        + "('Mikel', 'Etxeberria Ruiz', '11111111B', 45, '600111222', 'Hipertensión', 1), "
                        + "('Laura', 'Sánchez Díaz', '22222222C', 32, '600333444', 'Arritmia', 1)");
            }
        }
    }
}