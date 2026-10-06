package org.example;

import org.example.dao.DoctorDaoImpl;
import org.example.idao.DoctorDao;
import org.example.models.Doctor;

public class App {
    public static void main(String[] args) {
        //test
        DoctorDao doctorDao = new DoctorDaoImpl();

        Doctor nuevo = new Doctor("Luis", "Martín Pérez", "87654321B", 3000, "Pediatría");
        int id = doctorDao.add(nuevo);
        System.out.println("Añadido con id: " + id);

        System.out.println("Leído: " + doctorDao.getDoctor(id));

        nuevo.setSalary(3200);
        System.out.println("Actualizado: " + doctorDao.update(nuevo));
        System.out.println("Después de actualizar: " + doctorDao.getDoctor(id));

        System.out.println("Todos los doctores:");
        for (Doctor d : doctorDao.getDoctors()) {
            System.out.println("  " + d);
        }

        doctorDao.delete(id);
        System.out.println("Después de borrar: " + doctorDao.getDoctor(id));
    }
}