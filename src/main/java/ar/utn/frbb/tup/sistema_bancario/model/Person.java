package ar.utn.frbb.tup.sistema_bancario.model;

public class Person {
    private long id_client;
    private String name;
    private String lastname;
    private String email;
    private String phone;

    //constructor
    public Person(long id_client, String name, String lastname, String email, String phone) {
        this.id_client = id_client;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    // getters and Setters
    public long getId_client() {
        return id_client;
    }
    public void setId_client(long id_client) {
        this.id_client = id_client;
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
}