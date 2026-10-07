package org.example.repositories;

import org.example.dao.DoctorDaoImpl;
import org.example.dao.PatientDaoImpl;
import org.example.idao.DoctorDao;
import org.example.idao.PatientDao;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {
    private PatientDao patientDao = new PatientDaoImpl();
    private DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public Patient getPatient(int id) {
        Patient patient = patientDao.getPatient(id);
        if (patient == null) {
            return null;
        }
        Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
        patient.setDoctor(doctor);
        return patient;
    }
}