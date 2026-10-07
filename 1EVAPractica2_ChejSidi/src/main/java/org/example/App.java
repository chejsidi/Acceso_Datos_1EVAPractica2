package org.example;

import org.example.dao.DoctorDaoImpl;
import org.example.dao.PatientDaoImpl;
import org.example.idao.DoctorDao;
import org.example.idao.PatientDao;
import org.example.irepositories.DoctorRepository;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.repositories.DoctorRepositoryImpl;
import org.example.repositories.PatientRepositoryImpl;

public class App {
    public static void main(String[] args) {
        DoctorRepository doctorRepository = new DoctorRepositoryImpl();

        Doctor ana = doctorRepository.getDoctor(1);
        System.out.println("Doctora: " + ana.getName() + " " + ana.getLastname());
        System.out.println("Pacientes atendidos:");
        for (Patient p : ana.getAttendedPatients()) {
            System.out.println("  " + p.getName() + " " + p.getLastname());
        }

        System.out.println("Doctor que no existe: " + doctorRepository.getDoctor(-1)); // null


        PatientRepository patientRepository = new PatientRepositoryImpl();
        System.out.println("¿Ana (1) atiende a Mikel (1)? " + patientRepository.isPatientAttendedByDoctor(1, 1)); // true
        System.out.println("¿Doctor 99 atiende a Mikel (1)? " + patientRepository.isPatientAttendedByDoctor(1, 99)); // false
        System.out.println("¿Ana atiende a un paciente inexistente? " + patientRepository.isPatientAttendedByDoctor(-1, 1)); // false
    }
}