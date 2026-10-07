package org.example;

import org.example.dao.DoctorDaoImpl;
import org.example.dao.PatientDaoImpl;
import org.example.idao.DoctorDao;
import org.example.idao.PatientDao;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.repositories.PatientRepositoryImpl;

public class App {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        System.out.println("Doctor del paciente 1: " + doctorDao.getDoctorByPatientId(1));

        PatientRepository patientRepository = new PatientRepositoryImpl();
        System.out.println("Paciente 1 con su doctor: " + patientRepository.getPatient(1));
        System.out.println("Paciente que no existe: " + patientRepository.getPatient(-1)); // null
    }
}