package org.example.models;

public class Patient {
    private int id;
    private String name;
    private String lastname;
    private String dni;
    private int age;
    private String phone;
    private String disease;

    public Patient() {
    }

    public Patient(String name, String lastname, String dni, int age, String phone, String disease) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.age = age;
        this.phone = phone;
        this.disease = disease;
    }

    public Patient(int id, String name, String lastname, String dni, int age, String phone, String disease) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.age = age;
        this.phone = phone;
        this.disease = disease;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni='" + dni + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", disease='" + disease + '\'' +
                '}';
    }
}