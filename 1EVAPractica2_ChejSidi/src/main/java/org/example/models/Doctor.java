package org.example.models;

public class Doctor {
    private int id;
    private String name;
    private String lastname;
    private String dni;
    private double salary;
    private String speciality;

    public Doctor() {
    }
    public Doctor(String name, String lastname, String dni, double salary, String speciality) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.salary = salary;
        this.speciality = speciality;
    }

    public Doctor(int id, String name, String lastname, String dni, double salary, String speciality) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.salary = salary;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni='" + dni + '\'' +
                ", salary=" + salary +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}