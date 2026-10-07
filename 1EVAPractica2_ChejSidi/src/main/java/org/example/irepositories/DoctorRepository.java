package org.example.irepositories;

import org.example.models.Doctor;

public interface DoctorRepository {
    Doctor getDoctor(int doctor_id);
}