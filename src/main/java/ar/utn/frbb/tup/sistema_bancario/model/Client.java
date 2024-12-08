package ar.utn.frbb.tup.sistema_bancario.model;

import ar.utn.frbb.tup.sistema_bancario.model.enums.EntityType;

import java.time.LocalDate;
import java.util.Set;

public class Client extends Person {
    private long id_client;
    private EntityType entityType;
    private Set<Account> accounts;
    private LocalDate registrationDate;
    private boolean status;

    //constructor

    public Client(String name, String lastname, String dni, String email, String phone, long id_client, EntityType entityType, Set<Account> accounts, LocalDate registrationDate, boolean status) {
        super(name, lastname, dni, email, phone);
        this.id_client = id_client;
        this.entityType = entityType;
        this.accounts = accounts;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    //getters and setters
    public long getId_client() {
        return id_client;
    }
    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public EntityType getEntityType() {
        return entityType;
    }
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "\n Id: " + id_client +
                "\n Tipo de persona: " + entityType +
                "\n Cuentas: " + accounts +
                "\n Fecha de alta: " + registrationDate +
                "\n Estado del cliente: " + status;
    }
}