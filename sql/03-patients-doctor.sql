ALTER TABLE patients
    ADD COLUMN doctor_id INT NULL,
    ADD CONSTRAINT fk_patients_doctor
        FOREIGN KEY (doctor_id) REFERENCES doctors (id)
        ON DELETE SET NULL;

UPDATE patients SET doctor_id = 1;