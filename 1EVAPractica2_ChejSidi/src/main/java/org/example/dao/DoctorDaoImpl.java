package org.example.dao;

import org.example.idao.DoctorDao;
import org.example.models.Doctor;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    /** Inserta el doctor y devuelve el id que le ha asignado la base de datos (-1 si falla). */
    @Override
    public int add(Doctor doctor) {
        String query = "insert into doctors (name, lastname, dni, salary, speciality) values (?, ?, ?, ?, ?)";
        int id = -1;
        // el PreparedStatement se cierra solo al terminar.
        // no cierro la conexion porque es el singleton
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection()
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getLastname());
            ps.setString(3, doctor.getDni());
            ps.setDouble(4, doctor.getSalary());
            ps.setString(5, doctor.getSpeciality());
            ps.executeUpdate();

            // RETURN_GENERATED_KEYS permite recuperar el id AUTO_INCREMENT generado
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                doctor.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delete(int id) {
        String query = "delete from doctors where id=?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Devuelve el doctor con ese id, o null si no existe. */
    @Override
    public Doctor getDoctor(int id) {
        String query = "select * from doctors where id=?";
        Doctor doctor = null;
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doctor = crearDoctor(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getDoctors() {
        String query = "select * from doctors";
        List<Doctor> doctors = new ArrayList<>();
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doctors.add(crearDoctor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    /** Devuelve true si se ha actualizado, false si el doctor no existe o ha fallado. */
    @Override
    public boolean update(Doctor doctor) {
        if (!doctorExists(doctor.getId())) {
            return false;
        }
        String query = "update doctors set name=?, lastname=?, dni=?, salary=?, speciality=? where id=?";
        int filas = 0;
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getLastname());
            ps.setString(3, doctor.getDni());
            ps.setDouble(4, doctor.getSalary());
            ps.setString(5, doctor.getSpeciality());
            ps.setInt(6, doctor.getId());
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filas > 0;
    }

    private boolean doctorExists(int id) {
        return getDoctor(id) != null;
    }

    /** Convierte la fila actual del ResultSet en un objeto Doctor. */
    private Doctor crearDoctor(ResultSet rs) throws SQLException {
        return new Doctor(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("lastname"),
                rs.getString("dni"),
                rs.getDouble("salary"),
                rs.getString("speciality"));
    }
}