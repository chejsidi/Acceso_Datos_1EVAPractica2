package org.example;

import org.example.dao.DoctorDaoImpl;
import org.example.dao.PatientDaoImpl;
import org.example.idao.DoctorDao;
import org.example.idao.PatientDao;
import org.example.models.Doctor;
import org.example.models.Patient;

public class App {
    public static void main(String[] args) {
        PatientDao patientDao = new PatientDaoImpl();

        Patient nuevo = new Patient("Jon", "Aguirre Gil", "33333333D", 28, "600555666", "Asma");
        int id = patientDao.add(nuevo);
        System.out.println("Añadido con id: " + id);

        System.out.println("Leído: " + patientDao.getPatient(id));

        nuevo.setAge(29);
        System.out.println("Actualizado: " + patientDao.update(nuevo));
        System.out.println("Después de actualizar: " + patientDao.getPatient(id));

        System.out.println("Todos los pacientes:");
        for (Patient p : patientDao.getPatients()) {
            System.out.println("  " + p);
        }

        patientDao.delete(id);
        System.out.println("Después de borrar: " + patientDao.getPatient(id));

    }
}