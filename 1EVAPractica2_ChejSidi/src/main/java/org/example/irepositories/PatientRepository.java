package org.example.irepositories;

import org.example.models.Patient;

public interface PatientRepository {
    Patient getPatient(int id);
    //void add(Patient patient);
    //void update(Patient patient);
    //void remove(Patient patient);
}