package org.example.dao;

import org.example.idao.PatientDao;
import org.example.models.Patient;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public int add(Patient patient) {
        String query = "insert into patients (name, lastname, dni, age, phone, disease) values (?, ?, ?, ?, ?, ?)";
        int id = -1;
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection()
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setInt(4, patient.getAge());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getDisease());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                patient.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delete(int id) {
        String query = "delete from patients where id=?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Devuelve el paciente con ese id, o null si no existe. */
    @Override
    public Patient getPatient(int id) {
        String query = "select * from patients where id=?";
        Patient patient = null;
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patient = crearPatient(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> getPatients() {
        String query = "select * from patients";
        List<Patient> patients = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                patients.add(crearPatient(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    /** Devuelve true si se ha actualizado, false si el paciente no existe o ha fallado. */
    @Override
    public boolean update(Patient patient) {
        if (!patientExists(patient.getId())) {
            return false;
        }
        String query = "update patients set name=?, lastname=?, dni=?, age=?, phone=?, disease=? where id=?";
        int filas = 0;
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setInt(4, patient.getAge());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getDisease());
            ps.setInt(7, patient.getId());
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filas > 0;
    }

    private boolean patientExists(int id) {
        return getPatient(id) != null;
    }

    /** Convierte la fila actual del ResultSet en un objeto Patient. */
    private Patient crearPatient(ResultSet rs) throws SQLException {
        return new Patient(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("lastname"),
                rs.getString("dni"),
                rs.getInt("age"),
                rs.getString("phone"),
                rs.getString("disease"));
    }
}