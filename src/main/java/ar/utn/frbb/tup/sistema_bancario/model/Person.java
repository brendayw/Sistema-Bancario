package ar.utn.frbb.tup.sistema_bancario.model;

import java.time.LocalDate;

public class Person {
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private LocalDate birthDate;

    //constructor
    public Person(String name, String lastname, String dni, String email, String phone, LocalDate birthDate) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    // getters and Setters
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}